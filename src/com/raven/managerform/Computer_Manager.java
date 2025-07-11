package com.raven.managerform;

import com.raven.classes.AreaClass;
import com.raven.classes.ComputerClass;
import com.raven.classes.DeviceClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Computer;
import com.raven.dbfunction.Device;
import com.raven.dbfunction.Statistic;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class Computer_Manager extends javax.swing.JPanel {
    
    public void loadForm4_1(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<ComputerClass> computers = Computer.SelectComputer(); 

            for (ComputerClass computer : computers) {
                String mapc = computer.getMaPC();
                String ram = computer.getRam();
                String rom = computer.getRom();
                String cpu = computer.getCpu();
                String vga = computer.getVga();
                int somay = computer.getSoMay();
                String trangthai = computer.getTrangThai();
                String makv = computer.getMaKv();
                
                model.addRow(new Object[]{mapc, ram, rom, cpu, vga, somay, trangthai, makv});
            }
 
    }
    public Computer_Manager() {
        initComponents();
                ArrayList<String> result = Statistic.thongKeThietBi();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")), result.get(0), result.get(3), ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), result.get(1), result.get(4), ""));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), result.get(2), result.get(5), ""));
        //  add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        loadForm4_1();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();

        setBackground(new java.awt.Color(241, 241, 241));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh Sách Máy Tính");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PC", "RAM", "ROM", "CPU", "VGA", "Số Máy", "Trạng Thái", "Mã KV"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnInsert.setText("Thêm");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(spTable)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "PC", "MAPC");
        loadForm4_1();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!!");
            return;
        }

        ComputerClass computer = new ComputerClass();
        computer.setMaPC(table.getValueAt(selectedRow, 0).toString());
        computer.setRam(table.getValueAt(selectedRow, 1).toString());
        computer.setRom(table.getValueAt(selectedRow, 2).toString());
        computer.setCpu(table.getValueAt(selectedRow, 3).toString());
        computer.setVga(table.getValueAt(selectedRow, 4).toString());
        computer.setSoMay(Integer.parseInt(table.getValueAt(selectedRow, 5).toString()));
        computer.setTrangThai(table.getValueAt(selectedRow, 6).toString());
        computer.setMaKv(table.getValueAt(selectedRow, 7).toString());

        // Mở form cập nhật tổng quát
        Update updateForm = new Update((UpdatableEntity) computer, this::loadForm4_1); // constructor mới
        updateForm.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
//        ComputerClass computer = new ComputerClass();
//        Insert insertForm = new Insert((UpdatableEntity) computer, this::loadForm4_1);
//        insertForm.setVisible(true);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm máy tính");
        dialog.setSize(450, 360);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        JTextField ramField = new JTextField();
        ramField.setPreferredSize(new Dimension(200, 30));
        JTextField romField = new JTextField();
        romField.setPreferredSize(new Dimension(200, 30));
        JTextField cpuField = new JTextField();
        cpuField.setPreferredSize(new Dimension(200, 30));
        JTextField vgaField = new JTextField();
        vgaField.setPreferredSize(new Dimension(200, 30));
        JTextField soMayField = new JTextField();
        soMayField.setPreferredSize(new Dimension(200, 30));
        JComboBox<String> trangThaiComboBox = new JComboBox<>(new String[] {"HONG","HOATDONG","TRONG","SUACHUA"});
        trangThaiComboBox.setPreferredSize(new Dimension(200, 30));
        
        
        JComboBox<String> maKVComboBox = new JComboBox<>();
        maKVComboBox.setPreferredSize(new Dimension(200, 30));
    
        // Populate the ComboBox with "MAKV - TENKV" from the database
        ArrayList<AreaClass> areas = Area.SelectArea();
        for (AreaClass area : areas) {
            maKVComboBox.addItem(area.getMaKV() + " - " + area.getTenKV());
        }
    
        inputPanel.add(new JLabel("RAM:"));
        inputPanel.add(ramField);
        inputPanel.add(new JLabel("ROM:"));
        inputPanel.add(romField);
        inputPanel.add(new JLabel("CPU:"));
        inputPanel.add(cpuField);
        inputPanel.add(new JLabel("VGA:"));
        inputPanel.add(vgaField);
        inputPanel.add(new JLabel("Số Máy:"));
        inputPanel.add(soMayField);
        inputPanel.add(new JLabel("Trạng Thái:"));
        inputPanel.add(trangThaiComboBox);
        inputPanel.add(new JLabel("Mã KV:"));
        inputPanel.add(maKVComboBox);
    
        dialog.add(inputPanel);
    
        // Insert button
        JPanel insertbuttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton insertButton = new JButton("Thêm");
        insertButton.addActionListener(e -> {
            String ram = ramField.getText();
            String rom = romField.getText();
            String cpu = cpuField.getText();
            String vga = vgaField.getText();
            int soMay = Integer.parseInt(soMayField.getText());
            String trangThai = trangThaiComboBox.getSelectedItem().toString();
            String maKV = maKVComboBox.getSelectedItem().toString().split(" - ")[0]; // Extract MAKV
    
            Boolean check = Computer.AddComputer(ram, rom, cpu, vga, soMay, trangThai, maKV);
            if (check) JOptionPane.showMessageDialog(dialog, "Thêm thành công!");
            else JOptionPane.showMessageDialog(dialog, "Không thể thêm", "Error", JOptionPane.ERROR_MESSAGE);
            loadcomputerdetail(model); // Reload table data
            dialog.dispose(); // Close the dialog
        });
        insertbuttonPanel.add(insertButton);
        dialog.add(insertbuttonPanel);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed
private static void loadcomputerdetail(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing rows

        ArrayList<ComputerClass> computers = Computer.SelectComputer();
        for (ComputerClass computer : computers) {
            tableModel.addRow(new Object[]{
                    computer.getMaPC(),
                    computer.getRam(),
                    computer.getRom(),
                    computer.getCpu(),
                    computer.getVga(),
                    computer.getSoMay(),
                    computer.getTrangThai(),
                    computer.getMaKv()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private com.raven.component.Card card1;
    private com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
