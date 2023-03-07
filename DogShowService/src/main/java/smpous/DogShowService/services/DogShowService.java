package smpous.DogShowService.services;

import java.util.ArrayList;

import smpous.DogShowService.model.DogShow;

public interface DogShowService {

    public DogShow update(DogShow editedDogShow);
    public DogShow findByDogShowId(String id);
    public Boolean deleteDogShow(String id);
    public ArrayList<DogShow> getAll();
    public DogShow save(DogShow dogShow);
    
}
