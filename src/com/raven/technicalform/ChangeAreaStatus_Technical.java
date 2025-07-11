package com.raven.technicalform;

import com.raven.classes.AreaClass;
import com.raven.classes.ComputerClass;
import com.raven.classes.ComputerDDetailsClass;
import com.raven.classes.DeviceClass;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Computer;
import com.raven.dbfunction.ComputerDDetails;
import com.raven.dbfunction.Device;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import com.raven.swing.Table;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ChangeAreaStatus_Technical extends javax.swing.JPanel {
    
    public ChangeAreaStatus_Technical(){
        initComponents();
        
        // 1. Cấu hình layout dạng lưới cuộn được cho leftPanel
        mainPanel.setLayout(new BorderLayout());
        panel = new JPanel(new BorderLayout());
        // Table to display work hours
        String[] columnNames = {"Mã KV","Mã LKV", "Tên KV","Số lượng Máy", "Số Tầng","Trạng Thái"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        computerTable = new Table();
        computerTable.setModel(tableModel);
        scrollPane = new JScrollPane(computerTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
         
        panel.add(scrollPane, BorderLayout.CENTER);
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        StatusChange = new JButton("Thay đổi trạng thái");
        Refresh = new JButton("Refresh");

        StatusChange.addActionListener(e -> {
            int selectedRow = computerTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Vui lòng chọn 1 bản ghi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            opennewdialog(tableModel.getValueAt(selectedRow, 0).toString(), tableModel);
        });
        
        Refresh.addActionListener(e -> {
            loadcomputerdata(tableModel);
        });
        loadcomputerdata(tableModel);
        buttonpanel.add(Detail);
        buttonpanel.add(StatusChange);
        buttonpanel.add(Refresh);
        panel.add(buttonpanel, BorderLayout.SOUTH);
        mainPanel.add(panel);
    }
    private static void loadcomputerdata(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing rows

        ArrayList<AreaClass> areas = Area.SelectArea();
        for (AreaClass area : areas) {
            tableModel.addRow(new Object[]{
                area.getMaKV(),
                area.getMaLKV(),
                area.getTenKV(),
                area.getSoLuongMayKV(),
                area.getSoTang(),
                area.getTrangThai()
            });
        }
    }
    
    private void opennewdialog(String makv, DefaultTableModel tableModel){
        JDialog dialog = new JDialog((Frame) null,"Khu vực", true);
        dialog.setSize(300,120);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null);
        dialog.setBackground(Color.WHITE);
        JPanel maindialogPanel = new JPanel(new BorderLayout());
        maindialogPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); 
        maindialogPanel.setBackground(Color.WHITE);
        JPanel panel = new JPanel(new GridLayout(0,2,10,10));
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Trạng thái"));
        String[] cacloainhap = {"Bảo Trì","Hoạt Động","Đóng"};
        JComboBox loainhap = new JComboBox<>(cacloainhap);
        panel.add(loainhap);
        JButton addButton = new JButton("Add");
        maindialogPanel.add(panel, BorderLayout.CENTER);
        maindialogPanel.add(addButton, BorderLayout.SOUTH);
        dialog.add(maindialogPanel, BorderLayout.CENTER);
        addButton.addActionListener(e ->{
            try {
                Boolean success = Area.UpdateAreaStatus(makv,(String) loainhap.getSelectedItem());
                if (success) JOptionPane.showMessageDialog(panel, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(maindialogPanel, "Lỗi", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            loadcomputerdata(tableModel);
            dialog.dispose();
        });
        dialog.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new java.awt.Panel();
        panel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        computerTable = new com.raven.swing.Table();
        buttonpanel = new javax.swing.JPanel();
        Detail = new javax.swing.JButton();
        StatusChange = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(915, 620));

        computerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPane.setViewportView(computerTable);

        Detail.setText("jButton1");

        StatusChange.setText("jButton1");

        Refresh.setText("jButton1");

        javax.swing.GroupLayout buttonpanelLayout = new javax.swing.GroupLayout(buttonpanel);
        buttonpanel.setLayout(buttonpanelLayout);
        buttonpanelLayout.setHorizontalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StatusChange)
                .addGap(47, 47, 47)
                .addComponent(Detail)
                .addGap(41, 41, 41)
                .addComponent(Refresh)
                .addGap(249, 249, 249))
        );
        buttonpanelLayout.setVerticalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonpanelLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatusChange, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE))
                .addGap(537, 537, 537))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Detail;
    private javax.swing.JButton Refresh;
    private javax.swing.JButton StatusChange;
    private javax.swing.JPanel buttonpanel;
    private com.raven.swing.Table computerTable;
    private java.awt.Panel mainPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
