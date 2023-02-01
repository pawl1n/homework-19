package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.PhotoRepository;
import com.helloworld.hello_world.repository.entity.Photo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public Photo findById(Long id) {
        return photoRepository.findById(id).orElseThrow();
    }

    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }
    
    public Photo findByDescription(String description) {
        return photoRepository.findPhotoByDescription(description);
    }

    public List<Photo> findByDescriptionContaining(String description) {
        return photoRepository.findPhotosByDescriptionContaining(description);
    }

    public void updatePhoto(Photo photoDetails) {
        Photo photo = photoRepository.findById(photoDetails.getId())
                .orElseThrow(EntityNotFoundException::new);

        if (photoDetails.getUrl() != null) {
            photo.setUrl(photoDetails.getUrl());
        }
        if (photoDetails.getDescription() != null) {
            photo.setDescription(photoDetails.getDescription());
        }

        photoRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
