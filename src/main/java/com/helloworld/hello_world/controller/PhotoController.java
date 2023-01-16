package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.repository.entity.Photo;
import com.helloworld.hello_world.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping
    public List<Photo> findAll() {
        return photoService.findAll();
    }

    @GetMapping("/{id}")
    public Photo findById(@PathVariable Long id) {
        return photoService.findById(id);
    }

    @GetMapping("/create")
    public void savePhoto(@RequestParam String url, @RequestParam Long student_id, @RequestParam String description) {
        photoService.savePhoto(url, student_id, description);
    }

}
