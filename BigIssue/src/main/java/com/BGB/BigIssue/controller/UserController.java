package com.BGB.BigIssue.controller;

import com.BGB.BigIssue.model.MySQLDatabaseInterface;
import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.User;
import com.BGB.BigIssue.model.UserFactory;

public class UserController {

	private UserFactory uf;
	private SHA1Encryption encryptor;
	private MySQLDatabaseInterface storage;
	private User user;
	
	public UserController(UserFactory uf, MySQLDatabaseInterface storage, SHA1Encryption encryptor){
		this.uf = uf;
		this.storage = storage;
		this.encryptor = encryptor;
	}
	
	public void newUser(String userName, String password){
		User user = (User) uf.newObject();
		user.setName(userName);
		byte[] salt = encryptor.generateSalt();
		user.setSalt(salt);
		user.setPass(encryptor.encrypt(password, salt));
		storage.newUser(user);		
	}
	
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
	
	public User login(){
		return this.user;
	}
	
	public void removeUser(String userName){
		storage.removeUser(userName);
	}
	
}
