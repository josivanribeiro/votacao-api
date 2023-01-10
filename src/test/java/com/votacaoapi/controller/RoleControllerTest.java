//package com.votacaoapi.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.votacaoapi.Application;
//import com.votacaoapi.dto.MembershipDTO;
//import com.votacaoapi.dto.RoleDTO;
//import com.votacaoapi.service.RoleService;
//import org.hamcrest.core.Is;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@AutoConfigureMockMvc
//public class RoleControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RoleService roleService;
//
//    private static final ObjectMapper om = new ObjectMapper();
//
//    private static final String ROLE_ID = "1";
//    private static final String TEAM_MEMBER_ID = "2";
//    private static final String USER_ID = "3";
//    private static final String TEAM_ID = "4";
//
//    @Test
//    public void createRoleOK() throws Exception {
//        Mockito.when(roleService.save(any(RoleDTO.class))).thenReturn(getNewRoleDTOMock());
//
//        mockMvc.perform(post("/roles/")
//                        .content(om.writeValueAsString(getNewRoleDTOMock()))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", Is.is("Josivan Silva")))
//                .andExpect(jsonPath("$.memberships[0].userId", Is.is("10")))
//                .andExpect(jsonPath("$.memberships[0].teamId", Is.is("50")))
//                .andExpect(jsonPath("$.teamMemberIds[0]", Is.is("1")));
//
//        verify(roleService, times(1)).save(any(RoleDTO.class));
//    }
//
//    @Test
//    public void assignRoleTeamMemberOK() throws Exception {
//        Mockito.when(roleService.assignRoleTeamMember(ROLE_ID, TEAM_MEMBER_ID)).thenReturn(getNewRoleDTOMock());
//
//        mockMvc.perform(put("/roles/assignroleteammember/1/2")
//                        .content(om.writeValueAsString(getNewRoleDTOMock()))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", Is.is("Josivan Silva")))
//                .andExpect(jsonPath("$.memberships[0].userId", Is.is("10")))
//                .andExpect(jsonPath("$.memberships[0].teamId", Is.is("50")))
//                    .andExpect(jsonPath("$.teamMemberIds[0]", Is.is("1")));
//
//    }
//
//    @Test
//    public void getRoleByMembershipOK() throws Exception {
//        Mockito.when(roleService.getRoleByMembership(USER_ID, TEAM_ID)).thenReturn(getNewRoleDTOMock());
//
//        mockMvc.perform(get("/roles/getrolebymembership?userId=3&teamId=4")
//                        .content(om.writeValueAsString(getNewRoleDTOMock()))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", Is.is("Josivan Silva")))
//                .andExpect(jsonPath("$.memberships[0].userId", Is.is("10")))
//                .andExpect(jsonPath("$.memberships[0].teamId", Is.is("50")))
//                .andExpect(jsonPath("$.teamMemberIds[0]", Is.is("1")));
//
//        verify(roleService, times(1)).getRoleByMembership(USER_ID, TEAM_ID);
//
//    }
//
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
//
//    private RoleDTO getNewRoleDTOMock() {
//        RoleDTO newRoleDTO = new RoleDTO();
//        newRoleDTO.setName("Josivan Silva");
//        MembershipDTO membershipDTO = new MembershipDTO();
//        membershipDTO.setUserId("10");
//        membershipDTO.setTeamId("50");
//        List<MembershipDTO> membershipDTOs = new ArrayList<>();
//        membershipDTOs.add(membershipDTO);
//        newRoleDTO.setMemberships(membershipDTOs);
//        List<String> teamMemberIds = new ArrayList<>();
//        teamMemberIds.add("1");
//        newRoleDTO.setTeamMemberIds(teamMemberIds);
//        return newRoleDTO;
//    }
//
//}
