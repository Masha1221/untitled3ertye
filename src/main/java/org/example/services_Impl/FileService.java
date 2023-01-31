//1) finish the task to the end
// 2) pass the test from NIX
// 3)
// 4) learn 15 word from notebook
// 5)learn 10 question from  interview
// 6) squat 300 times
//7) understand how mvs works

//user with role baby should upload file. return id from controller


//user can get file by id getFileByID
//for test must be 2 users , the first with role baby , the second with role sales
//we need controller where will be created user, role can be in body
//user may have few roles
//role is created in separate controller
//must be get allRoles , after that we can get id of Role and set it when we will create user


//fn+command+f6
//control + option + <- & ->
//ctrl + N

/*
    1.
    2.Save FileEntity in H2 data base
    and return id from controller;
    3.Get FilEntity from data Base by id from step 2.
    4. Convert FileEntity to Multipart file;
    5. Download Multipart file from Postman.
 */


package org.example.services_Impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entities.FileEntity;
import org.example.entities.RoleEntity;
import org.example.entities.UserEntity;
import org.example.repositories.FileRepository;
import org.example.repositories.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class FileService {

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    public FileService(FileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    public void saveFile(MultipartFile multipartFile) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> user = userRepository.findUserEntitiesByName(auth.getName());
        List<RoleEntity> roleEntities = user.orElseThrow().getRoles();
                 if (roleEntities.stream().anyMatch(role -> role.getName().equals("BABY"))) {
                log.info("User successfully authenticated!!!");
                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileName(multipartFile.getOriginalFilename());
                fileEntity.setBytes(multipartFile.getBytes());
                fileEntity.setSize(multipartFile.getSize());
                fileEntity.setContentType(multipartFile.getContentType());
                fileRepository.save(fileEntity);
            }
        }

    public ResponseEntity<byte[]> getFileById(Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> user = userRepository.findUserEntitiesByName(auth.getName());
        List<RoleEntity> roleEntities = user.orElseThrow().getRoles();
        if (roleEntities.stream().anyMatch(role -> role.getName().equals("BABY"))) {
            log.info("User successfully authenticated!!!");
            FileEntity fileEntity = fileRepository.findById(id).orElseThrow();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileEntity.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                    .body(fileEntity.getBytes());
        }
        throw new RuntimeException("Current user does not authorized.");
    }
}


