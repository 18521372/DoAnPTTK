/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
    public class HoaDon implements Serializable{
    private int mahoadon;
    private java.sql.Date ngayhoadon;
    private int trigia;
    private int makh ;
    private String ghichu;
    private int manv ;
    private Byte statusflag;
    private ArrayList<HangHoa> listHangHoa;

    public HoaDon(int mahoadon, Date ngayhoadon, int trigia, int makh, String ghichu, int manv, Byte statusflag, ArrayList<HangHoa> listHangHoa) {
        this.mahoadon = mahoadon;
        this.ngayhoadon = ngayhoadon;
        this.trigia = trigia;
        this.makh = makh;
        this.ghichu = ghichu;
        this.manv = manv;
        this.statusflag = statusflag;
        this.listHangHoa = listHangHoa;
    }

    public HoaDon() {
        this.listHangHoa = new ArrayList<HangHoa>();
        this.statusflag=-1;
    }



    public void setListHangHoa(ArrayList<HangHoa> listHangHoa) {
        this.listHangHoa = listHangHoa;
    }

    public ArrayList<HangHoa> getListHangHoa() {
        return listHangHoa;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public void setNgayhoadon(Date ngayhoadon) {
        this.ngayhoadon = ngayhoadon;
    }

    public void setTrigia(int trigia) {
        this.trigia = trigia;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public void setStatusflag(Byte statusflag) {
        this.statusflag = statusflag;
    }
    

    public int getMahoadon() {
        return mahoadon;
    }

    public Date getNgayhoadon() {
        return ngayhoadon;
    }

    public int getTrigia() {
        return trigia;
    }

    public int getMakh() {
        return makh;
    }

    public String getGhichu() {
        return ghichu;
    }

    public int getManv() {
        return manv;
    }

    public Byte getStatusflag() {
        return statusflag;
    }
    
}
