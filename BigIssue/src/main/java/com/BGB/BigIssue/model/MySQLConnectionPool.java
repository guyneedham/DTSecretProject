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

	public static MySQLConnectionPool getInstance(String url, String name, String pass, int conns, int threads){
		if(instance != null){
			return instance;
		} else {
			connList = Collections.synchronizedList(new ArrayList<Connection>());
			instance = new MySQLConnectionPool(url, name, pass, conns, threads);
			return instance;
		}
	}

	private MySQLConnectionPool(String url, String name, String pass, int conns, int threads){
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

	}

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


	public Connection checkOut() {
		Connection conn = pool.invoke(new ConnectionPoolCheckOut(logger));
		return conn;
	}

	public void checkIn(Connection conn) {
		pool.invoke(new ConnectionPoolCheckIn(logger, conn));

	}

}
