package com.example.donsoft.repository;

import com.example.donsoft.model.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhotosRepository extends CrudRepository<Photo,Integer> {

}
