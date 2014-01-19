package com.BGB.BigIssue.controller;

import java.sql.Date;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.BGB.BigIssue.model.Complaint;
import com.BGB.BigIssue.model.StorageInterface;

/**
 * The complaints controller handles searching for complaints, adding complaints against a vendor at a pitch and banning vendors from pitches.
 * @author guyneedham
 *
 */
public class ComplaintsController {
	
	private StorageInterface storage;
	private Logger logger = LoggerFactory.getLogger(ComplaintsController.class);
	
	public ComplaintsController(StorageInterface storage){
		this.storage = storage;
	}
	
	/**
	 * The searchCompByVendor method searches for all complaints against a vendor.
	 * 
	 * @param vendorID int the ID of the vendor
	 * @return complaints an ArrayList<Complaint>
	 */
	public ArrayList<Complaint> seachCompByVendor(int vendorID){
		logger.info("Searching complaints for vendor {} for user {}.",vendorID,LoginController.userName);
		return storage.searchCompByVendor(vendorID);
	}
	
	/**
	 * The searchCompByPitch method searches for all complaints against a pitch
	 * 
	 * @param pitchID int the ID of the pitch
	 * @return complaints an ArrayList<Complaint>
	 */
	public ArrayList<Complaint> searchCompByPitch(int pitchID){
		logger.info("Searching complaints for pitch {} for user {}.", pitchID, LoginController.userName);
		return storage.searchCompByPitch(pitchID);
	}
	
	/**
	 * The addComplaint method adds a complaint against a vendor at a pitch.
	 * @param pitchID int the ID of the pitch
	 * @param vendorID int the ID of the vendor
	 * @param date Date the date of the complaint
	 * @param complaint String the complaint
	 */
	public void addComplaint(int pitchID, int vendorID, Date date, String complaint){
		logger.info("Adding complaint to pitch {} against vendor {} at date {} for user {}.",pitchID,vendorID,date,LoginController.userName);
		storage.addComplaint(pitchID, vendorID, date, complaint);
	}
	
	/**
	 * The banVendorFromPitch method bans a vendor from a pitch.
	 * 
	 * @param vendor int the vendor
	 * @param pitch int the pitch
	 * @param date Date the date
	 */
	public void banVendorFromPitch(int vendor, int pitch, Date date){
		logger.info("Banning vendor {} from pitch {} at date {} for user {}.",vendor, pitch,date, LoginController.userName);
		storage.banVendorFromPitch(vendor, pitch,date);
	}

}
