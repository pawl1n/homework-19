package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.repository.entity.Photo;
import com.helloworld.hello_world.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
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

    @GetMapping("/description")
    public Photo findByDescription(@RequestParam String description) {
        return photoService.findByDescription(description);
    }

    @GetMapping("/containing")
    public List<Photo> findByDescriptionContaining(@RequestParam String description) {
        return photoService.findByDescriptionContaining(description);
    }
}
