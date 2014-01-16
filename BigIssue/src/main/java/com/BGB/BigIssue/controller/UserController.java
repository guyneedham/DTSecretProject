package com.BGB.BigIssue.controller;

import com.BGB.BigIssue.model.MySQLDatabaseInterface;
import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.User;
import com.BGB.BigIssue.model.UserFactory;

/**
 * Controller responsible for User object management.
 * Adds and removes users from storage.
 * Logs users in and verifies them.
 * @author guyneedham
 *
 */
public class UserController {

	private UserFactory uf;
	private SHA1Encryption encryptor;
	private MySQLDatabaseInterface storage;
	private User user;
	public static String userName;
	
	public UserController(UserFactory uf, MySQLDatabaseInterface storage, SHA1Encryption encryptor){
		this.uf = uf;
		this.storage = storage;
		this.encryptor = encryptor;
	}
	
	/**
	 * The newUser method creates a new stored user with a username and password
	 * @param userName
	 * @param password
	 */
	public void newUser(String userName, String password){
		User user = (User) uf.newObject();
		user.setName(userName);
		byte[] salt = encryptor.generateSalt();
		user.setSalt(salt);
		user.setPass(encryptor.encrypt(password, salt));
		storage.newUser(user);		
	}
	
	/**
	 * The check method checks if the username and password are valid.
	 * @param userName
	 * @param password
	 * @return 0 if the username and password are valid, 1 if the username if not found and 2 if the password is incorrect.
	 */
	public int check(String userName, String password){
		User userUnderTest = storage.getUser(userName);
		
		if(userUnderTest != null){
			
			if(encryptor.authenticate(password, userUnderTest.getPass(), userUnderTest.getSalt())){
				this.user = userUnderTest;
				return 0;
			} else {
				return 2;
			}
		} else {
			return 1;
		}
		
	}
	
	/**
	 * Returns a User object. This object is null if the check() method did not return 0.
	 * @return User
	 */
	public User login(){
		UserController.userName = user.getName();
		return this.user;
	}
	
	/**
	 * Removes a user from storage.
	 * @param userName
	 */
	public void removeUser(String userName){
		storage.removeUser(userName);
	}
	
	public void changePass(String userName, String pass){
		User user = storage.getUser(userName);
		byte[] password = encryptor.encrypt(pass, user.getSalt());
		storage.changePass(userName, password);
	}
	
}
