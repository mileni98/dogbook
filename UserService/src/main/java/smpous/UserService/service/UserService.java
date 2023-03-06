package smpous.UserService.service;

import java.util.ArrayList;

import smpous.UserService.model.User;

public interface UserService {
    
	public User findByUsername(String username) throws Exception;
    public User approve(User editedUser);
    public ArrayList<User> getAll();
    public ArrayList<User> getAllNonApproved();
    public Boolean deleteUser(String username);
   
}
