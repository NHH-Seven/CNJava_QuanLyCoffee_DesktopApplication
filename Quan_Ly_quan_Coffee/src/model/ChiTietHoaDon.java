package model;

import java.math.BigDecimal;

public class ChiTietHoaDon {
    private int id;
    private int hoaDon_id;
    private int sanPham_id;
    private int soLuong;
    private BigDecimal donGia;
    private SanPham sanPham; // Để hiển thị thông tin sản phẩm
    
    // Constructors
    public ChiTietHoaDon() {}
    
    public ChiTietHoaDon(int sanPham_id, int soLuong, BigDecimal donGia) {
        this.sanPham_id = sanPham_id;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    public ChiTietHoaDon(int hoaDon_id, int sanPham_id, int soLuong, BigDecimal donGia) {
        this.hoaDon_id = hoaDon_id;
        this.sanPham_id = sanPham_id;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    public ChiTietHoaDon(int id, int hoaDon_id, int sanPham_id, int soLuong, BigDecimal donGia) {
        this.id = id;
        this.hoaDon_id = hoaDon_id;
        this.sanPham_id = sanPham_id;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getHoaDon_id() {
        return hoaDon_id;
    }
    
    public void setHoaDon_id(int hoaDon_id) {
        this.hoaDon_id = hoaDon_id;
    }
    
    public int getSanPham_id() {
        return sanPham_id;
    }
    
    public void setSanPham_id(int sanPham_id) {
        this.sanPham_id = sanPham_id;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public BigDecimal getDonGia() {
        return donGia;
    }
    
    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }
    
    public SanPham getSanPham() {
        return sanPham;
    }
    
    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
    
    public BigDecimal getThanhTien() {
        return donGia.multiply(new BigDecimal(soLuong));
    }
    
    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "id=" + id + ", sanPham_id=" + sanPham_id + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
}
