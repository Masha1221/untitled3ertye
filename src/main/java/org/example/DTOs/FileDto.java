package org.example.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private int id;
    private String fileName;
    private byte[] bytes;
    private long size;
    private String contentType;
    private UserDto userDto;
}
