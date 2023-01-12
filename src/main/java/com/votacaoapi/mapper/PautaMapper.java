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

    private final VotoMapper votoMapper;

    public PautaMapper(VotoMapper votoMapper) {
        this.votoMapper = votoMapper;
    }

    public PautaDTO convertToPautaDTO(Pauta pauta) {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setId(pauta.getId());
        pautaDTO.setNome(pauta.getNome());
        if (pauta.getVotos() != null && pauta.getVotos().size() > 0) {
            List<VotoDTO> votoDTOs = pauta.getVotos().stream()
                    .map(votoMapper::convertToVotoDTO)
                    .collect(Collectors.toList());
            pautaDTO.setVotos(votoDTOs);
        }
        return pautaDTO;
    }

    public Pauta convertToPauta(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setId(pautaDTO.getId());
        pauta.setNome(pautaDTO.getNome());
        if (pautaDTO.getVotos() != null && pautaDTO.getVotos().size() > 0) {
            List<Voto> votos = pautaDTO.getVotos().stream()
                    .map(votoMapper::convertToVoto)
                    .collect(Collectors.toList());
            pauta.setVotos(votos);
        }
        return pauta;
    }
}
