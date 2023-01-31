package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.DTOs.RoleDto;
import org.example.services_Impl.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/roles")
    public String createRole(@RequestBody RoleDto role) {
        roleService.createRole(role);
        return "Role is created. Role  - " + role.getName() + ".";
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        return roleService.getRoles();
    }
}