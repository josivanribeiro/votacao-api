//package com.votacaoapi.service;
//
//import com.votacaoapi.dto.MembershipDTO;
//import com.votacaoapi.dto.RoleDTO;
//import com.votacaoapi.entity.Membership;
//import com.votacaoapi.entity.Role;
//import com.votacaoapi.exception.EntityNotFoundException;
//import com.votacaoapi.mapper.RoleMapper;
//import com.votacaoapi.repository.RoleRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class RoleServiceTest {
//
//    @Mock
//    private RoleRepository roleRepository;
//
//    @Mock
//    private RoleMapper roleMapper;
//
//    @InjectMocks
//    private RoleService roleService;
//
//    private static final String ROLE_ID = "1";
//    private static final String TEAM_MEMBER_ID = "2";
//    private static final String USER_ID = "100";
//    private static final String TEAM_ID = "150";
//
//    @Test
//    void shouldSaveRoleSuccessfully() {
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId("1");
//        roleDTO.setName("Nome");
//        roleDTO.setMemberships(new ArrayList<>());
//
//        final Role role = new Role();
//        role.setId("1");
//        role.setName("Nome");
//        role.setMemberships(new ArrayList<>());
//
//        given(roleMapper.convertToRole(roleDTO)).willAnswer(invocation -> role);
//        given(roleMapper.convertToRoleDTO(role)).willAnswer(invocation -> roleDTO);
//        given(roleRepository.save(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
//
//        RoleDTO savedRoleDTO = roleService.save(roleDTO);
//
//        assertNotNull(savedRoleDTO);
//
//        verify(roleRepository).save(ArgumentMatchers.any());
//    }
//
//    @Test
//    void shouldThrowErrorWhenSaveRoleWithoutName() {
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId("1");
//        roleDTO.setName("");
//        roleDTO.setMemberships(new ArrayList<>());
//
//        assertThrows(IllegalArgumentException.class, () -> {
//           roleService.save(roleDTO);
//        });
//
//        verify(roleRepository, never()).save(any(Role.class));
//    }
//
//    @Test
//    void shouldAssignRoleTeamMemberSuccessfully() throws EntityNotFoundException {
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId("1");
//        roleDTO.setName("Nome");
//        roleDTO.setMemberships(new ArrayList<>());
//
//        final Role role = new Role();
//        role.setId("1");
//        role.setName("Nome");
//        role.setMemberships(new ArrayList<>());
//
//        given(roleRepository.findById(ArgumentMatchers.any())).willAnswer(invocation -> Optional.of(role));
//        given(roleMapper.convertToRoleDTO(role)).willAnswer(invocation -> roleDTO);
//        given(roleRepository.save(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
//
//        RoleDTO savedRoleDTO = roleService.assignRoleTeamMember(ROLE_ID, TEAM_MEMBER_ID);
//
//        assertNotNull(savedRoleDTO);
//
//        verify(roleRepository).findById(ArgumentMatchers.any());
//        verify(roleRepository).save(ArgumentMatchers.any());
//    }
//
//    @Test
//    void shouldThrowErrorWhenAssignRoleTeamMemberWithNonExistentRole() {
//        given(roleRepository.findById(ArgumentMatchers.any())).willAnswer(invocation -> Optional.empty());
//
//        assertThrows(EntityNotFoundException.class, () -> {
//            roleService.assignRoleTeamMember(ROLE_ID, TEAM_MEMBER_ID);
//        });
//
//        verify(roleRepository, never()).save(any(Role.class));
//    }
//
//    @Test
//    void shouldGetRoleByMembershipSuccessfully() throws EntityNotFoundException {
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId("1");
//        roleDTO.setName("Nome");
//        roleDTO.setMemberships(new ArrayList<>());
//
//        final Role role = new Role();
//        role.setId("1");
//        role.setName("Nome");
//        role.setMemberships(new ArrayList<>());
//
//        given(roleRepository.findByMembershipsContaining(ArgumentMatchers.any())).willAnswer(invocation -> Optional.of(role));
//        given(roleMapper.convertToRoleDTO(role)).willAnswer(invocation -> roleDTO);
//
//        RoleDTO foundRoleDTO = roleService.getRoleByMembership(USER_ID, TEAM_ID);
//
//        assertNotNull(foundRoleDTO);
//
//        verify(roleRepository).findByMembershipsContaining(ArgumentMatchers.any());
//    }
//
//    @Test
//    void shouldThrowErrorWhenGetRoleByMembershipWithNonExistentRole() {
//        given(roleRepository.findByMembershipsContaining(ArgumentMatchers.any())).willAnswer(invocation -> Optional.empty());
//
//        assertThrows(EntityNotFoundException.class, () -> {
//            roleService.getRoleByMembership(USER_ID, TEAM_ID);
//        });
//
//        verify(roleMapper, never()).convertToRoleDTO(any(Role.class));
//    }
//
//    @Test
//    void shouldGetMembershipsByRoleSuccessfully() throws EntityNotFoundException {
//        final Role role = new Role();
//        role.setId("1");
//        role.setName("Nome");
//        Membership membership = new Membership();
//        membership.setUserId(USER_ID);
//        membership.setTeamId(TEAM_ID);
//        List<Membership> memberships = new ArrayList<>();
//        memberships.add(membership);
//        role.setMemberships(memberships);
//
//        final MembershipDTO membershipDTO = new MembershipDTO();
//        membershipDTO.setUserId(USER_ID);
//        membershipDTO.setTeamId(TEAM_ID);
//
//        List<MembershipDTO> membershipDTOs = new ArrayList<>();
//        membershipDTOs.add(membershipDTO);
//
//        given(roleRepository.findById(ArgumentMatchers.any())).willAnswer(invocation -> Optional.of(role));
//        given(roleMapper.convertToMembershipDTOs(any())).willAnswer(invocation -> membershipDTOs);
//
//        List<MembershipDTO> foundMembershipDTOs = roleService.getMembershipsByRole(ROLE_ID);
//
//        assertNotNull(foundMembershipDTOs);
//
//        verify(roleRepository).findById(ArgumentMatchers.any());
//    }
//
//    @Test
//    void shouldThrowErrorWhenGetMembershipsByRoleWithNonExistentRole() {
//        given(roleRepository.findById(ArgumentMatchers.any())).willAnswer(invocation -> Optional.empty());
//
//        assertThrows(EntityNotFoundException.class, () -> {
//            roleService.getMembershipsByRole(ROLE_ID);
//        });
//
//        verify(roleMapper, never()).convertToMembershipDTOs(anyList());
//    }
//
//    @Test
//    void shouldThrowErrorWhenGetMembershipsByRoleWithNonExistentMembership() {
//        final Role role = new Role();
//        role.setId("1");
//        role.setName("Nome");
//        role.setMemberships(new ArrayList<>());
//
//        given(roleRepository.findById(ArgumentMatchers.any())).willAnswer(invocation -> Optional.of(role));
//
//        assertThrows(EntityNotFoundException.class, () -> {
//            roleService.getMembershipsByRole(ROLE_ID);
//        });
//
//        verify(roleMapper, never()).convertToMembershipDTOs(anyList());
//    }
//
//}
