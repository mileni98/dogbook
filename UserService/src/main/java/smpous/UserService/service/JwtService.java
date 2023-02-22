package smpous.UserService.service;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import smpous.UserService.model.Role;
import smpous.UserService.service.JwtService;

public interface JwtService {

  public String extractUsername(String token);
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
  public String generateToken(UserDetails userDetails, Role role);
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Role role);
  public boolean isTokenValid(String token, UserDetails userDetails);

}
