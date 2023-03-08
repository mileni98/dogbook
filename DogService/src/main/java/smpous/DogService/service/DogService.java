package smpous.DogService.service;

import java.util.List;

import smpous.DogService.model.Dog;

public interface DogService {
    
    public Dog save(Dog dog);
    public Dog findByName(String name);
    public Dog findDogById(Long id);
    public Boolean deleteDogById(Long id);
    public List<Dog> getAll();
    public void deleteAll();
    
}
