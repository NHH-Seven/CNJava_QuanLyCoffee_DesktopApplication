package controller;

import model.SanPham;
import model.HoaDon;
import model.ChiTietHoaDon;
import model.Voucher;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * Controller xử lý logic bán hàng
 * 
 * @author zhieu
 */
public class BanHangController {
    
    private DatabaseUtil dbUtil;
    
    public BanHangController() {
        this.dbUtil = new DatabaseUtil();
    }
    
    /**
     * Lấy danh sách sản phẩm
     */
    public List<SanPham> getAllSanPham() {
        List<SanPham> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE trangThai = N'Còn'";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
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
                danhSach.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSach;
    }
    
    /**
     * Tìm kiếm sản phẩm theo tên
     */
    public List<SanPham> timKiemSanPhamTheoTen(String tenSanPham) {
        List<SanPham> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE tenSanPham LIKE ? AND trangThai = 'Còn'";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + tenSanPham + "%");
            ResultSet rs = stmt.executeQuery();
            
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
                danhSach.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    
    /**
     * Tìm kiếm sản phẩm theo loại
     */
    public List<SanPham> timKiemSanPhamTheoLoai(String loaiSanPham) {
        List<SanPham> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE loai = ? AND trangThai = N'Còn'";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, loaiSanPham);
            ResultSet rs = stmt.executeQuery();
            
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
                danhSach.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm theo loại: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSach;
    }
    
    /**
     * Lấy thông tin sản phẩm theo ID
     */
    public SanPham getSanPhamById(int id) {
        String sql = "SELECT * FROM SanPham WHERE id = ?";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
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
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Lấy thông tin voucher theo mã
     */
    public Voucher getVoucherByMa(String maVoucher) {
        String sql = "SELECT * FROM Voucher WHERE MaVoucher = ? AND TrangThai = N'Kích hoạt'";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, maVoucher);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt("ID"));
                voucher.setMaVoucher(rs.getString("MaVoucher"));
                voucher.setPhanTramGiamGia(rs.getBigDecimal("phanTramGiam"));
                voucher.setNgayBatDau(rs.getDate("NgayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                voucher.setTrangThai(rs.getString("TrangThai").equals("Kích hoạt"));
                return voucher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Lấy danh sách voucher còn hạn sử dụng
     */
    public List<Voucher> getVouchersConHan() {
        List<Voucher> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM Voucher WHERE TrangThai = N'Kích hoạt' " +
                    "AND (NgayKetThuc IS NULL OR NgayKetThuc >= GETDATE()) " +
                    "AND (NgayBatDau IS NULL OR NgayBatDau <= GETDATE())";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt("ID"));
                voucher.setMaVoucher(rs.getString("MaVoucher"));
                voucher.setPhanTramGiamGia(rs.getBigDecimal("phanTramGiam"));
                voucher.setNgayBatDau(rs.getDate("NgayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                voucher.setTrangThai(rs.getString("TrangThai").equals("Kích hoạt"));
                danhSach.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    
    /**
     * Tạo hóa đơn mới
     */
    public boolean taoHoaDon(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDon) {
        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            conn.setAutoCommit(false);
            
            // Thêm hóa đơn
            String sqlHoaDon = "INSERT INTO HoaDon (ngayLap, tongTien, giamGia, thanhTien, trangThai, nhanVien_id, voucher_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtHoaDon = conn.prepareStatement(sqlHoaDon, Statement.RETURN_GENERATED_KEYS);
            
            stmtHoaDon.setTimestamp(1, new Timestamp(hoaDon.getNgayLap().getTime()));
            stmtHoaDon.setBigDecimal(2, hoaDon.getTongTien());
            stmtHoaDon.setBigDecimal(3, hoaDon.getGiamGia());
            stmtHoaDon.setBigDecimal(4, hoaDon.getThanhTien());
            stmtHoaDon.setBoolean(5, hoaDon.isTrangThai());
            stmtHoaDon.setInt(6, hoaDon.getNhanVien_id());
            stmtHoaDon.setInt(7, hoaDon.getVoucher_id());
            
            int affectedRows = stmtHoaDon.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmtHoaDon.getGeneratedKeys();
                if (rs.next()) {
                    int hoaDonId = rs.getInt(1);
                    
                    // Thêm chi tiết hóa đơn
                    String sqlChiTiet = "INSERT INTO ChiTietHoaDon (HoaDon_id, SanPham_id, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmtChiTiet = conn.prepareStatement(sqlChiTiet);
                    
                    for (ChiTietHoaDon ct : chiTietHoaDon) {
                        stmtChiTiet.setInt(1, hoaDonId);
                        stmtChiTiet.setInt(2, ct.getSanPham_id());
                        stmtChiTiet.setInt(3, ct.getSoLuong());
                        stmtChiTiet.setBigDecimal(4, ct.getDonGia());
                        stmtChiTiet.addBatch();
                        
                        // Cập nhật số lượng sản phẩm
                        String sqlUpdateSP = "UPDATE SanPham SET SoLuong = SoLuong - ? WHERE ID = ?";
                        PreparedStatement stmtUpdateSP = conn.prepareStatement(sqlUpdateSP);
                        stmtUpdateSP.setInt(1, ct.getSoLuong());
                        stmtUpdateSP.setInt(2, ct.getSanPham_id());
                        stmtUpdateSP.executeUpdate();
                    }
                    stmtChiTiet.executeBatch();
                    
                    conn.commit();
                    return true;
                }
            }
            conn.rollback();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
} 