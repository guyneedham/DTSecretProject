package com.BGB.BigIssue.controller;

import java.awt.Image;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.BGB.BigIssue.model.Badge;
import com.BGB.BigIssue.model.StorageInterface;
import com.BGB.BigIssue.model.Vendor;
import com.BGB.BigIssue.model.VendorFactory;
/**
 * 
 * @author guyneedham
 *
 */
public class VendorController {


	private StorageInterface storage;
	private VendorFactory vf;
	
	public VendorController(StorageInterface storage, VendorFactory vf){
		this.storage = storage;
		this.vf = vf;
	}
	
	public void addVendor(String firstName, String lastName){
		storage.addVendor(firstName, lastName);
	}
	
	public void removeVendor(Vendor v){
		storage.removeVendor(v.getFirstName(), v.getLastName());
	}
	
	public void vendorTransaction(int ID, int hubLocation, int totalBought, DecimalFormat totalCash, Date DOT){
		storage.vendorAddsTransaction(ID, hubLocation, totalBought, totalCash, DOT);
	}
	
	public int getVendorIDFromName(String firstName, String lastName){
		return storage.getVendorIDFromName(firstName, lastName);
	}
	
	public void vendorAddsToSavings(String firstname, String lastname, DecimalFormat moneyIn){
		storage.vendorAddsToSavings(firstname, lastname, moneyIn);
	}
	
	public void vendorWithdrawsFromSavings(String firstname, String lastname, DecimalFormat moneyOut){
		storage.vendorWithdrawsFromSavings(firstname, lastname, moneyOut);
	}
	
	public ArrayList<Badge> publishVendorHistory(int vendorID){
		return storage.publishVendorHistory(vendorID);
	}
	
	public Vendor getVendor(int ID){
		return storage.getVendor(ID);
	}
	public void addImage(Image image, int vendorID, Date expiry){
		storage.addVendorImage(image,vendorID,expiry);
	}
	
}
