package com.BGB.BigIssue.controller;

import java.util.ArrayList;

import com.BGB.BigIssue.model.MySQLDatabaseInterface;
import com.BGB.BigIssue.model.Pitch;
import com.BGB.BigIssue.model.PitchFactory;

public class PitchController {


	private MySQLDatabaseInterface storage;
	private PitchFactory pf;
	
	public PitchController(MySQLDatabaseInterface storage, PitchFactory pf){
		this.storage = storage;
		this.pf = pf;
	}
	
	public ArrayList<Pitch> showUnregisteredPitches(){
		return storage.listOfUnregisteredPitches();
	}
	
	public void registerPitch(int badge, int pitch){
		storage.addPitchToVendor(badge, pitch);
	}
	
	public ArrayList<Pitch> badgeHistory(int badgeID){
		return storage.publishBadgeHistory(badgeID);
	}
	
}
