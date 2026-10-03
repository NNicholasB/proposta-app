package com.study.proposta_app.controller;

import com.study.proposta_app.DTO.PropostaRequestDTO;
import com.study.proposta_app.DTO.PropostaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/proposta")
public class PropostaController {

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO){
        return null;
    }
}
