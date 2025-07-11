package com.raven.technicalform;

import com.raven.receptionform.*;
import com.raven.classes.AreaClass;
import com.raven.classes.EmployeeClass;
import com.raven.classes.RevenueClass;
import com.raven.classes.WorkHoursClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Employee;
import com.raven.dbfunction.Statistic;
import com.raven.dbfunction.WorkHours;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Information_Technical extends javax.swing.JPanel {
    public Information_Technical(String manv) {
        initComponents();
        EmployeeClass nhanvien = Employee.SelectEmployeeByID(manv);
        jLabel1.setText("Mã nhân viên: ");
        jLabel2.setText(nhanvien.getManv());
        jLabel3.setText("User id");
        jLabel4.setText(nhanvien.getUserId());
        jLabel5.setText("Họ tên");
        jLabel6.setText(nhanvien.getHoten());
        jLabel7.setText("SDT");
        jLabel8.setText(nhanvien.getSdt());
        jLabel9.setText("Ngày sinh");
        jLabel10.setText(nhanvien.getNgaysinh().toString());
        jLabel11.setText("Ngày vào làm");
        jLabel12.setText(nhanvien.getNgvl().toString());
        jLabel13.setText("Địa chỉ");
        jLabel14.setText(nhanvien.getDiachi());
        jLabel15.setText("Quyền hạn");
        jLabel16.setText(nhanvien.getRoleType());
        jLabel17.setText("Mã số thuế");
        jLabel18.setText(nhanvien.getMasothuecn());
        jLabel19.setText("BHYT");
        jLabel20.setText(nhanvien.getSobhyt());
        jLabel21.setText("Email");
        jLabel22.setText(nhanvien.getEmail());
        jLabel23.setText("Mã loại nhân viên");
        jLabel24.setText(nhanvien.getMalnv());



    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        panel = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(241, 241, 241));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(0, 2, 10, 10));

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);

        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4);

        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);

        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6);

        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7);

        jLabel8.setText("jLabel8");
        jPanel1.add(jLabel8);

        jLabel9.setText("jLabel9");
        jPanel1.add(jLabel9);

        jLabel10.setText("jLabel10");
        jPanel1.add(jLabel10);

        jLabel11.setText("jLabel11");
        jPanel1.add(jLabel11);

        jLabel12.setText("jLabel12");
        jPanel1.add(jLabel12);

        jLabel13.setText("jLabel13");
        jPanel1.add(jLabel13);

        jLabel14.setText("jLabel14");
        jPanel1.add(jLabel14);

        jLabel15.setText("jLabel15");
        jPanel1.add(jLabel15);

        jLabel16.setText("jLabel16");
        jPanel1.add(jLabel16);

        jLabel17.setText("jLabel17");
        jPanel1.add(jLabel17);

        jLabel18.setText("jLabel18");
        jPanel1.add(jLabel18);

        jLabel19.setText("jLabel19");
        jPanel1.add(jLabel19);

        jLabel20.setText("jLabel20");
        jPanel1.add(jLabel20);

        jLabel21.setText("jLabel21");
        jPanel1.add(jLabel21);

        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22);

        jLabel23.setText("jLabel23");
        jPanel1.add(jLabel23);

        jLabel24.setText("jLabel24");
        jPanel1.add(jLabel24);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 875, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
