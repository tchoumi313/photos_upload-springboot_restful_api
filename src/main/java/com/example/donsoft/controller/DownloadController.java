package com.example.donsoft.controller;

import com.example.donsoft.model.Photo;
import com.example.donsoft.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    @Autowired
    private PhotoService photoService;
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        Photo photo = photoService.get(id);
        if(photo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        byte[] data = photo.getImage();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment").filename(photo.getFileName()).build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
