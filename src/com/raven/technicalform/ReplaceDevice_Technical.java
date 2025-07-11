/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.technicalform;


import com.raven.classes.AreaClass;
import com.raven.classes.ComputerClass;
import com.raven.classes.DeviceClass;
import com.raven.dbfunction.Product;
import com.raven.classes.ProductClass;
import com.raven.classes.ReplaceDeviceClass;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Computer;
import com.raven.dbfunction.ComputerDDetails;
import com.raven.dbfunction.DBConnection;
import com.raven.dbfunction.Device;
import com.raven.dbfunction.ReplaceDevice;
import com.raven.managerform.Customer_Manager;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class ReplaceDevice_Technical extends javax.swing.JPanel {

    private static Connection conn;
    private static String manv;
    /**
     * Creates new form Form_1
     * @param manv
     */
    public ReplaceDevice_Technical(String manv) throws SQLException {
        this.manv= manv;
        this.conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        initComponents();
        
        // 1. Cấu hình layout dạng lưới cuộn được cho leftPanel
        mainPanel.setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // 3 cột
        gridPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(450, 600));
        String[] columnNames = {"MATTTB", "MATB", "Trạng Thái", "Ngày Tạo", "Số Máy", "Số Tầng", "Tên KV"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        deviceTable = new Table();
        deviceTable.setModel(tableModel);
        ReplaceButton = new JButton("Replace");
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        btnPanel.setBackground(Color.WHITE);
        fillReplacementTable(tableModel);
        ReplaceButton.addActionListener(e->{
            int selectedRow = deviceTable.getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(mainPanel, "Vui lòng chọn 1 bản ghi","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else openDeviceDialog(tableModel.getValueAt(selectedRow, 0).toString());
        });
        JButton commitButton = new JButton("Commit");
        commitButton.addActionListener(e->{
            try {
                conn.commit();
                System.out.println("SUCCESS: Commited transaction successfull");
            } catch (SQLException ex) {
                System.out.println("ERROR: Failed to commit transaction");
            }
        });
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e->{
            fillReplacementTable(tableModel);
        });
        JComboBox<String> isolationLevelCombo = new JComboBox<>(new String[]{"READ_COMMITTED", "SERIALIZABLE"});
        isolationLevelCombo.addActionListener(e->{
            String choice = (String) isolationLevelCombo.getSelectedItem();
            if ("READ_COMMITTED".equals(choice)) try {
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                System.out.println("MODIFY: Read commited");
            } catch (SQLException ex) {
                Logger.getLogger(Customer_Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            else try {
                conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                System.out.println("MODIFY: Serializable");
            } catch (SQLException ex) {
                Logger.getLogger(Customer_Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnPanel.add(ReplaceButton);
        btnPanel.add(commitButton);
        btnPanel.add(refreshButton);
        btnPanel.add(isolationLevelCombo);
        scrollPane = new JScrollPane(deviceTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);


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
        jScrollPane2 = new javax.swing.JScrollPane();
        DeviceDialogTable = new javax.swing.JTable();
        mainPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        deviceTable = new com.raven.swing.Table();
        ReplaceButton = new javax.swing.JButton();

        DeviceDialogTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(DeviceDialogTable);

        javax.swing.GroupLayout choice_deviceLayout = new javax.swing.GroupLayout(choice_device.getContentPane());
        choice_device.getContentPane().setLayout(choice_deviceLayout);
        choice_deviceLayout.setHorizontalGroup(
            choice_deviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choice_deviceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        choice_deviceLayout.setVerticalGroup(
            choice_deviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choice_deviceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        setPreferredSize(new java.awt.Dimension(915, 620));

        mainPanel.setLayout(new java.awt.BorderLayout());

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

        mainPanel.add(scrollPane, java.awt.BorderLayout.PAGE_END);

        ReplaceButton.setText("Thay thế");
        mainPanel.add(ReplaceButton, java.awt.BorderLayout.PAGE_START);

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
                .addContainerGap(433, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );
    }// </editor-fold>//GEN-END:initComponents
    private static void fillReplacementTable(DefaultTableModel tableModel) {
        //{"MATTTB", "MATB", "Trạng Thái", "Ngày Tạo", "Số Máy", "Số Tầng", "Tên KV"};
        tableModel.setRowCount(0); // Clear existing rows

        ArrayList<ReplaceDeviceClass> devices = ReplaceDevice.SelectRDeviceNonReplaced();
        for (ReplaceDeviceClass device : devices) {
            ComputerClass cp = Computer.SelectComputerById(ComputerDDetails.SelectCDDetailsByMATB(device.getMaTB()).getMaPC());
            AreaClass area = Area.SelectAreaById(cp.getMaKv());
            tableModel.addRow(new Object[]{
                    device.getMaTTTB(),
                    device.getMaTB(),
                    device.getTrangThai(),
                    device.getCreatedAt(),
                    cp.getSoMay(),
                    area.getSoTang(),
                    area.getTenKV()
            });
        }
    }
    private static void openDeviceDialog(String matttb){
        JDialog choice_device = new JDialog((Frame) null,"Chọn thiết bị", true);
        choice_device.setSize(800,500);
        choice_device.setLayout(new BorderLayout());
        choice_device.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); 
        panel.setBackground(Color.WHITE);
        String[] columnNames = {"Mã Thiết Bị", "Tên Thiết Bị", "Loại", "Ngày", "Trạng thái"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        Table deviceTable = new Table();
        deviceTable.setBackground(Color.WHITE);
        deviceTable.setShowGrid(false);
        deviceTable.setBorder(null);
        deviceTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(deviceTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setBackground(Color.WHITE);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        buttonPanel.setBackground(Color.WHITE);
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        addButton.addActionListener(e->{
            int selectedRow = deviceTable.getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(choice_device, "Vui lòng chọn một bản ghi!","Warning",JOptionPane.WARNING_MESSAGE);
            }
            try {
                Boolean success = ReplaceDevice.ChangeDeviceTest(conn, matttb,manv,tableModel.getValueAt(selectedRow, 0).toString());
                if (success) JOptionPane.showMessageDialog(choice_device, "Thành công","Success",JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(choice_device, "Lỗi","Warning",JOptionPane.WARNING_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(choice_device, "Lỗi","Warning",JOptionPane.ERROR_MESSAGE);
            }
            
            choice_device.dispose();
        });
        
        loadDeviceData(tableModel);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        choice_device.add(panel);
        choice_device.setVisible(true);
    }
    private static void loadDeviceData(DefaultTableModel tableModel) {
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DeviceDialogTable;
    private javax.swing.JButton ReplaceButton;
    private javax.swing.JDialog choice_device;
    private com.raven.swing.Table deviceTable;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
