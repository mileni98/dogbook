package smpous.DogShowService.controller;

import java.util.ArrayList;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smpous.DogShowService.model.DogShow;
import smpous.DogShowService.services.DogShowService;

@RestController
@RequestMapping("/api/v1/dogshows")
public class DogShowController {

    @Autowired
    private DogShowService dogShowService;

    @RequestMapping("/reset_repo")
    public String home() {

        dogShowService.deleteAll();

        DogShow tmp = new DogShow();
        tmp.setId("1");
        tmp.setName("CACIB");
        tmp.setLocation("Novi Sad");
        tmp.setDate(Date.valueOf("2023-01-01"));
        tmp.setMaximumScore(10);
        tmp.setClosed(false);
        tmp.setFinished(false);
        dogShowService.save(tmp);

        return tmp.toString();
    }

    // get all dog shows
    @GetMapping(value = "/all", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDogShows() {
        try{
            return new ResponseEntity<ArrayList<DogShow>>(dogShowService.getAll(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
    // register new dog show
    @PostMapping(value = "/add-dogshow",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDogShow(@RequestBody DogShow dogShow){
        try {
            return new ResponseEntity<DogShow>(dogShowService.save(dogShow), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update dog show
    @PutMapping(value = "/update-dogshow",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDogShow(@RequestBody DogShow dogShow){
        try {
            return new ResponseEntity<DogShow>(dogShowService.update(dogShow), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get dog show by Id
    @GetMapping(path = "/{dogShowId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDogShow(@PathVariable("dogShowId") String dogShowId){
        try {
            return new ResponseEntity<DogShow>(dogShowService.findByDogShowId(dogShowId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
