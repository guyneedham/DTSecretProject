package com.BGB.BigIssue.model;

public class UserFactory implements AbstractFactory {

	public User newObject() {
		User user = new User();
		return user;
	}

	
	
}
