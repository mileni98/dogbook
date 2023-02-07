package smpous.UserService.service;

import java.util.ArrayList;

import smpous.UserService.model.User;

public interface UserService {
    
	public User findByUsername(String username);
    public Boolean deleteUser(String username);
    public ArrayList<User> getAll();

}
