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
	private VendorFactory vf;
	private TabardFactory tf;

	public MySQLDatabase(MySQLConnectionPool pool, UserFactory uf, VendorFactory vf, TabardFactory tf){
		this.pool = pool;
		this.uf = uf;
		this.vf = vf;
		this.tf = tf;
	}

	public void addVendor(String firstname, String lastname) {
		Connection conn = pool.checkOut();

		try {

			CallableStatement stmt = conn.prepareCall("Call AddVendor(?,?)");
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

	}

	public void removeVendor(String firstname, String lastname) {
		Connection conn = pool.checkOut();

		try {

			CallableStatement stmt = conn.prepareCall("Call RemoveVendor(?,?)");
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.executeUpdate();
		} catch (SQLException e) {
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
			stmt.setInt(1, badgeID);
			stmt.setInt(2, hubLocation);
			stmt.setInt(3, totalBought);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

	}

	public int getVendorIDFromName(String firstname, String lastname) {
		Connection conn = pool.checkOut();
		int vid = 0;
		try {

			CallableStatement stmt = conn.prepareCall(" Call VendorIDFromNames(?,?)");
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				vid = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
		return vid;
	}

	public void addPitchToVendor(int badge, int pitch) {
		Connection conn = pool.checkOut();
		try {

			CallableStatement stmt = conn.prepareCall(" Call VendorToPitch(?,?)");
			stmt.setInt(1, badge);
			stmt.setInt(2, pitch);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
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
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
		return hmap;

	}

	public void removeUser(String userName) {
		Connection conn = pool.checkOut();
		try {

			CallableStatement stmt = conn.prepareCall("CALL RemoveUser(?)");
			stmt.setString(1, userName);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

	}

	public void changePass(String userName, byte[] password) {
		Connection conn = pool.checkOut();
		try {

			CallableStatement stmt = conn.prepareCall("CALL ChangePassword(?)");
			stmt.setString(1, userName);
			stmt.setBytes(2, password);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

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
		Connection conn = pool.checkOut();
		try {

			CallableStatement stmt = conn.prepareCall("CALL AddBadge(?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, colour);
			stmt.setDate(3, start);
			stmt.setDate(4, end);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
	}

	public int newBadgeIDForVendor(int vendorID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void badgeIDToBadge(int vendorBadgeID, int badge) {
		// TODO Auto-generated method stub

	}

	public Vendor getVendor(int iD) {
		Connection conn = pool.checkOut();
		Vendor vendor = null;

		try {

			CallableStatement stmt = conn.prepareCall("Call GetVendor(?)");
			stmt.setInt(1, iD);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				vendor = vf.newObject();
				vendor.setVendorID(rs.getInt(1));
				vendor.setFirstName(rs.getString(2));
				vendor.setLastName(rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}

		return vendor;
	}

	public void assignTabardToVendor(int tabardID, int vendorID) {
		/*Connection conn = pool.checkOut();
		try {

			CallableStatement stmt = conn.prepareCall("CALL ChangePassword(?)");
			stmt.setString(1, userName);
			stmt.setBytes(2, password);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
		 */ //Write stored proc for this in mysql
	}

	public ArrayList<Tabard> listAvailableTabards() {
		Connection conn = pool.checkOut();
		ArrayList<Tabard> tabards = new ArrayList<Tabard>();
		try {

			CallableStatement stmt = conn.prepareCall("CALL AvailableTabards()");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Tabard tabard = tf.newObject();
				tabard.setID(rs.getInt(1));
				tabard.setStatus(rs.getString(1));

				tabards.add(tabard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
		return tabards;
	}

	public String viewTabardStatus(int tabardID) {
		Connection conn = pool.checkOut();
		String
		try {

			CallableStatement stmt = conn.prepareCall("CALL AvailableTabards()");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Tabard tabard = tf.newObject();
				tabard.setID(rs.getInt(1));
				tabard.setStatus(rs.getString(1));

				tabards.add(tabard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.checkIn(conn);
		}
		return tabards;
	}

}
