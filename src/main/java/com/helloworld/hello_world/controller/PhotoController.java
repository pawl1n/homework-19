package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.PhotoDto;
import com.helloworld.hello_world.controller.dto.PhotoMapper;
import com.helloworld.hello_world.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @GetMapping
    public List<PhotoDto> findAll() {
        return photoService.findAll().stream().map(photoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public PhotoDto findById(@PathVariable Long id) {
        return photoMapper.toDto(photoService.findById(id));
    }

    @GetMapping("/description")
    public PhotoDto findByDescription(@RequestParam String description) {
        return photoMapper.toDto(photoService.findByDescription(description));
    }

    @GetMapping("/containing")
    public List<PhotoDto> findByDescriptionContaining(@RequestParam String description) {
        return photoService.findByDescriptionContaining(description).stream().map(photoMapper::toDto).toList();
    }

    @PostMapping
    public void savePhoto(@RequestBody PhotoDto photoDto) {
        photoService.savePhoto(photoMapper.toDomain(photoDto));
    }

    @PutMapping
    public void updatePhoto(@RequestBody PhotoDto photoDto) {
        photoService.updatePhoto(photoMapper.toDomain(photoDto));
    }

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }
}
