package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import model.Database;
import model.NguoiDung;
import model.NhanVien;
import utils.HashUtil;
import view.RegisterView;

/**
 * Controller xử lý chức năng đăng ký
 * 
 * @author zhieu
 */
public class RegisterController {
    private RegisterView view;
    
    /**
     * Constructor
     * @param view View đăng ký
     */
    public RegisterController(RegisterView view) {
        this.view = view;
    }
    
    /**
     * Xử lý đăng ký người dùng mới
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @param hoTen Họ tên
     * @param gioiTinh Giới tính
     * @param vaiTro Vai trò (admin/nhanvien)
     */
    public void register(String username, String password, String hoTen, String gioiTinh, String vaiTro) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int nguoiDungId = -1;
        
        try {
            conn = Database.getInstance().getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction
            
            // Kiểm tra username đã tồn tại chưa
            String checkUserSql = "SELECT COUNT(*) FROM NguoiDung WHERE tenDangNhap = ?";
            pstmt = conn.prepareStatement(checkUserSql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                view.showError("Tên đăng nhập đã tồn tại, vui lòng chọn tên khác!");
                return;
            }
            
            // Mã hóa mật khẩu
            String hashedPassword = HashUtil.hashPassword(password);
            
            // Thêm vào bảng NguoiDung
            String insertUserSql = "INSERT INTO NguoiDung (tenDangNhap, matKhau, vaiTro, ngayTao) VALUES (?, ?, ?, GETDATE())";
            pstmt = conn.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, vaiTro);
            pstmt.executeUpdate();
            
            // Lấy id của người dùng vừa thêm
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                nguoiDungId = rs.getInt(1);
            } else {
                throw new SQLException("Không thể lấy ID của người dùng, không có ID được tạo.");
            }
            
            // Thêm thông tin vào bảng NhanVien
            String insertStaffSql = "INSERT INTO NhanVien (hoTen, gioiTinh, ngayVaoLam, nguoiDung_id) VALUES (?, ?, GETDATE(), ?)";
            pstmt = conn.prepareStatement(insertStaffSql);
            pstmt.setString(1, hoTen);
            pstmt.setString(2, gioiTinh);
            pstmt.setInt(3, nguoiDungId);
            pstmt.executeUpdate();
            
            // Commit transaction
            conn.commit();
            
            // Tạo đối tượng NguoiDung
            NguoiDung nguoiDung = new NguoiDung(nguoiDungId, username, hashedPassword, vaiTro, new Date());
            
            // Thông báo thành công
            view.showSuccess("Đăng ký tài khoản thành công!");
            
            // Chuyển về giao diện đăng nhập
            view.navigateToLogin();
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback khi có lỗi
                }
            } catch (SQLException ex) {
                System.err.println("Lỗi khi rollback: " + ex.getMessage());
            }
            view.showError("Lỗi khi đăng ký: " + e.getMessage());
            System.err.println("Lỗi SQL: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Khôi phục auto commit
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi khôi phục auto commit: " + e.getMessage());
            }
            
            // Đóng các tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
}