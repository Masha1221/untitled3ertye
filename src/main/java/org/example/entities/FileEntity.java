package org.example.entities;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Accessors(chain = true)
@Table(name = "files")
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String fileName;
    @Column
    @Lob
    private byte[] bytes;
    @Column
    private long size;
    @Column
    private String contentType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_ID", referencedColumnName = "id")
    @ToString.Exclude
    private UserEntity userEntity;

    public FileEntity() {
    }
}
