package com.BGB.BigIssue.controller;


import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.BGB.BigIssue.model.StorageInterface;
import com.BGB.BigIssue.model.VendorBadge;

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
	
	/**
	 * The newBadgeIDForVendor method creates a badge ID for a 
	 * @param vendorID
	 * @return
	 */
	public int newBadgeIDForVendor(int vendorID){
		int badgeID = storage.newBadgeIDForVendor(vendorID);
		logger.info("Vendor {} was assigned badge ID {} for user {}.",vendorID, badgeID, LoginController.userName);
		return badgeID;
	}
	
	/**
	 * The badgeIDToBadge method adds a specific badge ID to a type of badge.
	 * @param vendorBadgeID int the badge ID of the vendor
	 * @param badge int the ID of the badge type
	 */
	public void badgeIDToBadge(int vendorBadgeID, int badge){
		logger.info("Registering vendor with badge ID {} to badge {} for user {}.",vendorBadgeID,badge,LoginController.userName);
		storage.badgeIDToBadge(vendorBadgeID,badge);
	}
	
	
	/**
	 * The getVendorBadge method returns the vendor specific badge with a known badge ID.
	 * 
	 * @param badgeID int the ID of the badge
	 * @return badge a VendorBadge
	 */
	public VendorBadge getVendorBadge(int badgeID){
		logger.info("Getting the vendor specific badge with ID {} for user {}.",badgeID,LoginController.userName);
		return storage.getVendorBadge(badgeID);
	}
	
}
