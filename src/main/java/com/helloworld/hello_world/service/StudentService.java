package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.StudentRepository;
import com.helloworld.hello_world.repository.entity.Student;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Student studentDetails) {
        Student student = studentRepository.findById(studentDetails.getId())
                .orElseThrow(EntityNotFoundException::new);

        if (studentDetails.getName() != null) {
            student.setName(studentDetails.getName());
        }
        if (studentDetails.getEmail() != null) {
            student.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getPhotos() != null && !studentDetails.getPhotos().isEmpty()) {
            student.setPhotos(studentDetails.getPhotos());
        }

        studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
