package com.votacaoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {
    private String id;
    private SessaoVotacaoDTO sessaoVotacaoDTO;
    private List<VotoDTO> votos;

}
