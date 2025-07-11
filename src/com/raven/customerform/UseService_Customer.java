/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.customerform;

import com.raven.classes.ComputerDDetailsClass;
import com.raven.classes.DeviceClass;
import com.raven.classes.PlayerHistoryClass;
import com.raven.dbfunction.Product;
import com.raven.classes.ProductClass;
import com.raven.classes.UsedServiceClass;
import com.raven.dbfunction.ComputerDDetails;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.Device;
import com.raven.dbfunction.PlayerHistory;
import com.raven.dbfunction.RentComputer;
import com.raven.dbfunction.UsedService;
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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class UseService_Customer extends javax.swing.JPanel {
    private String maKH;
    private PlayerHistoryClass playerHistoryClass;
    /**
     * Creates new form Form_1
     */
    public UseService_Customer(String MaKH) {
        initComponents();
        maKH = MaKH;
        playerHistoryClass = PlayerHistory.SelectPHistoryByCusID(MaKH);
        txtPrice.setText(String.valueOf(Customer.SelectCustomerByID(MaKH).getSdutk()));
        // 1. Cấu hình layout dạng lưới cuộn được cho leftPanel
        leftPanel.setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // 3 cột
        gridPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(450, 600));
        
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        
        // 2. Tạo danh sách sản phẩm mẫu
        ArrayList<ProductClass> products = Product.SelectProduct();
        
        // 3. Bổ sung dữ liệu sản phẩm vào bảng và cập nhật tổng tiền
        java.util.Map<String, Integer> productMap = new java.util.HashMap<>();

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) selectedProductTable.getModel();
        model.setRowCount(0);  // Xóa dữ liệu cũ

        // 4. Tạo panel sản phẩm và sự kiện
        for (ProductClass product : products) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
            productPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            productPanel.setBackground(Color.WHITE);
            productPanel.setPreferredSize(new Dimension(150, 180));
            productPanel.setMaximumSize(new Dimension(150, 180));
            productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Ảnh sản phẩm
            JLabel imageLabel = new JLabel();
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imageLabel.setPreferredSize(new Dimension(100, 100));
            imageLabel.setMaximumSize(new Dimension(100, 100));
            imageLabel.setMinimumSize(new Dimension(100, 100));

            try {
                ImageIcon originalIcon = new ImageIcon(getClass().getResource(product.getUrl()));
                Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                imageLabel.setText("No Image");
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
            }

            // Tên sản phẩm
            JLabel nameLabel = new JLabel(product.getTenSP());
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Giá tiền
            JLabel priceLabel = new JLabel(String.format("%.0f VND", product.getDonGiaBQ()));
            priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            priceLabel.setForeground(Color.DARK_GRAY);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Thêm các thành phần vào panel
            productPanel.add(Box.createVerticalStrut(5));
            productPanel.add(imageLabel);
            productPanel.add(Box.createVerticalStrut(5));
            productPanel.add(nameLabel);
            productPanel.add(priceLabel);

            // Sự kiện khi click chọn sản phẩm
            productPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String key = product.getMaSP();
                    int quantity = productMap.getOrDefault(key, 0) + 1;
                    productMap.put(key, quantity);

                    boolean found = false;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 1).equals(product.getTenSP())) {
                            model.setValueAt(quantity, i, 2);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        model.addRow(new Object[]{product.getMaSP(), product.getTenSP(), 1});
                    }

                    // Cập nhật tổng tiền
                    double totalPrice = 0;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String name = model.getValueAt(i, 1).toString();
                        int qty = (int) model.getValueAt(i, 2);
                        for (ProductClass p : products) {
                            if (p.getTenSP().equals(name)) {
                                totalPrice += p.getDonGiaBQ() * qty;
                                break;
                            }
                        }
                    }
                    txtTotalPrice.setText(String.format("%.0f", totalPrice));
                }
            });

            gridPanel.add(productPanel);
        }
        SwingUtilities.invokeLater(() -> {
            int numProducts = products.size();
            int numCols = 3;
            int numRows = (int) Math.ceil(numProducts / (double) numCols);
            int panelWidth = numCols * (150 + 10);
            int panelHeight = numRows * (190 + 10);
            gridPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            gridPanel.revalidate();  // cập nhật layout
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnHistory = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        txt = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedProductTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(915, 620));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        btnHistory.setText("Lịch sử");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        jPanel3.setPreferredSize(new java.awt.Dimension(395, 100));

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOrder.setText("Đặt");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tiêu hao:");

        txtTotalPrice.setEditable(false);

        txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt.setText("Số tiền hiện có");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrice))
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt)
                        .addComponent(txtTotalPrice))
                    .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        selectedProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Số Thứ Tự", "Tên Sản Phẩm", "Số Lượng"
            }
        ));
        jScrollPane1.setViewportView(selectedProductTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        DefaultTableModel model = (DefaultTableModel) selectedProductTable.getModel();
        model.setRowCount(0);  // Xóa tất cả dòng trong bảng

        txtTotalPrice.setText("0");  // Reset tổng điểm về 0 nếu có
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        if (playerHistoryClass == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy lịch sử chơi. Không thể đặt dịch vụ.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Iterate through the table and add each product to the database
            for (int i = 0; i < selectedProductTable.getRowCount(); i++) {
                String maSP = selectedProductTable.getValueAt(i, 0).toString();
                String tenSP = selectedProductTable.getValueAt(i, 1).toString();
                int soLuong = Integer.parseInt(selectedProductTable.getValueAt(i, 2).toString());
    
                // Call AddUService to add the selected product
                UsedService.AddUService(playerHistoryClass.getMaLS(), maSP, soLuong, "Non-serviced");
            }
    
            JOptionPane.showMessageDialog(null, "Đặt Thành Công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            DefaultTableModel model = (DefaultTableModel) selectedProductTable.getModel();
            model.setRowCount(0);

    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Lịch sử đặt của bạn", true);
        dialog.setSize(800, 400);
        dialog.setLayout(new BorderLayout());
    
        // Add a title label
        JLabel titleLabel = new JLabel("Lịch sử đặt dịch vụ của bạn", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        dialog.add(titleLabel, BorderLayout.NORTH);
    
        // Create a table to display device details
        String[] columnNames = {"Mã DV","Mã SP", "Tên SP", "Số lượng","Thời gian đặt","Trạng thái"};
        DefaultTableModel tableModels = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModels);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);
    
        // Fetch 
        ArrayList<UsedServiceClass> uservices = UsedService.SelectArrayUServiceByID(playerHistoryClass.getMaLS());
        for (UsedServiceClass uservice : uservices) {
            // Fetch device name and type using SelectDeviceByID
            ProductClass name = Product.SelectProductByID(uservice.getMaSP());
            tableModels.addRow(new Object[]{
                uservice.getMaDVSD(),
                uservice.getMaSP(),
                name.getTenSP(),
                uservice.getSoLuong(),
                uservice.getCreatedat(),
                uservice.getStatus()
            });
        }

        // Add a "Close" button
        JPanel functionpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton closeButton = new JButton("Đóng");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setBackground(new Color(229, 80, 80)); // Blue color
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        closeButton.addActionListener(e -> dialog.dispose());
        functionpanel.add(closeButton);
        
        JButton cancelButton = new JButton("Hủy dịch vụ");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelButton.setBackground(new Color(229, 80, 80)); // Blue color
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        cancelButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an object to update!");
                return;
            }
            boolean success = UsedService.DeleteUService(tableModels.getValueAt(selectedRow, 0).toString());
            if (success) JOptionPane.showMessageDialog(dialog, "Thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(dialog, "Hủy không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
            dialog.dispose();
        });
        functionpanel.add(cancelButton);
        
        dialog.add(functionpanel, BorderLayout.SOUTH);
        
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnHistoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JTable selectedProductTable;
    private javax.swing.JLabel txt;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtTotalPrice;
    // End of variables declaration//GEN-END:variables
}
