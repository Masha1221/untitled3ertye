package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.DTOs.UserDto;
import org.example.services_Impl.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userServ;

    @PostMapping("/users")
    public void createUser(@RequestBody UserDto userDto) {
        log.info("User with name {} - created. User has role - {}", userDto, userDto.getRoles());
        userServ.createUser(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userServ.getUsers();
    }
}
