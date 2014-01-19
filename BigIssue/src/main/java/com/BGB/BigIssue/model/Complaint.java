package com.BGB.BigIssue.model;

import java.sql.Date;

public class Complaint {

	private int complaintID;
	private int vendorID;
	private Date compDate;
	private int pitchID;
	private String complaint;
	
	public int getComplaintID() {
		return complaintID;
	}
	
	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	public Date getCompDate() {
		return compDate;
	}

	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}

	public int getPitchID() {
		return pitchID;
	}

	public void setPitchID(int pitchID) {
		this.pitchID = pitchID;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	
	
}
