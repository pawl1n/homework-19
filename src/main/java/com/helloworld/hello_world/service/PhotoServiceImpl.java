package com.helloworld.hello_world.service;

import com.helloworld.hello_world.exception.PhotoNotFoundException;
import com.helloworld.hello_world.exception.StudentNotFoundException;
import com.helloworld.hello_world.repository.PhotoRepository;
import com.helloworld.hello_world.repository.StudentRepository;
import com.helloworld.hello_world.repository.entity.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo findById(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(PhotoNotFoundException::new);
    }

    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }
    
    @Override
    public Photo findByDescription(String description) {
        return photoRepository.findPhotoByDescription(description);
    }

    @Override
    public List<Photo> findByDescriptionContaining(String description) {
        return photoRepository.findPhotosByDescriptionContaining(description);
    }

    @Override
    public Photo updatePhoto(Photo photoDetails) {
        Photo photo = photoRepository.findById(photoDetails.getId())
                .orElseThrow(PhotoNotFoundException::new);

        if (photoDetails.getUrl() != null) {
            photo.setUrl(photoDetails.getUrl());
        }
        if (photoDetails.getDescription() != null) {
            photo.setDescription(photoDetails.getDescription());
        }

        return photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(Long id) {
        Photo photo = photoRepository.findById(id)
                        .orElseThrow(PhotoNotFoundException::new);
        photoRepository.delete(photo);
    }
}
