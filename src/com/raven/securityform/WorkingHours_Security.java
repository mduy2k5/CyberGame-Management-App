package com.raven.securityform;

import com.raven.receptionform.*;
import com.raven.classes.AreaClass;
import com.raven.classes.EmployeeClass;
import com.raven.classes.RevenueClass;
import com.raven.classes.WorkHoursClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Employee;
import com.raven.dbfunction.Statistic;
import com.raven.dbfunction.WorkHours;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class WorkingHours_Security extends javax.swing.JPanel {
    private String manv;
    public void loadForm3(String manv){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<WorkHoursClass> whours = WorkHours.SelectAllWHoursByMANV(manv); // Gọi hàm từ lớp Area

            for (WorkHoursClass whour : whours) {
                String maca = whour.getMaca();
                String Manv = whour.getManv();
                String tgbd = whour.getTgbd().toString();
                String tgkt = whour.getTgkt().toString();
                int giolv = whour.getWorkHours();
                int tangca = whour.getOvertimeHours();
                String trangthai = whour.getTrangthai();
                manv = Manv;
                model.addRow(new Object[]{maca, Manv, tgbd, tgkt, giolv, tangca, trangthai});
            }
 
    }
    public WorkingHours_Security(String manv) {
        initComponents();
        ArrayList<String> result = Statistic.thongKeCaLam();
        //  add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        loadForm3(manv);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        btnAddShift = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setBackground(new java.awt.Color(241, 241, 241));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh Sách Ca Làm");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Ca", "Mã NV", "Thời Gian BĐ", "Thời Gian KT", "Số Giờ Làm Việc", "Tăng Ca", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        btnAddShift.setText("Thêm Ca Làm");
        btnAddShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddShiftActionPerformed(evt);
            }
        });

        btnFinish.setText("Kết Thúc Ca");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddShift, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnFinish, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1))
                    .addComponent(btnRefresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddShift, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
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
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddShiftActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Thêm Ca Làm", true);
            dialog.setSize(600, 400);
            dialog.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JDateChooser startDateChooser = new JDateChooser();
            startDateChooser.setDateFormatString("yyyy-MM-dd");
            JTextField startTimeField = new JTextField("HH:MM:SS");

            JDateChooser endDateChooser = new JDateChooser();
            endDateChooser.setDateFormatString("yyyy-MM-dd");
            JTextField endTimeField = new JTextField("HH:MM:SS");

            // Start Date
            gbc.gridx = 0;
            gbc.gridy = 0;
            dialog.add(new JLabel("Ngày bắt đầu:"), gbc);
            gbc.gridx = 1;
            dialog.add(startDateChooser, gbc);

            // Start Time
            gbc.gridx = 0;
            gbc.gridy = 1;
            dialog.add(new JLabel("Giờ bắt đầu (HH:MM:SS):"), gbc);
            gbc.gridx = 1;
            dialog.add(startTimeField, gbc);

            // End Date
            gbc.gridx = 0;
            gbc.gridy = 2;
            dialog.add(new JLabel("Ngày kết thúc:"), gbc);
            gbc.gridx = 1;
            dialog.add(endDateChooser, gbc);

            // End Time
            gbc.gridx = 0;
            gbc.gridy = 3;
            dialog.add(new JLabel("Giờ kết thúc (HH:MM:SS):"), gbc);
            gbc.gridx = 1;
            dialog.add(endTimeField, gbc);

            // Add "Save" button
            JButton saveButton = new JButton("Save");
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 3;
            dialog.add(saveButton, gbc);

            saveButton.addActionListener(saveEvent -> {
                try {
                    java.util.Date startDate = startDateChooser.getDate();
                    java.util.Date endDate = endDateChooser.getDate();
                    String startTime = startTimeField.getText();
                    String endTime = endTimeField.getText();

                    if (startDate == null || endDate == null || startTime.isEmpty() || endTime.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ ngày và giờ.", "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Kết hợp ngày và giờ
                    String startDateTimeStr = new java.text.SimpleDateFormat("yyyy-MM-dd").format(startDate) + " " + startTime;
                    String endDateTimeStr = new java.text.SimpleDateFormat("yyyy-MM-dd").format(endDate) + " " + endTime;

                    java.sql.Timestamp tgbd = java.sql.Timestamp.valueOf(startDateTimeStr);
                    java.sql.Timestamp tgkt = java.sql.Timestamp.valueOf(endDateTimeStr);

                    WorkHours.AddWHours(manv, tgbd, tgkt);
                    JOptionPane.showMessageDialog(dialog, "Work hours added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadForm3(manv);
                    dialog.dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, "Sai định dạng giờ. Hãy dùng HH:MM:SS", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

    }//GEN-LAST:event_btnAddShiftActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn muốn kết thúc?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Call the FinishWHours method
                    WorkHours.FinishWHours(manv);
                    JOptionPane.showMessageDialog(null, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Refresh the table after finishing work hours
                    loadForm3(manv);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

    }//GEN-LAST:event_btnFinishActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddShift;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
