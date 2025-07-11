package com.raven.receptionform;

import com.raven.classes.AreaClass;
import com.raven.classes.CustomerClass;
import com.raven.classes.ExchangeGDetailsClass;
import com.raven.classes.GiftClass;
import com.raven.classes.UsersClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.ExchangeGiftDetails;
import com.raven.dbfunction.Gift;
import com.raven.dbfunction.Statistic;
import com.raven.dbfunction.User;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.event.DeleteUtil;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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

public class ExchangeGift_Reception extends javax.swing.JPanel {
    public void loadForm1(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<ExchangeGDetailsClass> egdetails = ExchangeGiftDetails.SelectAllExchangeGiftDetails(); 

            for (ExchangeGDetailsClass egdetail : egdetails) {
                String madq = egdetail.getMaDQ();
                String makh = egdetail.getMaKH();
                String maqt = egdetail.getMaQT();
                String ngaydoi = egdetail.getCreatedat().toString();
                int soluong = egdetail.getSoLuong();
                String trangthai = egdetail.getTrangThai();
                
                model.addRow(new Object[]{madq, makh, maqt, ngaydoi, soluong, trangthai});
            }
 
    }
    public ExchangeGift_Reception() {
        initComponents();
        ArrayList<String> result = Statistic.thongKeKhachHang();
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
        loadForm1();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();
        panelCustomer = new com.raven.swing.PanelBorder();
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

        panelCustomer.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh Sách Đổi Quà");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ĐQ", "Mã KH", "Mã QT", "Ngày Đổi", "Số Lượng", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        javax.swing.GroupLayout panelCustomerLayout = new javax.swing.GroupLayout(panelCustomer);
        panelCustomer.setLayout(panelCustomerLayout);
        panelCustomerLayout.setHorizontalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addComponent(spTable)
                        .addContainerGap())
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
        );
        panelCustomerLayout.setVerticalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panelCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String exchangeId = table.getValueAt(selectedRow, 0).toString();
                
                boolean success = Gift.callExchangeGift(exchangeId);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadForm1(); // Refresh the table
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 bản ghi!", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "LICH_SU_DOI_QUA", "MADQ");
        loadForm1();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
            JDialog dialog = new JDialog((Frame) null, "Thêm", true);
                dialog.setSize(600, 400);
                dialog.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Input fields
                JTextField customerIdField = new JTextField();
                customerIdField.setPreferredSize(new Dimension(200, 30)); // Set preferred size

                JComboBox<String> giftIdField = new JComboBox<>();
                giftIdField.setPreferredSize(new Dimension(200, 30)); // Set preferred size

                JTextField quantityField = new JTextField();
                quantityField.setPreferredSize(new Dimension(200, 30)); // Set preferred size

                JTextField searchField = new JTextField();
                searchField.setPreferredSize(new Dimension(200, 30)); // Set preferred size

                JButton searchButton = new JButton("Find");

                // Populate the dropdown menu with gifts
                ArrayList<GiftClass> gifts = Gift.SelectGift();
                for (GiftClass gift : gifts) {
                    giftIdField.addItem(gift.getMaQT() + " - " + gift.getNoiDung());
                }

                // Add "Search by EMAIL or SDT" label
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 1;
                dialog.add(new JLabel("Tìm kiếm"), gbc);

                // Add search field and button in the same row
                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                dialog.add(searchField, gbc);

                gbc.gridx = 3;
                gbc.gridy = 0;
                gbc.gridwidth = 1;
                dialog.add(searchButton, gbc);

                // Add "Customer ID" label and field
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                dialog.add(new JLabel("Mã khách hàng"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.gridwidth = 3;
                dialog.add(customerIdField, gbc);

                // Add "Gift ID" label and dropdown
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 1;
                dialog.add(new JLabel("Mã quà tặng"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.gridwidth = 3;
                dialog.add(giftIdField, gbc);

                // Add "Quantity" label and field
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 1;
                dialog.add(new JLabel("Số lượng:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 3;
                gbc.gridwidth = 3;
                dialog.add(quantityField, gbc);

                // Search button action listener
                searchButton.addActionListener(e1 -> {
                    String searchValue = searchField.getText();
                    if (searchValue.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    CustomerClass customer = null;
                    if (searchValue.contains("@")) {
                        // Search by EMAIL
                        customer = Customer.SelectCustomerByEmail(searchValue);
                    } else {
                        // Search by SDT
                        customer = Customer.SelectCustomerByPhone(searchValue);
                    }

                    if (customer != null) {
                        customerIdField.setText(customer.getMakh());
                        JOptionPane.showMessageDialog(dialog, "Tìm thấy: " + User.SelectUserByMAKH(customer.getMakh()).getHoTen(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Không tìm thấy", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            });

            // Add "Save" button
            JButton saveButton = new JButton("Save");
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 4;
            dialog.add(saveButton, gbc);

            saveButton.addActionListener(saveEvent -> {
                try {
                    String customerId = customerIdField.getText();
                    String selectedGift = (String) giftIdField.getSelectedItem();
                    int quantity = Integer.parseInt(quantityField.getText());

                    // Extract the MAQT (Gift ID) from the selected item
                    String giftId = selectedGift.split(" - ")[0];

                    // Call the EXCHANGE_GIFT procedure
                    boolean success = ExchangeGiftDetails.addExchangeGiftDetails(customerId, giftId, quantity);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadForm1();// Refresh the table
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private com.raven.component.Card card1;
    private com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelCustomer;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
