package Code;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertRow {
	public static void main(String[] args) {
		final String TABLE_NAME = "Post";

		try (Connection con = DatabaseConnection.getConnection();
				Statement stmt = con.createStatement();) {
			String query = "INSERT INTO " + TABLE_NAME +
					" VALUES (20582, 'Come and meet us at Building 14 of RMIT', 'SD2C45', 10, 24, '12/05/2023 10:10')";

			int result = stmt.executeUpdate(query);

			if (result == 1) {
				System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
				System.out.println(result + " row(s) affected");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

