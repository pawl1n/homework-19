package com.helloworld.hello_world.repository;

import com.helloworld.hello_world.repository.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findPhotoByDescription(String description);
    List<Photo> findPhotosByDescriptionContaining(String description);
}
