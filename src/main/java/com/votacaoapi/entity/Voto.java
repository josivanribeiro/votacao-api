package com.votacaoapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Voto {
    private String id;
    private Associado associado;
    private String descricao;
}
