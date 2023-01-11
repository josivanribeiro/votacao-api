package com.votacaoapi.service;

import com.votacaoapi.dto.*;
import com.votacaoapi.entity.Pauta;
import com.votacaoapi.mapper.PautaMapper;
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

    public PautaService(PautaRepository pautaRepository, PautaMapper pautaMapper) {
        this.pautaRepository = pautaRepository;
        this.pautaMapper = pautaMapper;
    }

    public RespostaVotacaoDTO save(VotacaoDTO votacaoDTO) {
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setDescricao(votacaoDTO.getCampo1());
        AssociadoDTO associadoDTO = new AssociadoDTO();
        associadoDTO.setCpf(votacaoDTO.getCampo2().toString());
        votoDTO.setAssociadoDTO(associadoDTO);

        PautaDTO pautaDTO = new PautaDTO();
        List<VotoDTO> votos = new ArrayList<>();
        votos.add(votoDTO);
        pautaDTO.setVotos(votos);

        Pauta pauta = pautaMapper.convertToPauta(pautaDTO);
        PautaDTO pautaDTOResponse = pautaMapper.convertToPautaDTO(pautaRepository.save(pauta));
        logger.info("Pauta salva com sucesso.");

        RespostaVotacaoDTO respostaVotacaoDTO = new RespostaVotacaoDTO();
        BotaoOKDTO botaoOKDTO = new BotaoOKDTO();
        BodyDTO bodyDTO = new BodyDTO();
        if (pautaDTOResponse.getVotos() != null
                && pautaDTOResponse.getVotos().size() > 0) {
            bodyDTO.setCampo1(pautaDTOResponse.getVotos().get(0).getDescricao());
            bodyDTO.setCampo2(new Long(pautaDTOResponse.getVotos().get(0).getAssociadoDTO().getCpf()));
        }
        botaoOKDTO.setBody(bodyDTO);
        respostaVotacaoDTO.setBotaoOk(botaoOKDTO);

        return respostaVotacaoDTO;
    }

}
