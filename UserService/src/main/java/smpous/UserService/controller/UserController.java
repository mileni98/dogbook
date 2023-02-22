package smpous.UserService.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smpous.UserService.model.User;
import smpous.UserService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
 
    // get user thats currently logged in
    @GetMapping(path = "/whoami",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
	public ResponseEntity<?> user(Principal user){
        try {
            return new ResponseEntity<User>(this.userService.findByUsername(user.getName()), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
  	
    // get all users
    @GetMapping(path = "/all",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<ArrayList<User>>(userService.getAll(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get all non approved users
    @GetMapping(path = "/all-non-approved",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllNonApproved(){
        try {
            return new ResponseEntity<ArrayList<User>>(userService.getAllNonApproved(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get user by username
    @GetMapping(path = "/{username}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable("username") String username){
        try{
            return new ResponseEntity<User>(userService.findByUsername(username), HttpStatus.OK);    
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // approve users registration
    @PutMapping(path = "/{username}/approve",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> approveRegistration(@PathVariable("username") String username){
        try {
            return new ResponseEntity<User>(userService.approve(username), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete user by username
    @DeleteMapping(path = "/{username}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username){
        try{
            userService.deleteUser(username);
            return new ResponseEntity<String>("User successfully su deleted", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
