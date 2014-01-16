package com.BGB.BigIssue.controller;

import com.BGB.BigIssue.model.MySQLDatabaseInterface;

public class BadgeController {


	MySQLDatabaseInterface storage;
	
	public BadgeController(MySQLDatabaseInterface storage){
		this.storage = storage;
	}
	
}
