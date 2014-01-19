package com.BGB.BigIssue.model;

import java.awt.Image;

public class Vendor {

	private int VendorID;
	private String firstName;
	private String lastName;
	private int savingsTotal;
	private Image profile;
	
	public int getVendorID() {
		return VendorID;
	}
	
	public void setVendorID(int vendorID) {
		VendorID = vendorID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSavingsTotal() {
		return savingsTotal;
	}

	public void setSavingsTotal(int savingsTotal) {
		this.savingsTotal = savingsTotal;
	}

	public Image getProfile() {
		return profile;
	}

	public void setProfile(Image profile) {
		this.profile = profile;
	}
	
}
