package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {
    private int id;
    private Date ngayLap;
    private BigDecimal tongTien;
    private BigDecimal giamGia;
    private BigDecimal thanhTien;
    private boolean trangThai;
    private int nhanVien_id;
    private int khachHang_id;
    private int voucher_id;
    private List<ChiTietHoaDon> chiTietList;
    
    // Constructors
    public HoaDon() {
        this.chiTietList = new ArrayList<>();
        this.ngayLap = new Date();
        this.trangThai = true;
    }
    
    public HoaDon(Date ngayLap, BigDecimal tongTien, BigDecimal giamGia, BigDecimal thanhTien, 
                  boolean trangThai, int nhanVien_id, int khachHang_id, int voucher_id) {
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
        this.nhanVien_id = nhanVien_id;
        this.khachHang_id = khachHang_id;
        this.voucher_id = voucher_id;
        this.chiTietList = new ArrayList<>();
    }
    
    public HoaDon(int id, Date ngayLap, BigDecimal tongTien, int nhanVien_id, int khachHang_id, int voucher_id) {
        this.id = id;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.nhanVien_id = nhanVien_id;
        this.khachHang_id = khachHang_id;
        this.voucher_id = voucher_id;
        this.chiTietList = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getNgayLap() {
        return ngayLap;
    }
    
    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    public BigDecimal getTongTien() {
        return tongTien;
    }
    
    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
    
    public BigDecimal getGiamGia() {
        return giamGia;
    }
    
    public void setGiamGia(BigDecimal giamGia) {
        this.giamGia = giamGia;
    }
    
    public BigDecimal getThanhTien() {
        return thanhTien;
    }
    
    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    public boolean isTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    public int getNhanVien_id() {
        return nhanVien_id;
    }
    
    public void setNhanVien_id(int nhanVien_id) {
        this.nhanVien_id = nhanVien_id;
    }
    
    public int getKhachHang_id() {
        return khachHang_id;
    }
    
    public void setKhachHang_id(int khachHang_id) {
        this.khachHang_id = khachHang_id;
    }
    
    public int getVoucher_id() {
        return voucher_id;
    }
    
    public void setVoucher_id(int voucher_id) {
        this.voucher_id = voucher_id;
    }
    
    public List<ChiTietHoaDon> getChiTietList() {
        return chiTietList;
    }
    
    public void setChiTietList(List<ChiTietHoaDon> chiTietList) {
        this.chiTietList = chiTietList;
    }
    
    public void addChiTiet(ChiTietHoaDon chiTiet) {
        this.chiTietList.add(chiTiet);
    }
    
    @Override
    public String toString() {
        return "HoaDon{" + 
                "id=" + id + 
                ", ngayLap=" + ngayLap + 
                ", tongTien=" + tongTien + 
                ", giamGia=" + giamGia + 
                ", thanhTien=" + thanhTien + 
                ", trangThai=" + trangThai + 
                '}';
    }
}
