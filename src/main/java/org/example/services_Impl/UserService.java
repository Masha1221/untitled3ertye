package org.example.services_Impl;

import org.example.DTOs.RoleDto;
import org.example.DTOs.UserDto;
import org.example.entities.RoleEntity;
import org.example.entities.UserEntity;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void createUser(UserDto userDto) {

        List<UserEntity> entities = userRepository.findAll();
        for (UserEntity user : entities) {
            if (user.getName().equals(userDto.getName())) {
                throw new RuntimeException("A user with the same name already exists!!!");
            }
        }
        UserEntity user = new UserEntity();

        List<Integer> IDs = userDto.getRoles()
                .stream()
                .map(RoleDto::getId)
                .collect(Collectors.toList());

        List<RoleEntity> roleEntity = roleRepository.findAllById(IDs);
        user.setRoles(roleEntity);
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    public List<UserDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities
                .stream()
                .map(user -> (new UserDto(user.getName(), user.getLogin(), user.getPassword(), user.getRoles()
                        .stream()
                        .map(role -> new RoleDto(role.getId(), role.getName())).collect(Collectors.toList()))))
                .collect(Collectors.toList());
    }
}


