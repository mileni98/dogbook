package smpous.UserService.service;

import smpous.UserService.auth.AuthenticationRequest;
import smpous.UserService.auth.AuthenticationResponse;
import smpous.UserService.auth.RegisterRequest;

public interface AuthenticationService {

  public AuthenticationResponse register(RegisterRequest request);
  public AuthenticationResponse authenticate(AuthenticationRequest request);

}
