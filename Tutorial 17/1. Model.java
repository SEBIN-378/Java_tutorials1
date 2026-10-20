DATABASE SETUP


  CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

CREATE TABLE counter (
    visitor_count INT DEFAULT 0
);

-- Insert the initial record if empty
INSERT INTO counter (visitor_count) VALUES (0);

MODEL


  import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterModel {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // Update with your MySQL password

    // Connects to the database and increments the visitor count
    public int incrementVisitorCount() {
        int updatedCount = 0;

        String selectQuery = "SELECT visitor_count FROM counter LIMIT 1";
        String updateQuery = "UPDATE counter SET visitor_count = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // 1. Read current count
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
                 ResultSet rs = selectStmt.executeQuery()) {
                
                if (rs.next()) {
                    updatedCount = rs.getInt("visitor_count") + 1;
                } else {
                    // Handle case where table is empty
                    updatedCount = 1;
                    String insertQuery = "INSERT INTO counter (visitor_count) VALUES (1)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.executeUpdate();
                    }
                    return updatedCount;
                }
            }

            // 2. Update with incremented count
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, updatedCount);
                updateStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedCount;
    }
}
