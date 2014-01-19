package com.BGB.BigIssue.controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.BGB.BigIssue.model.ConnectionPool;
import com.BGB.BigIssue.model.ConnectionSettings;
import com.BGB.BigIssue.model.MySQLConnectionPool;
import com.BGB.BigIssue.model.MySQLDatabase;
import com.BGB.BigIssue.model.SHA1Encryption;
import com.BGB.BigIssue.model.TempMySQLDB;
import com.BGB.BigIssue.model.User;
import com.BGB.BigIssue.model.UserFactory;

public class LoginControllerTest {


	private static UserController uc;
	private static MySQLDatabase db;
	private static UserFactory uf;
	private static String userName;
	private static String password;
	private static SHA1Encryption ec;
	private static LoginController lc;
	private static ConnectionSettings settings;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userName = "name";
		password = "password";
		settings = new ConnectionSettings();
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance(settings.getServ(), settings.getName(), settings.getPass(), 1, 1);
		uf = new UserFactory();
		
		db = new MySQLDatabase(pool,uf);
		ec = new SHA1Encryption();
		
		
		
		lc = new LoginController(ec,db,settings);

		uc = new UserController(uf,db,ec);

		//uc.newUser(userName, password);
	}
	
	@Test
	public void testLoginReturnsZeroWithCorrectDetails(){	
		int value = lc.check(userName, password);
		assertEquals(0,value);
	}
	
	@Test
	public void testLoginReturnsOneWithIncorrectUserName(){
		//uc.newUser(userName, password);
		int value = lc.check("wrong", password);
		assertEquals(1,value);
	}
	
	@Test
	public void testLoginReturnsTwoWithIncorrectPassword(){
		//uc.newUser(userName, password);
		int value = lc.check(userName, "wrong password");
		assertEquals(2,value);
	}

}
