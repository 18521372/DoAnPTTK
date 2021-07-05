/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class HangHoa implements Serializable {
    private int mahanghoa;
    private String tenhanghoa;
    private Date ngaysx;
    private Date hsd;
    private String nhasx;
    private int soluong; 
    private String donvi;
    private int dongia;
    private Byte statusflag;

    public void setNhasx(String nhasx) {
        this.nhasx = nhasx;
    }

    public void setStatusflag(Byte statusflag) {
        this.statusflag = statusflag;
    }

    public String getNhasx() {
        return nhasx;
    }

    public Byte getStatusflag() {
        return statusflag;
    }

    public HangHoa() {
        
    }

    public void setMahanghoa(int mahanghoa) {
        this.mahanghoa = mahanghoa;
    }

    public void setTenhanghoa(String tenhanghoa) {
        this.tenhanghoa = tenhanghoa;
    }

    public void setNgaysx(Date ngaysx) {
        this.ngaysx = ngaysx;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getMahanghoa() {
        return mahanghoa;
    }

    public String getTenhanghoa() {
        return tenhanghoa;
    }

    public Date getNgaysx() {
        return ngaysx;
    }

    public Date getHsd() {
        return hsd;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getDonvi() {
        return donvi;
    }

    public int getDongia() {
        return dongia;
    }

    public HangHoa(int mahanghoa, String tenhanghoa, Date ngaysx, Date hsd, String nhasx, int soluong, String donvi, int dongia, Byte statusflag) {
        this.mahanghoa = mahanghoa;
        this.tenhanghoa = tenhanghoa;
        this.ngaysx = ngaysx;
        this.hsd = hsd;
        this.nhasx = nhasx;
        this.soluong = soluong;
        this.donvi = donvi;
        this.dongia = dongia;
        this.statusflag = statusflag;
    }

    
    
    
}
