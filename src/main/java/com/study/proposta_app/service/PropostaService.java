package com.study.proposta_app.service;

import com.study.proposta_app.DTO.PropostaRequestDTO;
import com.study.proposta_app.DTO.PropostaResponseDTO;
import com.study.proposta_app.entity.Proposta;
import com.study.proposta_app.mapper.PropostaMapper;
import com.study.proposta_app.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PropostaService {


    private final PropostaRepository propostaRepository;


    private final PropostaMapper propostaMapper;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    private final NotificacaoRabbitService notificacaoRabbitService;


    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO){
        Proposta proposta = propostaMapper.toProposta(requestDTO);
        propostaRepository.save(proposta);
        int prioridade= proposta.getUsuario().getRenda()>10000? 10 : 5;
        MessagePostProcessor messagePostProcessor=message -> {
            message.getMessageProperties().setPriority(prioridade);
            return message;
        };
        notificarRabbitMQ(proposta,messagePostProcessor);
         return propostaMapper.convertEntityToDto(proposta);
    }
    private void notificarRabbitMQ(Proposta proposta, MessagePostProcessor messagePostProcessor){
        try{
            notificacaoRabbitService.notificar(proposta,exchange,messagePostProcessor);
        }catch(RuntimeException e){
            proposta.setIntegrada(false);
                    }



    }
    public List<PropostaResponseDTO> obterProposta() {
      List<PropostaResponseDTO> lista= propostaMapper.converteListEntityToListDTO(propostaRepository.findAll());
      return lista;
    }
}
