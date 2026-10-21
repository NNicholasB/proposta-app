package com.study.proposta_app.service;


import com.study.proposta_app.DTO.PropostaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void notificar(PropostaResponseDTO propostaResponseDTO){
        template.convertAndSend("/propostas",propostaResponseDTO);
    }
}
