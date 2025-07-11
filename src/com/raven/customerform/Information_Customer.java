/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.customerform;

import com.raven.classes.ComputerDDetailsClass;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.User;
import com.raven.classes.CustomerClass;
import com.raven.classes.DeviceClass;
import com.raven.classes.PlayerHistoryClass;
import com.raven.classes.UsersClass;
import com.raven.dbfunction.ComputerDDetails;
import com.raven.dbfunction.Device;
import com.raven.dbfunction.PlayerHistory;
import com.raven.dbfunction.RentComputer;
import com.raven.dbfunction.ReplaceDevice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author RAVEN
 */
public class Information_Customer extends javax.swing.JPanel {
    
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
                        "Bạn có muốn báo cáo hư hỏng?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );
    
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean result = ReplaceDevice.AddRDeviceByCus(maTB);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Đã báo cáo!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Báo cáo thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
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
    private void openReportBrokenDialog(String maKH, String maLS) {
        JDialog dialog = new JDialog((Frame) null, "Báo cáo thiết bị hỏng", true);
        dialog.setSize(800, 400);
        dialog.setLayout(new BorderLayout());
    
        // Add a title label
        JLabel titleLabel = new JLabel("Thiết bị: " + maLS, JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        dialog.add(titleLabel, BorderLayout.NORTH);
    
        // Create a table to display device details
        String[] columnNames = {"Mã TB", "Tên TB", "Loại TB", "Báo cáo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only the "Action" column is editable
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);
    
        // Fetch device details using SelectCDDetailsByMAPC
        String maPC = RentComputer.SelectRComputerByID(maLS).getMaPC();
        ArrayList<ComputerDDetailsClass> deviceDetails = ComputerDDetails.SelectCDDetailsByMAPC(maPC);
        for (ComputerDDetailsClass detail : deviceDetails) {
            // Fetch device name and type using SelectDeviceByID
            String deviceName = "";
            String deviceType = "";
            DeviceClass device = Device.SelectDeviceByID(detail.getMaTB());
            if (device != null) {
                deviceName = device.getTentb();
                deviceType = device.getLoaitb(); // Assuming LOAITB is a field in DeviceClass
            }
    
            tableModel.addRow(new Object[]{
                    detail.getMaTB(),
                    deviceName,
                    deviceType,
                    "Báo cáo"
            });
        }
    
        // Add a custom renderer and editor for the "Action" column
        table.getColumn("Báo cáo").setCellRenderer(new ButtonRenderer());
        table.getColumn("Báo cáo").setCellEditor(new ReportButtonEditor(new JCheckBox(), maKH, tableModel));
    
        // Add a "Close" button
        JButton closeButton = new JButton("Đóng");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setBackground(new Color(52, 152, 219)); // Blue color
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        closeButton.addActionListener(e -> dialog.dispose());
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
    
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    private void showCustomerInfo(CustomerClass c, UsersClass u, String MAKH) {
        CustomerClass customer = Customer.SelectCustomerByID(MAKH);
        UsersClass user = User.SelectUserByMAKH(MAKH);
        
        if (customer == null || user == null) {
            JLabel errorLabel = new JLabel("Thông tin của bạn: ", JLabel.CENTER);
            errorLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            add(errorLabel, BorderLayout.CENTER);
            return;
        }

        setLayout(new GridLayout(0, 2, 10, 10));  // 2 cột

        add(new JLabel("Mã khách hàng (MAKH):"));
        add(new JLabel(c.getMakh()));

        add(new JLabel("Mã User"));
        add(new JLabel(c.getUserid()));

        add(new JLabel("Hạng khách hàng"));
        add(new JLabel(c.getMahkh()));

        add(new JLabel("Điểm tích lũy"));
        add(new JLabel(String.valueOf(c.getSodiemtichluy())));

        add(new JLabel("Số dư:"));
        add(new JLabel(String.valueOf(c.getSdutk())));

        add(new JLabel("Trạng thái:"));
        add(new JLabel(c.getTrangthai()));

        add(new JLabel("Họ và tên:"));
        add(new JLabel(u.getHoTen()));

        add(new JLabel("Vai trò:"));
        add(new JLabel(u.getRoleType()));

        add(new JLabel("Số điện thoại:"));
        add(new JLabel(u.getSdt()));

        add(new JLabel("Date of Birth:"));
        add(new JLabel(u.getNgaySinh().toString()));

        add(new JLabel("Địa chỉ:"));
        add(new JLabel(u.getDiaChi()));

        add(new JLabel("Ngày tạo tài khoản:"));
        add(new JLabel(u.getCreatedAt().toString()));
    }
    
    public void createInformationPanel(String maKH) {
        PlayerHistoryClass playerHistory = PlayerHistory.SelectPHistoryByCusID(maKH);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        CustomerClass customer = Customer.SelectCustomerByID(maKH);
        UsersClass user = User.SelectUserByMAKH(maKH);  

        if (customer == null || user == null) {
            JLabel errorLabel = new JLabel("Không tìm  thấy thông tin" + maKH, JLabel.CENTER);
            errorLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            mainPanel.removeAll();
            mainPanel.add(errorLabel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            return;
        }

        JLabel titleLabel = new JLabel("Thông tin khách hàng", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(Color.WHITE);

        populateInfoPanel(infoPanel, customer, user);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton refreshButton = new JButton("Refresh");
        styleButton(refreshButton);
        refreshButton.addActionListener(e -> {
            infoPanel.removeAll();
            CustomerClass refreshedCustomer = Customer.SelectCustomerByID(maKH);
            UsersClass refreshedUser = User.SelectUserByMAKH(maKH);
            if (refreshedCustomer != null && refreshedUser != null) {
                populateInfoPanel(infoPanel, refreshedCustomer, refreshedUser);
            } else {
                infoPanel.add(new JLabel("Không tìm thấy thông tin " + maKH, JLabel.CENTER));
            }
            infoPanel.revalidate();
            infoPanel.repaint();
        });

        buttonPanel.add(refreshButton);
        
        JButton reportButton = new JButton("Báo cáo thiết bị");
        styleButton(reportButton);
        reportButton.addActionListener(e -> {
            openReportBrokenDialog(maKH, playerHistory.getMaLS());
        });
        buttonPanel.add(reportButton);
        // Xóa nội dung cũ trước khi add mới
        mainPanel.removeAll();
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(infoPanel), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }


    // Helper method to populate the info panel with customer and user data
    private static void populateInfoPanel(JPanel infoPanel, CustomerClass customer, UsersClass user) {
        addInfoRow(infoPanel, "Mã khách hàng: ", customer.getMakh());
        addInfoRow(infoPanel, "Mã User", customer.getUserid());
        addInfoRow(infoPanel, "Hạng khách hàng: ", customer.getMahkh());
        addInfoRow(infoPanel, "Điểm tích lũy: ", String.valueOf(customer.getSodiemtichluy()));
        addInfoRow(infoPanel, "Số dư: ", String.valueOf(customer.getSdutk()));
        addInfoRow(infoPanel, "Trạng thái:", customer.getTrangthai());
        addInfoRow(infoPanel, "Họ và tên:", user.getHoTen());
        addInfoRow(infoPanel, "Vai trò:", user.getRoleType());
        addInfoRow(infoPanel, "Số điện thoại:", user.getSdt());
        addInfoRow(infoPanel, "Ngày sinh:", user.getNgaySinh() != null ? user.getNgaySinh().toString() : "N/A");
        addInfoRow(infoPanel, "Email:", user.getEmail());
        addInfoRow(infoPanel, "Địa chỉ:", user.getDiaChi());
        addInfoRow(infoPanel, "Ngày tạo:", user.getCreatedAt() != null ? user.getCreatedAt().toString() : "N/A");
    }

    // Helper method to add a row of information
    private static void addInfoRow(JPanel panel, String label, String value) {
        JLabel keyLabel = new JLabel(label);
        keyLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(keyLabel);

        JLabel valueLabel = new JLabel(value != null ? value : "N/A");
        valueLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(valueLabel);
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

    public Information_Customer(String MAKH) {
        initComponents();
        createInformationPanel(MAKH);
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

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 903, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
