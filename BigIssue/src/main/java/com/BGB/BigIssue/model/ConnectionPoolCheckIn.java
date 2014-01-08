package com.BGB.BigIssue.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.RecursiveAction;

import org.slf4j.Logger;

public class ConnectionPoolCheckIn extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger;
	private Connection conn;
	
	public ConnectionPoolCheckIn(Logger logger, Connection conn){
		this.logger = logger;
		this.conn = conn;
	}
	
	@Override
	protected void compute() {
		if(MySQLConnectionPool.size.get() < MySQLConnectionPool.conns){
			logger.debug("Checking in connection.");
			MySQLConnectionPool.connList.add(conn);
			MySQLConnectionPool.size.getAndIncrement();
			logger.debug("Pool size is now {}.",MySQLConnectionPool.size.get());
		} else{
			logger.debug("Pool is full, closing excess connection.");
			try {
				conn.close();
			} catch (SQLException e) {
				logger.debug("SQL Exception in closing database connection.");
				e.printStackTrace();
			}
		}
		
	}



}
