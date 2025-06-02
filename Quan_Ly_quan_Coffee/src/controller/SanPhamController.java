package controller;
import model.Database;
import model.SanPham;
import utils.DatabaseUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller xử lý các thao tác liên quan đến Sản phẩm
 */
public class SanPhamController {
    private Database database;
    
    // Các giá trị hợp lệ cho trạng thái
    public static final String TRANG_THAI_CON = "Còn";
    public static final String TRANG_THAI_HET = "Hết";
    
    /**
     * Constructor cho SanPhamController
     */
    public SanPhamController() {
        database = Database.getInstance();
    }
    
    /**
     * Lấy danh sách tất cả sản phẩm
     * @return Danh sách các đối tượng SanPham
     */
    public List<SanPham> getAllSanPham() {
        List<SanPham> danhSachSanPham = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM SanPham ORDER BY id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setDonGia(rs.getBigDecimal("donGia"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setNgayNhapHang(rs.getDate("ngayNhapHang"));
                sp.setNgayHetHan(rs.getDate("ngayHetHan"));
                sp.setLoai(rs.getString("loai"));
                String trangThaiStr = rs.getString("trangThai");
                sp.setTrangThai("Còn".equalsIgnoreCase(trangThaiStr));
                sp.setHinhAnh(rs.getString("hinhAnh"));
                danhSachSanPham.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return danhSachSanPham;
    }
    
    /**
     * Lấy sản phẩm theo id
     * @param id ID của sản phẩm cần lấy
     * @return Đối tượng SanPham tìm thấy hoặc null nếu không tồn tại
     */
    public SanPham getSanPhamById(int id) {
        SanPham sanPham = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM SanPham WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                sanPham = new SanPham();
                sanPham.setId(rs.getInt("id"));
                sanPham.setTenSanPham(rs.getString("tenSanPham"));
                sanPham.setMoTa(rs.getString("moTa"));
                sanPham.setDonGia(rs.getBigDecimal("donGia"));
                sanPham.setSoLuong(rs.getInt("soLuong"));
                sanPham.setNgayNhapHang(rs.getDate("ngayNhapHang"));
                sanPham.setNgayHetHan(rs.getDate("ngayHetHan"));
                sanPham.setLoai(rs.getString("loai"));
                String trangThaiStr = rs.getString("trangThai");
                sanPham.setTrangThai("Còn".equalsIgnoreCase(trangThaiStr));
                sanPham.setHinhAnh(rs.getString("hinhAnh"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy sản phẩm theo ID: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return sanPham;
    }
    
    /**
     * Thêm sản phẩm mới
     * @param sanPham Đối tượng SanPham cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean themSanPham(SanPham sanPham) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = database.getConnection();
            String sql = "INSERT INTO SanPham (tenSanPham, moTa, donGia, soLuong, ngayNhapHang, ngayHetHan, loai, trangThai, hinhAnh) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sanPham.getTenSanPham());
            ps.setString(2, sanPham.getMoTa());
            ps.setBigDecimal(3, sanPham.getDonGia());
            ps.setInt(4, sanPham.getSoLuong());
            ps.setDate(5, new java.sql.Date(sanPham.getNgayNhapHang().getTime()));
            ps.setDate(6, new java.sql.Date(sanPham.getNgayHetHan().getTime()));
            ps.setString(7, sanPham.getLoai());
            ps.setString(8, sanPham.isTrangThai() ? "Còn" : "Hết");
            ps.setString(9, sanPham.getHinhAnh());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Cập nhật thông tin sản phẩm
     * @param sanPham Đối tượng SanPham cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean suaSanPham(SanPham sanPham) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = database.getConnection();
            String sql = "UPDATE SanPham SET tenSanPham = ?, moTa = ?, donGia = ?, soLuong = ?, " +
                         "ngayNhapHang = ?, ngayHetHan = ?, loai = ?, trangThai = ?, hinhAnh = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sanPham.getTenSanPham());
            ps.setString(2, sanPham.getMoTa());
            ps.setBigDecimal(3, sanPham.getDonGia());
            ps.setInt(4, sanPham.getSoLuong());
            ps.setDate(5, new java.sql.Date(sanPham.getNgayNhapHang().getTime()));
            ps.setDate(6, new java.sql.Date(sanPham.getNgayHetHan().getTime()));
            ps.setString(7, sanPham.getLoai());
            ps.setString(8, sanPham.isTrangThai() ? "Còn" : "Hết");
            ps.setString(9, sanPham.getHinhAnh());
            ps.setInt(10, sanPham.getId());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Xóa sản phẩm theo id
     * @param id ID của sản phẩm cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean xoaSanPham(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = database.getConnection();
            String sql = "DELETE FROM SanPham WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Tìm kiếm sản phẩm theo tên
     * @param tenSanPham Tên sản phẩm cần tìm
     * @return Danh sách các sản phẩm phù hợp
     */
    public List<SanPham> timKiemSanPhamTheoTen(String tenSanPham) {
        List<SanPham> ketQua = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM SanPham WHERE tenSanPham LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tenSanPham + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setDonGia(rs.getBigDecimal("donGia"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setNgayNhapHang(rs.getDate("ngayNhapHang"));
                sp.setNgayHetHan(rs.getDate("ngayHetHan"));
                sp.setLoai(rs.getString("loai"));
                String trangThaiStr = rs.getString("trangThai");
                sp.setTrangThai("Còn".equalsIgnoreCase(trangThaiStr));
                sp.setHinhAnh(rs.getString("hinhAnh"));
                ketQua.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm theo tên: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return ketQua;
    }
    
    /**
     * Tìm kiếm sản phẩm theo loại
     * @param loai Loại sản phẩm cần tìm
     * @return Danh sách các sản phẩm phù hợp
     */
    public List<SanPham> timKiemSanPhamTheoLoai(String loai) {
        List<SanPham> ketQua = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM SanPham WHERE loai = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, loai);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setDonGia(rs.getBigDecimal("donGia"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setNgayNhapHang(rs.getDate("ngayNhapHang"));
                sp.setNgayHetHan(rs.getDate("ngayHetHan"));
                sp.setLoai(rs.getString("loai"));
                String trangThaiStr = rs.getString("trangThai");
                sp.setTrangThai("Còn".equalsIgnoreCase(trangThaiStr));
                sp.setHinhAnh(rs.getString("hinhAnh"));
                ketQua.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm theo loại: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return ketQua;
    }
    
    /**
     * Lấy danh sách sản phẩm theo trạng thái
     * @param trangThai Trạng thái sản phẩm (Còn/Hết)
     * @return Danh sách các sản phẩm phù hợp
     */
    public List<SanPham> getSanPhamTheoTrangThai(String trangThai) {
        List<SanPham> ketQua = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // Kiểm tra tính hợp lệ của trạng thái
        if (!TRANG_THAI_CON.equals(trangThai) && !TRANG_THAI_HET.equals(trangThai)) {
            return ketQua; // Trả về danh sách rỗng nếu trạng thái không hợp lệ
        }
        
        try {
            conn = database.getConnection();
            String sql = "SELECT * FROM SanPham WHERE trangThai = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, trangThai);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setDonGia(rs.getBigDecimal("donGia"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setNgayNhapHang(rs.getDate("ngayNhapHang"));
                sp.setNgayHetHan(rs.getDate("ngayHetHan"));
                sp.setLoai(rs.getString("loai"));
                String trangThaiStr = rs.getString("trangThai");
                sp.setTrangThai("Còn".equalsIgnoreCase(trangThaiStr));
                sp.setHinhAnh(rs.getString("hinhAnh"));
                ketQua.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy sản phẩm theo trạng thái: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return ketQua;
    }
    
    /**
     * Cập nhật trạng thái sản phẩm
     * @param id ID của sản phẩm cần cập nhật
     * @param trangThai Trạng thái mới (Còn/Hết)
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean capNhatTrangThaiSanPham(int id, String trangThai) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        // Kiểm tra tính hợp lệ của trạng thái
        if (!TRANG_THAI_CON.equals(trangThai) && !TRANG_THAI_HET.equals(trangThai)) {
            return false; // Từ chối cập nhật nếu trạng thái không hợp lệ
        }
        
        try {
            conn = database.getConnection();
            String sql = "UPDATE SanPham SET trangThai = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, trangThai);
            ps.setInt(2, id);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái sản phẩm: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Cập nhật giá sản phẩm
     * @param id ID của sản phẩm cần cập nhật
     * @param donGia Giá mới của sản phẩm
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean capNhatGiaSanPham(int id, BigDecimal donGia) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = database.getConnection();
            String sql = "UPDATE SanPham SET donGia = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, donGia);
            ps.setInt(2, id);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật giá sản phẩm: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
        }
    }
    
    /**
     * Đếm số lượng sản phẩm theo loại
     * @param loai Loại sản phẩm cần đếm
     * @return Số lượng sản phẩm thuộc loại
     */
    public int demSanPhamTheoLoai(String loai) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT COUNT(*) AS soluong FROM SanPham WHERE loai = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, loai);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt("soluong");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm sản phẩm theo loại: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return count;
    }
    
    /**
     * Lấy tất cả các loại sản phẩm hiện có
     * @return Danh sách các loại sản phẩm
     */
    public List<String> getAllLoaiSanPham() {
        List<String> danhSachLoai = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = database.getConnection();
            String sql = "SELECT DISTINCT loai FROM SanPham ORDER BY loai";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                danhSachLoai.add(rs.getString("loai"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách loại sản phẩm: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
        }
        
        return danhSachLoai;
    }
}
