package com.BGB.BigIssue.model;

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
		
	}

}