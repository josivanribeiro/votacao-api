package com.votacaoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {
    private String id;
    private int tempoAbertura = 1;
    private String status;
}
