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
        User user = userRepository.findByUsername(username).orElseThrow();
        if(user == null){
            throw new IllegalStateException("User does not exist!");
        }
        return user;
    }

    @Override
    public Boolean deleteUser(String username){
        User user = this.findByUsername(username);
        userRepository.delete(user);
        return true;
    }
    
    @Override
    public ArrayList<User> getAll(){
        return userRepository.findAll();
    }

}
