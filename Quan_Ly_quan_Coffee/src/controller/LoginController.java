
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Database;
import utils.HashUtil;
import view.LoginView;

/**
 * Controller xử lý các chức năng liên quan đến đăng nhập
 * 
 * @author zhieu
 */
public class LoginController {
    private LoginView view;
    
    /**
     * Constructor với tham số là view
     * @param view View đăng nhập
     */
    public LoginController(LoginView view) {
        this.view = view;
    }
    
    /**
     * Phương thức xử lý đăng nhập
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     */
    public void login(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Lấy kết nối từ Database Singleton
            conn = Database.getInstance().getConnection();
            
            // Truy vấn người dùng theo tên đăng nhập
            String sql = "SELECT id, tenDangNhap, matKhau, vaiTro FROM NguoiDung WHERE tenDangNhap = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String storedPassword = rs.getString("matKhau");
                String vaiTro = rs.getString("vaiTro");
                
                // Kiểm tra mật khẩu (sử dụng HashUtil để so sánh)
                if (HashUtil.verifyPassword(password, storedPassword)) {
                    // Đăng nhập thành công
                    view.showSuccess("Đăng nhập thành công với vai trò: " + vaiTro);
                    
                    // Chuyển hướng đến giao diện chính
                    view.navigateToMainView(vaiTro, id);
                } else {
                    // Mật khẩu không đúng
                    view.showError("Mật khẩu không chính xác!");
                }
            } else {
                // Không tìm thấy người dùng
                view.showError("Tên đăng nhập không tồn tại!");
            }
        } catch (SQLException e) {
            view.showError("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng các tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                // Không đóng connection ở đây vì sử dụng Singleton pattern
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Phương thức thay đổi mật khẩu
     * @param userId ID người dùng
     * @param oldPassword Mật khẩu cũ
     * @param newPassword Mật khẩu mới
     * @return true nếu thay đổi thành công, false nếu thất bại
     */
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Lấy kết nối từ Database Singleton
            conn = Database.getInstance().getConnection();
            
            // Truy vấn người dùng theo ID
            String sqlSelect = "SELECT matKhau FROM NguoiDung WHERE id = ?";
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("matKhau");
                
                // Kiểm tra mật khẩu cũ
                if (HashUtil.verifyPassword(oldPassword, storedPassword)) {
                    // Mật khẩu cũ đúng, tiến hành cập nhật mật khẩu mới
                    rs.close();
                    stmt.close();
                    
                    // Hash mật khẩu mới
                    String hashedNewPassword = HashUtil.hashPassword(newPassword);
                    
                    // Cập nhật mật khẩu
                    String sqlUpdate = "UPDATE NguoiDung SET matKhau = ? WHERE id = ?";
                    stmt = conn.prepareStatement(sqlUpdate);
                    stmt.setString(1, hashedNewPassword);
                    stmt.setInt(2, userId);
                    
                    int rowsAffected = stmt.executeUpdate();
                    return rowsAffected > 0;
                } else {
                    // Mật khẩu cũ không đúng
                    return false;
                }
            } else {
                // Không tìm thấy người dùng
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng các tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                // Không đóng connection ở đây vì sử dụng Singleton pattern
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Phương thức kiểm tra quyền admin
     * @param userId ID người dùng
     * @return true nếu là admin, false nếu không phải
     */
    public boolean isAdmin(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Lấy kết nối từ Database Singleton
            conn = Database.getInstance().getConnection();
            
            // Truy vấn vai trò của người dùng
            String sql = "SELECT vaiTro FROM NguoiDung WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String vaiTro = rs.getString("vaiTro");
                return "admin".equals(vaiTro);
            }
            
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng các tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                // Không đóng connection ở đây vì sử dụng Singleton pattern
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}