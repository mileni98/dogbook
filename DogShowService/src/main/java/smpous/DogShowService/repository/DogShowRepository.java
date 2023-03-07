package smpous.DogShowService.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

import smpous.DogShowService.model.DogShow;

public interface DogShowRepository extends MongoRepository <DogShow, Integer>{

    public DogShow getById(String id);
    public ArrayList<DogShow> findAll();

}
