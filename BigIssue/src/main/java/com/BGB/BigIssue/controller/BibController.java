package com.BGB.BigIssue.controller;

import com.BGB.BigIssue.model.MySQLDatabaseInterface;

public class BibController {

	private MySQLDatabaseInterface storage;
	
	public BibController(MySQLDatabaseInterface storage){
		this.storage = storage;
	}
	
}
