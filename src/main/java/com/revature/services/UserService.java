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
		j.info("UserService.getAll()");
		j.info("UserService.getAll() : VALUE was returned");
		return userDao.getAll();
	}

	public User getById(int userId) {
		j.info("UserService.getById(" + userId + ")");
		j.info("UserService.getById(" + userId + ") : VALUE was returned");
		return userDao.getById(userId);
	}

	public User getByUsername(String username) {
		j.info("UserService.getByUsername(" + username + ")");
		j.info("UserService.getByUsername(" + username + ") : VALUE was returned");
		return userDao.getByUsername(username);
	}

	public User getByCredentials(String username, String password) {
		j.info("UserService.getByCredentials(" + username + "," + password + ")");
		User user = null;
		
		// Verify that neither of the credentials are empty string
		if (!username.equals("") && !password.equals("")) {
			user = userDao.getByCredentials(username, password);
			if(user != null) {
				j.info("UserService.getByCredentials(" + username + "," + password + ") : Variable == False :if block entered.");
				j.info("UserService.getByCredentials(" + username + "," + password + ") : VALUE was returned");
				return user;
			}
		}
		
//		log.info("Username and/or password is empty!");
		j.info("UserService.getByCredentials(" + username + "," + password + ") : VALUE was returned");
		return null;
	}
	
	public User add(User newUser) {
		j.info("UserService.add(" + newUser + ")");

		// Verify that there are no empty fields
		if (newUser.getUsername().equals("") || newUser.getPassword().equals("") || newUser.getFirstName().equals("")
				|| newUser.getLastName().equals("") || newUser.getEmail().equals("")){
			j.info("UserService(" + newUser + ") : Variable == False :if block entered.");
	//		log.info("New User had empty fields!");
			j.info("UserService.add(" + newUser + ") : VALUE was returned");
			return null;
		}
		j.info("UserService.add(" + newUser + ") : VALUE was returned");
		return userDao.add(newUser);
	}
	
}
