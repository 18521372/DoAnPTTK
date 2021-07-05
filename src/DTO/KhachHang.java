package DTO;

import java.sql.Date;

/**
 * * Khach Hang
 *
 * @author Admin
 * @since 6/15/2021 12:20 SA
 */

public class KhachHang {
    private Long maKH;
    private String tenKH;
    private Date ngaySinh;
    private String diaChi;
    private Integer soDienThoai;
    private String email;
    private Boolean gioiTinh;
    private Boolean statusFlag;

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH=" + maKH +
                ", tenKH='" + tenKH + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai=" + soDienThoai +
                ", email='" + email + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", statusFlag=" + statusFlag +
                '}';
    }

    public Long getMaKH() {
        return maKH;
    }

    public void setMaKH(Long maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(Integer soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Boolean getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Boolean statusFlag) {
        this.statusFlag = statusFlag;
    }

    public KhachHang(Long maKH, String tenKH, Date ngaySinh, String diaChi, Integer soDienThoai, String email, Boolean gioiTinh, Boolean statusFlag) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.statusFlag = statusFlag;
    }
    
    public KhachHang() {}
}
