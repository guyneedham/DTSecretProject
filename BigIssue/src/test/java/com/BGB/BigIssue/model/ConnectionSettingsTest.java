package com.BGB.BigIssue.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectionSettingsTest {

	@Test
	public void testReaderGetsDetails() {
		ConnectionSettings reader = new ConnectionSettings();
		String url = reader.getServ();
		String name = reader.getName();
		String pass = reader.getPass();
		assertEquals(true, url.equals("jdbc:mysql://86.28.169.115:3306/Big_Issue_Schema?"));
		assertEquals(true, name.equals("Guy"));
		assertEquals(true, pass.equals("BigIssue"));
	}

}
