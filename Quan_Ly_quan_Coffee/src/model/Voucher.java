package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Model class cho bảng Voucher
 * 
 * @author zhieu
 */
public class Voucher {
    private int id;
    private String maVoucher;
    private BigDecimal phanTramGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private boolean trangThai;
    
    public Voucher() {
    }
    
    public Voucher(int id, String maVoucher, BigDecimal phanTramGiamGia, Date ngayBatDau, Date ngayKetThuc, boolean trangThai) {
        this.id = id;
        this.maVoucher = maVoucher;
        this.phanTramGiamGia = phanTramGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getMaVoucher() {
        return maVoucher;
    }
    
    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }
    
    public BigDecimal getPhanTramGiamGia() {
        return phanTramGiamGia;
    }
    
    public void setPhanTramGiamGia(BigDecimal phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }
    
    public Date getNgayBatDau() {
        return ngayBatDau;
    }
    
    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }
    
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }
    
    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    
    public boolean isTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    @Override
    public String toString() {
        return "Voucher{" + "id=" + id + ", maVoucher=" + maVoucher + ", phanTramGiamGia=" + phanTramGiamGia + ", trangThai=" + trangThai + '}';
    }
}
