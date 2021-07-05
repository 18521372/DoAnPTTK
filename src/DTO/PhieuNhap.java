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
    public class PhieuNhap implements Serializable{
    private int maphieunhap;
    private java.sql.Date ngayphieunhap;
    private int trigia;
    private int manv ;
    private Byte statusflag;
    private ArrayList<HangHoa> listHangHoa;

    public PhieuNhap(int maphieunhap,int manv, Date ngayphieunhap, int trigia,  Byte statusflag, ArrayList<HangHoa> listHangHoa) {
        this.maphieunhap = maphieunhap;
        this.ngayphieunhap = ngayphieunhap;
        this.trigia = trigia;
        this.manv = manv;
        this.statusflag = statusflag;
        this.listHangHoa = listHangHoa;
    }

    public PhieuNhap() {
        this.listHangHoa = new ArrayList<HangHoa>();
        this.statusflag=-1;
    }



    public void setListHangHoa(ArrayList<HangHoa> listHangHoa) {
        this.listHangHoa = listHangHoa;
    }

    public ArrayList<HangHoa> getListHangHoa() {
        return listHangHoa;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public void setNgayphieunhap(Date ngayphieunhap) {
        this.ngayphieunhap = ngayphieunhap;
    }

    public void setTrigia(int trigia) {
        this.trigia = trigia;
    }



    public void setManv(int manv) {
        this.manv = manv;
    }

    public void setStatusflag(Byte statusflag) {
        this.statusflag = statusflag;
    }
    

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public Date getNgayphieunhap() {
        return ngayphieunhap;
    }

    public int getTrigia() {
        return trigia;
    }




    public int getManv() {
        return manv;
    }

    public Byte getStatusflag() {
        return statusflag;
    }
    
}
