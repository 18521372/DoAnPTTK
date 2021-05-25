/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ACER
 */
public class BChuyenMangHinh {

    public JPanel root;

    public BChuyenMangHinh(JPanel jPanel1) {
        this.root=jPanel1;
    }
    public void setview(JPanel jfrChucNang){
        jfrChucNang.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(jfrChucNang);
        root.validate();
        root.repaint();
    } 
}
