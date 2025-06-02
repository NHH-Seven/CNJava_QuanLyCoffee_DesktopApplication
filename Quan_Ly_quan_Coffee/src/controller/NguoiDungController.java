package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Database;
import model.NguoiDung;
import utils.DatabaseUtil;

/**
 * Controller xử lý các thao tác liên quan đến người dùng
 */
public class NguoiDungController {
    
    /**
     * Lấy danh sách tất cả người dùng từ cơ sở dữ liệu
     * @return Danh sách đối tượng NguoiDung
     */
    public List<NguoiDung> layDanhSachNguoiDung() {
        List<NguoiDung> danhSachNguoiDung = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id, tenDangNhap, matKhau, vaiTro, ngayTao FROM NguoiDung ORDER BY id ASC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("tenDangNhap"));
                nguoiDung.setMatKhau(rs.getString("matKhau"));
                nguoiDung.setVaiTro(rs.getString("vaiTro"));
                
                Timestamp timestamp = rs.getTimestamp("ngayTao");
                if (timestamp != null) {
                    nguoiDung.setNgayTao(new Date(timestamp.getTime()));
                }
                
                danhSachNguoiDung.add(nguoiDung);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách người dùng: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return danhSachNguoiDung;
    }
    
    /**
     * Tìm người dùng theo ID
     * @param id ID của người dùng cần tìm
     * @return Đối tượng NguoiDung nếu tìm thấy, null nếu không tìm thấy
     */
    public NguoiDung timNguoiDungTheoId(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id, tenDangNhap, matKhau, vaiTro, ngayTao FROM NguoiDung WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("tenDangNhap"));
                nguoiDung.setMatKhau(rs.getString("matKhau"));
                nguoiDung.setVaiTro(rs.getString("vaiTro"));
                
                Timestamp timestamp = rs.getTimestamp("ngayTao");
                if (timestamp != null) {
                    nguoiDung.setNgayTao(new Date(timestamp.getTime()));
                }
                
                return nguoiDung;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm người dùng theo ID: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return null;
    }
    
    /**
     * Tìm người dùng theo tên đăng nhập
     * @param tenDangNhap Tên đăng nhập cần tìm
     * @return Đối tượng NguoiDung nếu tìm thấy, null nếu không tìm thấy
     */
    public NguoiDung timNguoiDungTheoTenDangNhap(String tenDangNhap) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id, tenDangNhap, matKhau, vaiTro, ngayTao FROM NguoiDung WHERE tenDangNhap = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("tenDangNhap"));
                nguoiDung.setMatKhau(rs.getString("matKhau"));
                nguoiDung.setVaiTro(rs.getString("vaiTro"));
                
                Timestamp timestamp = rs.getTimestamp("ngayTao");
                if (timestamp != null) {
                    nguoiDung.setNgayTao(new Date(timestamp.getTime()));
                }
                
                return nguoiDung;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm người dùng theo tên đăng nhập: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return null;
    }
    
    /**
     * Tìm kiếm người dùng theo từ khóa tên đăng nhập
     * @param tuKhoa Từ khóa tìm kiếm
     * @return Danh sách người dùng phù hợp
     */
    public List<NguoiDung> timKiemNguoiDungTheoTen(String tuKhoa) {
        List<NguoiDung> ketQua = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id, tenDangNhap, matKhau, vaiTro, ngayTao FROM NguoiDung WHERE tenDangNhap LIKE ? ORDER BY id ASC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tuKhoa + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("tenDangNhap"));
                nguoiDung.setMatKhau(rs.getString("matKhau"));
                nguoiDung.setVaiTro(rs.getString("vaiTro"));
                
                Timestamp timestamp = rs.getTimestamp("ngayTao");
                if (timestamp != null) {
                    nguoiDung.setNgayTao(new Date(timestamp.getTime()));
                }
                
                ketQua.add(nguoiDung);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm người dùng: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return ketQua;
    }
    
    /**
     * Thêm mới người dùng vào cơ sở dữ liệu
     * @param nguoiDung Đối tượng NguoiDung cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean themNguoiDung(NguoiDung nguoiDung) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "INSERT INTO NguoiDung (tenDangNhap, matKhau, vaiTro, ngayTao) VALUES (?, ?, ?, GETDATE())";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nguoiDung.getTenDangNhap());
            ps.setString(2, nguoiDung.getMatKhau());
            ps.setString(3, nguoiDung.getVaiTro());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm người dùng: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Sửa thông tin người dùng
     * @param nguoiDung Đối tượng NguoiDung cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean suaNguoiDung(NguoiDung nguoiDung) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Database.getInstance().getConnection();
            StringBuilder sql = new StringBuilder("UPDATE NguoiDung SET tenDangNhap = ?, vaiTro = ?");
            
            // Chỉ cập nhật mật khẩu nếu có nhập mật khẩu mới
            if (nguoiDung.getMatKhau() != null && !nguoiDung.getMatKhau().isEmpty()) {
                sql.append(", matKhau = ?");
            }
            
            sql.append(" WHERE id = ?");
            
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, nguoiDung.getTenDangNhap());
            ps.setString(2, nguoiDung.getVaiTro());
            
            if (nguoiDung.getMatKhau() != null && !nguoiDung.getMatKhau().isEmpty()) {
                ps.setString(3, nguoiDung.getMatKhau());
                ps.setInt(4, nguoiDung.getId());
            } else {
                ps.setInt(3, nguoiDung.getId());
            }
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi sửa người dùng: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Xóa người dùng khỏi cơ sở dữ liệu
     * @param id ID của người dùng cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean xoaNguoiDung(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "DELETE FROM NguoiDung WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa người dùng: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Kiểm tra xem tên đăng nhập đã tồn tại trong cơ sở dữ liệu chưa
     * @param tenDangNhap Tên đăng nhập cần kiểm tra
     * @param currentId ID hiện tại (dùng khi cập nhật, để loại trừ người dùng hiện tại)
     * @return true nếu tên đăng nhập đã tồn tại, false nếu chưa tồn tại
     */
    public boolean kiemTraTenDangNhapTonTai(String tenDangNhap, int currentId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "SELECT COUNT(*) FROM NguoiDung WHERE tenDangNhap = ? AND id != ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setInt(2, currentId);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra tên đăng nhập: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return false;
    }
    
    /**
     * Xác thực người dùng để đăng nhập
     * @param tenDangNhap Tên đăng nhập
     * @param matKhau Mật khẩu
     * @return Đối tượng NguoiDung nếu đăng nhập thành công, null nếu thất bại
     */
    public NguoiDung dangNhap(String tenDangNhap, String matKhau) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id, tenDangNhap, matKhau, vaiTro, ngayTao FROM NguoiDung WHERE tenDangNhap = ? AND matKhau = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTenDangNhap(rs.getString("tenDangNhap"));
                nguoiDung.setMatKhau(rs.getString("matKhau"));
                nguoiDung.setVaiTro(rs.getString("vaiTro"));
                
                Timestamp timestamp = rs.getTimestamp("ngayTao");
                if (timestamp != null) {
                    nguoiDung.setNgayTao(new Date(timestamp.getTime()));
                }
                
                return nguoiDung;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đăng nhập: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return null;
    }
    
    /**
     * Đếm số lượng tài khoản admin trong hệ thống
     * @return Số lượng tài khoản admin
     */
    public int demSoLuongAdmin() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "SELECT COUNT(*) FROM NguoiDung WHERE vaiTro = 'admin'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm số lượng admin: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return 0;
    }
    
    /**
     * Thay đổi mật khẩu cho người dùng
     * @param id ID của người dùng
     * @param matKhauMoi Mật khẩu mới
     * @return true nếu thay đổi thành công, false nếu thất bại
     */
    public boolean thayDoiMatKhau(int id, String matKhauMoi) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = Database.getInstance().getConnection();
            String sql = "UPDATE NguoiDung SET matKhau = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, matKhauMoi);
            ps.setInt(2, id);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thay đổi mật khẩu: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
}