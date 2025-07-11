package com.raven.managerform;

import com.raven.classes.AreaClass;
import com.raven.classes.EmployeeTypeClass;
import com.raven.classes.EventClass;
import com.raven.classes.PromotionClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Employee;
import com.raven.dbfunction.EmployeeWType;
import com.raven.dbfunction.Event;
import com.raven.dbfunction.Promotion;
import com.raven.dbfunction.Statistic;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class Event_Manager extends javax.swing.JPanel {
    public void loadForm8(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<EventClass> events = Event.SelectEvent();

            for (EventClass event: events) {
                String mask = event.getMask();
                String tensk = event.getTensk();
                String manv = event.getManv();
                String makv = event.getMakv();
                String noidung = event.getNoidung();
                String ngaybd = event.getTgbd().toString();
                String ngaykt = event.getTgkt().toString();
                String ngaytao = event.getcreateat().toString();
                
                model.addRow(new Object[]{mask, tensk, manv, makv, noidung, ngaybd, ngaykt, ngaytao});
                
            }
 
    }
    public Event_Manager() {
        initComponents();
                ArrayList<String> result = Statistic.thongKeSuKien();
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
        loadForm8();
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
        btnInsert = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

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
        jLabel1.setText("Danh Sách Sự Kiện");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SK", "Tên SK", "Mã NV", "Mã KV", "Nội Dung", "Ngày BĐ", "Ngày KT", "Ngày Tạo"
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

        btnInsert.setText("Thêm");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

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
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
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
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
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

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        EventClass event = new EventClass();
        Insert insertForm = new Insert((UpdatableEntity) event, this::loadForm8);
        insertForm.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "SU_KIEN", "MASK");
        loadForm8();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!");
            return;
        }
        //AddEvent(String tensk, java.sql.Date tgbd, java.sql.Date tgkt, String makv, String noidung, String manv)
        JDialog dialog = new JDialog();
        dialog.setTitle("Cập nhật");
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.add(new JLabel("Tên sk:"));
        JTextField tenskField = new JTextField();
        tenskField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(tenskField);
        tenskField.setText(table.getValueAt(selectedRow, 1).toString());

        inputPanel.add(new JLabel("Ngày bắt đầu:"));
        JDateChooser startdateField = new JDateChooser();
        startdateField.setDateFormatString("yyyy-MM-dd");
        startdateField.setFont(new Font("Arial", Font.PLAIN, 12));
        inputPanel.add(startdateField);
        java.util.Date utilDate = new java.util.Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            utilDate = sdf.parse(table.getValueAt(selectedRow, 5).toString());
//            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(dialog, "Lỗi định dạng", "Error", JOptionPane.ERROR_MESSAGE);
        }
        startdateField.setDate(utilDate);
        
        inputPanel.add(new JLabel("Ngày kết thúc:"));
        JDateChooser enddateField = new JDateChooser();
        enddateField.setDateFormatString("yyyy-MM-dd");
        enddateField.setFont(new Font("Arial", Font.PLAIN, 12));
        inputPanel.add(enddateField);
        utilDate = new java.util.Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            utilDate = sdf.parse(table.getValueAt(selectedRow, 6).toString());
//            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(dialog, "Lỗi định dạng", "Error", JOptionPane.ERROR_MESSAGE);
        }
        enddateField.setDate(utilDate);
        
        
        inputPanel.add(new JLabel("Mã khu vực:"));
        JComboBox kvComboBox = new JComboBox<>();
        kvComboBox.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(kvComboBox);
        
        ArrayList<AreaClass> kvs = Area.SelectArea();
        for (AreaClass kv : kvs) {
            if (table.getValueAt(selectedRow, 3).toString().equals(kv.getMaKV()))  kvComboBox.setSelectedItem(kv.getMaKV() + " - " + kv.getTenKV());
            kvComboBox.addItem(kv.getMaKV() + " - " + kv.getTenKV());
        }
        
        inputPanel.add(new JLabel("Nội dung:"));
        JTextField ndField = new JTextField();
        ndField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(ndField);
        ndField.setText(table.getValueAt(selectedRow, 4).toString());

        inputPanel.add(new JLabel("Mã NV: "));
        JTextField manvTextField = new JTextField();
        manvTextField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(manvTextField);
        manvTextField.setText(table.getValueAt(selectedRow, 2).toString());


        JButton submitButton = new JButton("Xác nhận");
        submitButton.addActionListener(submitEvent -> {
            String tensk = tenskField.getText();
            String makv = kvComboBox.getSelectedItem().toString().split(" - ")[0];
            String noidung = ndField.getText();
            String manv = manvTextField.getText();
            java.util.Date utilDate2 = startdateField.getDate();
            java.sql.Date startDate = new java.sql.Date(utilDate2.getTime());
            utilDate2 = enddateField.getDate();
            java.sql.Date endDate = new java.sql.Date(utilDate2.getTime());
            
            Boolean check = Event.UpdateEvent(table.getValueAt(selectedRow, 0).toString(),tensk, startDate, endDate, makv, noidung, manv);
            if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
            else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);

            // Insert into database
            loadForm8(); // Reload data
            dialog.dispose(); // Close the dialog
        });

        dialog.add(inputPanel);
        dialog.add(submitButton);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed


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
