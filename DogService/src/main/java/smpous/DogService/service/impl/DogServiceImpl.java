package smpous.DogService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smpous.DogService.model.Dog;
import smpous.DogService.repository.DogRepository;
import smpous.DogService.service.DogService;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogRepository dogRepository;

    @Override
    public Dog save(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog findByName(String name) {
        var dog = dogRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Dog with name " + name + " doesn't exist!"));
        return dog;
    }

    @Override
    public Dog findDogById(Long id) {
        Dog dog = dogRepository.getById(id);
        if (dog == null) {
            throw new IllegalStateException("Dog does not exist!");
        }
        return dog;
    }

    @Override
    public Boolean deleteDogById(Long id) {
        Dog dog = dogRepository.getById(id);
        if (dog == null) {
            throw new IllegalStateException("Dog does not exist!");
        } else {
            dogRepository.delete(dog);
            return true;
        }
    }

    @Override
    public List<Dog> getAll() {
        List<Dog> dogs = dogRepository.findAll();
        if (dogs.isEmpty()) {
            throw new RuntimeException("No dogs found.");
        }
        return dogs;
    }
    
    @Override
    public void deleteAll() {
        dogRepository.deleteAll();
    }

}
