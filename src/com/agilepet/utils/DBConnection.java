package com.agilepet.utils;
import java.sql.*;

public class DBConnection {


	// Create a variable for the connection string.
	static String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
			"databaseName=model;user=admin;password=340$Uuxwp7Mcxo7Khy";

	// Declare the JDBC objects.
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	public static void connect()
	{

		try {
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			// Create and execute an SQL statement that returns some data.
			String SQL = "SELECT TOP 10 * FROM DATOSMASCOTA";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				System.out.println(rs.getString(4) + " " + rs.getString(6));
			}
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (stmt != null) try { stmt.close(); } catch(Exception e) {}
			if (con != null) try { con.close(); } catch(Exception e) {}
		}
	}
}


