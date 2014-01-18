package com.BGB.BigIssue.model;

public class User {

	private byte[] salt;
	private byte[] pass;
	private String name;
	private int type;
	
	public byte[] getSalt() {
		return salt;
	}
	
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public byte[] getPass() {
		return pass;
	}
	
	public void setPass(byte[] pass) {
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
