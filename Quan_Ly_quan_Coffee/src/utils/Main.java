package utils;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            DatabaseUtil.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("❌ Không thể kết nối: " + e.getMessage());
        }
    }
}