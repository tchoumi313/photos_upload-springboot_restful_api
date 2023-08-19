package com.example.donsoft.controller;

import com.example.donsoft.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * The type Photo controller.
 */
@RestController
public class PhotoController {
    private Map<String,Photo> db  = new HashMap<String,Photo>(){{
        put("1", new Photo("1","donsoft.jpeg"));
        put("2", new Photo("2","donsft.jpeg"));
        put("3", new Photo("3","dnsoft.jpeg"));
    }};

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
    public Collection<Photo> get(){
        return db.values();
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> get(@PathVariable String id){
        if(db.containsKey(id)){
            return ResponseEntity.of(Optional.of(db.get(id)));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id){
        if(db.containsKey(id)){
            db.remove(id);
            return ResponseEntity.of(Optional.of(HttpStatus.ACCEPTED));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
