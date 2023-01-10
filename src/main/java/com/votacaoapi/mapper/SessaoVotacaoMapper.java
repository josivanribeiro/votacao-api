package com.votacaoapi.mapper;

import com.votacaoapi.dto.SessaoVotacaoDTO;
import com.votacaoapi.entity.SessaoVotacao;
import org.springframework.stereotype.Component;

@Component
public class SessaoVotacaoMapper {

    public SessaoVotacaoDTO convertToSessaoVotacaoDTO(SessaoVotacao sessaoVotacao) {
        SessaoVotacaoDTO sessaoVotacaoDTO = new SessaoVotacaoDTO();
        sessaoVotacaoDTO.setId(sessaoVotacao.getId());
        sessaoVotacaoDTO.setStatus(sessaoVotacao.getStatus());
        sessaoVotacaoDTO.setTempoAbertura(sessaoVotacao.getTempoAbertura());
        return sessaoVotacaoDTO;
    }

    public SessaoVotacao convertToSessaoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO) {
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setId(sessaoVotacaoDTO.getId());
        sessaoVotacao.setStatus(sessaoVotacaoDTO.getStatus());
        sessaoVotacao.setTempoAbertura(sessaoVotacaoDTO.getTempoAbertura());
        return sessaoVotacao;
    }

}
