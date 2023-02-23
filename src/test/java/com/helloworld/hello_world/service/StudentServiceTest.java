package com.helloworld.hello_world.service;

import com.helloworld.hello_world.exception.StudentNotFoundException;
import com.helloworld.hello_world.repository.StudentRepository;
import com.helloworld.hello_world.repository.entity.Student;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;
    private Student student1;
    private Student student2;
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        students = new ArrayList<>();

        student1 = new Student();
        student1.setId(1L);
        student1.setName("Student1");
        student1.setEmail("student1@test");
        students.add(student1);

        student2 = new Student();
        student2.setId(2L);
        student2.setName("Student2");
        student2.setEmail("student2@test");
        students.add(student2);
    }

    @AfterEach
    public void tearDown() {
        student1 = student2 = null;
        students = null;
    }

    @Test
    void shouldFindAll() {
        // given
        when(studentRepository.findAll()).thenReturn(students);

        // when
        List<Student> result = studentService.findAll();

        // then
        assertThat(result).isNotEmpty().containsExactly(student1, student2);
    }

    @Test
    void shouldFindById() {
        // given
        when(studentRepository.findById(student1.getId())).thenReturn(Optional.ofNullable(student1));

        // when
        Student result = studentService.findById(student1.getId());

        // then
        assertThat(result).isNotNull().isEqualTo(student1);
    }

    @Test
    void shouldNotFindById() {
        // given
        when(studentRepository.findById(any())).thenReturn(Optional.empty());

        // when
        ThrowableAssert.ThrowingCallable thrown = () -> studentService.findById(1L);

        // then
        assertThatThrownBy(thrown).isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void shouldCreateStudent() {
        // given
        when(studentRepository.save(any())).thenReturn(student1);

        // when
        Student result = studentService.createStudent(student1);

        // then
        assertThat(result).isNotNull().isEqualTo(student1);
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateStudent() {
        // given
        when(studentRepository.save(any())).thenReturn(student1);
        when(studentRepository.findById(student1.getId())).thenReturn(Optional.ofNullable(student1));

        // when
        Student result = studentService.updateStudent(student1);

        // then
        assertThat(result).isNotNull().isEqualTo(student1);
        verify(studentRepository, times(1)).findById(any());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void shouldNotUpdateStudent() {
        // given
        when(studentRepository.findById(any())).thenReturn(Optional.empty());

        // when
        ThrowableAssert.ThrowingCallable thrown = () -> studentService.updateStudent(student1);

        // then
        assertThatThrownBy(thrown).isInstanceOf(StudentNotFoundException.class);
        verify(studentRepository, times(1)).findById(any());
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void shouldDeleteStudent() {
        // given
        when(studentRepository.findById(student1.getId())).thenReturn(Optional.ofNullable(student1));
        // when
        studentService.delete(student1.getId());

        // then
        verify(studentRepository, times(1)).findById(student1.getId());
        verify(studentRepository, times(1)).delete(student1);
    }

    @Test
    void shouldNotDeleteStudent() {
        // given
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        // when
        ThrowableAssert.ThrowingCallable thrown = () -> studentService.delete(student1.getId());

        // then
        assertThatThrownBy(thrown).isInstanceOf(StudentNotFoundException.class);
        verify(studentRepository, times(1)).findById(student1.getId());
        verifyNoMoreInteractions(studentRepository);
    }
}