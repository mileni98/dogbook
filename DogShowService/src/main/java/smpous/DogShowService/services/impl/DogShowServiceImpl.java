package smpous.DogShowService.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smpous.DogShowService.model.DogShow;
import smpous.DogShowService.repository.DogShowRepository;
import smpous.DogShowService.services.DogShowService;

@Service
public class DogShowServiceImpl implements DogShowService{

    @Autowired DogShowRepository dogShowRepository;
    
    @Override
    public DogShow save(DogShow dogShow){
        dogShow.setClosed(false);
        dogShow.setFinished(false);
        return dogShowRepository.save(dogShow);
    }

    
    @Override
    public DogShow findByDogShowId(String id){
        DogShow dogShow = dogShowRepository.getById(id);
        if(dogShow == null){
            throw new IllegalStateException("Dog show does not exist!");
        }
        return dogShow;
    }
  
    @Override
    public ArrayList<DogShow> getAll() {
        ArrayList<DogShow> dogShows = dogShowRepository.findAll();
        if (dogShows.isEmpty()) {
            throw new RuntimeException("No dog shows found.");
        }
        return dogShows;
    }

    @Override
    public Boolean deleteDogShow(String id){
        DogShow dogShow = dogShowRepository.getById(id);
        if (dogShow == null){
            throw new IllegalStateException("Dog show does not exist!");
        } else {
            dogShowRepository.delete(dogShow);
            return true;    
        }
    }

    @Override
    public DogShow update(DogShow editedDogShow){
        DogShow existingDogShow = dogShowRepository.getById(editedDogShow.getId());
        if(existingDogShow == null){
            throw new IllegalStateException("Dog show does not exist!");
        } else {
            dogShowRepository.delete(existingDogShow);
            return dogShowRepository.save(editedDogShow);
        }
    }

    @Override
    public void deleteAll(){
        dogShowRepository.deleteAll();
    }

}
