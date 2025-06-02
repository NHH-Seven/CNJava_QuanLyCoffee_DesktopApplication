package model;

import java.math.BigDecimal;
import java.sql.Date;

public class SanPham {
    private int id;
    private String tenSanPham;
    private String moTa;
    private BigDecimal donGia;
    private int soLuong;
    private Date ngayNhapHang;
    private Date ngayHetHan;
    private String loai;
    private boolean trangThai;
    private String hinhAnh;
    
    // Constructors
    public SanPham() {}
    
    public SanPham(int id, String tenSanPham, String moTa, BigDecimal donGia, int soLuong, 
                  Date ngayNhapHang, Date ngayHetHan, String loai, boolean trangThai, String hinhAnh) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ngayNhapHang = ngayNhapHang;
        this.ngayHetHan = ngayHetHan;
        this.loai = loai;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTenSanPham() {
        return tenSanPham;
    }
    
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public BigDecimal getDonGia() {
        return donGia;
    }
    
    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public Date getNgayNhapHang() {
        return ngayNhapHang;
    }
    
    public void setNgayNhapHang(Date ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }
    
    public Date getNgayHetHan() {
        return ngayHetHan;
    }
    
    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }
    
    public String getLoai() {
        return loai;
    }
    
    public void setLoai(String loai) {
        this.loai = loai;
    }
    
    public boolean isTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    public String getHinhAnh() {
        return hinhAnh;
    }
    
    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    @Override
    public String toString() {
        return "SanPham{" +
                "id=" + id +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", moTa='" + moTa + '\'' +
                ", donGia=" + donGia +
                ", soLuong=" + soLuong +
                ", ngayNhapHang=" + ngayNhapHang +
                ", ngayHetHan=" + ngayHetHan +
                ", loai='" + loai + '\'' +
                ", trangThai=" + trangThai +
                ", hinhAnh='" + hinhAnh + '\'' +
                '}';
    }
}
