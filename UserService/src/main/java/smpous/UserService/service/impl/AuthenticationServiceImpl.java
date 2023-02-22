package smpous.UserService.service.impl;

import smpous.UserService.auth.AuthenticationRequest;
import smpous.UserService.auth.AuthenticationResponse;
import smpous.UserService.auth.RegisterRequest;
import smpous.UserService.model.Role;
import smpous.UserService.model.User;
import smpous.UserService.repository.UserRepository;
import smpous.UserService.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtServiceImpl jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    // register new user
    public AuthenticationResponse register(RegisterRequest request) {

        // check if a user with the given username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with username " + request.getUsername() + " already exists!");
        }

        // create new user
        var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.OWNER)
            .registrationDate(new Date())
            .approved(false)
            .build();

        // save new user to repository
        userRepository.save(user);

        // generate JWT token for new user
        var jwtToken = jwtService.generateToken(user, Role.OWNER);

        // return authentication response containing the JWT token
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    @Override
    // authenticate user
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // check if user with the given username exists
        var user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new IllegalArgumentException("Incorect username or password."));

        // check if password is correct
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorect username or password.");
        }
       
        // authenticate user by verifying username and password given in request
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        // check if registration is approved 
        if (!user.getApproved()) {
            throw new IllegalStateException("User is not approved yet.");
        }

        // generate JWT token for authenticated user
        var jwtToken = jwtService.generateToken(user, user.getRole());

        // return authentication response containing the JWT token
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

}
