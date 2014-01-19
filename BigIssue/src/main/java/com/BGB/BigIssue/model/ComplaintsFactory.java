package com.BGB.BigIssue.model;

public class ComplaintsFactory implements AbstractFactory {

	public Complaint newObject() {
		return new Complaint();
	}

}
