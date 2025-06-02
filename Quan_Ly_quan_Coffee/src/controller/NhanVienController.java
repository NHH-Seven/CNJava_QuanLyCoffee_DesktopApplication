package controller;

import model.Database;
import model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseUtil;

/**
 * Lớp NhanVienController là lớp điều khiển xử lý logic cho quản lý nhân viên
 */
public class NhanVienController {
    
    private Database database;
    
    /**
     * Constructor khởi tạo đối tượng Database sử dụng Singleton pattern
     */
    public NhanVienController() {
        database = Database.getInstance();
    }
    
    /**
     * Lấy danh sách tất cả nhân viên từ cơ sở dữ liệu
     * @return List<NhanVien> danh sách nhân viên
     */
    public List<NhanVien> layDanhSachNhanVien() {
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM NhanVien";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setHoTen(rs.getString("hoTen"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setNgayVaoLam(rs.getDate("ngayVaoLam"));
                nhanVien.setNguoiDungId(rs.getInt("nguoiDung_id"));
                nhanVien.setSoCCCD(rs.getString("soCCCD"));
                
                danhSachNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách nhân viên: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("⚠️ Lỗi khi đóng Statement: " + e.getMessage());
                }
            }
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
        
        return danhSachNhanVien;
    }
    
    /**
     * Tìm nhân viên theo ID
     * @param id ID nhân viên cần tìm
     * @return NhanVien nếu tìm thấy, null nếu không tìm thấy
     */
    public NhanVien timNhanVienTheoId(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NhanVien nhanVien = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setHoTen(rs.getString("hoTen"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setNgayVaoLam(rs.getDate("ngayVaoLam"));
                nhanVien.setNguoiDungId(rs.getInt("nguoiDung_id"));
                nhanVien.setSoCCCD(rs.getString("soCCCD"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm nhân viên theo ID: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
        
        return nhanVien;
    }
    
    /**
     * Tìm nhân viên theo số điện thoại
     * @param soDienThoai Số điện thoại cần tìm
     * @return NhanVien nếu tìm thấy, null nếu không tìm thấy
     */
    public NhanVien timNhanVienTheoSoDienThoai(String soDienThoai) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NhanVien nhanVien = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE soDienThoai=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, soDienThoai);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setHoTen(rs.getString("hoTen"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setNgayVaoLam(rs.getDate("ngayVaoLam"));
                nhanVien.setNguoiDungId(rs.getInt("nguoiDung_id"));
                nhanVien.setSoCCCD(rs.getString("soCCCD"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm nhân viên theo số điện thoại: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
        
        return nhanVien;
    }
    
    /**
     * Tìm kiếm nhân viên theo tên
     * @param hoTen Tên nhân viên cần tìm
     * @return List<NhanVien> danh sách nhân viên phù hợp
     */
    public List<NhanVien> timKiemNhanVienTheoTen(String hoTen) {
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE hoTen LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + hoTen + "%");
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setHoTen(rs.getString("hoTen"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setNgayVaoLam(rs.getDate("ngayVaoLam"));
                nhanVien.setNguoiDungId(rs.getInt("nguoiDung_id"));
                nhanVien.setSoCCCD(rs.getString("soCCCD"));
                
                danhSachNhanVien.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm nhân viên theo tên: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
        
        return danhSachNhanVien;
    }
    
    /**
     * Thêm nhân viên mới vào cơ sở dữ liệu
     * @param nhanVien Đối tượng NhanVien cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean themNhanVien(NhanVien nhanVien) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = database.getConnection();
            String sql = "INSERT INTO NhanVien (hoTen, gioiTinh, ngaySinh, soDienThoai, diaChi, ngayVaoLam, nguoiDung_id, soCCCD) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nhanVien.getHoTen());
            pstmt.setString(2, nhanVien.getGioiTinh());
            
            if (nhanVien.getNgaySinh() != null) {
                pstmt.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            } else {
                pstmt.setNull(3, java.sql.Types.DATE);
            }
            
            pstmt.setString(4, nhanVien.getSoDienThoai());
            pstmt.setString(5, nhanVien.getDiaChi());
            
            if (nhanVien.getNgayVaoLam() != null) {
                pstmt.setDate(6, new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
            } else {
                pstmt.setNull(6, java.sql.Types.DATE);
            }
            
            if (nhanVien.getNguoiDungId() > 0) {
                pstmt.setInt(7, nhanVien.getNguoiDungId());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            pstmt.setString(8, nhanVien.getSoCCCD());
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm nhân viên: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
    }
    
    /**
     * Cập nhật thông tin nhân viên trong cơ sở dữ liệu
     * @param nhanVien Đối tượng NhanVien cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean suaNhanVien(NhanVien nhanVien) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = database.getConnection();
            String sql = "UPDATE NhanVien SET hoTen=?, gioiTinh=?, ngaySinh=?, soDienThoai=?, "
                    + "diaChi=?, ngayVaoLam=?, nguoiDung_id=?, soCCCD=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, nhanVien.getHoTen());
            pstmt.setString(2, nhanVien.getGioiTinh());
            
            if (nhanVien.getNgaySinh() != null) {
                pstmt.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            } else {
                pstmt.setNull(3, java.sql.Types.DATE);
            }
            
            pstmt.setString(4, nhanVien.getSoDienThoai());
            pstmt.setString(5, nhanVien.getDiaChi());
            
            if (nhanVien.getNgayVaoLam() != null) {
                pstmt.setDate(6, new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
            } else {
                pstmt.setNull(6, java.sql.Types.DATE);
            }
            
            if (nhanVien.getNguoiDungId() > 0) {
                pstmt.setInt(7, nhanVien.getNguoiDungId());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            pstmt.setString(8, nhanVien.getSoCCCD());
            pstmt.setInt(9, nhanVien.getId());
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật nhân viên: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
    }
    
    /**
     * Xóa nhân viên khỏi cơ sở dữ liệu
     * @param id ID nhân viên cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean xoaNhanVien(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = database.getConnection();
            String sql = "DELETE FROM NhanVien WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa nhân viên: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
    }
    
    /**
     * Kiểm tra sự tồn tại của nhân viên theo số điện thoại
     * @param soDienThoai Số điện thoại cần kiểm tra
     * @param excludeId ID cần loại trừ khi kiểm tra (dùng khi cập nhật nhân viên hiện tại)
     * @return true nếu số điện thoại đã tồn tại, false nếu chưa tồn tại
     */
    public boolean kiemTraSoDienThoaiTonTai(String soDienThoai, int excludeId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT COUNT(*) FROM NhanVien WHERE soDienThoai = ? AND id != ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, soDienThoai);
            pstmt.setInt(2, excludeId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra số điện thoại: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
        
        return false;
    }
    
    /**
     * Đếm số lượng nhân viên trong cơ sở dữ liệu
     * @return số lượng nhân viên
     */
    public int demSoLuongNhanVien() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT COUNT(*) FROM NhanVien";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm số lượng nhân viên: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
            // Không đóng connection ở đây vì nó được quản lý bởi Database singleton
        }
        
        return 0;
    }
    
    /**
     * Kiểm tra kết nối cơ sở dữ liệu và cố gắng kết nối lại nếu cần
     * @return true nếu kết nối hoạt động, false nếu không thể kết nối
     */
    public boolean kiemTraKetNoi() {
        return database.reconnectIfNeeded();
    }
}