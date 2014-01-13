package com.BGB.BigIssue.model;

public class UserFactory implements AbstractFactory {

	public Object newObject() {
		User user = new User();
		return user;
	}

	
	
}
