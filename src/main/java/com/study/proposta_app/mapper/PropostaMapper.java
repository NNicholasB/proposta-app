package com.study.proposta_app.mapper;


import com.study.proposta_app.DTO.PropostaRequestDTO;
import com.study.proposta_app.entity.Proposta;
import org.mapstruct.Mapper;

@Mapper
public interface PropostaMapper {
    Proposta toProposta(PropostaRequestDTO propostaRequestDTO);
}
