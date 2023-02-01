package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.PhotoDto;
import com.helloworld.hello_world.controller.dto.PhotoMapper;
import com.helloworld.hello_world.repository.entity.Photo;
import com.helloworld.hello_world.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @GetMapping
    public ResponseEntity<List<PhotoDto>> findAll() {
        List<PhotoDto> responseDto = photoMapper.toDto(photoService.findAll());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> findById(@PathVariable Long id) {
        PhotoDto responseDto = photoMapper.toDto(photoService.findById(id));

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/description")
    public ResponseEntity<PhotoDto> findByDescription(@RequestParam String description) {
        PhotoDto responseDto = photoMapper.toDto(photoService.findByDescription(description));

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/containing")
    public ResponseEntity<List<PhotoDto>> findByDescriptionContaining(@RequestParam String description) {
        List<PhotoDto> responseDto = photoMapper.toDto(photoService.findByDescriptionContaining(description));

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<PhotoDto> savePhoto(@RequestBody PhotoDto photoDto) {
        Photo response = photoService.savePhoto(photoMapper.toDomain(photoDto));
        PhotoDto responseDto = photoMapper.toDto(response);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PhotoDto> updatePhoto(@RequestBody PhotoDto photoDto) {
        Photo response = photoService.updatePhoto(photoMapper.toDomain(photoDto));
        PhotoDto responseDto = photoMapper.toDto(response);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);

        return ResponseEntity.noContent().build();
    }
}
