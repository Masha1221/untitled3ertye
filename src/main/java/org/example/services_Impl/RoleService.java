package org.example.services_Impl;

import org.example.DTOs.RoleDto;
import org.example.entities.RoleEntity;
import org.example.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    RoleEntity mapRoleDtoToRoleEntity(RoleDto roleDto) {
        RoleEntity role = new RoleEntity();
        role.setName(roleDto.getName());
        return role;
    }

    public void createRole(RoleDto roleDto) {
        RoleEntity roleEntity = mapRoleDtoToRoleEntity(roleDto);
        roleRepository.save(roleEntity);
    }

    public List<RoleDto> getRoles() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities
                .stream()
                .map(roleEntity -> new RoleDto(roleEntity.getId(), roleEntity.getName()))
                .collect(Collectors.toList());
    }
}

