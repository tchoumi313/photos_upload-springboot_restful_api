package com.example.donsoft.controller;

import com.example.donsoft.model.Photo;
import com.example.donsoft.services.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * The type Photo controller.
 */
@RestController
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
    /**
     * Hello string.
     *
     * @return the string
     */
    @GetMapping("/")
    public String hello(){
        return "Hello welcome to DonSoft REST API";
    }

    /**
     * Get list.
     *
     * @return the list
     */
    @GetMapping("/photos")
    public Iterable<Photo> get(){
        return photoService.get();
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> get(@PathVariable Integer id){
        Photo photo = photoService.get(id);
        if(photo == null){
            return ResponseEntity.of(Optional.of(photoService.get(id)));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/photos/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
        Photo photo = photoService.get(id);
        if(photo == null){
            photoService.remove(id);
            return ResponseEntity.of(Optional.of(HttpStatus.ACCEPTED));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add photo response entity.
     *
     * @param photo the photo
     * @return the response entity
     */
   /* @PostMapping("/addPhoto")
    public ResponseEntity<HttpStatus> addPhoto(@RequestBody @Valid Photo photo){
        photoService.save(photo.getId(), photo);
        return ResponseEntity.of(Optional.of(HttpStatus.CREATED));
    }*/

    /**
     * Add photos response entity.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException the io exception
     */
    @PostMapping("/addPhotos")
    public ResponseEntity<HttpStatus> addPhotos(@RequestPart("fileName") MultipartFile file) throws IOException {
        Photo photo = photoService.save(file.getOriginalFilename(),file.getContentType(), file.getBytes());
        return ResponseEntity.of(Optional.of(HttpStatus.CREATED));
    }

    /*@PostMapping("/addPhotos")
    public ResponseEntity<HttpStatus> addPhotos(
            @RequestPart("fileName") MultipartFile file,
            @RequestParam("id") String id,
            @RequestParam("data") int data
    ) throws IOException {
        // Your existing code to handle the file

        // Now you can use the values of param1 and param2 in your logic
        System.out.println("Param 1: " + id);
        System.out.println("Param 2: " + data);

        return ResponseEntity.of(Optional.of(HttpStatus.CREATED));
    }*/

}
