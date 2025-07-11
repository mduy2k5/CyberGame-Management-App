package com.raven.receptionform;

import com.raven.classes.CustomerTypeClass;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.CustomerType;
import com.raven.dbfunction.DBConnection;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Customer_Reception extends javax.swing.JPanel {
    private static Connection conn;
    private static javax.swing.JComboBox<String> isolationlevel;
    public Customer_Reception() throws SQLException{
        Customer_Reception.conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        initComponents();
        JPanel customerPanel = createCustomerPanel(); // ← hoặc truyền mã khách hàng động
        setLayout(new BorderLayout());
        add(customerPanel, BorderLayout.CENTER);
    }
     private static void styleButton(JButton button) {
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    private static JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        // Input fields for customer details
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField dobField = new JTextField();
        JComboBox<String> customerTypeField = new JComboBox<>(); // Dropdown menu for customer type
        JTextField emailField = new JTextField();
    
        // Populate the dropdown menu with customer types
        ArrayList<CustomerTypeClass> customerTypes = CustomerType.SelectCType();
        for (CustomerTypeClass customerType : customerTypes) {
            customerTypeField.addItem(customerType.getMaHKH() + " - " + customerType.getTenHang());
        }
    
        // Add labels and input fields to the panel
        panel.add(new JLabel("Họ Tên:"));
        panel.add(nameField);
        panel.add(new JLabel("Địa Chỉ:"));
        panel.add(addressField);
        panel.add(new JLabel("Số Điện Thoại:"));
        panel.add(phoneField);
        panel.add(new JLabel("Ngày Sinh (YYYY-MM-DD):"));
        panel.add(dobField);
        panel.add(new JLabel("Hạng Khách Hàng:"));
        panel.add(customerTypeField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
    
        // Button to create a new customer
        JButton createButton = new JButton("Tạo Khách Hàng Mới");
        styleButton(createButton);
        isolationlevel = new JComboBox<String>();
        JPanel transactionpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // isolationlevel.removeAllItems();
        // changeisolationlevel = new JComboBox<>( new String [] {"TRANSACTION_READ_COMMITTED","TRANSACTION_SERIALIZABLE"});
        isolationlevel.addItem("READ COMMITTED");
        isolationlevel.addItem("SERIALIZABLE");
        
        JButton commit = new JButton("Commit");
        commit.addActionListener(e -> {
            btnCommitActionPerformed();
        });
        
        isolationlevel.addActionListener( e-> {isolationlevelActionPerformed();});
        
        transactionpanel.add(isolationlevel);
        transactionpanel.add(commit);
        // Add action listener to the button
        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String dob = dobField.getText();
            String selectedCustomerType = (String) customerTypeField.getSelectedItem(); // Get selected item
            String email = emailField.getText();
    
            // Validate input fields
            if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || dob.isEmpty() || selectedCustomerType == null || email.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Thiếu thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Extract the MAHKH (Customer Type ID) from the selected item
            String maHKH = selectedCustomerType.split(" - ")[0];
    
            // Call the CREATE_NEW_CUSTOMER procedure
            boolean success = Customer.callCreateNewCustomerTest(conn ,name, address, phone, dob, maHKH, email);
            if (success) {
                JOptionPane.showMessageDialog(panel, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
    
                // Clear input fields after successful creation
                nameField.setText("");
                addressField.setText("");
                phoneField.setText("");
                dobField.setText("");
                customerTypeField.setSelectedIndex(0); // Reset dropdown to the first item
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(panel, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Add the button to the panel
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(createButton);
        panel.add(transactionpanel);
        return panel;
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
            .addGap(0, 657, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private static void isolationlevelActionPerformed() {                                               
        String selected = (String) isolationlevel.getSelectedItem();
        if ("SERIALIZABLE".equals(selected)) {
            try {
                conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                conn.setAutoCommit(false);
                System.out.println("Modify: Serializable");
            } catch (SQLException ex) {
                Logger.getLogger(Deposit_Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                conn.setAutoCommit(false);
                System.out.println("Modify: Read Commited");
            } catch (SQLException ex) {
                Logger.getLogger(Deposit_Reception.class.getName()).log(Level.SEVERE, null, ex);
            }        
            }
    }      
    private static void btnCommitActionPerformed() {                                          
        try {
            conn.commit();
            System.out.println("Commit");
        } catch (SQLException ex) {
            System.out.println("Commit failed: " + ex);
        }
    }   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
