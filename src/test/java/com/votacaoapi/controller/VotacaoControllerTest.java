package com.votacaoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacaoapi.Application;
import com.votacaoapi.dto.*;
import com.votacaoapi.exception.EntityNotFoundException;
import com.votacaoapi.service.PautaService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class VotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaService pautaService;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    public void shouldSavePautaSuccessfully() throws Exception {
        Mockito.when(pautaService.save(any(VotacaoDTO.class))).thenReturn(getPautaDTOMock());

        mockMvc.perform(post("/votacoes/")
                        .content(om.writeValueAsString(getNewVotacaoDTOMock()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Is.is("1")))
                .andExpect(jsonPath("$.nome", Is.is("Opção 1")));

        verify(pautaService, times(1)).save(any(VotacaoDTO.class));
    }

    @Test
    public void shouldReturnInternalServerErrorWhenSavePauta() throws Exception {
        Mockito.when(pautaService.save(any(VotacaoDTO.class))).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(post("/votacoes/")
                        .content(om.writeValueAsString(getNewVotacaoDTOMock()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());

        verify(pautaService, times(1)).save(any(VotacaoDTO.class));
    }

    @Test
    public void shouldSaveSelectionSuccessfully() throws Exception {
        Mockito.when(pautaService.saveSelection(any(SelecaoDTO.class))).thenReturn(getFullPautaDTOMock());

        mockMvc.perform(post("/votacoes/selecao")
                        .content(om.writeValueAsString(getNewSelecaoDTOMock()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is("1")))
                .andExpect(jsonPath("$.nome", Is.is("Opção 1")))
                .andExpect(jsonPath("$.votos[0].associado.cpf", Is.is("34871040089")))
                .andExpect(jsonPath("$.votos[0].descricao", Is.is("Sim")));

        verify(pautaService, times(1)).saveSelection(any(SelecaoDTO.class));
    }

    @Test
    public void shouldReturnNotFoundErrorWhenSaveSelection() throws Exception {
        Mockito.when(pautaService.saveSelection(any(SelecaoDTO.class))).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(post("/votacoes/selecao")
                        .content(om.writeValueAsString(getNewSelecaoDTOMock()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(pautaService, times(1)).saveSelection(any(SelecaoDTO.class));
    }

    private VotacaoDTO getNewVotacaoDTOMock() {
        VotacaoDTO votacaoDTO = new VotacaoDTO();
        votacaoDTO.setCampo1("Sim");
        votacaoDTO.setCampo2(69151332086L);
        votacaoDTO.setIdCampoTexto("Texto");
        votacaoDTO.setIdCampoNumerico("999");
        votacaoDTO.setIdCampoData("01/01/2000");
        return votacaoDTO;
    }

    private SelecaoDTO getNewSelecaoDTOMock() {
        SelecaoDTO selecaoDTO = new SelecaoDTO();
        selecaoDTO.setCampo1("1");
        selecaoDTO.setCampo2(69151332086L);
        selecaoDTO.setCampo3("Sim");
        return selecaoDTO;
    }

    private PautaDTO getPautaDTOMock() {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setNome("Opção 1");
        pautaDTO.setId("1");
        return pautaDTO;
    }

    private PautaDTO getFullPautaDTOMock() {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setNome("Opção 1");
        pautaDTO.setId("1");
        VotoDTO votoDTO = new VotoDTO();
        AssociadoDTO associadoDTO = new AssociadoDTO();
        associadoDTO.setCpf("34871040089");
        votoDTO.setAssociado(associadoDTO);
        votoDTO.setDescricao("Sim");
        List<VotoDTO> votoDTOs = new ArrayList<>();
        votoDTOs.add(votoDTO);
        pautaDTO.setVotos(votoDTOs);
        return pautaDTO;
    }
}
