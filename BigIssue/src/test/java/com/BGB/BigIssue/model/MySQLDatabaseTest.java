package com.BGB.BigIssue.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MySQLDatabaseTest {

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
		pool = MySQLConnectionPool.getInstance(settings.getServ(),settings.getName(),settings.getPass(),1,1);
		db = new MySQLDatabase(pool, uf, vf, tf, cf, pf, bf);
		uf = new UserFactory();
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
		System.out.println(vendor);
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
		assertNotEquals(db.getTotalBoughtForVendor(5),Float.valueOf("0.0"));
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
	}
	
	@Test
	public void testVendorAddsToSavings(){
		
	}

}