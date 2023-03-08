package smpous.DogService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smpous.DogService.model.Dog;
import smpous.DogService.service.DogService;

@RestController
@RequestMapping(path = "/api/v1/dog")
public class DogController {

    @Autowired
    private DogService dogService;

    // get all dogs
    @GetMapping(value = "/all", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDogs() {
        try{
            return new ResponseEntity<List<Dog>>(dogService.getAll(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // register new dog 
    @PostMapping(value = "/add-dogs",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDogShow(@RequestBody Dog dog){
        try {
            return new ResponseEntity<Dog>(dogService.save(dog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get dog by id
    @GetMapping(path = "/{dogId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDogShow(@PathVariable("dogId") Long dogId){
        try {
            return new ResponseEntity<Dog>(dogService.findDogById(dogId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
