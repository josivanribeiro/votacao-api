package com.votacaoapi.controller;

import com.votacaoapi.dto.PautaDTO;
import com.votacaoapi.dto.SelecaoDTO;
import com.votacaoapi.dto.VotacaoDTO;
import com.votacaoapi.exception.EntityNotFoundException;
import com.votacaoapi.service.PautaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votacoes")
public class VotacaoController {

    private final PautaService pautaService;

    public VotacaoController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("/")
    public ResponseEntity<PautaDTO> salvaVoto(@RequestBody VotacaoDTO votacaoDTO) {
        try {
            PautaDTO pautaDTO = pautaService.save(votacaoDTO);
            return new ResponseEntity<>(pautaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/selecao")
    public ResponseEntity<PautaDTO> selecaoVoto(@RequestBody SelecaoDTO selecaoDTO) {
        try {
            PautaDTO pautaDTO = pautaService.saveSelection(selecaoDTO);
            return new ResponseEntity<>(pautaDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}