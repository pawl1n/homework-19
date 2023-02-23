package com.helloworld.hello_world.service;

import com.helloworld.hello_world.exception.StudentNotFoundException;
import com.helloworld.hello_world.repository.StudentRepository;
import com.helloworld.hello_world.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student studentDetails) {
        Student student = studentRepository.findById(studentDetails.getId())
                .orElseThrow(StudentNotFoundException::new);

        if (studentDetails.getName() != null) {
            student.setName(studentDetails.getName());
        }
        if (studentDetails.getEmail() != null) {
            student.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getPhotos() != null && !studentDetails.getPhotos().isEmpty()) {
            student.setPhotos(studentDetails.getPhotos());
        }

        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        Student student = studentRepository.findById(id)
                        .orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
    }
}
