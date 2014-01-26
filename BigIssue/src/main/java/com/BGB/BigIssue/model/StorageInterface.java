package com.BGB.BigIssue.model;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface StorageInterface {

	public void addVendor(String firstname, String lastname); //tested
	public void removeVendor(String firstname, String lastname); //tested
	public void vendorAddsTransaction(int venderID, int hubLocation, int totalBought, float totalCash, Date DOT); //tested
	public int getVendorIDFromName(String firstname, String lastname); //tested
	public void addPitchToVendor(int badge, int pitch); //tested
	public ArrayList<Pitch> listOfUnregisteredPitches(); //tested
	public void vendorAddsToSavings(String firstname, String lastname, float moneyIn); //tested
	public void vendorWithdrawsFromSavings(String firstname, String lastname, float moneyOut); //tested
	public ArrayList<Pitch> publishBadgeHistory(int badgeID);  //tested
	public ArrayList<Badge> publishVendorHistory(int vendorID); //tested
	public void newUser(User user);
	public HashMap<Integer, User> getUser(String userName);
	public void removeUser(String userName);
	public void changePass(String userName, byte[] password);
	public void newStorageUser(String userName, String password); //make a database user
	public void changeStoragePassword(String userName, String pass); //change the database password
	public void newBadge(String name, String colour, Date start, Date end); //tested
	public int newBadgeIDForVendor(int vendorID); //tested
	public void badgeIDToBadge(int vendorBadgeID, int badge); //tested
	public Vendor getVendor(int iD); //tested
	public void assignTabardToVendor(int tabardID, int vendorID); //tested
	public ArrayList<Tabard> listAvailableTabards();
	public String viewTabardStatus(int tabardID); //tested
	public void addVendorImage(Image image, int vendorID, Date expiry);
	public VendorBadge getVendorBadge(int badgeID);
	public ArrayList<Complaint> searchCompByVendor(int vendorID); //tested
	public ArrayList<Complaint> searchCompByPitch(int pitchID); //tested
	public void addComplaint(int pitchID, int vendorID, Date date,
			String complaint); //tested
	public void banVendorFromPitch(int vendor, int pitch, Date date); //tested
	public ArrayList<Pitch> viewVendorsBannedPitches(int vendorID); //tested
	public int getTotalBoughtForVendor(int vendor); //tested
	public void addPitch(String location1, String location2, String location3);
	public void removePitch(int pitchid); //tested
	public ArrayList<Badge> findBadge(String badgeName); //tested
	public ArrayList<Tabard> publishTabardHistory(int vendorid); //tested
	public float getVendorSavings(int vendorid); //tested
	public void removeComplaint(int complaintID);
	public void unbanVendorFromPitch(int vendorid, int pitchid);
	public void removeBadge(String badgeName);

}
