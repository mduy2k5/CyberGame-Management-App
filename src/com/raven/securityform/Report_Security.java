package com.raven.securityform;

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

public class Report_Security extends javax.swing.JPanel {
    private javax.swing.JPanel panelBaoCaoSuCo;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JComboBox<String> cboLoaiSuCo;
    private javax.swing.JTextField txtViTri;
    private javax.swing.JComboBox<String> cboMucDo;
    public Report_Security(){
        initComponents();
        initCustomComponents();
    }
    private void initCustomComponents() {
        panelBaoCaoSuCo = new javax.swing.JPanel();
        panelBaoCaoSuCo.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        //gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1; // Cho mỗi dòng 1 chút chiều cao

        // Tiêu đề
        javax.swing.JLabel lblTitle = new javax.swing.JLabel("BÁO CÁO VẤN ĐỀ SỰ CỐ", javax.swing.JLabel.CENTER);
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelBaoCaoSuCo.add(lblTitle, gbc);

        // Mô tả
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panelBaoCaoSuCo.add(new javax.swing.JLabel("Mô tả sự cố:"), gbc);

        txtMoTa = new javax.swing.JTextArea(3, 20);
        javax.swing.JScrollPane scrollMoTa = new javax.swing.JScrollPane(txtMoTa);
        gbc.gridx = 1;
        panelBaoCaoSuCo.add(scrollMoTa, gbc);

        // Loại sự cố
        gbc.gridx = 0; gbc.gridy = 2;
        panelBaoCaoSuCo.add(new javax.swing.JLabel("Loại sự cố:"), gbc);

        cboLoaiSuCo = new javax.swing.JComboBox<>(new String[] { "Thiết bị", "Phần mềm", "Dịch vụ", "Khu vực", "Camera" });
        gbc.gridx = 1;
        panelBaoCaoSuCo.add(cboLoaiSuCo, gbc);

        // Vị trí xảy ra
        gbc.gridx = 0; gbc.gridy = 3;
        panelBaoCaoSuCo.add(new javax.swing.JLabel("Vị trí xảy ra:"), gbc);

        txtViTri = new javax.swing.JTextField(20);
        gbc.gridx = 1;
        panelBaoCaoSuCo.add(txtViTri, gbc);

        // Mức độ nghiêm trọng
        gbc.gridx = 0; gbc.gridy = 4;
        panelBaoCaoSuCo.add(new javax.swing.JLabel("Mức độ nghiêm trọng:"), gbc);

        cboMucDo = new javax.swing.JComboBox<>(new String[] { "Thấp", "Trung bình", "Cao" });
        gbc.gridx = 1;
        panelBaoCaoSuCo.add(cboMucDo, gbc);

        // Hình ảnh
        gbc.gridx = 0; gbc.gridy = 5;
        panelBaoCaoSuCo.add(new javax.swing.JLabel("Hình ảnh (nếu có):"), gbc);

        javax.swing.JButton btnChonAnh = new javax.swing.JButton("Chọn ảnh...");
        gbc.gridx = 1;
        panelBaoCaoSuCo.add(btnChonAnh, gbc);

        // Gửi báo cáo
        javax.swing.JButton btnGui = new javax.swing.JButton("Gửi báo cáo");
        gbc.gridx = 0; gbc.gridy = 6;
        panelBaoCaoSuCo.add(btnGui, gbc);

        // Trở về
        javax.swing.JButton btnBack = new javax.swing.JButton("Trở về");
        gbc.gridx = 1;
        panelBaoCaoSuCo.add(btnBack, gbc);

        // Thêm panel này vào giao diện chính (ví dụ thay this bằng mainPanel nếu có)
        this.setLayout(new java.awt.BorderLayout());
        this.add(panelBaoCaoSuCo, java.awt.BorderLayout.CENTER);

        // Xử lý sự kiện Gửi
        btnGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String moTa = txtMoTa.getText().trim();
                String loai = cboLoaiSuCo.getSelectedItem().toString();

                if (moTa.isEmpty() || loai.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ mô tả và loại sự cố!");
                    return;
                }

                javax.swing.JOptionPane.showMessageDialog(null, "Báo cáo sự cố đã được ghi nhận!");
            }
        });

        // Sự kiện trở về (ẩn panel hoặc xử lý tuỳ layout)
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtMoTa.setText("");
            txtViTri.setText("");
            cboLoaiSuCo.setSelectedIndex(0);
            cboMucDo.setSelectedIndex(0);
        }
        });
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
