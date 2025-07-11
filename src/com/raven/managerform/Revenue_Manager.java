package com.raven.managerform;

import com.raven.classes.AreaClass;
import com.raven.managerform.Statistic_Manager;
import com.raven.classes.RevenueClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.Revenue;
import com.raven.dbfunction.Statistic;
import com.raven.event.DeleteUtil;
import com.raven.interfaces.UpdatableEntity;
import com.raven.model.Model_Card;
import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
public class Revenue_Manager extends javax.swing.JPanel {
    private Statistic_Manager form2_2;
    private void exportToExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Báo Cáo Doanh Thu");

            // Tạo dòng tiêu đề
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Ngày", "Tổng Doanh Thu", "Tổng Doanh Thu Dịch Vụ", "Tổng Số Dịch Vụ", "Tổng Số Giờ Chơi"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Ghi dữ liệu từ JTable
            DefaultTableModel model = (DefaultTableModel) table.getModel(); // Giả sử JTable của bạn tên là `table`
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(model.getValueAt(i, 2).toString()); // Ngày
                row.createCell(1).setCellValue(Double.parseDouble(model.getValueAt(i, 3).toString())); // Tổng Doanh Thu
                row.createCell(2).setCellValue(Double.parseDouble(model.getValueAt(i, 4).toString())); // Tổng Doanh Thu DV
                row.createCell(3).setCellValue(Double.parseDouble(model.getValueAt(i, 5).toString())); // Tổng Số DV
                row.createCell(4).setCellValue(Double.parseDouble(model.getValueAt(i, 6).toString())); // Tổng Số Giờ
            }

            // Tự động điều chỉnh độ rộng cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Chọn nơi lưu
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu báo cáo");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().endsWith(".xlsx")) {
                    fileToSave = new File(fileToSave.toString() + ".xlsx");
                }

                try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(this, "Xuất báo cáo thành công!");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất báo cáo: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void loadForm2_1(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<RevenueClass> revenues = Revenue.SelectRevenue(); // Gọi hàm từ lớp Area

            for (RevenueClass revenue : revenues) {
                String madt = revenue.getMaDT();
                String noidung = revenue.getNoiDung();
                String ngay = revenue.getCreatedAt().toString();
                double tongdoanhthu = revenue.getTongDoanhThu();
                double doanhthudv = revenue.getTongdoanhthudv();
                double tongsodv = revenue.getTongsodv();
                double tonggiochoi = revenue.getTongthoigian();

                model.addRow(new Object[]{madt, noidung, ngay, tongdoanhthu, doanhthudv, tongsodv, tonggiochoi});
            }
 
    }
    public Revenue_Manager() {
        initComponents();
                ArrayList<String> result = Statistic.thongKeDoanhThu();
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
        loadForm2_1();
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
        btnExport = new javax.swing.JButton();

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
        jLabel1.setText("Danh Sách Doanh Thu");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Doanh Thu", "Nội Dung", "Ngày", "Tổng Doanh Thu", "Tổng Doanh Thu Dịch Vụ", "Tổng Số Dịch Vụ", "Tổng Số Giờ Chơi"
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

        btnExport.setText("Xuất File Excel");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
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
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(btnInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
//        RevenueClass revenue = new RevenueClass();
//        Insert insertForm = new Insert((UpdatableEntity) revenue, this::loadForm2_1);
//        insertForm.setVisible(true);
        // Action for create button
            JDialog dialog = new JDialog();
            dialog.setTitle("Tạo mới");
            dialog.setSize(460, 150);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanel.add(new JLabel("Nội dung:"));
            JTextField ndField = new JTextField();
            ndField.setPreferredSize(new Dimension(200, 30));
            inputPanel.add(ndField);
            
            inputPanel.add(new JLabel("Loại:"));
            JComboBox typeField = new JComboBox();
            typeField.setModel(new DefaultComboBoxModel(new String[] {"DAILY", "MONTHLY"}));
            typeField.setSelectedIndex(0);
            typeField.setPreferredSize(new Dimension(200, 30));
            inputPanel.add(typeField);
            
            dialog.add(inputPanel);
            JButton submitButton = new JButton("Xác nhận");
            submitButton.addActionListener(e1 -> {
                // Call the method to create revenue
                try {
                    Boolean check = Revenue.callCalculateRevenue(ndField.getText(), typeField.getSelectedItem().toString());
                    if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                    dialog.dispose();
                    loadForm2_1();
                    form2_2.loadData("7","A","Daily");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Lỗi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.add(submitButton);
            dialog.setVisible(true);
        
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "DOANH_THU", "MADT");
        loadForm2_1();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!");
            return;
        }

        JDialog dialog = new JDialog();
            dialog.setTitle("Thêm");
            dialog.setSize(460, 120);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanel.add(new JLabel("Nội dung:"));
            JTextField ndField = new JTextField();
            ndField.setPreferredSize(new Dimension(200, 30));
            ndField.setText(table.getValueAt(selectedRow, 1).toString());
            inputPanel.add(ndField);
            
            dialog.add(inputPanel);
            JButton submitButton = new JButton("Xác nhận");
            submitButton.addActionListener(e1 -> {
                // Call the method to create revenue
                try {
                    Boolean check = Revenue.UpdateRevenue(table.getValueAt(selectedRow, 0).toString(), ndField.getText());
                    if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                    dialog.dispose();
                    loadForm2_1();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Lỗi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.add(submitButton);
            dialog.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        exportToExcel();
    }//GEN-LAST:event_btnExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExport;
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
