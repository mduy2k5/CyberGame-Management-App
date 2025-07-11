/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.technicalform;


import com.raven.classes.ComputerClass;
import com.raven.classes.ComputerDDetailsClass;
import com.raven.classes.DeviceClass;
import com.raven.classes.GiftClass;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Computer;
import com.raven.dbfunction.ComputerDDetails;
import com.raven.dbfunction.Device;
import com.raven.dbfunction.Gift;
import com.raven.swing.Table;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author RAVEN
 */
public class ChangeDeviceStatus_Technical extends javax.swing.JPanel {
    public void changeDeviceStatus(String maKH) {
        mainPanel.setLayout(new BorderLayout());
        panel = new JPanel(new BorderLayout());

        // Table to display work hours
        String[] columnNames = {"Mã TB","Tên TB", "Loại TB","Ngày", "Trạng thái"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        deviceTable = new Table();
        deviceTable.setModel(tableModel);
        scrollPane = new JScrollPane(deviceTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(scrollPane);

        Detail = new JButton("Thay đổi trạng thái");
        Detail.addActionListener(e->{
            int selectedRow = deviceTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Vui lòng chọn 1 bản ghi", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                opennewdialog(tableModel.getValueAt(selectedRow, 0).toString(), tableModel);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi định dạng", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        Refresh = new JButton("Refresh");
        Refresh.addActionListener(e->{
            loaddevicedata(tableModel);
        });
        loaddevicedata(tableModel);
        
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonpanel.add(Detail);
        buttonpanel.add(Refresh);
        mainPanel.add(buttonpanel, BorderLayout.SOUTH);
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.setVisible(true);
    }
    private void opennewdialog(String matb, DefaultTableModel tableModel){
        choice_device = new JDialog((Frame) null,"Thiết bị", true);
        choice_device.setSize(300,120);
        choice_device.setLayout(new BorderLayout());
        choice_device.setLocationRelativeTo(null);
        choice_device.setBackground(Color.WHITE);
        maindialogPanel = new JPanel(new BorderLayout());
        maindialogPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); 
        maindialogPanel.setBackground(Color.WHITE);
        JPanel panel = new JPanel(new GridLayout(0,2,10,10));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Trạng thái"));
        String[] cacloainhap = {"DALAP","HONG","HOATDONG"};
        JComboBox loainhap = new JComboBox<>(cacloainhap);
        panel.add(loainhap);
        JButton addButton = new JButton("Add");
        maindialogPanel.add(panel, BorderLayout.CENTER);
        maindialogPanel.add(addButton, BorderLayout.SOUTH);
        choice_device.add(maindialogPanel, BorderLayout.CENTER);
        addButton.addActionListener(e ->{
            try {
                Boolean success = Device.UpdateDeviceStatus(matb, (String) loainhap.getSelectedItem());
                if (success) JOptionPane.showMessageDialog(panel, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(maindialogPanel, "Lỗi", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            loaddevicedata(tableModel);
            choice_device.dispose();
        });
        choice_device.setVisible(true);
    }
    public ChangeDeviceStatus_Technical() {
        initComponents();
        changeDeviceStatus("KH001");
    }
    private static void loaddevicedata(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing rows

        ArrayList<DeviceClass> devices = Device.SelectDevice();
        for (DeviceClass device : devices) {
            tableModel.addRow(new Object[]{
                    device.getMatb(),
                    device.getTentb(),
                    device.getLoaitb(),
                    device.getCreatedAt(),
                    device.getTrangthai()
            });
         }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        choice_device = new javax.swing.JDialog();
        maindialogPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        deviceTable = new com.raven.swing.Table();
        buttonpanel = new javax.swing.JPanel();
        Refresh = new javax.swing.JButton();
        Detail = new javax.swing.JButton();

        javax.swing.GroupLayout maindialogPanelLayout = new javax.swing.GroupLayout(maindialogPanel);
        maindialogPanel.setLayout(maindialogPanelLayout);
        maindialogPanelLayout.setHorizontalGroup(
            maindialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        maindialogPanelLayout.setVerticalGroup(
            maindialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout choice_deviceLayout = new javax.swing.GroupLayout(choice_device.getContentPane());
        choice_device.getContentPane().setLayout(choice_deviceLayout);
        choice_deviceLayout.setHorizontalGroup(
            choice_deviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choice_deviceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maindialogPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        choice_deviceLayout.setVerticalGroup(
            choice_deviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choice_deviceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maindialogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(915, 620));

        deviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPane.setViewportView(deviceTable);

        Refresh.setText("jButton1");

        Detail.setText("jButton1");

        javax.swing.GroupLayout buttonpanelLayout = new javax.swing.GroupLayout(buttonpanel);
        buttonpanel.setLayout(buttonpanelLayout);
        buttonpanelLayout.setHorizontalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonpanelLayout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(388, Short.MAX_VALUE))
        );
        buttonpanelLayout.setVerticalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonpanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Detail;
    private javax.swing.JButton Refresh;
    private javax.swing.JPanel buttonpanel;
    private javax.swing.JDialog choice_device;
    private com.raven.swing.Table deviceTable;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel maindialogPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
