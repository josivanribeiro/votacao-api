package com.votacaoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votacaoapi.Application;
import com.votacaoapi.dto.BodyDTO;
import com.votacaoapi.dto.BotaoOKDTO;
import com.votacaoapi.dto.RespostaVotacaoDTO;
import com.votacaoapi.dto.VotacaoDTO;
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
        Mockito.when(pautaService.save(any(VotacaoDTO.class))).thenReturn(getRespostaVotacaoDTOMock());

        mockMvc.perform(post("/votacoes/")
                        .content(om.writeValueAsString(getNewVotacaoDTOMock()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.botaoOk.body.campo1", Is.is("Sim")))
                .andExpect(jsonPath("$.botaoOk.body.campo2", Is.is(69151332086L)));

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

//    @Test
//    public void getMembershipsByRoleOK() throws Exception {
//        List<MembershipDTO> membershipDTOs = new ArrayList<>();
//        MembershipDTO membershipDTO = new MembershipDTO();
//        membershipDTO.setUserId("10");
//        membershipDTO.setTeamId("50");
//        membershipDTOs.add(membershipDTO);
//
//        Mockito.when(roleService.getMembershipsByRole(ROLE_ID)).thenReturn(membershipDTOs);
//
//        mockMvc.perform(get("/roles/getmembershipsbyrole?roleId=1")
//                        .content(om.writeValueAsString(membershipDTOs))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].userId", Is.is("10")))
//                .andExpect(jsonPath("$.[0].teamId", Is.is("50")));
//
//        verify(roleService, times(1)).getMembershipsByRole(ROLE_ID);
//    }

    private VotacaoDTO getNewVotacaoDTOMock() {
        VotacaoDTO votacaoDTO = new VotacaoDTO();
        votacaoDTO.setCampo1("Sim");
        votacaoDTO.setCampo2(69151332086L);
        votacaoDTO.setIdCampoTexto("Texto");
        votacaoDTO.setIdCampoNumerico("999");
        votacaoDTO.setIdCampoData("01/01/2000");
        return votacaoDTO;
    }

    private RespostaVotacaoDTO getRespostaVotacaoDTOMock() {
        RespostaVotacaoDTO newRespostaVotacaoDTO = new RespostaVotacaoDTO();
        BotaoOKDTO botaoOKDTO = new BotaoOKDTO();
        BodyDTO bodyDTO = new BodyDTO();
        bodyDTO.setCampo1("Sim");
        bodyDTO.setCampo2(69151332086L);
        botaoOKDTO.setBody(bodyDTO);
        newRespostaVotacaoDTO.setBotaoOk(botaoOKDTO);
        return newRespostaVotacaoDTO;
    }
}
