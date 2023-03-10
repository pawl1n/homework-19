package com.helloworld.hello_world.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "photo", schema = "nix")
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(generator = "photo_generator")
    @SequenceGenerator(
            name = "photo_generator",
            sequenceName = "photo_seq",
            schema = "nix",
            allocationSize = 1
    )
    private Long id;

    @Column
    private String url;

    @Column
    private Long studentId;

    @Column
    private String description;

}
