package com.BGB.BigIssue.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.BGB.BigIssue.model.StorageInterface;
import com.BGB.BigIssue.model.Tabard;

/**
 * The BibController controls access to vendor bibs.
 * It is responsible for assigning bibs to vendors, listing the available bibs, viewing bib status, and paying for bibs.
 * @author guyneedham
 *
 */
public class TabardController {

	private StorageInterface storage;
	private final Logger logger = LoggerFactory.getLogger(TabardController.class);
	public TabardController(StorageInterface storage){
		this.storage = storage;
	}
	
	/**
	 * The assignTabardToVendor method assigns a tabard to a vendor.
	 * @param tabardID int the ID of the tabard
	 * @param vendorID int the ID of the vendor
	 */
	public void assignTabardToVendor(int tabardID, int vendorID){
		storage.assignTabardToVendor(tabardID,vendorID);
		logger.info("Assigning tabard {} to vendor {} for user {}.",tabardID,vendorID,LoginController.userName);
	}
	
	/**
	 * The listAvailableTabards method lists all tabards which can be assigned to a vendor.
	 * @return tabards an ArrayList<Tabard>
	 */
	public ArrayList<Tabard> listAvailableTabards(){
		logger.info("Listing all available tabards for user {}.",LoginController.userName);
		return storage.listAvailableTabards();
	}
	
	/**
	 * The viewTabardStatus shows the status of a specific tabard.
	 * @param tabardID int the ID of the tabard
	 * @return status a String
	 */
	public String viewTabardStatus(int tabardID){
		logger.info("Viewing tabard {} status for user {}.",tabardID,LoginController.userName);
		return storage.viewTabardStatus(tabardID);
	}
	
}
