package com.BGB.BigIssue.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MySQLDatabase implements StorageInterface {

	private MySQLConnectionPool pool;

	
	public MySQLDatabase(MySQLConnectionPool pool){
		this.pool = pool;
	}
	
	public void addVendor(String firstname, String lastname) {
		// TODO Auto-generated method stub

	}

	public void removeVendor(String firstname, String lastname) {
		// TODO Auto-generated method stub

	}

	public void vendorAddsTransaction(int badgeID, int hubLocation,
			int totalBought, DecimalFormat totalCash, Date DOT) {
		// TODO Auto-generated method stub

	}

	public int getVendorIDFromName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addPitchToVendor(int badge, int pitch) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Pitch> listOfUnregisteredPitches() {
		// TODO Auto-generated method stub
		return null;
	}

	public void vendorAddsToSavings(String firstname, String lastname,
			DecimalFormat moneyIn) {
		// TODO Auto-generated method stub

	}

	public void vendorWithdrawsFromSavings(String firstname, String lastname,
			DecimalFormat moneyOut) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Pitch> publishBadgeHistory(int badgeID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Badge> publishVendorHistory(int vendorID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void newUser(User user) {
		Connection conn = pool.checkOut();
		
		try {
			CallableStatement stmt = conn.prepareCall("NewUser(?,?,?)");
			//name salt password
			stmt.setString(1, user.getName());
			stmt.setBytes(2, user.getSalt());
			stmt.setBytes(3, user.getPass());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
	}

	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeUser(String userName) {
		// TODO Auto-generated method stub

	}

	public void changePass(String userName, byte[] password) {
		// TODO Auto-generated method stub

	}

	public void newStorageUser(String userName, String password) {
		
		Connection conn = pool.checkOut();
		try {
			CallableStatement stmt = conn.prepareCall("NewDBUser(?,?)");
			stmt.setString(1, userName);
			stmt.setString(2, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

	}

	public void changeStoragePassword(String userName, String pass) {
		// TODO Auto-generated method stub

	}

	public void newBadge(String name, String colour, Date start, Date end) {
		// TODO Auto-generated method stub

	}

}