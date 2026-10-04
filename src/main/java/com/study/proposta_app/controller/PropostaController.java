package com.study.proposta_app.controller;

import com.study.proposta_app.DTO.PropostaRequestDTO;
import com.study.proposta_app.DTO.PropostaResponseDTO;
import com.study.proposta_app.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaService service;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO){
        PropostaResponseDTO response = service.cria(requestDTO);
        return ResponseEntity.ok(response);
    }
}
