/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DBConnect;
import DTO.HangHoa;
import DTO.HoaDon;
import GUI.MenuGUI;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BQuanLyHoaDon {
    private javax.swing.JTable jTable;
    

    public BQuanLyHoaDon(JFrame jfr, JTable jTable1) {
        this.jTable = jTable1;
    }

    public BQuanLyHoaDon() {
        
    }
    public ArrayList <HangHoa> GetDataCTHD(int MaHD){
            Connection con=DBConnect.getConnection();
            String sql="SELECT * FROM `hanghoa`AS hh,`cthd`AS ct WHERE `mahd`=? AND hh.mahanghoa=ct.mahanghoa";
            ArrayList<HangHoa> list=new ArrayList<HangHoa>();
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,MaHD);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    list.add(new HangHoa(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getByte(12),rs.getString(7),rs.getInt(8),rs.getByte(9)));
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
    }
    public ArrayList<HoaDon> GetDataHoaDon(){
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `hoadon`";
        ArrayList<HoaDon> list=new ArrayList<HoaDon>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                list.add(new HoaDon(rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getByte(7),GetDataCTHD(rs.getInt(1))));
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void ShowDataHoaDon(){
        this.jTable. getSelectionModel().clearSelection();
        this.jTable.removeAll();    
        DefaultTableModel DTM=(DefaultTableModel) jTable.getModel();
        DTM.setRowCount(0);
        for (HoaDon HD:this.GetDataHoaDon()){
            HoaDon hd= HD;
            DTM.addRow(new Object[]{hd.getMahoadon(),hd.getMakh(),hd.getManv(),hd.getNgayhoadon(),hd.getTrigia()});
        }
    }


    public void DeleteHoaDon(HoaDon HD) {
       if(HD.getStatusflag()!=-1){ 
        Connection con=DBConnect.getConnection();
//Xóa CTHD
        String sql="DELETE FROM `cthd` WHERE `mahd`=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, HD.getMahoadon());
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(HangHoa CTHD:HD.getListHangHoa()){
//tăng soluong HangHoa
            BQuanLyKho.TangSoLuongHangHoa(CTHD.getMahanghoa(),CTHD.getSoluong());
        }
//Xóa Hóa Đơn
        Connection con1=DBConnect.getConnection();
        String sql1="DELETE FROM `hoadon` WHERE `mahoadon`=?";
        try {
            PreparedStatement ps = con1.prepareStatement(sql1);
            ps.setInt(1, HD.getMahoadon());
            ps.executeUpdate();
            con1.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ShowDataHoaDon();
       }
       else JOptionPane.showMessageDialog(null, "Chọn hóa đơn để xóa ", "Thông báo ", JOptionPane.ERROR_MESSAGE);
    }
    public void ShowDataCTHD(int MaHoaDon) {
        this.jTable.removeAll();
        int Tong=0;
        DefaultTableModel DTM=(DefaultTableModel) jTable.getModel();
        DTM.setRowCount(0);
        for (HangHoa HH:GetDataCTHD(MaHoaDon)){
            HangHoa hh= HH;
            DTM.addRow(new Object[]{hh.getMahanghoa(),hh.getTenhanghoa(),hh.getDongia(),hh.getDonvi(),hh.getSoluong(),hh.getDongia()*hh.getSoluong()});
            Tong+=(hh.getDongia()*hh.getSoluong());
        }    
        DTM.addRow(new Object[]{"","","","","","Tổng tiền: "+Tong});
    }
    public void ShowDataCTHD(ArrayList<HangHoa> HHlist) {
        this.jTable.removeAll();
        int Tong=0;
        DefaultTableModel DTM=(DefaultTableModel) jTable.getModel();  
        jTable.clearSelection();
        DTM.setRowCount(0);
        for (HangHoa HH:HHlist){
            DTM.addRow(new Object[]{HH.getMahanghoa(),HH.getTenhanghoa(),HH.getDongia(),HH.getDonvi(),HH.getSoluong(),HH.getDongia()*HH.getSoluong()});
            Tong+=(HH.getDongia()*HH.getSoluong());
        }  
        DTM.addRow(new Object[]{"","","","","Tổng tiền: ",Tong});
    }

    public void ThemHoaDon(HoaDon HD) {
        Connection con=DBConnect.getConnection();
        String sql="INSERT INTO hoadon(  trigia, makh, ghichu, manv, statusflag,ngayhoadon) VALUES (?,?,?,?,0,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,HD.getTrigia());
            ps.setInt(2,HD.getMakh());
            ps.setString(3,HD.getGhichu());
            ps.setInt(4,MenuGUI.manv);
            ps.setDate(5,HD.getNgayhoadon());
            ps.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Thêm Thành công ", "Thông báo ", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
// getMaHoaDon
        Connection con2=DBConnect.getConnection();
            String sql2="SELECT mahoadon FROM hoadon HD WHERE NOT EXISTS(SELECT mahoadon FROM hoadon SS WHERE SS.mahoadon>HD.mahoadon)";
            try {
                PreparedStatement ps = con2.prepareStatement(sql2);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    HD.setMahoadon(rs.getInt(1));
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }

            for(HangHoa CTHD:HD.getListHangHoa()){
// luu CTHD
                ThemCTHD(HD,CTHD);
//giam soluong HangHoa
                BQuanLyKho.TangSoLuongHangHoa(CTHD.getMahanghoa(),-CTHD.getSoluong());
            }
            
        
            
        
    }
    public void ThemCTHD(HoaDon HD,HangHoa CTHD) {
        Connection con=DBConnect.getConnection();
        String sql="INSERT INTO `cthd`(`mahd`, `mahanghoa`, `soluong`, `statusflag`) VALUES (?,?,?,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
//            
            ps.setInt(1,HD.getMahoadon());
            ps.setInt(2,CTHD.getMahanghoa());
            ps.setInt(3,CTHD.getSoluong());
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static boolean validateHD(HoaDon HD) {
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `khachhang`WHERE makh=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, HD.getMakh());
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                return true;
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public HoaDon GetDataHoaDon(int MaHD) {
        Connection con=DBConnect.getConnection();
        String sql="SELECT * FROM `hoadon`WHERE mahoadon=?";
        HoaDon HD=new HoaDon();
        Byte i=-1;
        HD.setStatusflag(i);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, MaHD);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                return new HoaDon(rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getByte(7),GetDataCTHD(rs.getInt(1)));
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return HD;
    }

    public void UpDateHoaDon(HoaDon HD) {
        Connection con=DBConnect.getConnection();
        String sql="UPDATE `hoadon` SET `ghichu`=? WHERE `mahoadon`=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, HD.getGhichu());
            ps.setInt(2, HD.getMahoadon());
            ps.executeUpdate();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
// thêm lại hàn hóa đả mua
        for(HangHoa CTHD:this.GetDataCTHD(HD.getMahoadon())){
                BQuanLyKho.TangSoLuongHangHoa(CTHD.getMahanghoa(),CTHD.getSoluong());
            }
// Xóa CTHD cũ
        Connection con1=DBConnect.getConnection();
        String sql1="DELETE FROM `cthd` WHERE `mahd`=?";
        try {
            PreparedStatement ps = con1.prepareStatement(sql1);
            ps.setInt(1, HD.getMahoadon());
            ps.executeUpdate();
            con1.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
// thêm lại CTHD mới
        for(HangHoa CTHD:HD.getListHangHoa()){
                ThemCTHD(HD,CTHD);
//giảm hàng hóa
                BQuanLyKho.TangSoLuongHangHoa(CTHD.getMahanghoa(),-CTHD.getSoluong());
            }
        JOptionPane.showMessageDialog(null, "sửa Thành công ", "Thông báo ", JOptionPane.INFORMATION_MESSAGE);
    }
}
