package com.example.donsoft.services;

import com.example.donsoft.model.Photo;
import com.example.donsoft.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotoService {

   /* private Map<String, Photo> db  = new HashMap<String,Photo>(){{
        put("1", new Photo("1","donsoft.jpeg"));
        put("2", new Photo("2","donsft.jpeg"));
        put("3", new Photo("3","dnsoft.jpeg"));
    }};*/

    @Autowired
    private PhotosRepository photosRepository;

    public Iterable<Photo> get() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);;
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
       // photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setImage(data);
        System.out.println(contentType);
        if (contentType == null) {
            photo.setContentType("image/jpeg");
        }else {
            photo.setContentType(contentType);
        }

        photosRepository.save(photo);
        return photo;
    }
}
