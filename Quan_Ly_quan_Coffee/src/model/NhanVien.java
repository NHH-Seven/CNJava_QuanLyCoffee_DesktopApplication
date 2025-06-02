package model;

import java.util.Date;

public class NhanVien {
    private int id;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String soDienThoai;
    private String diaChi;
    private Date ngayVaoLam;
    private int nguoiDungId;
    private String soCCCD;
    
    // Constructors
    public NhanVien() {}
    
    public NhanVien(String hoTen, String gioiTinh, Date ngaySinh, String soDienThoai, 
                   String diaChi, Date ngayVaoLam, String soCCCD) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
        this.soCCCD = soCCCD;
    }
    
    public NhanVien(int id, String hoTen, String gioiTinh, Date ngaySinh, String soDienThoai, 
                   String diaChi, Date ngayVaoLam, int nguoiDungId, String soCCCD) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
        this.nguoiDungId = nguoiDungId;
        this.soCCCD = soCCCD;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getGioiTinh() {
        return gioiTinh;
    }
    
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public Date getNgaySinh() {
        return ngaySinh;
    }
    
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }
    
    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }
    
    public int getNguoiDungId() {
        return nguoiDungId;
    }
    
    public void setNguoiDungId(int nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }
    
    public String getSoCCCD() {
        return soCCCD;
    }
    
    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }
    
    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + '}';
    }
}
