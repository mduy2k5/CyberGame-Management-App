package com.raven.customerform;

import com.raven.classes.DepositClass;
import com.raven.dbfunction.Depositer;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.sql.Connection;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;


public class Deposit_Customer extends javax.swing.JPanel {

    public Deposit_Customer(String MAKH) {
        initComponents();
        JPanel depositPanel = createDepositPanel(MAKH); // ← hoặc truyền mã khách hàng động
        setLayout(new BorderLayout());
        add(depositPanel, BorderLayout.CENTER);
    }
    
    private static void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
    
     public JPanel createDepositPanel(String maKH) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);
    
        // Top panel for deposit packs
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(Color.WHITE);
    
        // Deposit pack buttons
        JButton pack10k = new JButton("10,000 VND");
        JButton pack20k = new JButton("20,000 VND");
        JButton pack50k = new JButton("50,000 VND");
        JButton pack100k = new JButton("100,000 VND");
    
        // Style buttons
        styleButton(pack10k);
        styleButton(pack20k);
        styleButton(pack50k);
        styleButton(pack100k);
    
        // Add buttons to the top panel
        topPanel.add(pack10k);
        topPanel.add(pack20k);
        topPanel.add(pack50k);
        topPanel.add(pack100k);
    
        // Bottom panel for table and actions
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
    
        // Table for displaying deposit records
        String[] columnNames = {"Mã GD", "Mã KH", "Thời Gian", "Phương thức", "Số tiền", "Trạng thái"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable depositTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(depositTable);
    
        // Populate the table with deposit records
        populateTable(maKH, tableModel);
        bottomPanel.add(tableScrollPane, BorderLayout.CENTER);
        // Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Trở Về"); // New "Back" button
    
        // Style buttons
        refreshButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        refreshButton.setBackground(new Color(144, 198, 124));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        styleButton(backButton); // Style the "Back" button
    
        // Add buttons to the action panel
        actionPanel.add(refreshButton);
    
        // Add components to the bottom panel
        bottomPanel.add(actionPanel, BorderLayout.SOUTH);
    
        // Add top and bottom panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);
    
        // Action for "Refresh" button
        refreshButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            populateTable(maKH, tableModel);
        });
    

    
        // Action for "Back" button
        backButton.addActionListener(e -> {
            // Show the top panel and hide the table
            topPanel.setVisible(true);
            bottomPanel.removeAll();
            bottomPanel.add(actionPanel, BorderLayout.SOUTH);
    
            // Hide the "Back" button
            actionPanel.remove(backButton);
    
            bottomPanel.revalidate();
            bottomPanel.repaint();
        });
    
        
        // Action for deposit pack buttons
        pack10k.addActionListener(e -> openDepositDialog(maKH, "10,000 VND", tableModel));
        pack20k.addActionListener(e -> openDepositDialog(maKH, "20,000 VND", tableModel));
        pack50k.addActionListener(e -> openDepositDialog(maKH, "50,000 VND", tableModel));
        pack100k.addActionListener(e -> openDepositDialog(maKH, "100,000 VND", tableModel));
        return mainPanel;
    }

    private void openDepositDialog(String maKH, String packCost, DefaultTableModel tablemodel) {
        // Open a dialog for deposit
        JDialog depositDialog = new JDialog((Frame) null, "Nạp", true);
        depositDialog.setSize(400, 600);
        depositDialog.setLayout(new BorderLayout());
    
        // Load and resize the QR image
        ImageIcon qrIcon = null;
        try {
            ImageIcon originalIcon = new ImageIcon(Deposit_Customer.class.getResource("/com/raven/images/QR.png")); // Adjust the path as needed
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Resize to 300x300
            qrIcon = new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.err.println("❌ Error loading QR image: " + e.getMessage());
        }
    
        JLabel qrLabel = new JLabel(qrIcon);
        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositDialog.add(qrLabel, BorderLayout.CENTER);
    
        // Panel for dropdown and "Add" button
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    
        // Dropdown menu for "Hinh thuc"
        JLabel dropdownLabel = new JLabel("Hình thức:");
        JComboBox<String> dropdownMenu = new JComboBox<>(new String[]{"Online", "Offline"});
    
        // "Add" button
        JButton addButton = new JButton("Nạp");
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        addButton.setBackground(new Color(144, 198, 124));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        // Add components to the action panel
        actionPanel.add(dropdownLabel);
        actionPanel.add(dropdownMenu);
        actionPanel.add(addButton);
    
        depositDialog.add(actionPanel, BorderLayout.SOUTH);
    
        // Text below the QR code
        JLabel textLabel = new JLabel("NAPTIEN " + packCost + " " + maKH, JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        depositDialog.add(textLabel, BorderLayout.NORTH);
    
        // Action for "Add" button
        addButton.addActionListener(e -> {
            String hinhThuc = (String) dropdownMenu.getSelectedItem(); // Get selected value from dropdown
            int soTien = Integer.parseInt(packCost.replace(",", "").replace(" VND", "")); // Parse pack cost to integer
    
            // Call the Deposit function
            boolean success = Depositer.Deposit(maKH, soTien, hinhThuc, " "); // Use "Hinh thuc" as "Trang thai"
            if (success) {
                JOptionPane.showMessageDialog(depositDialog, "Nạp tiền thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
                populateTable(maKH, tablemodel);
                depositDialog.dispose(); // Close the dialog
            } else {
                JOptionPane.showMessageDialog(depositDialog, "Nạp tiền thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        depositDialog.setLocationRelativeTo(null);
        depositDialog.setVisible(true);
    }
    private void populateTable(String maKH, DefaultTableModel tableModel) {
        ArrayList<DepositClass> deposits = Depositer.SelectDeposit(maKH);
        tableModel.setRowCount(0);
        for (DepositClass deposit : deposits) {
            tableModel.addRow(new Object[]{
                    deposit.getMaGD(),
                    deposit.getMaKH(),
                    deposit.getCreatedAt(),
                    deposit.getHinhThuc(),
                    deposit.getSoTien(),
                    deposit.getTrangThai(),
                    "Hành động" // Placeholder for the action column
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(915, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
