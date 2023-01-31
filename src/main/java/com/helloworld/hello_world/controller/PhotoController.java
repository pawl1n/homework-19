package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.PhotoDTO;
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
    public List<PhotoDTO> findAll() {
        return photoService.findAll().stream().map(photoMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public PhotoDTO findById(@PathVariable Long id) {
        return photoMapper.toDTO(photoService.findById(id));
    }

    @GetMapping("/description")
    public PhotoDTO findByDescription(@RequestParam String description) {
        return photoMapper.toDTO(photoService.findByDescription(description));
    }

    @GetMapping("/containing")
    public List<PhotoDTO> findByDescriptionContaining(@RequestParam String description) {
        return photoService.findByDescriptionContaining(description).stream().map(photoMapper::toDTO).toList();
    }

//    @PostMapping
//    public void savePhoto(@RequestBody PhotoDTO photoDTO) {
//        photoService.savePhoto(photoMapper.toDomain(photoDTO));
//    }

    @PutMapping("/{id}")
    public void updatePhoto(@PathVariable Long id, @RequestBody PhotoDTO photoDTO) {
        photoService.updatePhoto(id, photoMapper.toDomain(photoDTO));
    }

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }
}
