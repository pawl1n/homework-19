package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.StudentDto;
import com.helloworld.hello_world.exception.StudentNotFoundException;
import com.helloworld.hello_world.repository.StudentRepository;
import com.helloworld.hello_world.repository.entity.Student;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentControllerIT {
    @Autowired
    private StudentRepository studentRepository;
    @LocalServerPort
    private int springBootPort;

    @BeforeAll
    public void setup() {
        RestAssured.port = springBootPort;

        Student student = new Student();
        student.setId(1L);
        student.setName("Student1");
        student.setEmail("student1@test");
        studentRepository.save(student);
    }

    @Test
    void shouldFindAll() {
        RestAssured
                .when()
                    .get("/students")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldFindById() {
        RestAssured
                .given()
                    .pathParam("id", 1)
                .when()
                    .get("/students/{id}")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", CoreMatchers.equalTo(1))
                    .body("name", Matchers.equalToIgnoringCase("Student1"))
                    .body("email", Matchers.equalToIgnoringCase("student1@test"))
                    .body("photos", Matchers.hasSize(0));
    }

    @Test
    void shouldNotFindById() {
        RestAssured
                .given()
                    .pathParam("id", 0)
                .when()
                    .get("/students/{id}")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", CoreMatchers.equalTo(new StudentNotFoundException().getMessage()));
    }

    @Test
    void shouldCreateStudent() {
        StudentDto studentDto = new StudentDto(null, "Student2", "student2@test", null);

        RestAssured
                .given()
                    .body(studentDto)
                    .contentType(ContentType.JSON)
                .when()
                    .post("/students")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("id", Matchers.notNullValue())
                    .body("name", Matchers.equalToIgnoringCase(studentDto.name()))
                    .body("email", Matchers.equalToIgnoringCase(studentDto.email()));
    }

    @Test
    void shouldUpdateStudent() {
        StudentDto studentDto = new StudentDto(2L, "Student2_updated", "student2@test", null);

        RestAssured
                .given()
                    .body(studentDto)
                    .contentType(ContentType.JSON)
                .when()
                    .put("/students")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", Matchers.notNullValue())
                    .body("name", Matchers.equalToIgnoringCase(studentDto.name()))
                    .body("email", Matchers.equalToIgnoringCase(studentDto.email()));
    }

    @Test
    void shouldNotUpdateStudent() {
        StudentDto studentDto = new StudentDto(0L, "Student2_updated", "student2@test", null);

        RestAssured
                .given()
                    .body(studentDto)
                    .contentType(ContentType.JSON)
                .when()
                    .put("/students")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", CoreMatchers.equalTo(new StudentNotFoundException().getMessage()));
    }

    @Test
    void delete() {
        RestAssured
                .given()
                    .pathParam("id", 0)
                .when()
                    .delete("/students/{id}")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", CoreMatchers.equalTo(new StudentNotFoundException().getMessage()));
    }
}