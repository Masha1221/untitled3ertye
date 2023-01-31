package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.services_Impl.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService filServ;

    @PostMapping("files/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        filServ.saveFile(multipartFile);
        log.info("File name " + multipartFile.getOriginalFilename());
    }

    @GetMapping("files/{id}/download")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        return filServ.getFileById(id);
    }
}


