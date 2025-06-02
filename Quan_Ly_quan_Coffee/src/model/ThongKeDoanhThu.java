package model;

import java.math.BigDecimal;
import java.util.Date;

public class ThongKeDoanhThu {
    private int id;
    private Date ngay;
    private BigDecimal tongDoanhThu;
    private int soHoaDon;
    
    // Constructors
    public ThongKeDoanhThu() {}
    
    public ThongKeDoanhThu(Date ngay, BigDecimal tongDoanhThu, int soHoaDon) {
        this.ngay = ngay;
        this.tongDoanhThu = tongDoanhThu;
        this.soHoaDon = soHoaDon;
    }
    
    public ThongKeDoanhThu(int id, Date ngay, BigDecimal tongDoanhThu, int soHoaDon) {
        this.id = id;
        this.ngay = ngay;
        this.tongDoanhThu = tongDoanhThu;
        this.soHoaDon = soHoaDon;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getNgay() {
        return ngay;
    }
    
    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
    
    public BigDecimal getTongDoanhThu() {
        return tongDoanhThu;
    }
    
    public void setTongDoanhThu(BigDecimal tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }
    
    public int getSoHoaDon() {
        return soHoaDon;
    }
    
    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }
    
    @Override
    public String toString() {
        return "ThongKeDoanhThu{" +
                "id=" + id +
                ", ngay=" + ngay +
                ", tongDoanhThu=" + tongDoanhThu +
                ", soHoaDon=" + soHoaDon +
                '}';
    }
}