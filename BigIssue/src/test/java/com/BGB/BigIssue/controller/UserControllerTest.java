package com.BGB.BigIssue.controller;

import static org.junit.Assert.*;

import com.BGB.BigIssue.controller.UserController;
import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.TempMySQLDB;
import com.BGB.BigIssue.model.User;
import com.BGB.BigIssue.model.UserFactory;

import org.junit.Before;
import org.junit.Test;

public class UserControllerTest {

	private UserController uc;
	private TempMySQLDB db;
	private UserFactory uf;
	private String userName;
	private String password;
	private SHA1Encryption ec;
	private LoginController lc;
	
	@Before
	public void setup(){
		userName = "name";
		password = "password";
		
		uf = new UserFactory();
		db = new TempMySQLDB();
		ec = new SHA1Encryption();
		
		lc = new LoginController(ec, db);
		uc = new UserController(uf,db,ec);
	}
	

	
	@Test
	public void testRemoveUserRemovesAUser(){
		uc.newUser(userName, password);
		uc.removeUser(userName);
		int value = lc.check(userName, password);
		assertEquals(1,value);
	}
	
	@Test
	public void testChangePasswordChangesPassword(){
		uc.newUser(userName, password);
		uc.changePass(userName, "newPass");
		int value = lc.check(userName, "newPass");
		assertEquals(0,value);				
	}
	
	
}
