package com.votacaoapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class SessaoVotacao {
    @Id
    private String id;
    private int tempoAbertura = 1;
    private String status;
}
