package com.BGB.BigIssue.controller;

import com.BGB.BigIssue.model.MySQLDatabaseInterface;

public class VendorController {


	MySQLDatabaseInterface storage;
	
	public VendorController(MySQLDatabaseInterface storage){
		this.storage = storage;
	}
	
}
