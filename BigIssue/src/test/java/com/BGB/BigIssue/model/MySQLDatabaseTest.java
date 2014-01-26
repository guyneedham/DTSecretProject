package com.BGB.BigIssue.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;

import javax.print.attribute.Size2DSyntax;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.BGB.BigIssue.controller.UserController;

public class MySQLDatabaseTest {

	private static UserController uc;
	private static SHA1Encryption encryptor;
	private static StorageInterface storage;
	private static MySQLDatabase db;
	private static MySQLConnectionPool pool;
	private static ConnectionSettings settings;
	private static UserFactory uf;
	private static ComplaintsFactory cf;
	private static PitchFactory pf;
	private static BadgeFactory bf;
	private static TabardFactory tf;
	private static VendorFactory vf;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		settings = new ConnectionSettings();
		pool = MySQLConnectionPool.getInstance(settings.getServ(),settings.getName(),settings.getPass(),10,1);
		db = new MySQLDatabase(pool, uf, vf, tf, cf, pf, bf);
		uf = new UserFactory();
		encryptor = new SHA1Encryption();
		uc = new UserController(uf,db,encryptor);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}
	
	@Test
	public void testAddVendor(){
		String firstname = "BD";
		String lastname = "Test";
		db.addVendor(firstname, lastname);
		int vendor = db.getVendorIDFromName(firstname, lastname);
		db.removeVendor(firstname, lastname);
		assertNotNull(vendor);
	}
	
	@Test
	public void testRemoveVendor(){
		String firstname = "BD";
		String lastname = "Test";
		db.addVendor(firstname, lastname);
		db.removeVendor(firstname, lastname);
		int vendor = db.getVendorIDFromName(firstname, lastname);
		assertEquals(vendor,0);
	}
	
	@Test
	public void testVendorAddsTransaction(){
		db.vendorAddsTransaction(5, 1, 1, Float.valueOf("2.5"), Date.valueOf("2013-01-01"));
		float total = db.getTotalBoughtForVendor(5);
		assertNotEquals(total,Float.valueOf("0.0"));
	}
	
	@Test
	public void testAddPitchToVendor(){
		int badge = 5;
		int pitch = 4;
		db.addPitchToVendor(badge, pitch);
		ArrayList<Pitch> pitches = db.publishBadgeHistory(badge);
		int pitchid = pitches.get(0).getPitchID();
		assertEquals(pitch, pitchid);
	}
	
	@Test
	public void testListOfUnregisteredPitches(){
		ArrayList<Pitch> pitches = db.listOfUnregisteredPitches();
		assertNotEquals(pitches.size(),0);
		assertEquals(true, pitches instanceof ArrayList<?>);
	}
	
	@Test
	public void testVendorAddsToSavings(){
		String firstname = "Test";
		String lastname = "Test";
		float moneyIn = ((float) 1.0);
		db.vendorAddsToSavings(firstname, lastname, moneyIn);
		float floaty = db.getVendorSavings(5);
		assertNotEquals(floaty, (float) 0);
	}

	@Test
	public void testVendorWithdrawsFromSavings(){
		String firstname = "Test";
		String lastname = "Test";
		float moneyOut = ((float) 1.0);
		float before = db.getVendorSavings(5);
		db.vendorWithdrawsFromSavings(firstname, lastname, moneyOut);
		float after = db.getVendorSavings(5);
		assertNotEquals(before,after);
		db.vendorAddsToSavings(firstname, lastname, moneyOut);
	}
	
	@Test
	public void testPublishBadgeHistory(){
		ArrayList<Pitch> pitches = db.publishBadgeHistory(5);
		assertEquals(true,pitches instanceof ArrayList<?>);
	}

	@Test
	public void testPublishVendorHistory(){
		ArrayList<Badge> badges = db.publishVendorHistory(5);
		assertEquals(true, badges instanceof ArrayList<?>);
	}

	@Test
	public void testNewBadge(){
		db.newBadge("Test", "Test", Date.valueOf("2014-01-01"), Date.valueOf("2014-01-01"));
	}
	
	@Test
	public void testGetNewBadgeIDForVendor(){
		int badges = db.newBadgeIDForVendor(5);
		assertNotEquals(badges,0);
	}
	
	@Test
	public void testBadgeIDToBadge(){
		int before = db.publishVendorHistory(5).size();
		db.badgeIDToBadge(db.newBadgeIDForVendor(5), 1);
		int after = db.publishVendorHistory(5).size();
		assertNotEquals(before,after);
		
	}
	
	@Test
	public void testGetVendor(){
		Vendor vendor = db.getVendor(5);
		assertEquals(vendor.getVendorID(),5);
	}
	
	@Test
	public void testAssignTabardToVendor(){
		db.assignTabardToVendor(2, 5);
		String status = db.viewTabardStatus(2);
		assertEquals(status,"Assigned");
	}
	
	/*@Test
	public void newUser(){
		uc.newUser("Ben", "Password");
	}*/
	
	@Test
	public void testSearchComplaintByVendor(){
		db.addComplaint(5, 4, Date.valueOf("2014-01-01"), "Kicked my dog");
		ArrayList<Complaint> complaints = db.searchCompByVendor(5);
		db.removeComplaint(complaints.get(0).getComplaintID());
		assertNotEquals(complaints.size(),0);
	}
	
	@Test
	public void testSearchComplaintByPitch(){
		db.addComplaint(5, 4, Date.valueOf("2014-01-01"), "Called my nan a bell");
		ArrayList<Complaint> complaints = db.searchCompByPitch(4);
		assertEquals(complaints.get(0).getPitchID(),4);
		db.removeComplaint(complaints.get(0).getComplaintID());
	}
	
	@Test
	public void testBanVendorFromPitchAndViewBannedPitches(){
		db.banVendorFromPitch(5, 4, Date.valueOf("2014-01-01"));
		ArrayList<Pitch> pitches = db.viewVendorsBannedPitches(5);
		db.unbanVendorFromPitch(5, 4);
		assertEquals(pitches.get(0).getPitchID(),4);
	}
	
	@Test
	public void testGetTotalBoughtForVendor(){
		db.vendorAddsTransaction(5, 1, 1, (float) 6, Date.valueOf("2014-01-01"));
		int total = db.getTotalBoughtForVendor(5);
		assertNotEquals(total,0);
	}
	
	@Test
	public void testAddPitch(){
		int before = db.listOfUnregisteredPitches().size();
		db.addPitch("North London", "Islington", "M&S");
		int after = db.listOfUnregisteredPitches().size();
		assertNotEquals(before,after);
	}
	
	@Test
	public void testFindBadge(){
		db.newBadge("BadgeTest", "Maroon", Date.valueOf("2014-01-01"), Date.valueOf("2014-01-02"));
		ArrayList<Badge> badges = db.findBadge("BadgeTest");
		db.removeBadge("BadgeTest");
		assertEquals(badges.size(),1);
	}
	
	@Test
	public void testPublishTabardHistory(){
		db.assignTabardToVendor(1, 5);
		ArrayList<Tabard> tabards = db.publishTabardHistory(5);
		assertEquals(tabards.get(0).getID(),1);
		
	}
	
	@Test
	public void testGetVendorSavings(){
		db.vendorAddsToSavings("Test", "Test", (float) 1);
		float total = db.getVendorSavings(5);
		assertEquals((float) 1, total,1);
		db.vendorWithdrawsFromSavings("Test", "Test", (float) 1);
	}
}