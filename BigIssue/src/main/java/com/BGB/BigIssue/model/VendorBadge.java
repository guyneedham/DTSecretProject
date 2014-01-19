package com.BGB.BigIssue.model;

import java.awt.Image;
import java.sql.Date;

public class VendorBadge {

	private Image profilePic;
	private Date expire;
	private int badgeID;
	
	public Image getProfilePic() {
		return profilePic;
	}
	
	public void setProfilePic(Image profilePic) {
		this.profilePic = profilePic;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public int getBadgeID() {
		return badgeID;
	}

	public void setBadgeID(int badgeID) {
		this.badgeID = badgeID;
	}
	
}
