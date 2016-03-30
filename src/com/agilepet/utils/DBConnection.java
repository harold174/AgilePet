package com.agilepet.utils;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author harold
 *
 */
public class DBConnection {


	// Create a variable for the connection string.
	static String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
			"databaseName=model;user=admin;password=340$Uuxwp7Mcxo7Khy";

	// Declare the JDBC objects.
	static Connection con = null;
	//static Statement stmt = null;
	static ResultSet rs = null;

	public static void main(String[] args) {


		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			Class.forName("org.postgresql.Driver");
			System.out.println("PostgreSQL JDBC Driver Registered!");

			connection = DriverManager.getConnection(
					"jdbc:postgresql://agilepetdb.cbi1v6cha2du.us-east-1.rds.amazonaws.com:5432/AgilepetDB", "agilepet",
					"agilepet");

			if (connection != null) {
				System.out.println("You made it, take control your database now!");

				st = connection.createStatement();
				rs = st.executeQuery("SELECT * FROM mascota m " +
						"join zona_segura_mascota z on m.id = z.id_mascota " +
						"where serial_collar=1080056328 " +
						"and (3.59 between z.coordenada_x2 and z.coordenada_x1 or 3.59 between z.coordenada_x1 and z.coordenada_x2)" +
						"and (-74 between coordenada_y1 and coordenada_y2 or -74 between coordenada_y2 and coordenada_y1)");
				while (rs.next())
				{
					System.out.print("Column 1 returned ");
					System.out.println(rs.getString(2));
				} 

			} else {
				System.out.println("Failed to make connection!");
			}



		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

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







	}
}


