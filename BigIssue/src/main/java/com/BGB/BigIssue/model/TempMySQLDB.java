package com.BGB.BigIssue.model;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Temporary data structure to allow testing of logging in and user creation.
 * @author guyneedham
 *
 */
public class TempMySQLDB implements StorageInterface {

	HashMap<String,User> map = new HashMap<String,User>();

	HashMap<String,String> users = new HashMap<String, String>();
	public void newUser(User user) {
		//System.out.println("Putting a user");
		map.put(user.getName(),user);
		//System.out.println("map size is "+map.size());

	}

	public User getUser(String userName) {
		if(map.containsKey(userName)){
			//System.out.println("Getting a user");
			User user = map.get(userName);
			//System.out.println("Username is "+user.getName());
			return user;
		} else {
			return null;
		}
	}
	
	public void removeUser(String userName){
		map.remove(userName);
	}
	
	public void changePass(String userName, byte[] password){
		User user = map.get(userName);
		user.setPass(password);
		map.remove(userName);
		map.put(userName, user);
	}


	//not implemented

	public void addVendor(String firstname, String lastname) {
		// TODO Auto-generated method stub

	}

	public void removeVendor(String firstname, String lastname) {
		// TODO Auto-generated method stub

	}

	public void vendorAddsTransaction(int badgeID, int hubLocation,
			int totalBought, DecimalFormat totalCash, Date DOT) {
		// TODO Auto-generated method stub

	}

	public int getVendorIDFromName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addPitchToVendor(int badge, int pitch) {
		// TODO Auto-generated method stub

	}

	public ArrayList listOfUnregisteredPitches() {
		// TODO Auto-generated method stub
		return null;
	}

	public void vendorAddsToSavings(String firstname, String lastname,
			DecimalFormat moneyIn) {
		// TODO Auto-generated method stub

	}

	public void vendorWithdrawsFromSavings(String firstname, String lastname,
			DecimalFormat moneyOut) {
		// TODO Auto-generated method stub

	}

	public ArrayList publishBadgeHistory(int badgeID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList publishVendorHistory(int vendorID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void newStorageUser(String userName, String password) {
		users.put(userName, password);
		
	}

	public void changeStoragePassword(String userName, String pass) {
		users.remove(userName);
		users.put(userName, pass);
		
	}

	





}
