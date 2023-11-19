package Code;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) {
		final String TABLE_NAME = "User";

		try (Connection con = DatabaseConnection.getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME 
										+ "(Username VARCHAR(20) NOT NULL,"
										+ "Firstname VARCHAR(20) NOT NULL,"
										+ "Lastname VARCHAR(20) NOT NULL,"
										+ "Password VARCHAR(20) NOT NULL,"
										+ "PRIMARY KEY (Username))");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
