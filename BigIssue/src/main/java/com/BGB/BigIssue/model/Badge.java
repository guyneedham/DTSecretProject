package com.BGB.BigIssue.model;

import java.sql.Date;

public class Badge {

	private int badgeID;
	private String name;
	private String colour;
	private Date start;
	private Date end;
	
	public int getBadgeID() {
		return badgeID;
	}
	
	public void setBadgeID(int badgeID) {
		this.badgeID = badgeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
