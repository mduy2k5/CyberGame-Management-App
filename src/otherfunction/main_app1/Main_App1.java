/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main_app1;
import OtherFunction.*;
import com.raven.customermains.createCustomerMainPanel;
import com.raven.dbfunction.User;
import com.raven.receptionmain.createReceptionMainPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import otherfunction.*;
/**
 *
 * @author congd
 */
public class Main_App1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        new otherfunction.LoginUIVer1().createmainpanel();
//        createReceptionMainPanel.main(User.SelectIDByUserID("US20250518170649","NHANVIEN"));
//        createCustomerMainPanel.main(User.SelectIDByUserID("US20250518160246","KHACHHANG"));
        
    }
    
}
