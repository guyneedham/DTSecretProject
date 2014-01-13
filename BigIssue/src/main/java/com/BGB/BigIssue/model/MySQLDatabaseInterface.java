package com.BGB.BigIssue.model;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

public interface MySQLDatabaseInterface {

	public void addVendor(String firstname, String lastname);
	public void removeVendor(String firstname, String lastname);
	public void vendorAddsTransaction(int badgeID, int hubLocation, int totalBought, DecimalFormat totalCash, Date DOT);
	public int getVendorIDFromName(String firstname, String lastname);
	public void addPitchToVendor(int badge, int pitch);
	public ArrayList listOfUnregisteredPitches(); //arraylist of pitches
	public void vendorAddsToSavings(String firstname, String lastname, DecimalFormat moneyIn);
	public void vendorWithdrawsFromSavings(String firstname, String lastname, DecimalFormat moneyOut);
	public ArrayList publishBadgeHistory(int badgeID); //arraylist of pitches
	public ArrayList publishVendorHistory(int vendorID); //arraylist of badges
	public void newUser(User user);
	public User getUser(String userName);
	public void removeUser(String userName);
	public void changePass(String userName, byte[] password);

}
