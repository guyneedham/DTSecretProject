package com.BGB.BigIssue.model;

import java.sql.Connection;

public abstract class ConnectionPool {

	private static ConnectionPool instance;
	
	public static ConnectionPool getInstance(String url, String name, String pass, int conns, int threads){
		return instance;
	}
	
	public synchronized Connection getConnection(){
		Connection conn = null;
		return conn;
	}
	
	public Connection checkOut(){
		Connection conn = null;
		return conn;
	}
	
	public void checkIn(Connection conn){
		
	}
	
}
