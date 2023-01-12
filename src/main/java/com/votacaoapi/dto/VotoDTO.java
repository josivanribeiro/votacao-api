package com.votacaoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {
    private String id;
    private AssociadoDTO associado;
    private String descricao;
}
