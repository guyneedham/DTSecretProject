package com.BGB.BigIssue.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
	private static ComplaintsFactory cf;
	private static PitchFactory pf;
	private static BadgeFactory bf;
	private static TabardFactory tf;
	private static VendorFactory vf;
	private static String firstname;
	private static String lastname;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		settings = new ConnectionSettings();
		pool = MySQLConnectionPool.getInstance(settings.getServ(),settings.getName(),settings.getPass(),1,1);
		db = new MySQLDatabase(pool, uf, vf, tf, cf, pf, bf);
		uf = new UserFactory();
		enc = new SHA1Encryption();
		uc = new UserController(uf,db,enc);
		lc = new LoginController(enc,db,settings);
		
		//create test vendor
		firstname = "Test";
		lastname = "User";
		

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Test
	public void testAddVendorPlacesVendorInDatabase(){
		db.addVendor(firstname,lastname);
		int result = db.getVendorIDFromName(firstname,lastname);
		db.removeVendor(firstname, lastname);
		assertNotEquals(result, 0);
	}
	
	@Test
	public void testRemoveVendorObliteratesVendorFromDatabase(){
		int first = db.getVendorIDFromName(firstname, lastname);
		db.addVendor(firstname, lastname);
		db.removeVendor(firstname, lastname);
		int last = db.getVendorIDFromName(firstname, lastname);
		assertEquals(first, last);
	}

	@Test
	public void testGetVendorIDFromNameReturnsTheCorrectVendorID(){
		db.addVendor(firstname, lastname);
		int result = db.getVendorIDFromName(firstname, lastname);
		db.removeVendor(firstname, lastname);
		assertNotEquals(result, 0);
	}


	
	
	
}
