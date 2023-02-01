package com.helloworld.hello_world.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student", schema = "nix")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(generator = "student_generator")
    @SequenceGenerator(
            name = "student_generator",
            sequenceName = "student_seq",
            schema = "nix",
            allocationSize = 1
    )
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Photo> photos;

}
