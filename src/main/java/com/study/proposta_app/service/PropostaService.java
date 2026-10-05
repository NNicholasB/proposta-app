package com.study.proposta_app.service;

import com.study.proposta_app.DTO.PropostaRequestDTO;
import com.study.proposta_app.DTO.PropostaResponseDTO;
import com.study.proposta_app.entity.Proposta;
import com.study.proposta_app.mapper.PropostaMapper;
import com.study.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private final PropostaMapper propostaMapper;

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO){
        Proposta proposta = propostaMapper.toProposta(requestDTO);
        propostaRepository.save(proposta);
        return propostaMapper.convertEntityToDto(proposta);
    }

    public List<PropostaResponseDTO> obterProposta() {
      List<PropostaResponseDTO> lista= propostaMapper.converteListEntityToListDTO(propostaRepository.findAll());
      return lista;
    }
}
