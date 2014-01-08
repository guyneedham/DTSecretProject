package com.BGB.BigIssue.model;

import java.sql.Connection;
import java.util.concurrent.RecursiveTask;

import org.slf4j.Logger;

public class ConnectionPoolCheckOut extends RecursiveTask<Connection> {

	private Logger logger;
	
	public ConnectionPoolCheckOut(Logger logger){
		this.logger = logger;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Connection compute() {
		Connection conn = null;
		if(MySQLConnectionPool.size.get() > 0){
			logger.debug("Getting a connection from the pool.");
			conn = MySQLConnectionPool.connList.get(0);
			MySQLConnectionPool.size.getAndDecrement();
			logger.debug("Pool size is now {}.",MySQLConnectionPool.size.get());
		}
		else {
			logger.debug("Openning a new SQL connection as pool was empty.");
			conn = MySQLConnectionPool.openConnection();
		}
		return conn;
	}



}
