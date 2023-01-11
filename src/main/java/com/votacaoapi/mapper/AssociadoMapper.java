package com.votacaoapi.mapper;

import com.votacaoapi.dto.AssociadoDTO;
import com.votacaoapi.entity.Associado;
import org.springframework.stereotype.Component;

@Component
public class AssociadoMapper {

    public AssociadoDTO convertToAssociadoDTO(Associado associado) {
        AssociadoDTO associadoDTO = new AssociadoDTO();
        associadoDTO.setId(associado.getId());
        associadoDTO.setNome(associado.getNome());
        associadoDTO.setCpf(associado.getCpf());
        return associadoDTO;
    }

    public Associado convertToAssociado(AssociadoDTO associadoDTO) {
        Associado associado = new Associado();
        associado.setId(associadoDTO.getId());
        associado.setNome(associadoDTO.getNome());
        associado.setCpf(associadoDTO.getCpf());
        return associado;
    }

}
