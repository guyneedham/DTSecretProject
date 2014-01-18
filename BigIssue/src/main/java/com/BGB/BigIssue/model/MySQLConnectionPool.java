package com.BGB.BigIssue.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Connection pool to MySQL databases.
 * @author guyneedham
 *
 */
public class MySQLConnectionPool extends ConnectionPool {

	public static MySQLConnectionPool instance;
	public static List<Connection> connList;
	final static Logger logger = LoggerFactory.getLogger(MySQLConnectionPool.class);

	private static String url;
	private static String name;
	private static String pass;
	
	public static int conns;
	
	public static ForkJoinPool pool;
	public static AtomicInteger size;

	/**
	 * Public method to get an instance of a MySQLConnectionPool.
	 * If there is no instance, a new pool is made.
	 * If the username or password supplied are different to those of the current instance, a new instance is created.
	 * 
	 * @param url a String the database URL
	 * @param uname a String the user name
	 * @param upass a String the password
	 * @param conns int the number of connections to use
	 * @param threads int the number of threads to use
	 * @return instance of a MySQLConnectionPool
	 */
	public static MySQLConnectionPool getInstance(String url, String uname, String upass, int conns, int threads){
		if(instance != null){
			if(name.equals(uname) && pass.equals(upass)){
				return instance;
			} else {
				connList = Collections.synchronizedList(new ArrayList<Connection>());
				instance = new MySQLConnectionPool(url, uname, upass, conns, threads);
				return instance;
			}
			
		} else {
			connList = Collections.synchronizedList(new ArrayList<Connection>());
			instance = new MySQLConnectionPool(url, uname, upass, conns, threads);
			return instance;
		}
	}

	/**
	 * Private constructor to create a new instance of the connection pool.
	 * 
	 * @param url a String the database URL
	 * @param uname a String the user name
	 * @param upass a String the password
	 * @param conns int the number of connections to use
	 * @param threads int the number of threads to use
	 * 
	 */
	private MySQLConnectionPool(String url, String name, String pass, int conns, int threads){
		logger.debug("Making a new connection pool {}.",this.hashCode());
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Class not found exception when getting MySQL driver.");
			e.printStackTrace();
		}

		MySQLConnectionPool.url = url;
		MySQLConnectionPool.name = name;
		MySQLConnectionPool.pass = pass;

		for(int i = 0; i<conns;i++){
			connList.add(openConnection());
		}

		pool = new ForkJoinPool(threads);
		size = new AtomicInteger(conns);
		MySQLConnectionPool.conns = conns;

		Runtime.getRuntime().addShutdownHook(new Thread() { //if there is an unexpected shutdown, all connections will close
			@Override
			public void run() {
				logger.info("Connection pool {} closing.",this.hashCode());
				logger.trace("Running Shutdown Hook");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					logger.error("Interrupted exception.");
				}
			}
		});
		
	}

	/**
	 * Method to open a database connection.
	 * 
	 * @return conn a MySQL database connection
	 */
	public static synchronized Connection openConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url+
					"user="+name+"&password="+pass);
		} catch (SQLException e) {
			logger.error("SQL Exception creating a new connection.");
			e.printStackTrace();
		}
		return conn;
	}


	/**
	 * The checkOut method checks a connection out of the pool.
	 * 
	 * @return conn a MySQL database connection.
	 */
	public Connection checkOut() {
		Connection conn = pool.invoke(new ConnectionPoolCheckOut(logger));
		return conn;
	}

	/**
	 * The checkIn method checks a connection into the pool.
	 * 
	 * @param conn a MySQL database connection
	 */
	public void checkIn(Connection conn) {
		pool.invoke(new ConnectionPoolCheckIn(logger, conn));

	}

}
