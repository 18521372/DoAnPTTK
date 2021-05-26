/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DBConnect;
import GUI.sidebarGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class BDangNhap {
    public JButton jbtnLogin;
    public JButton jbtnExit;
    public JTextField jtfUser;
    public JTextField jtfPass;
    public JFrame jfr;
    public static String nameLogin = "";

    public BDangNhap(JFrame jfr, JButton jbtnLogin, JTextField jtfUser, JTextField jtfPass,JPanel jpanel1) {
        this.jfr = jfr;
        this.jbtnLogin = jbtnLogin;
        this.jtfUser = jtfUser;
        this.jtfPass = jtfPass;
    }

    public void setView() {
        this.jfr.setLocationRelativeTo(null);
        this.jfr.setTitle("Đăng nhập phần mềm");
        jtfUser.requestFocus();

    }
    public void DangNhap(){
        String user = jtfUser.getText();
        String pass = jtfPass.getText();
        try {
            Connection con = DBConnect.getConnection();
            String sql = "SELECT * FROM taikhoan";
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (user.equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
                        nameLogin = user;
                        new sidebarGUI().setVisible(true); 
                        jfr.setVisible(false);
                }
            }
            con.close();
            if (nameLogin.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(jfr, "Tên người dùng hoặc mật khẩu không hợp lệ\nXin vui lòng thử lại!", "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                reset();
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(BDangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void reset() {
        jtfUser.requestFocus();
        jtfUser.setText("");
        jtfPass.setText("");
    }
    
}
