package com.BGB.BigIssue.controller;

import com.BGB.BigIssue.model.ConnectionPool;
import com.BGB.BigIssue.model.ConnectionSettings;
import com.BGB.BigIssue.model.MySQLConnectionPool;
import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.StorageInterface;
import com.BGB.BigIssue.model.User;
import com.BGB.BigIssue.model.UserFactory;

/**
 * The LoginController is responsible for controlling users logging into the app.
 * @author guyneedham
 *
 */
public class LoginController {

	private UserFactory uf;
	private SHA1Encryption encryptor;
	private StorageInterface storage;
	private User user;
	public static String userName;
	private MySQLConnectionPool pool;
	
	
	public LoginController(SHA1Encryption enc, StorageInterface stor){
		this.storage = stor;
		this.encryptor = enc;
	}
	
	/**
	 * The check method checks if the username and password are valid.
	 * This method necessitates change if the database type is changed.
	 * 
	 * @param userName
	 * @param password
	 * @return 0 if the username and password are valid, 1 if the username if not found and 2 if the password is incorrect.
	 */
	public int check(String userName, String password){
		ConnectionSettings settings = new ConnectionSettings();
		
		User userUnderTest = storage.getUser(userName);
		
		if(userUnderTest != null){
			
			if(encryptor.authenticate(password, userUnderTest.getPass(), userUnderTest.getSalt())){
							
				pool = MySQLConnectionPool.getInstance(settings.getServ(), userName, password, 1, 1);
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
	 * The getUserPool method returns the new connection pool created for the user after login.
	 * The login process uses a dedicated connection pool which is replaced by the return value of this method.
	 * 
	 * @return pool a ConnectionPool with the user credentials.
	 */
	public ConnectionPool getUserPool(){
		return this.pool;
	}
	
	/**
	 * Returns a User object. This object is null if the check() method did not return 0.
	 * @return User
	 */
	public User login(){
		UserController.user = this.user;
		LoginController.userName = user.getName();
		return this.user;
	}
	
	
}
