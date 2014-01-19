package com.BGB.BigIssue.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQLDatabase implements StorageInterface {

	private MySQLConnectionPool pool;
	private UserFactory uf;

	public MySQLDatabase(MySQLConnectionPool pool, UserFactory uf){
		this.pool = pool;
		this.uf = uf;
	}

	public void addVendor(String firstname, String lastname) {
		Connection conn = pool.checkOut();

		try {

			CallableStatement stmt = conn.prepareCall("Call AddVendor(?,?)");
			//name salt password
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

	}

	public void removeVendor(String firstname, String lastname) {
		Connection conn = pool.checkOut();

		try {

			CallableStatement stmt = conn.prepareCall("Call RemoveVendor(?,?)");
			//name salt password
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}


	}

	public void vendorAddsTransaction(int badgeID, int hubLocation,
			int totalBought, DecimalFormat totalCash, Date DOT) {
		Connection conn = pool.checkOut();
		try {

			CallableStatement stmt = conn.prepareCall("Call VendorAddsTransaction(?,?,?,?,?)");
			//name salt password
			stmt.setInt(1, badgeID);
			stmt.setInt(2, hubLocation);
			stmt.setInt(3, totalBought);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

	}

	public int getVendorIDFromName(String firstname, String lastname) {
		// TODO Auto-generated method stub Call VendorIDFromNames
		Connection conn = pool.checkOut();
		int vid = 0;
		try {

			CallableStatement stmt = conn.prepareCall(" Call VendorIDFromNames(?,?)");
			//name salt password
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				vid = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
		return vid;
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

	public void testSelect(){
		Connection conn = pool.checkOut();
		String statement = "SELECT * FROM Vendor";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

	}


	public void newUser(User user) {
		Connection conn = pool.checkOut();

		try {

			CallableStatement stmt = conn.prepareCall("CALL NewUser(?,?,?)");
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

	public HashMap<Integer,User> getUser(String userName) {
		Connection conn = pool.checkOut();
		User user = (User) uf.newObject();
		HashMap<Integer, User> hmap = new HashMap<Integer, User>();
		try {

			CallableStatement stmt = conn.prepareCall("Call GetUser(?)");

			//name salt password
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				user.setName(rs.getString(1));
				user.setSalt(rs.getBytes(2));
				user.setPass(rs.getBytes(3));
				if(rs.wasNull()){
					user = null;
				}
				if(user.getName().equals(userName)){
					hmap.put(1, user);
				}
				else{
					hmap.put(0,user);

				}


			}
			return hmap;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
		return hmap;

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
			String statement = "CREATE USER '"+userName+"'@'%' IDENTIFIED BY '"+password+"'";
			//CallableStatement stmt = conn.prepareCall("NewDBUser(?,?)");
			//stmt.setString(1, userName);
			//stmt.setString(2, password);
			Statement stmt = conn.createStatement();
			stmt.execute(statement);

			String statement2 = "GRANT ALL PRIVILEGES ON *.* TO '"+userName+"'@'%'";
			Statement stmt2  = conn.createStatement();
			stmt2.execute(statement2);
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

	public int newBadgeIDForVendor(int vendorID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void badgeIDToBadge(int vendorBadgeID, int badge) {
		// TODO Auto-generated method stub

	}

	public Vendor getVendor(int iD) {
		// TODO Auto-generated method stub
		return null;
	}

	public void assignTabardToVendor(int tabardID, int vendorID) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Tabard> listAvailableTabards() {
		// TODO Auto-generated method stub
		return null;
	}

	public String viewTabardStatus(int tabardID) {
		// TODO Auto-generated method stub
		return null;
	}

}
