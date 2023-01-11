package com.votacaoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoDTO {
    private String campo1;
    private Long campo2;
    private String idCampoTexto;
    private String idCampoNumerico;
    private String idCampoData;
}