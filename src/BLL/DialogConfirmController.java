/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Constant;
import DTO.KhachHang;
import BLL.KhachHangService;
import GUI.DialogConfirm;
import GUI.DialogNotificationSuccess;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Son
 */
public class DialogConfirmController {
    private DialogConfirm dialogConfirm;
    
    private KhachHang khachHang;
    
    private DefaultTableModel table;
    
    private KhachHangService khachHangService;
    
    private ManagementCustomerController managementCustomerController;
    
    private List<KhachHang> khachhangs = new ArrayList<>();
    
    public DialogConfirmController(DialogConfirm dialogConfirm) {
        this.dialogConfirm = dialogConfirm;
        init();
        eventHandle();
        khachHangService = new KhachHangService();
    }
    
    private void init() {
        dialogConfirm.setVisible(true);
        dialogConfirm.setSize(400, 250);
        dialogConfirm.setLocation(500, 300);
        dialogConfirm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    private void eventHandle() {
        this.dialogConfirm.getBtnCancel().addActionListener(e -> {this.dialogConfirm.setVisible(false);});
        this.dialogConfirm.getBtnSave().addActionListener(e -> saveCustomer());
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public DefaultTableModel getTable() {
        return table;
    }

    public void setTable(DefaultTableModel table) {
        this.table = table;
    }
    
    private void saveCustomer() {
        khachHangService.update(khachHang);
        khachhangs = khachHangService.findAll();
        managementCustomerController.setKhachhangs(khachhangs);
       this.dialogConfirm.setVisible(false);
       new DialogNotificationSuccessController(new DialogNotificationSuccess());
        for (int j = 0 ; j < khachhangs.size() ; j ++) {
           table.setValueAt(j + 1, j, 0);
           table.setValueAt(khachhangs.get(j).getMaKH(), j, 1);
           table.setValueAt(khachhangs.get(j).getTenKH(), j, 2);
           table.setValueAt(khachhangs.get(j).getGioiTinh() == true ? Constant.MALE : Constant.FEMALE, j, 3);
           table.setValueAt(khachhangs.get(j).getSoDienThoai(), j, 4);
           table.setValueAt(khachhangs.get(j).getEmail(), j, 5);
           table.setValueAt("", j, 6);
       }
    }

    public ManagementCustomerController getManagementCustomerController() {
        return managementCustomerController;
    }

    public void setManagementCustomerController(ManagementCustomerController managementCustomerController) {
        this.managementCustomerController = managementCustomerController;
    }
    
    
}
