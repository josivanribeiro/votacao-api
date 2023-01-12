package com.votacaoapi.mapper;

import com.votacaoapi.dto.VotoDTO;
import com.votacaoapi.entity.Voto;
import org.springframework.stereotype.Component;

@Component
public class VotoMapper {

    private final AssociadoMapper associadoMapper;

    public VotoMapper(AssociadoMapper associadoMapper) {
        this.associadoMapper = associadoMapper;
    }

    public VotoDTO convertToVotoDTO(Voto voto) {
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setId(voto.getId());
        votoDTO.setDescricao(voto.getDescricao());
        votoDTO.setAssociado(associadoMapper.convertToAssociadoDTO(voto.getAssociado()));
        return votoDTO;
    }

    public Voto convertToVoto(VotoDTO votoDTO) {
        Voto voto = new Voto();
        voto.setId(votoDTO.getId());
        voto.setDescricao(votoDTO.getDescricao());
        voto.setAssociado(associadoMapper.convertToAssociado(votoDTO.getAssociado()));
        return voto;
    }
}
