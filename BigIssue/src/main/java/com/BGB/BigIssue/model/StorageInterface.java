package com.BGB.BigIssue.model;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface StorageInterface {

	public void addVendor(String firstname, String lastname);
	public void removeVendor(String firstname, String lastname);
	public void vendorAddsTransaction(int venderID, int hubLocation, int totalBought, float totalCash, Date DOT);
	public int getVendorIDFromName(String firstname, String lastname);
	public void addPitchToVendor(int badge, int pitch);
	public ArrayList<Pitch> listOfUnregisteredPitches();
	public void vendorAddsToSavings(String firstname, String lastname, float moneyIn); // 
	public void vendorWithdrawsFromSavings(String firstname, String lastname, float moneyOut);
	public ArrayList<Pitch> publishBadgeHistory(int badgeID);
	public ArrayList<Badge> publishVendorHistory(int vendorID);
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
	public VendorBadge getVendorBadge(int badgeID);
	public ArrayList<Complaint> searchCompByVendor(int vendorID);
	public ArrayList<Complaint> searchCompByPitch(int pitchID);
	public void addComplaint(int vendorID, int pitchID, Date date, String complaint);
	public void banVendorFromPitch(int vendor, int pitch, Date date); 
	public ArrayList<Pitch> viewVendorsBannedPitches(int vendorID);
	public int getTotalBoughtForVendor(int vendor);
	public void addPitch(String location1, String location2, String location3);
	public void removePitch(int pitchid);
	public ArrayList<Badge> findBadge(String badgeName);
	public ArrayList<Tabard> publishTabardHistory(int vendorid);
	public float getVendorSavings(int vendorid); 
	public void removeComplaint(int complaintID);
	public void unbanVendorFromPitch(int vendorid, int pitchid);
	public void removeBadge(String badgeName);

}
