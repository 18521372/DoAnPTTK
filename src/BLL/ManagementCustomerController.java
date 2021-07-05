/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Constant;
import BLL.ViewUtil;
import DTO.KhachHang;
import BLL.KhachHangService;
import GUI.DialogConfirm;
import GUI.DialogConfirm;
import GUI.DialogConfirmDelete;
import GUI.DialogNotificationSuccess;
import GUI.MenuGUI;
import GUI.ManagementCustomer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Son
 */
public class ManagementCustomerController {
    
    private  ManagementCustomer managementCustomerView;
    
    private  KhachHangService khachHangService;
    
    private JTable tableListCustomer;
    
    private DefaultTableModel  tableModel;
    
    private List<KhachHang> khachhangs;
    
    private MenuGUI homeView;
    
   private static final String[] COLUMNS = {"STT", "Mã khách hàng", "Họ và tên", "Giới tính", "SĐT", "Email", "Ghi chú"};
    
    public ManagementCustomerController(ManagementCustomer managementCustomer){
       this.managementCustomerView = managementCustomer;
       this.khachHangService = new KhachHangService();
       ViewUtil.showView(managementCustomer);
       initData();
       eventHandle();
    }
    
    public void eventHandle() {
        managementCustomerView.getBtnUpdateImage().addActionListener(e -> updateAvatar());
        managementCustomerView.getjBack().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               managementCustomerView.setVisible(false);
               homeView.setVisible(true);
            }
        });
        managementCustomerView.getInputCustomerCode().setEditable(false);
        tableListCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableListCustomer.rowAtPoint(e.getPoint());
                KhachHang khClicked = khachhangs.get(row);
                managementCustomerView.getInputCustomerName().setText(khClicked.getTenKH());
                managementCustomerView.getCbSex().setSelectedIndex(khClicked.getGioiTinh() ? 0 : 1);
                Calendar cal = Calendar.getInstance();
                Date birthDay = khClicked.getNgaySinh();
                cal.setTime(birthDay);
                managementCustomerView.getInputDay().setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
                managementCustomerView.getInputMonth().setText(String.valueOf(cal.get(Calendar.MONTH) + 1));
                managementCustomerView.getInputYear().setText(String.valueOf(cal.get(Calendar.YEAR)));
                managementCustomerView.getInputPhoneNumber().setText(String.valueOf(khClicked.getSoDienThoai()));
                managementCustomerView.getInputEmail().setText(khClicked.getEmail());
                managementCustomerView.getInputAddress().setText(khClicked.getDiaChi());
                managementCustomerView.getInputCustomerCode().setText(String.valueOf(khClicked.getMaKH()));
            }
        });
        managementCustomerView.getjSave().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (managementCustomerView.getInputCustomerCode().getText() != null ||
                        managementCustomerView.getInputCustomerCode().getText() != "") {
                    showDialogConfirmChange();
                }
            }
        });
        
        managementCustomerView.getjAdd().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               KhachHang kh = new KhachHang();
                kh.setDiaChi(managementCustomerView.getInputAddress().getText());
                kh.setTenKH(managementCustomerView.getInputCustomerName().getText());
                kh.setGioiTinh(managementCustomerView.getCbSex().getSelectedIndex() == 0 ? Boolean.TRUE : Boolean.FALSE);
                kh.setSoDienThoai(Integer.valueOf(managementCustomerView.getInputPhoneNumber().getText()));
                kh.setEmail(managementCustomerView.getInputEmail().getText());
                kh.setDiaChi(managementCustomerView.getInputAddress().getText());
                Calendar cal = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                c.set(Integer.parseInt(managementCustomerView.getInputYear().getText()), Integer.parseInt(managementCustomerView.getInputMonth().getText()) - 1 , Integer.parseInt(managementCustomerView.getInputDay().getText()), 0, 0);
                kh.setNgaySinh(new Date(c.getTimeInMillis()));
                kh.setStatusFlag(Boolean.TRUE);
                //kh.setMaKH(Long.valueOf(managementCustomerView.getInputCustomerCode().getText()));
                khachHangService.create(kh);
                managementCustomerView.getInputAddress().setText("");
                managementCustomerView.getInputCustomerName().setText("");
                managementCustomerView.getInputPhoneNumber().setText("");
                managementCustomerView.getInputEmail().setText("");
                managementCustomerView.getInputAddress().setText("");
                managementCustomerView.getInputYear().setText("");
                managementCustomerView.getInputMonth().setText("");
                managementCustomerView.getInputDay().setText("");
                
                khachhangs = khachHangService.findAll();
                for (int j = 0 ; j < khachhangs.size() ; j ++) {
                     if (tableModel.getRowCount() < j + 1) {
                        tableModel.addRow(new Object[]{tableModel.getRowCount() +1,
                        khachhangs.get(j).getMaKH(), 
                        khachhangs.get(j).getTenKH(),
                        khachhangs.get(j).getGioiTinh() == true ? Constant.MALE : Constant.FEMALE,
                        khachhangs.get(j).getSoDienThoai(), 
                        khachhangs.get(j).getEmail(), ""});
                    }else {
                        tableModel.setValueAt(j + 1, j, 0);
                        tableModel.setValueAt(khachhangs.get(j).getMaKH(), j, 1);
                        tableModel.setValueAt(khachhangs.get(j).getTenKH(), j, 2);
                        tableModel.setValueAt(khachhangs.get(j).getGioiTinh() == true ? Constant.MALE : Constant.FEMALE, j, 3);
                        tableModel.setValueAt(khachhangs.get(j).getSoDienThoai(), j, 4);
                        tableModel.setValueAt(khachhangs.get(j).getEmail(), j, 5);
                        tableModel.setValueAt("", j, 6);
                     }
               }
                new DialogNotificationSuccessController(new DialogNotificationSuccess());
            } 
        });
        
                    managementCustomerView.getBtnXoa().addActionListener(e -> {
                   DialogConfirmDeleteController deleteController =  new DialogConfirmDeleteController(new DialogConfirmDelete());
                   deleteController.setManagementCustomerView(managementCustomerView);
                   deleteController.setKhachHangService(khachHangService);
                   deleteController.setTableListCustomer(tableListCustomer);
                   deleteController.setTableModel(tableModel);
                   deleteController.setMaKH(Long.valueOf(managementCustomerView.getInputCustomerCode().getText()));
                    });
    }
    
    private void initData() {
       tableListCustomer = managementCustomerView.getTableListCustomer();
       tableModel = (DefaultTableModel) tableListCustomer.getModel();
       for (int i = 0 ; i < COLUMNS.length ; i ++) {
           tableModel.addColumn(COLUMNS[i]);
       }
       khachhangs = khachHangService.findAll();
       for (int j = 0 ; j < khachhangs.size() ; j ++) {
               tableModel.addRow(new Object[]{j+1,
               khachhangs.get(j).getMaKH(), 
               khachhangs.get(j).getTenKH(),
               khachhangs.get(j).getGioiTinh() == true ? Constant.MALE : Constant.FEMALE,
               khachhangs.get(j).getSoDienThoai(), 
               khachhangs.get(j).getEmail(), ""});
       }
    }
    
    private void updateAvatar() {
        // update avartar for customer
    }
    
    private void showDialogConfirmChange() {
        KhachHang kh = new KhachHang();
        kh.setDiaChi(managementCustomerView.getInputAddress().getText());
        kh.setTenKH(managementCustomerView.getInputCustomerName().getText());
        kh.setGioiTinh(managementCustomerView.getCbSex().getSelectedIndex() == 0 ? Boolean.TRUE : Boolean.FALSE);
        kh.setSoDienThoai(Integer.valueOf(managementCustomerView.getInputPhoneNumber().getText()));
        kh.setEmail(managementCustomerView.getInputEmail().getText());
        kh.setDiaChi(managementCustomerView.getInputAddress().getText());
        Calendar cal = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(managementCustomerView.getInputYear().getText()), Integer.parseInt(managementCustomerView.getInputMonth().getText()) - 1 , Integer.parseInt(managementCustomerView.getInputDay().getText()), 0, 0);
        kh.setNgaySinh(new Date(c.getTimeInMillis()));
        kh.setMaKH(Long.valueOf(managementCustomerView.getInputCustomerCode().getText()));
        DialogConfirmController controller =  new DialogConfirmController(new DialogConfirm());
        controller.setKhachHang(kh);
        controller.setTable(tableModel);
        controller.setManagementCustomerController(this);
    }

    public MenuGUI getHomeView() {
        return homeView;
    }

    public void setHomeView(MenuGUI homeView) {
        this.homeView = homeView;
    }

    public List<KhachHang> getKhachhangs() {
        return khachhangs;
    }

    public void setKhachhangs(List<KhachHang> khachhangs) {
        this.khachhangs = khachhangs;
    }
    
    
}
