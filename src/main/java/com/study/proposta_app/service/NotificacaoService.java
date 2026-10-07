package com.study.proposta_app.service;


import com.study.proposta_app.DTO.PropostaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class NotificacaoService {

    private RabbitTemplate rabbitTemplate;


    public void notificar(PropostaResponseDTO propostaResponseDTO,String exchange){
        rabbitTemplate.convertAndSend(exchange,"",propostaResponseDTO);
    }
}
