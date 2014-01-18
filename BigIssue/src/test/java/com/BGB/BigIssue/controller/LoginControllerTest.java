package com.BGB.BigIssue.controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.TempMySQLDB;
import com.BGB.BigIssue.model.User;
import com.BGB.BigIssue.model.UserFactory;

public class LoginControllerTest {


	private static UserController uc;
	private static TempMySQLDB db;
	private static UserFactory uf;
	private static String userName;
	private static String password;
	private static SHA1Encryption ec;
	private static LoginController lc;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userName = "name";
		password = "password";
		
		db = new TempMySQLDB();
		ec = new SHA1Encryption();
		
		uf = new UserFactory();
		
		lc = new LoginController(ec,db);

		uc = new UserController(uf,db,ec);

		uc.newUser(userName, password);
	}

	@Test
	public void testNewUserCreatesAUser(){
		lc.check(userName, password);
		User user = lc.login();
		assertEquals(user.getName(),userName);
	}
	
	@Test
	public void testLoginReturnsZeroWithCorrectDetails(){	
		int value = lc.check(userName, password);
		assertEquals(0,value);
	}
	
	@Test
	public void testLoginReturnsOneWithIncorrectUserName(){
		uc.newUser(userName, password);
		int value = lc.check("wrong", password);
		assertEquals(1,value);
	}
	
	@Test
	public void testLoginReturnsTwoWithIncorrectPassword(){
		uc.newUser(userName, password);
		int value = lc.check(userName, "wrong password");
		assertEquals(2,value);
	}

}
