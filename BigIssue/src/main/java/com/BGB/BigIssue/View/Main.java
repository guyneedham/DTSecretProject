package com.BGB.BigIssue.View;

import com.BGB.BigIssue.model.ConnectionSettings;
import com.BGB.BigIssue.model.MySQLConnectionPool;

public class Main {
	
	public static void main(String[] args){
		ConnectionSettings conSettings = new ConnectionSettings();
		MySQLConnectionPool conPool = MySQLConnectionPool.getInstance(conSettings.getServ(),conSettings.getName(),conSettings.getPass(),1,1);
	}

}
