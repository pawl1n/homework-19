package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.PhotoRepository;
import com.helloworld.hello_world.repository.StudentRepository;
import com.helloworld.hello_world.repository.entity.Photo;
import com.helloworld.hello_world.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final StudentRepository studentRepository;

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public Photo findById(Long id) {
        return photoRepository.findById(id).orElseThrow();
    }

    public void savePhoto(String url, Long student_id, String description) {
        Photo photo = new Photo();
        photo.setUrl(url);
        Student student = studentRepository.findById(student_id).orElseThrow();
        photo.setStudent(student);
        photo.setDescription(description);

        photoRepository.save(photo);
    }
    
    public Photo findByDescription(String description) {
        return photoRepository.findPhotoByDescription(description);
    }

    public List<Photo> findByDescriptionContaining(String description) {
        return photoRepository.findPhotosByDescriptionContaining(description);
    }
}
