package com.BGB.BigIssue.model;

import java.awt.Image;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public interface StorageInterface {

	public void addVendor(String firstname, String lastname);
	public void removeVendor(String firstname, String lastname);
	public void vendorAddsTransaction(int badgeID, int hubLocation, int totalBought, DecimalFormat totalCash, Date DOT);
	public int getVendorIDFromName(String firstname, String lastname);
	public void addPitchToVendor(int badge, int pitch);
	public ArrayList<Pitch> listOfUnregisteredPitches(); //arraylist of pitches
	public void vendorAddsToSavings(String firstname, String lastname, DecimalFormat moneyIn);
	public void vendorWithdrawsFromSavings(String firstname, String lastname, DecimalFormat moneyOut);
	public ArrayList<Pitch> publishBadgeHistory(int badgeID); //arraylist of pitches
	public ArrayList<Badge> publishVendorHistory(int vendorID); //arraylist of badges
	public void newUser(User user);
	public HashMap<Integer, User> getUser(String userName);
	public void removeUser(String userName);
	public void changePass(String userName, byte[] password);
	public void newStorageUser(String userName, String password); //make a database user
	public void changeStoragePassword(String userName, String pass); //change the database password
	public void newBadge(String name, String colour, Date start, Date end);
	public int newBadgeIDForVendor(int vendorID);
	public void badgeIDToBadge(int vendorBadgeID, int badge);
	public Vendor getVendor(int iD);
	public void assignTabardToVendor(int tabardID, int vendorID);
	public ArrayList<Tabard> listAvailableTabards();
	public String viewTabardStatus(int tabardID);
	public void addVendorImage(Image image, int vendorID, Date expiry);
	public void addExpiryToBadge(int badgeID, Date expiry);
	public VendorBadge getVendorBadge(int badgeID);
	public ArrayList<Complaint> searchCompByVendor(int vendorID);
	public ArrayList<Complaint> searchCompByPitch(int pitchID);
	public void addComplaint(int pitchID, int vendorID, Date date,
			String complaint);
	public void banVendorFromPitch(int vendor, int pitch, Date date);
	public double getTotalBoughtForVendor(int vendor);
	public void addPitch(String location1, String location2, String location3);
	public void removePitch(int pitchid);
	public ArrayList<Badge> findBadge(String badgeName);
	public ArrayList<Tabard> publishTabardHistory(int vendorid);

}
