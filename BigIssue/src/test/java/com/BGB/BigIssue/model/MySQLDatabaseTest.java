package com.BGB.BigIssue.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.BGB.BigIssue.controller.LoginController;
import com.BGB.BigIssue.controller.UserController;

public class MySQLDatabaseTest {

	private static MySQLDatabase db;
	private static MySQLConnectionPool pool;
	private static ConnectionSettings settings;
	private static UserController uc;
	private static LoginController lc;
	private static UserFactory uf;
	private static SHA1Encryption enc;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		settings = new ConnectionSettings();
		pool = MySQLConnectionPool.getInstance(settings.getServ(),settings.getName(),settings.getPass(),1,1);
		db = new MySQLDatabase(pool);
		uf = new UserFactory();
		enc = new SHA1Encryption();
		uc = new UserController(uf,db,enc);
		lc = new LoginController(enc,db,settings);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void testNewUserCreatesNewUser() {
		
		db.testSelect();
		
		User user = (User) uf.newObject();
		
		byte[] salt = enc.generateSalt();
		user.setSalt(salt);
		user.setName("NewUserTest");
		byte[] pass = enc.encrypt("password", salt);
		user.setPass(pass);
		
		db.newUser(user);
		int login = lc.check("GuyTest", "password");		
		assertEquals(0,login);
	}

}
