/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author PC
 */
public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private String ngaySinh;
    private String diaChi;
    private int soDienThoai;
    private String email;
    private int gioiTinh; //0= Nu, 1 = Nam
    private String chucVu;
    private String queQuan;
    private String ngayVaoLam;
    private String hinhAnh;
    private String username;
    private String password;
    private int luongCoBan;
    private int luongThuong;
    private float heSoLuong;
    private String ghiChu;
    private int statusFlag; //0 = disable, 1 = active

    public NhanVien(int maNhanVien, String tenNhanVien, String ngaySinh, String diaChi, int soDienThoai, String email, int gioiTinh, String chucVu, String queQuan, String ngayVaoLam, String hinhAnh, String username, String password, int luongCoBan, int luongThuong, float heSoLuong, int statusFlag, String ghiChu) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.queQuan = queQuan;
        this.ngayVaoLam = ngayVaoLam;
        this.hinhAnh = hinhAnh;
        this.username = username;
        this.password = password;
        this.luongCoBan = luongCoBan;
        this.luongThuong = luongThuong;
        this.heSoLuong = heSoLuong;
        this.ghiChu = ghiChu;
        this.statusFlag = statusFlag;
    }


 
    public NhanVien() {
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(int luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public int getLuongThuong() {
        return luongThuong;
    }

    public void setLuongThuong(int luongThuong) {
        this.luongThuong = luongThuong;
    }

    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(int statusFlag) {
        this.statusFlag = statusFlag;
    }
    
    
}
