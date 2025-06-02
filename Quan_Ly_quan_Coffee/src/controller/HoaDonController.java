/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.HoaDon;
import model.ChiTietHoaDon;
import model.SanPham;
import utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * Controller xử lý logic hóa đơn
 * 
 * @author zhieu
 */
public class HoaDonController {
    
    private DatabaseUtil dbUtil;
    
    public HoaDonController() {
        this.dbUtil = new DatabaseUtil();
    }
    
    /**
     * Lấy danh sách hóa đơn
     */
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon ORDER BY ngayLap DESC";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setNgayLap(rs.getTimestamp("ngayLap"));
                hd.setTongTien(rs.getBigDecimal("tongTien"));
                hd.setGiamGia(rs.getBigDecimal("giamGia"));
                hd.setThanhTien(rs.getBigDecimal("thanhTien"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                hd.setNhanVien_id(rs.getInt("nhanVien_id"));
                hd.setKhachHang_id(rs.getInt("khachHang_id"));
                hd.setVoucher_id(rs.getInt("voucher_id"));
                
                // Lấy chi tiết hóa đơn
                List<ChiTietHoaDon> chiTietList = getChiTietHoaDon(hd.getId());
                hd.setChiTietList(chiTietList);
                
                danhSach.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    
    /**
     * Lấy chi tiết hóa đơn theo ID hóa đơn
     */
    public List<ChiTietHoaDon> getChiTietHoaDon(int hoaDonId) {
        List<ChiTietHoaDon> danhSach = new ArrayList<>();
        String sql = "SELECT cthd.*, sp.tenSanPham, sp.donGia as giaGoc " +
                    "FROM ChiTietHoaDon cthd " +
                    "JOIN SanPham sp ON cthd.sanPham_id = sp.id " +
                    "WHERE cthd.hoaDon_id = ?";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, hoaDonId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setId(rs.getInt("id"));
                cthd.setHoaDon_id(rs.getInt("hoaDon_id"));
                cthd.setSanPham_id(rs.getInt("sanPham_id"));
                cthd.setSoLuong(rs.getInt("soLuong"));
                cthd.setDonGia(rs.getBigDecimal("donGia"));
                
                // Tạo đối tượng SanPham để lưu thông tin
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("sanPham_id"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setDonGia(rs.getBigDecimal("giaGoc"));
                cthd.setSanPham(sp);
                
                danhSach.add(cthd);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSach;
    }
    
    /**
     * Lấy thông tin hóa đơn theo ID
     */
    public HoaDon getHoaDonById(int id) {
        String sql = "SELECT * FROM HoaDon WHERE id = ?";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setNgayLap(rs.getTimestamp("ngayLap"));
                hd.setTongTien(rs.getBigDecimal("tongTien"));
                hd.setGiamGia(rs.getBigDecimal("giamGia"));
                hd.setThanhTien(rs.getBigDecimal("thanhTien"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                hd.setNhanVien_id(rs.getInt("nhanVien_id"));
                hd.setKhachHang_id(rs.getInt("khachHang_id"));
                hd.setVoucher_id(rs.getInt("voucher_id"));
                
                // Lấy chi tiết hóa đơn
                List<ChiTietHoaDon> chiTietList = getChiTietHoaDon(hd.getId());
                hd.setChiTietList(chiTietList);
                
                return hd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Tìm kiếm hóa đơn theo ngày
     */
    public List<HoaDon> timKiemHoaDonTheoNgay(Date ngayBatDau, Date ngayKetThuc) {
        List<HoaDon> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE ngayLap BETWEEN ? AND ? ORDER BY ngayLap DESC";
        
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, new java.sql.Date(ngayBatDau.getTime()));
            stmt.setDate(2, new java.sql.Date(ngayKetThuc.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setNgayLap(rs.getTimestamp("ngayLap"));
                hd.setTongTien(rs.getBigDecimal("tongTien"));
                hd.setGiamGia(rs.getBigDecimal("giamGia"));
                hd.setThanhTien(rs.getBigDecimal("thanhTien"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                hd.setNhanVien_id(rs.getInt("nhanVien_id"));
                hd.setKhachHang_id(rs.getInt("khachHang_id"));
                hd.setVoucher_id(rs.getInt("voucher_id"));
                
                // Lấy chi tiết hóa đơn
                List<ChiTietHoaDon> chiTietList = getChiTietHoaDon(hd.getId());
                hd.setChiTietList(chiTietList);
                
                danhSach.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
}
