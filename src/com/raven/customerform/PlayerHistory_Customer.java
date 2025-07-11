/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.customerform;

import com.raven.classes.AreaClass;
import com.raven.classes.ComputerClass;
import com.raven.classes.ComputerDDetailsClass;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.User;
import com.raven.classes.CustomerClass;
import com.raven.classes.DeviceClass;
import com.raven.classes.PlayerHistoryClass;
import com.raven.classes.ProductClass;
import com.raven.classes.UsedServiceClass;
import com.raven.classes.UsersClass;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Computer;
import com.raven.dbfunction.ComputerDDetails;
import com.raven.dbfunction.Device;
import com.raven.dbfunction.PlayerHistory;
import com.raven.dbfunction.Product;
import com.raven.dbfunction.RentComputer;
import com.raven.dbfunction.ReplaceDevice;
import com.raven.dbfunction.UsedService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author RAVEN
 */
public class PlayerHistory_Customer extends javax.swing.JPanel {
    private String maKH;
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
    
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    class ReportButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String maKH;
        private DefaultTableModel tableModel;
        private String maTB;
    
        public ReportButtonEditor(JCheckBox checkBox, String maKH, DefaultTableModel tableModel) {
            super(checkBox);
            this.maKH = maKH;
            this.tableModel = tableModel;
    
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to report this device as broken?",
                        "Confirm Report",
                        JOptionPane.YES_NO_OPTION
                );
    
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean result = ReplaceDevice.AddRDeviceByCus(maTB);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Device reported successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to report device. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            maTB = table.getValueAt(row, 0).toString(); // Get MATB from the first column
            button.setText((value == null) ? "" : value.toString());
            return button;
        }
    
        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }
    
    public void loadForm4_1(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<PlayerHistoryClass> pls = PlayerHistory.SelectAllPHistoryByCusID(maKH); 

            for (PlayerHistoryClass pl : pls) {                
                model.addRow(new Object[]{pl.getMaLS(), pl.getNgayBD(), pl.getNgayKT(), pl.getStatus()});
            }
 
    }

    

    // Helper method to style buttons
    private static void styleButton(JButton button) {
        button.setBackground(new Color(144, 198, 124)); // Modern blue color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for hover effect
    }

    public PlayerHistory_Customer(String MAKH) {
        initComponents();
        this.maKH = MAKH;
        loadForm4_1();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Lịch Sử Chơi");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã LS", "Thời Gian BD", "Thời Gian KT", "Trạng Thái"
            }
        ));
        jScrollPane1.setViewportView(table);

        jButton1.setText("In Phiếu ");

        jButton2.setText("Xem Chi Tiết");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(279, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(298, 298, 298))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 lịch sử!");
            return;
        }
        JDialog dialog = new JDialog();
        dialog.setTitle("Thông tin chi tiết");
        dialog.setSize(720, 460);
        dialog.setLayout(new BorderLayout());
        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(0, 6, 10, 10));
        
        ComputerClass com = PlayerHistory.SelectComputerByPHId(table.getValueAt(selectedRow, 0).toString());
        
        inputPanel.add(new JLabel("Mã Máy"));
        inputPanel.add(new JLabel(com.getMaPC()));
        inputPanel.add(new JLabel("KV:"));
        inputPanel.add(new JLabel(com.getMaKv()));
        
        inputPanel.add(new JLabel("RAM"));
        inputPanel.add(new JLabel(com.getRam()));
        inputPanel.add(new JLabel("ROM:"));
        inputPanel.add(new JLabel(com.getRom()));
        inputPanel.add(new JLabel("CPU:"));
        inputPanel.add(new JLabel(com.getCpu()));
        inputPanel.add(new JLabel("VGA:"));
        inputPanel.add(new JLabel(com.getVga()));
        inputPanel.add(new JLabel("Số Máy:"));
        inputPanel.add(new JLabel(String.valueOf(com.getSoMay())));
        inputPanel.add(new JLabel("Trạng Thái:"));
        inputPanel.add(new JLabel(com.getTrangThai()));

        String[] columnNames = {"MADVSD", "MALS", "MASP", "TENSP", "SL", "Trạng thái","Thời gian"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable uServiceTable = new JTable(tableModel);
        
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        ArrayList<UsedServiceClass> uss = UsedService.SelectArrayUServiceByID(table.getValueAt(selectedRow, 0).toString());

        for (UsedServiceClass us : uss) {       
            ProductClass temp = Product.SelectProductByID(us.getMaSP());
            tableModel.addRow(new Object[]{us.getMaDVSD(), us.getMaLS(), us.getMaSP(), temp.getTenSP(), us.getSoLuong(), us.getSoLuong(), us.getCreatedat()});
        }
        
        JScrollPane scrollPane = new JScrollPane(uServiceTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        dialog.add(inputPanel, BorderLayout.NORTH);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
