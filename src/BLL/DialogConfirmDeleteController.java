/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Constant;
import DTO.KhachHang;
import BLL.KhachHangService;
import GUI.DialogConfirmDelete;
import GUI.ManagementCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Son
 */
public class DialogConfirmDeleteController {
    private DialogConfirmDelete dialogConfirmDeleteView;
    
    private KhachHang khachhang;
    
    private Long maKH;
    
    private JTable tableListCustomer;
    
    private DefaultTableModel tableModel;
    
    private ManagementCustomer managementCustomerView;
    
    private KhachHangService khachHangService;
    
    public DialogConfirmDeleteController(DialogConfirmDelete view) {
        this.dialogConfirmDeleteView = view;
        init();
        eventHandle();
    }
    
    private void init() {
        dialogConfirmDeleteView.setVisible(true);
        dialogConfirmDeleteView.setSize(400, 250);
        dialogConfirmDeleteView.setLocation(500, 300);
        dialogConfirmDeleteView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
     
    }
    
    private void eventHandle() {
        dialogConfirmDeleteView.getBtnDelete().addActionListener(e -> deleteKhachHang());
        dialogConfirmDeleteView.getBtnCancel().addActionListener(e-> dialogConfirmDeleteView.setVisible(false));
    }
    
    private void deleteKhachHang() {
        List<KhachHang> khachhangs = new ArrayList<>();
        if (managementCustomerView.getInputCustomerCode().getText() != "") {
                    int result = khachHangService.delete(maKH);
                    khachhangs = khachHangService.findAll();
                    if (result == 1) {
                        dialogConfirmDeleteView.setVisible(false);
                        tableModel.removeRow(tableListCustomer.getRowCount() - 1);
                        managementCustomerView.getInputAddress().setText("");
                        managementCustomerView.getInputCustomerName().setText("");
                        managementCustomerView.getInputPhoneNumber().setText("");
                        managementCustomerView.getInputEmail().setText("");
                        managementCustomerView.getInputAddress().setText("");
                        managementCustomerView.getInputCustomerCode().setText("");
                        managementCustomerView.getInputYear().setText("");
                        managementCustomerView.getInputMonth().setText("");
                        managementCustomerView.getInputDay().setText("");
                    }
                     for (int j = 0 ; j < khachhangs.size() ; j ++) {
                        tableModel.setValueAt(j + 1, j, 0);
                        tableModel.setValueAt(khachhangs.get(j).getMaKH(), j, 1);
                        tableModel.setValueAt(khachhangs.get(j).getTenKH(), j, 2);
                        tableModel.setValueAt(khachhangs.get(j).getGioiTinh() == true ? Constant.MALE : Constant.FEMALE, j, 3);
                        tableModel.setValueAt(khachhangs.get(j).getSoDienThoai(), j, 4);
                        tableModel.setValueAt(khachhangs.get(j).getEmail(), j, 5);
                        tableModel.setValueAt("", j, 6);
                    
               }
        }
    }

    public KhachHang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(KhachHang khachhang) {
        this.khachhang = khachhang;
    }

    public Long getMaKH() {
        return maKH;
    }

    public void setMaKH(Long maKH) {
        this.maKH = maKH;
    }

    public JTable getTableListCustomer() {
        return tableListCustomer;
    }

    public void setTableListCustomer(JTable tableListCustomer) {
        this.tableListCustomer = tableListCustomer;
    }

  

    public KhachHangService getKhachHangService() {
        return khachHangService;
    }

    public void setKhachHangService(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public ManagementCustomer getManagementCustomerView() {
        return managementCustomerView;
    }

    public void setManagementCustomerView(ManagementCustomer managementCustomerView) {
        this.managementCustomerView = managementCustomerView;
    }
    
    
}
