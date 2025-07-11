package com.raven.managerform;

import com.raven.classes.AreaClass;
import com.raven.classes.AreaPromotionClass;
import com.raven.classes.ImportGDetailsClass;
import com.raven.classes.ImportGoodsClass;
import com.raven.classes.ProductClass;
import com.raven.classes.ProductPromotionClass;
import com.raven.classes.PromotionClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.AreaPromotion;
import com.raven.dbfunction.ImportGDetails;
import com.raven.dbfunction.ImportGoods;
import com.raven.dbfunction.Product;
import com.raven.dbfunction.ProductPromotion;
import com.raven.dbfunction.Promotion;
import com.raven.dbfunction.Statistic;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Promotion_Manager extends javax.swing.JPanel {
    public void loadForm6(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<PromotionClass> promotions = Promotion.SelectPromotion(); 

            for (PromotionClass promotion : promotions) {
                String mact = promotion.getMaCTR();
                String tenct = promotion.getTenCTR();
                Double chietkhau = promotion.getChietKhau();
                String ngaybd = promotion.getNgayBD().toString();
                String ngaykt = promotion.getNgayKT().toString();
                String loaict = promotion.getLoaiCTR();
                String ngaytao = promotion.getCreateat().toString();
               
                model.addRow(new Object[]{mact, tenct, chietkhau, ngaybd, ngaykt, loaict, ngaytao});
                
            }
 
    }
    public Promotion_Manager() {
        initComponents();
                ArrayList<String> result = Statistic.thongKeKhuyenMai();
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
        loadForm6();
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
        jButton1 = new javax.swing.JButton();

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
        jLabel1.setText("Danh Sách Khuyến Mãi");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CT", "Tên CT", "Chiết Khấu", "Ngày BĐ", "Ngày KT", "Loại CT", "Ngày Tạo"
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

        jButton1.setText("Thêm chi tiết");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        PromotionClass promotion = new PromotionClass();
        Insert insertForm = new Insert((UpdatableEntity) promotion, this::loadForm6);
        insertForm.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "CHUONG_TRINH_KHUYEN_MAI", "MACTR");
        loadForm6();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!");
            return;
        }

        PromotionClass promotion = new PromotionClass();
        promotion.setMaCTR(table.getValueAt(selectedRow, 0).toString());
        promotion.setTenCTR(table.getValueAt(selectedRow, 1).toString());
        promotion.setChietKhau(Double.parseDouble(table.getValueAt(selectedRow, 2).toString()));
        promotion.setLoaiCTR(table.getValueAt(selectedRow, 5).toString());

        String promotionid = table.getValueAt(selectedRow, 0).toString();

            if (promotionid != null) {
                JDialog dialog = new JDialog();
                dialog.setTitle("Cập nhật");
                dialog.setSize(260, 240);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setLayout(new FlowLayout(FlowLayout.CENTER));

                JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
                inputPanel.add(new JLabel("Tên chương trình:"));
                JTextField tenctField = new JTextField();
                tenctField.setText(promotion.getTenCTR());
                tenctField.setPreferredSize(new Dimension(100, 30));
                inputPanel.add(tenctField);

                inputPanel.add(new JLabel("Chiết khấu:"));
                JTextField ckField = new JTextField();
                ckField.setText(String.valueOf(promotion.getChietKhau()));
                ckField.setPreferredSize(new Dimension(100, 30));
                inputPanel.add(ckField);
                
                inputPanel.add(new JLabel("Thời gian bắt đầu: "));
                JDateChooser startdateField = new JDateChooser();
                startdateField.setDateFormatString("yyyy-MM-dd");
                startdateField.setFont(new Font("Arial", Font.PLAIN, 12));
                inputPanel.add(startdateField);
                java.util.Date utilDate = new java.util.Date();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    utilDate = sdf.parse(table.getValueAt(selectedRow, 3).toString());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Định dạng sai", "Error", JOptionPane.ERROR_MESSAGE);
                }
                startdateField.setDate(utilDate);
        
                inputPanel.add(new JLabel("Thời gian kết thúc: "));
                JDateChooser enddateField = new JDateChooser();
                enddateField.setDateFormatString("yyyy-MM-dd");
                enddateField.setFont(new Font("Arial", Font.PLAIN, 12));
                inputPanel.add(enddateField);
                utilDate = new java.util.Date();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    utilDate = sdf.parse(table.getValueAt(selectedRow, 4).toString());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Định dạng sai", "Error", JOptionPane.ERROR_MESSAGE);
                }
                enddateField.setDate(utilDate);
                
                
                JButton submitButton = new JButton("Xác nhận");
                submitButton.addActionListener(submitEvent -> {
                    try {

                        // Update the expense in the database
                        java.util.Date tgbdDate = startdateField.getDate();
                        java.util.Date tgktDate = enddateField.getDate();
                        Boolean check =  Promotion.UpdatePromotion(promotionid, tenctField.getText(), tgbdDate, tgktDate, Double.parseDouble(ckField.getText()));
                        if (check) JOptionPane.showMessageDialog(panel, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Reload the table data
                        loadForm6();
                        dialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Đinh dạng sai", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                dialog.add(inputPanel);
                dialog.add(submitButton);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(panel, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
            }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int promotionselectedRow = table.getSelectedRow();
        if (promotionselectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!");
            return;
        }
        String makm = model.getValueAt(promotionselectedRow, 0).toString();
        String loaikm = model.getValueAt(promotionselectedRow, 5).toString();
        if ("KHUYENMAIKV".equalsIgnoreCase(loaikm)) areapromotion(makm);
        else productpromotion(makm);
    }//GEN-LAST:event_jButton1ActionPerformed

    private static void areapromotion(String promotionID){
        JDialog dialog = new JDialog();
        PromotionClass obj = Promotion.SelectPromotionByID(promotionID);
        
        dialog.setTitle("Chi tiết");
        dialog.setSize(820, 580);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        String[] columnNames = {"Mã KMKV","Mã CTR","Mã KV"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        JTable importTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(importTable);
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        inputPanel.add(new JLabel("Mã chương trình: " + obj.getMaCTR()));
        inputPanel.add(new JLabel("Tên chương trình: " + obj.getTenCTR()));
        inputPanel.add(new JLabel("Loại chương trình: "+ obj.getLoaiCTR()));
        inputPanel.add(new JLabel("Chiết khấu: "+ obj.getChietKhau()));
        inputPanel.add(new JLabel("Ngày bắt đầu: "+ obj.getNgayBD()));
        inputPanel.add(new JLabel("Ngày kết thúc: "+ obj.getNgayKT()));
        inputPanel.add(new JLabel("Ngày tạo: " + obj.getCreateat()));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton insertButton = new JButton("Thêm");
        JButton updateButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        loadareapromotion(tableModel, promotionID);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        insertButton.addActionListener(e -> {
            JDialog insdialog = new JDialog();
            insdialog.setTitle("Thêm");
            insdialog.setSize(430, 100);
            insdialog.setLocationRelativeTo(null);
            insdialog.setModal(true);
            insdialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JPanel inputPanelins = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanelins.add(new JLabel("Mã khu vực:"));
            //JTextField maspField = new JTextField();
            ArrayList<AreaClass> area = Area.SelectArea();
            ArrayList<String> Areaarray = new ArrayList<>();
            for (AreaClass a : area){
                Areaarray.add(a.getMaKV() + " - " + a.getTenKV());
            }
            JComboBox maspField = new JComboBox(Areaarray.toArray(new String[0]));
            maspField.setPreferredSize(new Dimension(150, 30));
            inputPanelins.add(maspField);
            
            
            
            JButton submitButton = new JButton("Xác nhận");
            submitButton.addActionListener(submitEvent -> {
                
                try {
                    String selected = maspField.getSelectedItem().toString();
                    Boolean check = AreaPromotion.AddAPromotion(selected.split(" - ")[0],promotionID);
                    if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                    else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(insdialog, "Lỗi định dạng", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                // Insert into database
                loadareapromotion(tableModel, promotionID); // Reload data
                insdialog.dispose(); // Close the dialog
            });

            insdialog.add(inputPanelins);
            insdialog.add(submitButton);
            insdialog.setVisible(true);
        });
        updateButton.addActionListener(e -> {
            int selectedRow = importTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Vui lòng chọn 1 bản ghi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get selected record details
            String masp = tableModel.getValueAt(selectedRow, 2).toString();
            String makmkv = tableModel.getValueAt(selectedRow, 0).toString();
            
            if (promotionID != null) {
                JDialog updialog = new JDialog();
                updialog.setTitle("Cập nhật");
                updialog.setSize(430, 100);
                updialog.setLocationRelativeTo(null);
                updialog.setModal(true);
                updialog.setLayout(new FlowLayout(FlowLayout.CENTER));

                JPanel inputPanelup = new JPanel(new GridLayout(0, 2, 10, 10));
                inputPanelup.add(new JLabel("Mã khu vực:"));
                ArrayList<AreaClass> area = Area.SelectArea();
                ArrayList<String> Areaarray = new ArrayList<>();
                for (AreaClass a : area){
                    Areaarray.add(a.getMaKV() + " - " + a.getTenKV());
                }
                JComboBox maspField = new JComboBox(Areaarray.toArray(new String[0]));
                AreaClass temp = Area.SelectAreaById(masp);
                maspField.setSelectedItem(masp + " - " + temp.getTenKV());
                maspField.setPreferredSize(new Dimension(150, 30));
                inputPanelup.add(maspField);

                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(submitEvent -> {
                    try {

                        // Update the expense in the database
                        String selected = maspField.getSelectedItem().toString();
                        Boolean check = AreaPromotion.UpdateAPromotion(makmkv,selected.split(" - ")[0]);
                        if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                        else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                        // Reload the table data
                        loadareapromotion(tableModel,promotionID);
                        updialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(updialog, "Định dạng sai", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                updialog.add(inputPanelup);
                updialog.add(submitButton);
                updialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        deleteButton.addActionListener(e -> {
            int selectedRow = importTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Vui lòng chọn 1 bản ghi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get selected record details
            String importgID = tableModel.getValueAt(selectedRow, 0).toString();

            if (importgID != null) {
                // Confirm deletion
                int confirm = JOptionPane.showConfirmDialog(mainPanel,
                        "Are you sure you want to delete this record?\n\n" +
                                "ID: " + importgID + "\n" ,
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Delete the device
                    Boolean check = AreaPromotion.DeleteAPromotion(importgID);
                    if (check) JOptionPane.showMessageDialog(mainPanel, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(dialog, "Lỗi.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Refresh the table
                    loadareapromotion(tableModel, promotionID);
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    private static void loadareapromotion(DefaultTableModel tableModel, String promotionID){
        tableModel.setRowCount(0); // Clear existing rows

        // Fetch deposit records using SelectDeposit
        ArrayList<AreaPromotionClass> promotions = AreaPromotion.SelectAPromotionByPId(promotionID);
        for (AreaPromotionClass pro : promotions) {
            tableModel.addRow(new Object[]{
                pro.getMaKMKV(),
                pro.getMaCTR(),
                pro.getMaKV()
            });
        }
    }
    private static void productpromotion(String promotionID){
        JDialog dialog = new JDialog();
        PromotionClass obj = Promotion.SelectPromotionByID(promotionID);
        
        dialog.setTitle("Chi tiết");
        dialog.setSize(820, 580);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        String[] columnNames = {"Mã KMSP","Mã CTR","Mã SP"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        JTable importTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(importTable);
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        inputPanel.add(new JLabel("Mã chương trình: " + obj.getMaCTR()));
        inputPanel.add(new JLabel("Tên chương trình: " + obj.getTenCTR()));
        inputPanel.add(new JLabel("Loại chương trình: "+ obj.getLoaiCTR()));
        inputPanel.add(new JLabel("Chiết khấu: "+ obj.getChietKhau()));
        inputPanel.add(new JLabel("Ngày bắt đầu: "+ obj.getNgayBD()));
        inputPanel.add(new JLabel("Ngày kết thúc: "+ obj.getNgayKT()));
        inputPanel.add(new JLabel("Ngày tạo: " + obj.getCreateat()));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton insertButton = new JButton("Thêm");
        JButton updateButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        loadproductpromotion(tableModel, promotionID);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        insertButton.addActionListener(e -> {
            JDialog insdialog = new JDialog();
            insdialog.setTitle("Thêm");
            insdialog.setSize(430, 100);
            insdialog.setLocationRelativeTo(null);
            insdialog.setModal(true);
            insdialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JPanel inputPanelins = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanelins.add(new JLabel("Mã sản phẩm:"));
            //JTextField maspField = new JTextField();
            ArrayList<ProductClass> area = Product.SelectProduct();
            ArrayList<String> Areaarray = new ArrayList<>();
            for (ProductClass a : area){
                Areaarray.add(a.getMaSP() + " - " + a.getTenSP());
            }
            JComboBox maspField = new JComboBox(Areaarray.toArray(new String[0]));
            maspField.setPreferredSize(new Dimension(150, 30));
            inputPanelins.add(maspField);
            
            
            
            JButton submitButton = new JButton("Xác nhận");
            submitButton.addActionListener(submitEvent -> {
                
                try {
                    String selected = maspField.getSelectedItem().toString();
                    Boolean check = ProductPromotion.AddPPromotion(selected.split(" - ")[0],promotionID);
                    if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                    else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(insdialog, "Lỗi định dạng", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                // Insert into database
                loadproductpromotion(tableModel, promotionID); // Reload data
                insdialog.dispose(); // Close the dialog
            });

            insdialog.add(inputPanelins);
            insdialog.add(submitButton);
            insdialog.setVisible(true);
        });
        updateButton.addActionListener(e -> {
            int selectedRow = importTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Vui lòng chọn 1 bản ghi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get selected record details
            String masp = tableModel.getValueAt(selectedRow, 2).toString();
            String makmkv = tableModel.getValueAt(selectedRow, 0).toString();
            
            if (promotionID != null) {
                JDialog updialog = new JDialog();
                updialog.setTitle("Cập nhật");
                updialog.setSize(430, 100);
                updialog.setLocationRelativeTo(null);
                updialog.setModal(true);
                updialog.setLayout(new FlowLayout(FlowLayout.CENTER));

                JPanel inputPanelup = new JPanel(new GridLayout(0, 2, 10, 10));
                inputPanelup.add(new JLabel("Mã sản phẩm:"));
                ArrayList<ProductClass> area = Product.SelectProduct();
                ArrayList<String> Areaarray = new ArrayList<>();
                for (ProductClass a : area){
                    Areaarray.add(a.getMaSP() + " - " + a.getTenSP());
                }
                JComboBox maspField = new JComboBox(Areaarray.toArray(new String[0]));
                AreaClass temp = Area.SelectAreaById(masp);
                maspField.setSelectedItem(masp + " - " + temp.getTenKV());
                maspField.setPreferredSize(new Dimension(150, 30));
                inputPanelup.add(maspField);

                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(submitEvent -> {
                    try {

                        // Update the expense in the database
                        String selected = maspField.getSelectedItem().toString();
                        Boolean check = ProductPromotion.UpdatePPromotion(makmkv,selected.split(" - ")[0]);
                        if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                        else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                        // Reload the table data
                        loadproductpromotion(tableModel,promotionID);
                        updialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(updialog, "Định dạng sai", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                updialog.add(inputPanelup);
                updialog.add(submitButton);
                updialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        deleteButton.addActionListener(e -> {
            int selectedRow = importTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Vui lòng chọn 1 bản ghi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get selected record details
            String importgID = tableModel.getValueAt(selectedRow, 0).toString();

            if (importgID != null) {
                // Confirm deletion
                int confirm = JOptionPane.showConfirmDialog(mainPanel,
                        "Are you sure you want to delete this record?\n\n" +
                                "ID: " + importgID + "\n" ,
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Delete the device
                    Boolean check = ProductPromotion.DeletePPromotion(importgID);
                    if (check) JOptionPane.showMessageDialog(mainPanel, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(dialog, "Lỗi.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Refresh the table
                    loadproductpromotion(tableModel, promotionID);
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    private static void loadproductpromotion(DefaultTableModel tableModel, String promotionID){
        tableModel.setRowCount(0); // Clear existing rows

        // Fetch deposit records using SelectDeposit
        ArrayList<ProductPromotionClass> promotions = ProductPromotion.SelectPPromotionByPId(promotionID);
        for (ProductPromotionClass pro : promotions) {
            tableModel.addRow(new Object[]{
                pro.getMAKMSP(),
                pro.getMaCTR(),
                pro.getmaSP()
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
