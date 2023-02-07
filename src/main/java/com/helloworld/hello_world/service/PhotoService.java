package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.entity.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> findAll();

    Photo findById(Long id);

    Photo savePhoto(Photo photo);

    Photo findByDescription(String description);

    List<Photo> findByDescriptionContaining(String description);

    Photo updatePhoto(Photo photoDetails);

    void deletePhoto(Long id);
}
