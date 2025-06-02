package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuanCoffee;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa"; // Thay đổi username SQL Server của bạn
    private static final String PASS = "123456789"; // Thay đổi password SQL Server của bạn

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) {
                System.out.println("✅ Kết nối thành công tới cơ sở dữ liệu.");
                return conn;
            } else {
                throw new SQLException("❌ Không thể thiết lập kết nối tới cơ sở dữ liệu.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Không tìm thấy driver SQL Server JDBC: " + e.getMessage());
            throw new SQLException("❌ Không tìm thấy driver SQL Server JDBC.", e);
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối database: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("🔒 Đã đóng kết nối.");
            } catch (SQLException e) {
                System.out.println("⚠️ Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("⚠️ Lỗi khi đóng PreparedStatement: " + e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("⚠️ Lỗi khi đóng ResultSet: " + e.getMessage());
            }
        }
    }
    
}
