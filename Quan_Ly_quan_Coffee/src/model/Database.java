package model;

import java.sql.Connection;
import java.sql.SQLException;
import utils.DatabaseUtil;


public class Database {
    private Connection connection;
    private static Database instance;
    
    /**
     * Constructor với modifier private để đảm bảo Singleton pattern
     */
    private Database() {
        // Constructor rỗng
    }
    
    /**
     * Phương thức getInstance() để thực hiện Singleton pattern
     * @return Instance của Database
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    /**
     * Thiết lập kết nối đến cơ sở dữ liệu
     * @return Connection - kết nối JDBC
     * @throws SQLException nếu không thể kết nối
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseUtil.getConnection();
        }
        return connection;
    }
    
    /**
     * Đóng kết nối đến cơ sở dữ liệu
     */
    public void closeConnection() {
        if (connection != null) {
            DatabaseUtil.closeConnection(connection);
            connection = null;
        }
    }
    
    /**
     * Kiểm tra kết nối có đang hoạt động không
     * @return true nếu kết nối hoạt động, false nếu không
     */
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Phương thức kiểm tra kết nối và thiết lập lại nếu cần
     * @return true nếu kết nối thành công, false nếu thất bại
     */
    public boolean reconnectIfNeeded() {
        try {
            if (!isConnected()) {
                connection = DatabaseUtil.getConnection();
                return true;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Không thể kết nối lại cơ sở dữ liệu: " + e.getMessage());
            return false;
        }
    }
}