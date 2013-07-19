package com.findme.repository;

import java.util.List;

import com.findme.model.User;

/**
 * @author vinodkumara
 * 
 */
public interface IUserDAO {

	List<User> getUsers();

	List<User> checkUser(User user);

	void updateUser(User user);

}
