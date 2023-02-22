package smpous.UserService.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smpous.UserService.model.User;
import smpous.UserService.repository.UserRepository;
import smpous.UserService.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        // check if user with the given username exists
        var user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " doesn't exist!"));
        return user;
    }

    @Override
    public User approve(String username){
        // check if user exists
        var existingUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " doesn't exist!"));
        existingUser.setApproved(true);
        return userRepository.save(existingUser);
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found.");
        }
        return users;
    }

    @Override
    public ArrayList<User> getAllNonApproved(){
        ArrayList<User> users = userRepository.findByApprovedFalse();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found.");
        }
        return users;
    }

    @Override
    public Boolean deleteUser(String username){
        // check if user with the given username exists
        var user = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " doesn't exist!"));
        userRepository.delete(user);
        return true;
    }
    
}
