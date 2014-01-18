package com.BGB.BigIssue.controller;

import com.BGB.BigIssue.model.ConnectionPool;
import com.BGB.BigIssue.model.ConnectionSettings;
import com.BGB.BigIssue.model.MySQLConnectionPool;
import com.BGB.BigIssue.model.StorageInterface;
import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.User;
import com.BGB.BigIssue.model.UserFactory;

/**
 * Controller responsible for User object management.
 * Adds and removes users from storage.
 * Changes user passwords.
 * 
 * @author guyneedham
 *
 */
public class UserController {

	private UserFactory uf;
	private SHA1Encryption encryptor;
	private StorageInterface storage;
	public static User user;
	public static String userName;
	private MySQLConnectionPool pool;
	
	public UserController(UserFactory uf, StorageInterface storage, SHA1Encryption encryptor){
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
	 * Removes a user from storage.
	 * @param userName a String the username to remove
	 */
	public void removeUser(String userName){
		storage.removeUser(userName);
	}
	
	/**
	 * The changePass method changes a named user's password. This method should only be accessible from the admin area.
	 * @param userName a String the username of the user to alter
	 * @param pass a String the new password
	 */
	
	public void changePass(String userName, String pass){
		User user = storage.getUser(userName);
		byte[] password = encryptor.encrypt(pass, user.getSalt());
		storage.changePass(userName, password);
	}
	
	/**
	 * The changeOwnPass method allows the currently logged in user to change their current password.
	 * It requires users to enter their previous password correctly.
	 * 
	 * @param oldPass a String the current password
	 * @param newPass a String the new password
	 * @return int 0 if successfully changed, 1 if the oldPass was incorrect
	 */
	public int changeOwnPass(String oldPass, String newPass){
		byte[] oldPassEncrypted = user.getPass();
		byte[] salt = user.getSalt();
		
		boolean checkOld = encryptor.authenticate(oldPass, oldPassEncrypted, salt);
		
		if(checkOld){
			byte[] newPassEncrypted = encryptor.encrypt(newPass, salt);
			storage.changePass(userName, newPassEncrypted);
			return 0;
		} else {
			return 1;
		}
	}
	
}
