package com.agilepet.utils;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * 
 * @author harold
 *
 */
public class DBConnection {

	// Create a variable for the connection string.
	static String dbConnectionUrl = "jdbc:postgresql://agilepetdb.cbi1v6cha2du.us-east-1.rds.amazonaws.com:5432/AgilepetDB";
	static String dbUser = "agilepet";
	static String dbPassword = "agilepet";

	//Connection attributes
	static Connection con = null;
	static ResultSet rs = null;

	public static ArrayList<String[]> executeQuery(String query) {

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		ArrayList<String[]> result = new ArrayList<String[]>();

		try {

			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbConnectionUrl, dbUser, dbPassword);

			if (connection != null) {
				System.out.println("Query: "+query);
				st = connection.createStatement();
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				
				while (rs.next())
				{
					String[] record = new String[rsmd.getColumnCount()];
		            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
		                    record[i-1] = rs.getString(i);
		            }
		            result.add(record);
				} 

			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();

		}catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
		return result;

	}

}


