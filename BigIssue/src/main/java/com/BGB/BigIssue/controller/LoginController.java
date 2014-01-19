package com.BGB.BigIssue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.BGB.BigIssue.model.ConnectionPool;
import com.BGB.BigIssue.model.ConnectionSettings;
import com.BGB.BigIssue.model.MySQLConnectionPool;
import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.StorageInterface;
import com.BGB.BigIssue.model.User;

/**
 * The LoginController is responsible for controlling users logging into the app.
 * @author guyneedham
 *
 */
public class LoginController {

	private SHA1Encryption encryptor;
	private StorageInterface storage;
	public static User user;
	public static String userName;
	private MySQLConnectionPool pool;
	private ConnectionSettings settings;
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	public LoginController(SHA1Encryption enc, StorageInterface stor, ConnectionSettings settings){
		this.storage = stor;
		this.encryptor = enc;
		this.settings = settings;
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
		
		User userUnderTest = storage.getUser(userName);
		
		if(userUnderTest != null){
			
			if(encryptor.authenticate(password, userUnderTest.getPass(), userUnderTest.getSalt())){
							
				pool = MySQLConnectionPool.getInstance(settings.getServ(), userName, password, 1, 1);
				LoginController.user = userUnderTest;
				logger.info("User {} logged in.",userName);
				return 0;
			} else {
				logger.info("User {} entered an incorrect password.",userName);
				return 2;
			}
		} else {
			logger.info("Username {} was not found.",userName);
			return 1;
		}
		
	}
	
	/**
	 * The getUserPool method returns the new connection pool created for the user after login.
	 * The login process uses a dedicated connection pool which is replaced by the return value of this method.
	 * 
	 * @return pool a ConnectionPool with the user credentials.
	 */
	public MySQLConnectionPool getUserPool(){
		logger.debug("Returning the user specific connection pool for user {}.",LoginController.userName);
		return this.pool;
	}
	
	/**
	 * Returns a User object. This object is null if the check() method did not return 0.
	 * @return User
	 */
	public User login(){
		logger.debug("Setting the global username to {} and returning the user.",user.getName());
		LoginController.userName = user.getName();
		return LoginController.user;
	}
	
	
}
