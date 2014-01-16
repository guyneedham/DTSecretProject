package com.BGB.BigIssue.controller;


import com.BGB.BigIssue.model.MySQLDatabaseInterface;

public class BadgeController {


	private MySQLDatabaseInterface storage;
	
	public BadgeController(MySQLDatabaseInterface storage){
		this.storage = storage;
	}
	
	
}
