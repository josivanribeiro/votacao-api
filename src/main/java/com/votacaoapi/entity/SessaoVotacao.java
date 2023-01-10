package com.votacaoapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessaoVotacao {
    private String id;
    private int tempoAbertura = 1;
    private String status;
}
