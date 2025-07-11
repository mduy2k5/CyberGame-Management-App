package com.raven.receptionform;

import com.raven.classes.AreaClass;
import com.raven.classes.CustomerClass;
import com.raven.classes.DepositClass;
import com.raven.classes.PaymentClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.DBConnection;
import com.raven.dbfunction.Depositer;
import com.raven.dbfunction.Payment;
import com.raven.dbfunction.Statistic;
import com.raven.dbfunction.User;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.sql.Connection;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.*;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Deposit_Reception extends javax.swing.JPanel {
    private static Connection conn;
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
        class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
            label = (value == null) ? "Click" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int selectedRow = table.getRowCount() - 1;
                String transactionId = table.getValueAt(selectedRow, 0).toString(); // Get MAKH from the table

                // Call the UpdateTransactionStatusToSuccess method
                boolean success = Depositer.UpdateTransactionStatusToSuccess(transactionId);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Trạng thái giao dịch đã được cập nhật là 'Success' cho khách hàng: " + transactionId, "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Refresh the table after a successful update
                    loadForm2();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật trạng thái giao dịch thất bại cho khách hàng: " + transactionId, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    
    public void loadForm2(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<DepositClass> deposits = Depositer.SelectAllDepositsNonDeposited()    ; // Gọi hàm từ lớp Area
            
            for (DepositClass deposit : deposits) {
                String magd = deposit.getMaGD();
                String makh = deposit.getMaKH();
                String thoigian = deposit.getCreatedAt().toString();
                String hinhthuc = deposit.getHinhThuc();
                double sotien = deposit.getSoTien();
                String trangthai = deposit.getTrangThai();
                
                model.addRow(new Object[]{magd, makh, thoigian, hinhthuc, sotien, trangthai, "Update"});      
            }
            if (table.getColumn("Hành Động") != null) {
                table.getColumn("Hành Động").setCellRenderer(new ButtonRenderer());
                table.getColumn("Hành Động").setCellEditor(new ButtonEditor(new JCheckBox(), table));
            }
    }
    public Deposit_Reception() throws SQLException {
        initComponents();
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        ArrayList<String> result = Statistic.thongKeTaiChinh();
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
        loadForm2();
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
        jLabel1.setText("Danh Sách Giao Dịch");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã GD", "Mã KH", "Thời Gian GD", "Hình Thức", "Số Tiền", "Trạng Thái", "Hành Động"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
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

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addContainerGap(24, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)))
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        JDialog dialog = new JDialog((Frame) null, "Thêm Giao Dịch mới", true);
            dialog.setSize(600, 400);
            dialog.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Input fields
            JTextField customerIdField = new JTextField();
            customerIdField.setPreferredSize(new Dimension(200, 30)); // Set preferred size

            JTextField amountField = new JTextField();
            amountField.setPreferredSize(new Dimension(200, 30)); // Set preferred size

            JTextField searchField = new JTextField();
            searchField.setPreferredSize(new Dimension(200, 30)); // Set preferred size

            JButton searchButton = new JButton("Tìm");

            // Add "Search by EMAIL or SDT" label
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            dialog.add(new JLabel("Tìm kiếm:"), gbc);

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
            dialog.add(new JLabel("Mã khách hàng:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            dialog.add(customerIdField, gbc);

            // Add "Amount" label and field
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            dialog.add(new JLabel("Số tiền:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            dialog.add(amountField, gbc);

            // Search button action listener
            searchButton.addActionListener(e1 -> {
                String searchValue = searchField.getText();
                if (searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin", "Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Đã tìm thấy: " + User.SelectUserByMAKH(customer.getMakh()).getHoTen(), "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Add "Save" button
            JButton saveButton = new JButton("Save");
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 4;
            dialog.add(saveButton, gbc);

            saveButton.addActionListener(saveEvent -> {
                try {
                    String customerId = customerIdField.getText();
                    int amount = Integer.parseInt(amountField.getText());
                    String method = "Offline";
                    String status = "Success";

                    // Call the DEPOSIT procedure
                    boolean success = Depositer.DepositTest(conn, customerId, amount, method, status);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadForm2(); // Refresh the table
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Định dạng sai", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
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
