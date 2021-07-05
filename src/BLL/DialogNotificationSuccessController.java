/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import GUI.DialogNotificationSuccess;
import javax.swing.JFrame;

/**
 *
 * @author Son
 */
public class DialogNotificationSuccessController {
    
    private DialogNotificationSuccess dialogNotificationSuccess;
    
    public DialogNotificationSuccessController(DialogNotificationSuccess dialog) {
        this.dialogNotificationSuccess = dialog;
        init();
        eventHandle();
    }
    
    private void init() {
        dialogNotificationSuccess.setVisible(true);
        dialogNotificationSuccess.setSize(400, 250);
        dialogNotificationSuccess.setLocation(500, 300);
        dialogNotificationSuccess.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    private void eventHandle() {
        dialogNotificationSuccess.getBtnClose().addActionListener(e -> dialogNotificationSuccess.setVisible(false));
    }
}
