package model;

import java.util.Date;

public class NguoiDung {
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
    private Date ngayTao;
    
    // Constructors
    public NguoiDung() {}
    
    public NguoiDung(String tenDangNhap, String matKhau, String vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }
    
    public NguoiDung(int id, String tenDangNhap, String matKhau, String vaiTro, Date ngayTao) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.ngayTao = ngayTao;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTenDangNhap() {
        return tenDangNhap;
    }
    
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public String getVaiTro() {
        return vaiTro;
    }
    
    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
    public Date getNgayTao() {
        return ngayTao;
    }
    
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    @Override
    public String toString() {
        return "NguoiDung{" + "id=" + id + ", tenDangNhap=" + tenDangNhap + ", vaiTro=" + vaiTro + '}';
    }
}

