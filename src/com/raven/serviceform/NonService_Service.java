package com.raven.serviceform;

import com.raven.receptionform.*;
import com.raven.classes.AreaClass;
import com.raven.classes.ComputerClass;
import com.raven.classes.CustomerClass;
import com.raven.classes.ExchangeGDetailsClass;
import com.raven.classes.GiftClass;
import com.raven.classes.ProductClass;
import com.raven.classes.RentComputerClass;
import com.raven.classes.UsedServiceClass;
import com.raven.classes.UsersClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.*;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.event.DeleteUtil;
import com.raven.managerform.Customer_Manager;
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

public class NonService_Service extends javax.swing.JPanel {
    private static Connection conn;
    public void loadForm1(){
        jLabel3.setText(String.valueOf(UsedService.CheckUServiceRemainTest(conn)));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        ArrayList<UsedServiceClass> usedServices = UsedService.SelectUServiceTest(conn);
        for (UsedServiceClass usedService : usedServices) {
            if ("Non-serviced".equalsIgnoreCase(usedService.getStatus())) {
                String maDVSD = usedService.getMaDVSD();
                String maLS = usedService.getMaLS();
                String maSP = usedService.getMaSP();
                String tenSP = ""; // Initialize TENSP
                int soMay = 0; // Initialize SOMAY
                int soTang = 0; // Initialize SOTANG
                String trangthai = usedService.getStatus();

                // Fetch product name (TENSP) using SelectProductByID
                ProductClass product = Product.SelectProductByIDTest(conn, maSP);
                if (product != null) {
                    tenSP = product.getTenSP(); // Use getTenSP() to fetch the product name
                }

                // Fetch additional data
                RentComputerClass rentComputer = RentComputer.SelectRComputerByIDTest(conn, maLS);
                if (rentComputer != null) {
                    ComputerClass computer = Computer.SelectComputerByIdTest(conn, rentComputer.getMaPC());
                    if (computer != null) {
                        soMay = computer.getSoMay();
                        AreaClass area = Area.SelectAreaByIdTest(conn, computer.getMaKv());
                        if (area != null) {
                            soTang = area.getSoTang();
                        }
                    }
                }

                // Add row to the table
                model.addRow(new Object[]{
                        maDVSD, // Add MADVSD as the first column
                        maLS,
                        maSP, // Add MASP as the third column
                        tenSP,
                        usedService.getSoLuong(),
                        soMay,
                        soTang,
                        usedService.getStatus(),
                        usedService.getCreatedat()
                });
            }
        }

 
    }
    public NonService_Service() throws SQLException {
        initComponents();
        this.conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
        panelCustomer = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        btnInsert1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(241, 241, 241));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelCustomer.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh Sách Chưa Phục Vụ");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DVSD", "Mã LS", "Mã SP", "Tên SP", "Số lượng", "Số máy", "Số tầng", "Trạng thái", "Thời gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        btnInsert1.setText("Refresh");
        btnInsert1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsert1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Số dịch vụ chưa phục vụ:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout panelCustomerLayout = new javax.swing.GroupLayout(panelCustomer);
        panelCustomer.setLayout(panelCustomerLayout);
        panelCustomerLayout.setHorizontalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomerLayout.createSequentialGroup()
                        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(panelCustomerLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)))
                        .addGap(474, 474, 474)
                        .addComponent(btnInsert1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );
        panelCustomerLayout.setVerticalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnInsert1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panelCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsert1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsert1ActionPerformed
        loadForm1();
    }//GEN-LAST:event_btnInsert1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelCustomer;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
