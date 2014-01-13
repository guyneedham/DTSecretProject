package con.BGB.BigIssue.controller;

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
	
	@Before
	public void setup(){
		userName = "name";
		password = "password";
		
		uf = new UserFactory();
		db = new TempMySQLDB();
		ec = new SHA1Encryption();
		
		uc = new UserController(uf,db,ec);
	}
	
	@Test
	public void testNewUserCreatesAUser(){
		uc.newUser(userName, password);
		uc.check(userName, password);
		User user = uc.login();
		assertEquals(user.getName(),userName);
	}
	
	@Test
	public void testLoginReturnsZeroWithCorrectDetails(){	
		uc.newUser(userName, password);
		int value = uc.check(userName, password);
		assertEquals(0,value);
	}
	
	@Test
	public void testLoginReturnsOneWithIncorrectUserName(){
		uc.newUser(userName, password);
		int value = uc.check("wrong", password);
		assertEquals(1,value);
	}
	
	@Test
	public void testLoginReturnsTwoWithIncorrectPassword(){
		uc.newUser(userName, password);
		int value = uc.check(userName, "wrong password");
		assertEquals(2,value);
	}
	
	@Test
	public void testRemoveUserRemovesAUser(){
		uc.newUser(userName, password);
		uc.removeUser(userName);
		int value = uc.check(userName, password);
		assertEquals(1,value);
	}
	
	
}
