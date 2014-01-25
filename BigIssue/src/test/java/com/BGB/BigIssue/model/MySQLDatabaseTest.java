package com.BGB.BigIssue.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.DecimalFormat;
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

	@Test
	public void testVendorAddsTransactionAddsATransactionToDatabase(){
		db.vendorAddsTransaction(5, 1, 1, (float) 2.00, Date.valueOf("2013-01-01"));
		Float floar = db.getTotalBoughtForVendor(5);
		Float five = Float.valueOf("0");
		assertNotEquals(floar,five);
	}

	@Test
	public void testAddPitchToVendor(){
		db.addPitchToVendor(5, 4);
		ArrayList<Pitch> pitches = db.publishBadgeHistory(5);
		int pid = pitches.get(0).getPitchID();
		assertEquals(pid, 4);
		//null pointer
	}

	@Test
	public void testListOfUnregisterePitchesReturnsAnArrayListOfPitches(){
		db.addPitch("Test", "Test", "Test");
		ArrayList<Pitch> pitches = db.listOfUnregisteredPitches();
		assertNotEquals(pitches.size(),0);

	}


	@Test
	public void testPublishBadgeHistory(){
		ArrayList<Pitch> arraylist = db.publishBadgeHistory(5);
		assertNotEquals(arraylist.size(),0);
	}
	@Test
	public void testPublishVendorHistory(){
		ArrayList<Badge> badges = db.publishVendorHistory(5);
		assertNotEquals(badges.size(),0);
	}

	@Test
	public void testNewBadgeAddsABadgeIntoTheDatabase(){
		db.newBadge("Test", "Test", Date.valueOf("2013-01-01"), Date.valueOf("2013-01-02"));
		ArrayList<Badge> badges = db.findBadge("Test");
		assertNotEquals(badges.size(),0);
	}

	@Test
	public void testgetVendor(){
		Vendor vendor = db.getVendor(5);
		assertEquals(vendor.getVendorID(),5);
	}
	
	@Test
	public void testassignTabardToVendor(){
		db.assignTabardToVendor(1, 5);
		ArrayList<Tabard> tabards = db.publishTabardHistory(5);
		assertNotEquals(tabards.size(),0);
	}
	
	@Test
	public void testListAvailableTabards(){
		ArrayList<Tabard> tabards  = db.listAvailableTabards();
		assertEquals(tabards.size(),0);
	}

	@Test
	public void testViewTabardStatus(){
		String string= db.viewTabardStatus(1);
		assertEquals(string,"Taken");
	}
}
