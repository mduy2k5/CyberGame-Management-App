package com.raven.managerform;

import com.raven.classes.AreaClass;
import com.raven.classes.CustomerClass;
import com.raven.classes.UsersClass;
import com.raven.component.Header;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.Statistic;
import com.raven.dbfunction.User;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.SearchListener;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.raven.dbfunction.*;
import javax.swing.JComboBox;
public class Customer_Manager extends javax.swing.JPanel implements SearchListener {
    private List<CustomerClass> originalData = new ArrayList<>();
    private Map<String, UsersClass> userMap = new HashMap<>();
    private Header header;
    private Connection conn;
    public void loadForm1() throws InterruptedException, SQLException{
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        originalData.clear();

        ArrayList<CustomerClass> customers = Customer.SelectCustomerTest(conn); 
        originalData = new ArrayList<>();
        for (CustomerClass customer : customers) {
            String makh = customer.getMakh();
            UsersClass u = User.SelectUserByMAKH(makh);
            userMap.put(makh, u); // Lưu vào map

            originalData.add(customer);
            model.addRow(new Object[]{
                makh, u.getHoTen(), u.getSdt(), u.getDiaChi(), u.getEmail(),
                u.getNgaySinh().toString(), customer.getSdutk()
            });
        }
    }
    
    public Customer_Manager(Header header) throws InterruptedException, SQLException {
        this.header = header;
        this.conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        initComponents();

        // Thiết lập listener tìm kiếm
        this.header.setSearchListener(this);

        // Tải dữ liệu ban đầu
        loadForm1();

        // Cấu hình table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        // Gán dữ liệu thống kê lên card
        ArrayList<String> result = Statistic.thongKeKhachHang();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")), result.get(0), result.get(3), ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), result.get(1), result.get(4), ""));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), result.get(2), result.get(5), ""));
    }
    @Override
    public void onSearch(String keyword) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (CustomerClass customer : originalData) {
            UsersClass u = userMap.get(customer.getMakh()); 

            if (u.getHoTen().toLowerCase().contains(keyword.toLowerCase()) ||
                customer.getMakh().toLowerCase().contains(keyword.toLowerCase()) ||
                u.getSdt().contains(keyword)) {

                model.addRow(new Object[]{
                    customer.getMakh(),
                    u.getHoTen(), u.getSdt(), u.getDiaChi(), u.getEmail(),
                    u.getNgaySinh().toString(), customer.getSdutk()
                });
            }
        }
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
        btnRefresh = new javax.swing.JButton();

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
        jLabel1.setText("Danh Sách Khách Hàng");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Họ Tên", "SĐT", "Địa Chỉ", "Email", "Ngày Sinh", "Số Dư TK"
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

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
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
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
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
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
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
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String makh = table.getValueAt(selectedRow, 0).toString();
            CustomerClass khachhang= Customer.SelectCustomerByID(makh);
            UsersClass userkh = User.SelectUserByMAKH(makh);

            if (makh != null) {
                JDialog dialog = new JDialog();
                dialog.setTitle("Sửa");
                dialog.setSize(320, 310);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);
                dialog.setLayout(new FlowLayout(FlowLayout.CENTER));

                JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
                inputPanel.add(new JLabel("Họ tên:"));
                JTextField tennvField = new JTextField();
                tennvField.setText(userkh.getHoTen());
                tennvField.setPreferredSize(new Dimension(100, 30));
                inputPanel.add(tennvField);

                inputPanel.add(new JLabel("Số điện thoại: "));
                JTextField sdtField = new JTextField();
                sdtField.setText(userkh.getSdt());
                sdtField.setPreferredSize(new Dimension(100,30));
                inputPanel.add(sdtField);

                inputPanel.add(new JLabel("Ngày Sinh(yyyy-MM-dd):"));
                String ngaySinhStr = userkh.getNgaySinh().toString(); // Ví dụ: "1995-12-31"
                JDateChooser dateChooser = new JDateChooser();

