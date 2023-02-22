package smpous.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smpous.UserService.auth.AuthenticationRequest;
import smpous.UserService.auth.AuthenticationResponse;
import smpous.UserService.auth.RegisterRequest;
import smpous.UserService.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  @Autowired
  private final AuthenticationService authService;

  // register new user
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
        return new ResponseEntity<AuthenticationResponse>(authService.register(request), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // authenticate user
  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
    try {
        return new ResponseEntity<AuthenticationResponse>(authService.authenticate(request), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (IllegalStateException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
    } catch (Exception e) {
        return new ResponseEntity<String>("An error error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}