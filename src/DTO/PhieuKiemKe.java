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
    public class PhieuKiemKe implements Serializable{
    private int maphieukiemke;
    private java.sql.Date ngayphieukiemke;
    private int trigia;
    private int manv ;
    private Byte statusflag;
    private ArrayList<HangHoa> listHangHoa;

    public PhieuKiemKe(int maphieukiemke,int manv, Date ngayphieukiemke, int trigia,  Byte statusflag, ArrayList<HangHoa> listHangHoa) {
        this.maphieukiemke = maphieukiemke;
        this.ngayphieukiemke = ngayphieukiemke;
        this.trigia = trigia;
        this.manv = manv;
        this.statusflag = statusflag;
        this.listHangHoa = listHangHoa;
    }

    public PhieuKiemKe() {
        this.listHangHoa = new ArrayList<HangHoa>();
        this.statusflag=-1;
    }



    public void setListHangHoa(ArrayList<HangHoa> listHangHoa) {
        this.listHangHoa = listHangHoa;
    }

    public ArrayList<HangHoa> getListHangHoa() {
        return listHangHoa;
    }

    public void setMaphieukiemke(int maphieukiemke) {
        this.maphieukiemke = maphieukiemke;
    }

    public void setNgayphieukiemke(Date ngayphieukiemke) {
        this.ngayphieukiemke = ngayphieukiemke;
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
    

    public int getMaphieukiemke() {
        return maphieukiemke;
    }

    public Date getNgayphieukiemke() {
        return ngayphieukiemke;
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
