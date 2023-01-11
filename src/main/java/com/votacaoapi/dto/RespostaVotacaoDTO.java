package com.votacaoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespostaVotacaoDTO {
    private String tipo;
    private String titulo;
    private ItensDTO itens;
    private BotaoOKDTO botaoOk;
    private BotaoCancelarDTO botaoCancelar;
}
