package com.study.proposta_app.listener;


import com.study.proposta_app.DTO.PropostaResponseDTO;
import com.study.proposta_app.entity.Proposta;
import com.study.proposta_app.mapper.PropostaMapper;
import com.study.proposta_app.repository.PropostaRepository;
import com.study.proposta_app.service.WebSocketService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private PropostaMapper mapper;


    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        atualizarProposta(proposta);
        PropostaResponseDTO responseDTO= mapper.convertEntityToDto(proposta);
        webSocketService.notificar(responseDTO);

    }

    private void atualizarProposta(Proposta proposta){
        propostaRepository.atualizarProposta(proposta.getId(),proposta.getAprovada(),proposta.getObservacao());
    }

}