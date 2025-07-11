/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.service;

import javax.swing.JFrame;
import otherfunction.LoginUIVer1;

/**
 *
 * @author maing
 */
public class ServiceLogout {
    public static void logout(JFrame currentFrame) throws java.sql.SQLException {
        // Đóng cửa sổ hiện tại
        currentFrame.dispose();
        // Mở lại cửa sổ đăng nhập
        LoginUIVer1 loginFrame = new LoginUIVer1();
        loginFrame.setVisible(true);
    }

}
