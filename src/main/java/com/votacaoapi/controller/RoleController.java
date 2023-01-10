//package com.votacaoapi.controller;
//
//import com.votacaoapi.dto.MembershipDTO;
//import com.votacaoapi.dto.RoleDTO;
//import com.votacaoapi.exception.EntityNotFoundException;
//import com.votacaoapi.service.RoleService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/roles")
//public class RoleController {
//
//    private final RoleService roleService;
//
//    public RoleController(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
//        try {
//            RoleDTO roleDTOResponse = roleService.save(roleDTO);
//            return new ResponseEntity<>(roleDTOResponse, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/assignroleteammember/{idRole}/{idTeamMember}")
//    public ResponseEntity<RoleDTO> assignRoleTeamMember(@PathVariable("idRole") String idRole,
//                                                        @PathVariable("idTeamMember") String idTeamMember) {
//        try {
//            RoleDTO roleDTOResponse = roleService.assignRoleTeamMember(idRole, idTeamMember);
//            return new ResponseEntity<>(roleDTOResponse, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/getrolebymembership")
//    public ResponseEntity<RoleDTO> getRoleByMembership(@RequestParam(required = true) String userId,
//                                                       @RequestParam(required = true) String teamId) {
//        try {
//            RoleDTO roleDTOResponse = roleService.getRoleByMembership(userId, teamId);
//            return new ResponseEntity<>(roleDTOResponse, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/getmembershipsbyrole")
//    public ResponseEntity<List<MembershipDTO>> getMembershipsByRole(@RequestParam(required = true) String roleId) {
//        try {
//            List<MembershipDTO> membershipDTOSResponse = roleService.getMembershipsByRole(roleId);
//            return new ResponseEntity<>(membershipDTOSResponse, HttpStatus.OK);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//}