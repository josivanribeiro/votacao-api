package com.votacaoapi.controller;

import com.votacaoapi.dto.RespostaVotacaoDTO;
import com.votacaoapi.dto.VotacaoDTO;
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
    public ResponseEntity<RespostaVotacaoDTO> salvaVoto(@RequestBody VotacaoDTO votacaoDTO) {
        try {
            RespostaVotacaoDTO respostaVotacaoDTO = pautaService.save(votacaoDTO);
            return new ResponseEntity<>(respostaVotacaoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/getmembershipsbyrole")
//    public ResponseEntity<List<MembershipDTO>> getMembershipsByRole(@RequestParam(required = true) String roleId) {
//        try {
//            List<MembershipDTO> membershipDTOSResponse = roleService.getMembershipsByRole(roleId);
//            return new ResponseEntity<>(membershipDTOSResponse, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

}