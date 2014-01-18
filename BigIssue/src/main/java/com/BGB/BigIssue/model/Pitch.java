package com.BGB.BigIssue.model;

import java.sql.Date;

public class Pitch {

	private int PitchID;
	private String location1;
	private String location2;
	private String location3;
	private Date registeredDate;
	
	public int getPitchID() {
		return PitchID;
	}
	
	public void setPitchID(int pitchID) {
		PitchID = pitchID;
	}
	
	public String getLocation1() {
		return location1;
	}
	
	public void setLocation1(String location1) {
		this.location1 = location1;
	}
	
	public String getLocation2() {
		return location2;
	}
	
	public void setLocation2(String location2) {
		this.location2 = location2;
	}
	
	public String getLocation3() {
		return location3;
	}
	
	public void setLocation3(String location3) {
		this.location3 = location3;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
}
