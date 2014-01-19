package com.BGB.BigIssue.controller;


import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.BGB.BigIssue.model.StorageInterface;

/**
 * Controls activity for a badge, including creating a new badge, deleting a badge and assigning a vendor to a badge.
 * 
 * @author guyneedham
 *
 */
public class BadgeController {


	private StorageInterface storage;
	private final Logger logger = LoggerFactory.getLogger(BadgeController.class);
	
	
	public BadgeController(StorageInterface storage){
		this.storage = storage;
	}
	/**
	 * The newBadge method creates a new badge entity which can then can have vendors assigned to it.
	 * @param name a String the name of the badge
	 * @param colour a String the colour of the badge
	 * @param start SQL Date the start date
	 * @param end SQL Date the end date
	 */
	public void newBadge(String name, String colour, Date start, Date end){
		logger.info("New badge {} created for user {}.",name,LoginController.userName);
		storage.newBadge(name,colour,start,end);
	}
	
	public int newBadgeIDForVendor(int vendorID){
		int badgeID = storage.newBadgeIDForVendor(vendorID);
		logger.info("Vendor {} was assigned badge ID {} for user {}.",vendorID, badgeID, LoginController.userName);
		return badgeID;
	}
	
	public void badgeIDToBadge(int vendorBadgeID, int badge){
		
		storage.badgeIDToBadge(vendorBadgeID,badge);
	}
	
	
	
}
