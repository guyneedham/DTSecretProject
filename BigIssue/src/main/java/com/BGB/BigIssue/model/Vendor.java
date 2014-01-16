package com.BGB.BigIssue.model;

public class Vendor {

	private int VendorID;
	private String firstName;
	private String lastName;
	
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
	
}
