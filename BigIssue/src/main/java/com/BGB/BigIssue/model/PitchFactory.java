package com.BGB.BigIssue.model;

public class PitchFactory implements AbstractFactory {

	public Object newObject() {
		return new Pitch();
	}

}
