package com.BGB.BigIssue.controller;

import java.util.ArrayList;

import com.BGB.BigIssue.model.MySQLDatabaseInterface;
import com.BGB.BigIssue.model.Pitch;
import com.BGB.BigIssue.model.PitchFactory;

public class PitchController {


	MySQLDatabaseInterface storage;
	PitchFactory pf;
	
	public PitchController(MySQLDatabaseInterface storage, PitchFactory pf){
		this.storage = storage;
		this.pf = pf;
	}
	
	public ArrayList<Pitch> showUnregisteredPitches(){
		return storage.listOfUnregisteredPitches();
	}
	
	
	
}
