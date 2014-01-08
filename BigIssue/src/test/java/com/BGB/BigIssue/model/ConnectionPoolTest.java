package com.BGB.BigIssue.model;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionPoolTest {

	private MySQLConnectionPool pool;
	
	@Before
	public void setUp(){
		ConnectionSettings settings = new ConnectionSettings();
		pool = MySQLConnectionPool.getInstance(settings.getServ(),settings.getName(),settings.getPass(),5,1);
	}
	
	@Test
	public void testConnectionPoolCheckoutMethodReturnsAConnection() {
		int size = MySQLConnectionPool.size.get();
		Connection conn = pool.checkOut();
		assertEquals(true, conn instanceof Connection);
		assertEquals(size-1,MySQLConnectionPool.size.get());
	}
	
	@Test
	public void testConnectionPoolCheckInMethodReturnsAConnectionToThePool(){
		Connection conn = pool.checkOut();
		int size = MySQLConnectionPool.size.get();
		pool.checkIn(conn);
		int endSize = MySQLConnectionPool.size.get();
		assertEquals(endSize,size+1);
	}
	
	@Test
	public void testConnectionPoolCheckoutsOutMoreConnectionsThanContains(){
		int size = MySQLConnectionPool.size.get();
		Connection conn=null;
		for(int i = size; i >= 0; i--){
			conn = pool.checkOut();
		}
		assertEquals(true, conn instanceof Connection);
	}
	
	@Test
	public void testConnectionPoolCheckInMethodWillCloseExcessConnections(){
		while(MySQLConnectionPool.size.get() < MySQLConnectionPool.conns){
			Connection conn = MySQLConnectionPool.openConnection();
			pool.checkIn(conn);
		}
		int size = MySQLConnectionPool.size.get();
		Connection conn = MySQLConnectionPool.openConnection();
		pool.checkIn(conn);
		assertEquals(size,MySQLConnectionPool.size.get());
	}

}
