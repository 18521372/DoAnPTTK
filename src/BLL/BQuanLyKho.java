/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DBConnect;
import DTO.HangHoa;
import DTO.HoaDon;
import DTO.PhieuNhap;
import DTO.PhieuKiemKe;
import GUI.MenuGUI;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BQuanLyKho {

    public BQuanLyKho() {
    }
    
    
    public ArrayList <HangHoa> GetDataHangHoa(){
        Connection con=DBConnect.getConnection();
            String sql="SELECT * FROM `hanghoa` ";
            ArrayList<HangHoa> list=new ArrayList<HangHoa>();
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    list.add(new HangHoa(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getByte(6),rs.getString(7),rs.getInt(8),rs.getByte(9)));
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
    
    }
    public HangHoa GetHangHoa(int mahanghoa)
    {
        HangHoa HH=new HangHoa();
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `hanghoa` WHERE mahanghoa=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mahanghoa);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                System.out.print(rs.getInt(1));
                HH = new HangHoa(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8),rs.getByte(9));
            }
            else HH.setStatusflag((byte)-1);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return HH;
    
    }
     public HangHoa GetHangHoa(String smahanghoa){
        int mahanghoa=-1;
        HangHoa HH=new HangHoa();
        try{
            mahanghoa = parseInt(smahanghoa);
        }catch(NumberFormatException e){
            HH.setStatusflag((byte)-1);
            return HH;
        }
        return GetHangHoa(mahanghoa); 
    }
    public static void TangSoLuongHangHoa(int mahanghoa,int Tang)
    {
        Connection con=DBConnect.getConnection();
        String sql="UPDATE `hanghoa` SET `tonkho`=tonkho+? WHERE `mahanghoa`=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Tang);
            ps.setInt(2, mahanghoa);
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void ShowDataHangHoa(JTable jTable1) {
        jTable1. getSelectionModel().clearSelection();
        jTable1.removeAll();    
        DefaultTableModel DTM=(DefaultTableModel) jTable1.getModel();
        DTM.setRowCount(0);
        for (HangHoa HH:this.GetDataHangHoa()){
            HangHoa hh= HH;
            DTM.addRow(new Object[]{hh.getMahanghoa(),hh.getTenhanghoa(),hh.getNhasx(),hh.getSoluong(),hh.getDonvi(),hh.getDongia()});
        }
    }
/////////////////////////////////////////PHIEU NHAP/////////////////////////////////////////////////////
    public PhieuNhap GetDataPhieuNhap(int MaPN) {
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `phieunhap` WHERE maphieunhap =?";
        PhieuNhap PN=new PhieuNhap();
        Byte i=-1;
        PN.setStatusflag(i);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, MaPN);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                return new PhieuNhap(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(4),rs.getByte(5),GetDataCTPN(rs.getInt(1)));
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PN;
    }


    private ArrayList<HangHoa> GetDataCTPN(int MaPN) {
                    Connection con=DBConnect.getConnection();
            String sql="SELECT * FROM `hanghoa`AS hh,`chitietphieunhap`AS ct WHERE `maphieunhap`=? AND hh.mahanghoa=ct.mahanghoa";
            ArrayList<HangHoa> list=new ArrayList<HangHoa>();
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,MaPN);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    list.add(new HangHoa(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getByte(12),rs.getString(7),rs.getInt(8),rs.getByte(9)));
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
    }

    public void ShowDataPhieuNhap(JTable jTable1) {
        jTable1. getSelectionModel().clearSelection();
        jTable1.removeAll();    
        DefaultTableModel DTM=(DefaultTableModel) jTable1.getModel();
        DTM.setRowCount(0);
        for (PhieuNhap PN:this.GetDataPhieuNhap()){
            PhieuNhap pn= PN;
            DTM.addRow(new Object[]{pn.getMaphieunhap(),pn.getManv(),pn.getNgayphieunhap(),pn.getTrigia()});
        }
    }

    private ArrayList<PhieuNhap> GetDataPhieuNhap() {
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `phieunhap`";
        ArrayList<PhieuNhap> list=new ArrayList<PhieuNhap>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                list.add(new PhieuNhap(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(4),rs.getByte(5),GetDataCTPN(rs.getInt(1))));
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void ShowDataCTPN(JTable jTable1,ArrayList<HangHoa> HHlist) {
        jTable1.removeAll();
        int Tong=0;
        DefaultTableModel DTM=(DefaultTableModel) jTable1.getModel();
        DTM.setRowCount(0);
        for (HangHoa HH:HHlist){
            DTM.addRow(new Object[]{HH.getMahanghoa(),HH.getTenhanghoa(),HH.getDongia(),HH.getDonvi(),HH.getSoluong(),HH.getDongia()*HH.getSoluong()});
            Tong+=(HH.getDongia()*HH.getSoluong());
        }  
        DTM.addRow(new Object[]{"","","","","Tổng tiền: ",Tong});
    }



        public void ThemPhieuNhap(PhieuNhap PN) {
        Connection con=DBConnect.getConnection();
        String sql="INSERT INTO `phieunhap`( `manv`, `ngaylapphieu`, `giatri`, `statusflag`) VALUES (?,?,?,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,PN.getManv());
            ps.setDate(2,PN.getNgayphieunhap());
            ps.setInt(3,PN.getTrigia());
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
// getMaPhieuNhap
        Connection con2=DBConnect.getConnection();
            String sql2="SELECT maphieunhap FROM phieunhap PN WHERE NOT EXISTS(SELECT maphieunhap FROM phieunhap SS WHERE SS.maphieunhap>PN.maphieunhap)";
            try {
                PreparedStatement ps = con2.prepareStatement(sql2);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    PN.setMaphieunhap(rs.getInt(1));
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
            }

            for(HangHoa CTPN:PN.getListHangHoa()){
// luu CTPN
                ThemCTPN(PN,CTPN);
//Them soluong HangHoa
                BQuanLyKho.TangSoLuongHangHoa(CTPN.getMahanghoa(),CTPN.getSoluong());
            }
            
        JOptionPane.showMessageDialog(null, "Thêm Thành công ", "Thông báo ", JOptionPane.INFORMATION_MESSAGE);
            
        
    }
    public void ThemCTPN(PhieuNhap PN,HangHoa CTPN) {
        Connection con=DBConnect.getConnection();
        String sql="INSERT INTO `chitietphieunhap`(`maphieunhap`, `mahanghoa`, `soluongnhap`, `statusflag`) VALUES (?,?,?,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,PN.getMaphieunhap());
            ps.setInt(2,CTPN.getMahanghoa());
            ps.setInt(3,CTPN.getSoluong());
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


/////////////////////////////////////////PHIEU KIEM KE//////////////////////////////////////////
public PhieuKiemKe GetDataPhieuKiemKe(int MaPKK) {
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `phieukiemke` WHERE maphieukiemke =?";
        PhieuKiemKe PKK=new PhieuKiemKe();
        Byte i=-1;
        PKK.setStatusflag(i);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, MaPKK);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                return new PhieuKiemKe(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(4),rs.getByte(5),GetDataCTPKK(rs.getInt(1)));
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PKK;
    }



    private ArrayList<HangHoa> GetDataCTPKK(int MaPKK) {
                    Connection con=DBConnect.getConnection();
            String sql="SELECT * FROM `hanghoa`AS hh,`chitietphieukiemke`AS ct WHERE `maphieukiemke`=? AND hh.mahanghoa=ct.mahanghoa";
            ArrayList<HangHoa> list=new ArrayList<HangHoa>();
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,MaPKK);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    list.add(new HangHoa(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getByte(12),rs.getString(7),rs.getInt(8),rs.getByte(9)));
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
    }

    public void ShowDataPhieuKiemKe(JTable jTable1) {
        jTable1. getSelectionModel().clearSelection();
        jTable1.removeAll();    
        DefaultTableModel DTM=(DefaultTableModel) jTable1.getModel();
        DTM.setRowCount(0);
        for (PhieuKiemKe PKK:this.GetDataPhieuKiemKe()){
            PhieuKiemKe pn= PKK;
            DTM.addRow(new Object[]{pn.getMaphieukiemke(),pn.getManv(),pn.getNgayphieukiemke(),pn.getTrigia()});
        }
    }

    private ArrayList<PhieuKiemKe> GetDataPhieuKiemKe() {
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `phieukiemke`";
        ArrayList<PhieuKiemKe> list=new ArrayList<PhieuKiemKe>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                list.add(new PhieuKiemKe(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(4),rs.getByte(5),GetDataCTPKK(rs.getInt(1))));
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void ShowDataCTPKK(JTable jTable1,ArrayList<HangHoa> HHlist) {
        jTable1.removeAll();
        int Tong=0;
        DefaultTableModel DTM=(DefaultTableModel) jTable1.getModel();
        DTM.setRowCount(0);
        for (HangHoa HH:HHlist){
            DTM.addRow(new Object[]{HH.getMahanghoa(),HH.getTenhanghoa(),HH.getDongia(),HH.getDonvi(),HH.getSoluong(),HH.getDongia()*HH.getSoluong()});
            Tong+=(HH.getDongia()*HH.getSoluong());
        }  
        DTM.addRow(new Object[]{"","","","","Tổng tiền: ",Tong});
    }



        public void ThemPhieuKiemKe(PhieuKiemKe PKK) {
        Connection con=DBConnect.getConnection();
        String sql="INSERT INTO `phieukiemke`( `manv`, `ngaylapphieu`, `giatri`, `statusflag`) VALUES (?,?,?,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,PKK.getManv());
            ps.setDate(2,PKK.getNgayphieukiemke());
            ps.setInt(3,PKK.getTrigia());
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
// getMaPhieuKiemKe
        Connection con2=DBConnect.getConnection();
            String sql2="SELECT maphieukiemke FROM phieukiemke PKK WHERE NOT EXISTS(SELECT maphieukiemke FROM phieukiemke SS WHERE SS.maphieukiemke>PKK.maphieukiemke)";
            try {
                PreparedStatement ps = con2.prepareStatement(sql2);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    PKK.setMaphieukiemke(rs.getInt(1));
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
            }

            for(HangHoa CTPKK:PKK.getListHangHoa()){
// luu CTPKK
                ThemCTPKK(PKK,CTPKK);
//Them soluong HangHoa
                BQuanLyKho.TangSoLuongHangHoa(CTPKK.getMahanghoa(),CTPKK.getSoluong());
            }
            
        JOptionPane.showMessageDialog(null, "Thêm Thành công ", "Thông báo ", JOptionPane.INFORMATION_MESSAGE);
            
        
    }
    public void ThemCTPKK(PhieuKiemKe PKK,HangHoa CTPKK) {
        Connection con=DBConnect.getConnection();
        String sql="INSERT INTO `chitietphieukiemke`(`maphieukiemke`, `mahanghoa`, `soluongkiemke`, `statusflag`) VALUES (?,?,?,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,PKK.getMaphieukiemke());
            ps.setInt(2,CTPKK.getMahanghoa());
            ps.setInt(3,CTPKK.getSoluong());
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyKho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