                try {
                    if (ngaySinhStr != null && !ngaySinhStr.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date ngaySinhDate = sdf.parse(ngaySinhStr);
                        dateChooser.setDate(ngaySinhDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dateChooser.setDateFormatString("yyyy-MM-dd");
                dateChooser.setFont(new Font("Arial", Font.PLAIN, 12));
                inputPanel.add(dateChooser);

                inputPanel.add(new JLabel("Email:"));
                JTextField emailField = new JTextField();
                emailField.setText(userkh.getEmail());
                emailField.setPreferredSize(new Dimension(100, 30));
                inputPanel.add(emailField);

                inputPanel.add(new JLabel("Địa chỉ: "));
                JTextField diachField = new JTextField();
                diachField.setText(userkh.getDiaChi());
                inputPanel.add(diachField);

                inputPanel.add(new JLabel("Mã hạng khách hàng:"));
                JTextField mlnvField = new JTextField();
                mlnvField.setText(khachhang.getMahkh());
                mlnvField.setPreferredSize(new Dimension(100, 30));
                inputPanel.add(mlnvField);

                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(submitEvent -> {
                    try {
                        java.util.Date utilDate = dateChooser.getDate(); // Lấy ngày được chọn
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                        // Update the expense in the database
                        Customer.UpdateCustomer(makh, mlnvField.getText());
                        Boolean check = User.UpdateUser(khachhang.getUserid(), tennvField.getText(), sdtField.getText(), sqlDate, emailField.getText(), diachField.getText());
                        if (check) {
                            JOptionPane.showMessageDialog(dialog, "Thành công");
                            loadForm1();
                            dialog.dispose();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Sai định dạng", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (InterruptedException | SQLException ex) {
                        Logger.getLogger(Customer_Manager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                dialog.add(inputPanel);
                dialog.add(submitButton);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "KHACH_HANG", "MAKH");
        try {
            loadForm1();
        } catch (InterruptedException | SQLException ex) {
            Logger.getLogger(Customer_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed

        JDialog dialog = new JDialog();
            dialog.setTitle("Insert Customer");
            dialog.setSize(320, 310);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanel.add(new JLabel("Họ tên:"));
            JTextField tennvField = new JTextField();
            tennvField.setPreferredSize(new Dimension(100, 30));
            inputPanel.add(tennvField);

            inputPanel.add(new JLabel("Số điện thoại: "));
            JTextField sdtField = new JTextField();
            sdtField.setPreferredSize(new Dimension(100,30));
            inputPanel.add(sdtField);

            inputPanel.add(new JLabel("Ngày (yyyy-MM-dd):"));
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("yyyy-MM-dd");
            dateChooser.setFont(new Font("Arial", Font.PLAIN, 12));
            inputPanel.add(dateChooser);
                    

            inputPanel.add(new JLabel("Email:"));
            JTextField emailField = new JTextField();
            emailField.setPreferredSize(new Dimension(100, 30));
            inputPanel.add(emailField);

            inputPanel.add(new JLabel("Địa chỉ: "));
            JTextField diachField = new JTextField();
            dateChooser.setPreferredSize(new Dimension(100,30));
            inputPanel.add(diachField);

            inputPanel.add(new JLabel("Mã hạng khách hàng:"));
            JTextField mlnvField = new JTextField();
            mlnvField.setPreferredSize(new Dimension(100, 30));
            inputPanel.add(mlnvField);
            

            JButton submitButton = new JButton("Submit");
            
            submitButton.addActionListener(submitEvent -> {
                java.util.Date utilDate = dateChooser.getDate(); // Lấy ngày được chọn
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                Boolean check = Customer.callCreateNewCustomer(tennvField.getText(), diachField.getText(), sdtField.getText(), sqlDate.toString(), mlnvField.getText(), emailField.getText());
                if (check) {
                    JOptionPane.showMessageDialog(dialog, "Thêm thành công!");
                    dialog.dispose(); // Close the dialog
                }
                else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
            try {
                // Insert into database
                loadForm1();
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(Customer_Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            });

            dialog.add(inputPanel);
            dialog.add(submitButton);
            dialog.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            loadForm1();
        } catch (InterruptedException | SQLException ex) {
            Logger.getLogger(Customer_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnRefresh;
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
