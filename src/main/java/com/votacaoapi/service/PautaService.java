package com.votacaoapi.service;

import com.votacaoapi.dto.PautaDTO;
import com.votacaoapi.entity.Pauta;
import com.votacaoapi.mapper.PautaMapper;
import com.votacaoapi.repository.PautaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    public PautaService(PautaRepository pautaRepository, PautaMapper pautaMapper) {
        this.pautaRepository = pautaRepository;
        this.pautaMapper = pautaMapper;
    }

    public PautaDTO save(PautaDTO pautaDTO) {
        Pauta pauta = pautaMapper.convertToPauta(pautaDTO);
        return pautaMapper.convertToPautaDTO(pautaRepository.save(pauta));
    }

}
