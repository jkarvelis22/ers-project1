package com.revature.services;



import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.utils.Josh4J;


public class UserService {
	static Josh4J j = Josh4J.getInstance();

//	private static Logger log = Logger.getLogger(UserService.class);
	
	private UserDAO userDao = new UserDAO();
	
	public List<User> getAll() {
		return userDao.getAll();
	}

	public User getById(int userId) {
		return userDao.getById(userId);
	}

	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}

	public User getByCredentials(String username, String password) {

		User user = null;
		
		// Verify that neither of the credentials are empty string
		if (!username.equals("") && !password.equals("")) {
			user = userDao.getByCredentials(username, password);
			if(user != null) {
				return user;
			}
		}
		
//		log.info("Username and/or password is empty!");
		return null;
	}
	
	public User add(User newUser) {

		// Verify that there are no empty fields
		if (newUser.getUsername().equals("") || newUser.getPassword().equals("") || newUser.getFirstName().equals("")
				|| newUser.getLastName().equals("") || newUser.getEmail().equals("")){
	//		log.info("New User had empty fields!");
			return null;
		}

		return userDao.add(newUser);
	}
	
}
