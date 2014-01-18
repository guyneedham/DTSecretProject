package com.BGB.BigIssue.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.BGB.BigIssue.model.StorageInterface;
import com.BGB.BigIssue.model.Pitch;
import com.BGB.BigIssue.model.PitchFactory;
/**
 * The PitchController class is responsible for passing data from the view to the model.
 * It is used to show unregistered vendor pitches, register pitches and show the badge history of a user.
 * 
 * @author guyneedham
 *
 */
public class PitchController {

	
	private final Logger logger = LoggerFactory.getLogger(PitchController.class);

	private StorageInterface storage;
	
	private String userName = UserController.userName;
	
	/**
	 * Public constructor for the PitchController.
	 * @param storage a storage object of type StorageInterface
	 */
	public PitchController(StorageInterface storage){
		this.storage = storage;
	}
	
	public ArrayList<Pitch> showUnregisteredPitches(){
		logger.info("Getting unregistered pitches for user {}.",userName);
		return storage.listOfUnregisteredPitches();
	}
	
	public void registerPitch(int badge, int pitch){
		logger.info("Registering pitch {} to vendor {} for user {}.",pitch,badge,userName);
		storage.addPitchToVendor(badge, pitch);
	}
	
	public ArrayList<Pitch> badgeHistory(int badgeID){
		logger.info("Getting pitch history for badge {} for user {}.",badgeID,userName);
		return storage.publishBadgeHistory(badgeID);
	}
	
}
