package com.votacaoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BotaoOKDTO {
    private String texto;
    private String url;
    private BodyDTO body;
}
