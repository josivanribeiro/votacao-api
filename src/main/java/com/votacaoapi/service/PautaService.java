package com.votacaoapi.service;

import com.votacaoapi.dto.*;
import com.votacaoapi.entity.Pauta;
import com.votacaoapi.exception.EntityNotFoundException;
import com.votacaoapi.mapper.PautaMapper;
import com.votacaoapi.mapper.VotoMapper;
import com.votacaoapi.repository.PautaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PautaService {

    Logger logger = LoggerFactory.getLogger(PautaService.class);

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;
    private final VotoMapper votoMapper;

    public PautaService(PautaRepository pautaRepository, PautaMapper pautaMapper, VotoMapper votoMapper) {
        this.pautaRepository = pautaRepository;
        this.pautaMapper = pautaMapper;
        this.votoMapper = votoMapper;
    }

    public PautaDTO save(VotacaoDTO votacaoDTO) {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setNome(votacaoDTO.getCampo1());
        pautaDTO.setId(votacaoDTO.getCampo2().toString());
        Pauta pauta = pautaMapper.convertToPauta(pautaDTO);
        PautaDTO pautaDTOResponse = pautaMapper.convertToPautaDTO(pautaRepository.save(pauta));
        logger.info("Pauta salva com sucesso.");
        return pautaDTOResponse;
    }

    public PautaDTO saveSelection(SelecaoDTO selecaoDTO) throws EntityNotFoundException {
        PautaDTO pautaDTO = pautaRepository.findById(selecaoDTO.getCampo1()).map(pautaMapper::convertToPautaDTO)
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada."));
        if (pautaDTO != null) {
            VotoDTO votoDTO = new VotoDTO();
            AssociadoDTO associadoDTO = new AssociadoDTO();
            associadoDTO.setCpf(selecaoDTO.getCampo2().toString());
            votoDTO.setAssociado(associadoDTO);
            votoDTO.setDescricao(selecaoDTO.getCampo3());
            if (pautaDTO.getVotos() == null) {
                List<VotoDTO> votoDTOs = new ArrayList<>();
                votoDTOs.add(votoDTO);
                pautaDTO.setVotos(votoDTOs);
            } else {
                pautaDTO.getVotos().add(votoDTO);
            }

            PautaDTO pautaDTOResponse = pautaMapper
                    .convertToPautaDTO(pautaRepository.save(pautaMapper.convertToPauta(pautaDTO)));
            logger.info("Pauta salva com sucesso.");
            return pautaDTOResponse;
        }
        return null;
    }

}
