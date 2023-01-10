package com.votacaoapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pauta {
    private String id;
    private SessaoVotacao sessaoVotacao;
    private List<Voto> votos;
}
