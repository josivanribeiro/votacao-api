package com.votacaoapi.mapper;

import com.votacaoapi.dto.PautaDTO;
import com.votacaoapi.dto.VotoDTO;
import com.votacaoapi.entity.Pauta;
import com.votacaoapi.entity.Voto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PautaMapper {

    private final SessaoVotacaoMapper sessaoVotacaoMapper;
    private final VotoMapper votoMapper;

    public PautaMapper(SessaoVotacaoMapper sessaoVotacaoMapper, VotoMapper votoMapper) {
        this.sessaoVotacaoMapper = sessaoVotacaoMapper;
        this.votoMapper = votoMapper;
    }

    public PautaDTO convertToPautaDTO(Pauta pauta) {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(pauta.getId());
        pautaDTO.setSessaoVotacaoDTO(sessaoVotacaoMapper.convertToSessaoVotacaoDTO(pauta.getSessaoVotacao()));
        List<VotoDTO> votoDTOs = pauta.getVotos().stream()
                .map(votoMapper::convertToVotoDTO)
                .collect(Collectors.toList());
        pautaDTO.setVotos(votoDTOs);
        return pautaDTO;
    }

    public Pauta convertToPauta(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setId(pautaDTO.getId());
        pauta.setSessaoVotacao(sessaoVotacaoMapper.convertToSessaoVotacao(pautaDTO.getSessaoVotacaoDTO()));
        List<Voto> votos = pautaDTO.getVotos().stream()
                .map(votoMapper::convertToVoto)
                .collect(Collectors.toList());
        pauta.setVotos(votos);
        return pauta;
    }
}
