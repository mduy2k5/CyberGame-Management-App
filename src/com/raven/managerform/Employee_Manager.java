package com.raven.managerform;

import com.raven.classes.AreaClass;
import com.raven.classes.AreaTypeClass;
import com.raven.classes.EmployeeClass;
import com.raven.classes.EmployeeTypeClass;
import com.raven.classes.RevenueClass;
import com.raven.classes.UsersClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.AreaType;
import com.raven.dbfunction.Employee;
import com.raven.dbfunction.EmployeeWType;
import com.raven.dbfunction.Statistic;
import com.raven.dbfunction.User;
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
import javax.swing.*;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Employee_Manager extends javax.swing.JPanel {
    public void loadForm3(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<EmployeeClass> employees = Employee.SelectEmployee(); // Gọi hàm từ lớp Area

            for (EmployeeClass employee : employees) {
                String manv = employee.getManv();
                String tennv = employee.getHoten();
                String sdt = employee.getSdt();
                String diachi = employee.getDiachi();
                String email = employee.getEmail();
                String chucvu = employee.getRoleType();
                String thoigian = employee.getCreatedAt().toString();

                model.addRow(new Object[]{manv, tennv, sdt, diachi, email, chucvu, thoigian});
            }
 
    }
    public Employee_Manager() {
        initComponents();
                ArrayList<String> result = Statistic.thongKeNhanVien();
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
        loadForm3();
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
        jLabel1.setText("Danh Sách Nhân Viên");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "SĐT", "Địa Chỉ", "Email", "Chức Vụ", "Thời Gian"
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
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
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
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm");
        dialog.setSize(450, 440);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.add(new JLabel("Họ tên:"));
        JTextField tennvField = new JTextField();
        tennvField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(tennvField);

        inputPanel.add(new JLabel("Số điện thoại: "));
        JTextField sdtField = new JTextField();
        sdtField.setPreferredSize(new Dimension(200,30));
        inputPanel.add(sdtField);

        inputPanel.add(new JLabel("Ngày sinh:"));
        JDateChooser dateField = new JDateChooser();
        dateField.setDateFormatString("yyyy-MM-dd");
        dateField.setFont(new Font("Arial", Font.PLAIN, 12));
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Địa chỉ: "));
        JTextField diachField = new JTextField();
        dateField.setPreferredSize(new Dimension(200,30));
        inputPanel.add(diachField);
        
        
        
        inputPanel.add(new JLabel("Mã loại nhân viên:"));
        JComboBox mlnvComboBox = new JComboBox<>();
        mlnvComboBox.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(mlnvComboBox);
        
        ArrayList<EmployeeTypeClass> emptype = EmployeeWType.SelectEWType();
        for (EmployeeTypeClass etype : emptype) {
            mlnvComboBox.addItem(etype.getMaLoaiNV()+ " - " + etype.getTenLoaiNV());
        }
        
        inputPanel.add(new JLabel("Mã số thuế:"));
        JTextField mstcnField = new JTextField();
        mstcnField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(mstcnField);

        inputPanel.add(new JLabel("Số BHYT: "));
        JTextField bhytTextField = new JTextField();
        bhytTextField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(bhytTextField);

        inputPanel.add(new JLabel("Quyền Hạn"));
        String[] items = {"Reception", "Technical", "Service","Manager","Security"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        inputPanel.add(comboBox);

        JButton submitButton = new JButton("Xác nhận");
        submitButton.addActionListener(submitEvent -> {
            String malnv = mlnvComboBox.getSelectedItem().toString().split(" - ")[0];
            java.util.Date utilDate = dateField.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            Boolean check = Employee.AddEmployee(tennvField.getText(), sdtField.getText(), sqlDate, emailField.getText(), diachField.getText(), malnv,mstcnField.getText() , bhytTextField.getText(), comboBox.getSelectedItem().toString());
            if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
            else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);

            // Insert into database
            loadForm3(); // Reload data
            dialog.dispose(); // Close the dialog
        });

        dialog.add(inputPanel);
        dialog.add(submitButton);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "NHAN_VIEN", "MANV");
        loadForm3();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!");
            return;
        }
        //table.getValueAt(selectedRow, 1).toString();
        //UpdateEmployee(String manv, String userId, String hoten, String sdt, java.sql.Date ngaysinh, String email, String diachi, String malnv, String masothuecn, String sobhyt)
        UsersClass temp_u = User.SelectUserByMANV(table.getValueAt(selectedRow, 0).toString());
        EmployeeClass temp_e = Employee.SelectEmployeeByID(table.getValueAt(selectedRow, 0).toString());
        JDialog dialog = new JDialog();
        dialog.setTitle("Sửa");
        dialog.setSize(450, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        inputPanel.add(new JLabel("Họ tên:"));
        JTextField tennvField = new JTextField();
        tennvField.setPreferredSize(new Dimension(200, 30));
        tennvField.setText(temp_e.getHoten());
        inputPanel.add(tennvField);

        inputPanel.add(new JLabel("Số điện thoại: "));
        JTextField sdtField = new JTextField();
        sdtField.setPreferredSize(new Dimension(200,30));
        sdtField.setText(temp_u.getSdt());
        inputPanel.add(sdtField);

        inputPanel.add(new JLabel("Ngày sinh(yyyy-MM-dd):"));
        JDateChooser dateField = new JDateChooser();
        dateField.setDateFormatString("yyyy-MM-dd");
        dateField.setFont(new Font("Arial", Font.PLAIN, 12));
        java.util.Date utilDate = new java.util.Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            utilDate = sdf.parse(temp_u.getNgaySinh().toString());
//            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(dialog, "Sai định dạng", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        dateField.setDate(utilDate);
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.setText(temp_u.getEmail());
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Địa chỉ: "));
        JTextField diachField = new JTextField();
        diachField.setPreferredSize(new Dimension(200,30));
        diachField.setText(temp_e.getDiachi());
        inputPanel.add(diachField);

        inputPanel.add(new JLabel("Mã loại nhân viên:"));
        JComboBox mlnvComboBox = new JComboBox<>();
        mlnvComboBox.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(mlnvComboBox);
        
        ArrayList<EmployeeTypeClass> emptype = EmployeeWType.SelectEWType();
        String tenloainv = "Reception";
        for (EmployeeTypeClass etype : emptype) {
            if (etype.getMaLoaiNV().equals(temp_e.getMalnv())) tenloainv = etype.getTenLoaiNV();
            mlnvComboBox.addItem(etype.getMaLoaiNV()+ " - " + etype.getTenLoaiNV());
        }
        mlnvComboBox.setSelectedItem(temp_e.getMalnv() + " - " + tenloainv);

        inputPanel.add(new JLabel("Mã số thuế:"));
        JTextField mstcnField = new JTextField();
        mstcnField.setPreferredSize(new Dimension(200, 30));
        mstcnField.setText(temp_e.getMasothuecn());
        inputPanel.add(mstcnField);

        inputPanel.add(new JLabel("Số BHYT: "));
        JTextField bhytTextField = new JTextField();
        bhytTextField.setPreferredSize(new Dimension(200, 30));
        bhytTextField.setText(temp_e.getSobhyt());
        inputPanel.add(bhytTextField);


        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(submitEvent -> {
            String malnv = mlnvComboBox.getSelectedItem().toString().split(" - ")[0];
            java.util.Date utilDate_2 = dateField.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate_2.getTime());
            //Boolean check = Employee.AddEmployee(tennvField.getText(), sdtField.getText(), sqlDate, emailField.getText(), diachField.getText(), mlnvField.getText(),mstcnField.getText() , bhytTextField.getText(), comboBox.getSelectedItem().toString());
            Boolean check = Employee.UpdateEmployee(table.getValueAt(selectedRow, 0).toString(), temp_u.getUserId(),tennvField.getText(), sdtField.getText(), sqlDate,emailField.getText(), diachField.getText(), malnv, mstcnField.getText(),bhytTextField.getText());
            if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
            else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);

            // Insert into database
            loadForm3(); // Reload data
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
