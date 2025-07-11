package com.raven.managerform;

import com.raven.classes.AreaClass;
import com.raven.classes.ImportDDetailsClass;
import com.raven.classes.ImportGDetailsClass;
import com.raven.classes.ImportGoodsClass;
import com.raven.component.Insert;
import com.raven.component.Update;
import com.raven.dbfunction.Area;
import com.raven.dbfunction.ImportDDetails;
import com.raven.dbfunction.ImportGDetails;
import com.raven.dbfunction.ImportGoods;
import com.raven.dbfunction.Product;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Import_Manager extends javax.swing.JPanel {
    public void loadForm9(){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            ArrayList<ImportGoodsClass> goods = ImportGoods.SelectIGoods(); 

            for (ImportGoodsClass good : goods) {
                String manh = good.getManh();
                String mact = good.getMachungtu();
                String mancc = good.getMancc();
                String manv = good.getManv();
                double trigia = good.getTrigia();
                String ngaynhap = good.getCreateAt().toString();
                String loainhap = good.getLoaiNhapHang();

                model.addRow(new Object[]{manh, mact, mancc, manv, trigia, ngaynhap, loainhap});
                
            }
 
    }
    public Import_Manager() {
        initComponents();
                ArrayList<String> result = Statistic.thongKeNhapHang();
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
        loadForm9();
    }
    private static void importdevicedetail(String importID){
        JDialog dialog = new JDialog();
        ImportGoodsClass Detailobject = ImportGoods.SelectIGoodsById(importID);
        dialog.setTitle("Thêm");
        dialog.setSize(820, 580);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        String[] columnNames = {"Mã thiết bị","Đơn giá","Chi phí khác","Chiết khấu","Thuế","Ngày nhập"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        JTable importTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(importTable);
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        inputPanel.add(new JLabel("Mã nhập hàng: " + Detailobject.getManh()));
        inputPanel.add(new JLabel("Mã chứng từ: " + Detailobject.getMachungtu()));
        inputPanel.add(new JLabel("Mã nhà cung cấp: "+ Detailobject.getMancc()));
        inputPanel.add(new JLabel("Mã nhân viên: "+ Detailobject.getManv()));
        inputPanel.add(new JLabel("Trị giá: "+ Detailobject.getTrigia()));
        inputPanel.add(new JLabel("Ngày nhập: "+ Detailobject.getCreateAt()));
        inputPanel.add(new JLabel("Loại nhập: " + Detailobject.getLoaiNhapHang()));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton insertButton = new JButton("Thêm");
        JButton updateButton = new JButton("Sửa");
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        loadimportdevicedata(tableModel, importID);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        insertButton.addActionListener(e -> {
            JDialog insdialog = new JDialog();
            insdialog.setTitle("Thêm");
            insdialog.setSize(350, 400);
            insdialog.setLocationRelativeTo(null);
            insdialog.setModal(true);
            insdialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JPanel inputPanelins = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanelins.add(new JLabel("Tên thiết bị:"));
            JTextField devicField = new JTextField();
            devicField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(devicField);

            inputPanelins.add(new JLabel("Loại thiết bị: "));
            JTextField loaitbField = new JTextField();
            loaitbField.setPreferredSize(new Dimension(100,30));
            inputPanelins.add(loaitbField);

            inputPanelins.add(new JLabel("Đơn giá"));
            JTextField dongiaField = new JTextField();
            dongiaField.setPreferredSize(new Dimension(100,30));
            inputPanelins.add(dongiaField);

            inputPanelins.add(new JLabel("Chí phí khác"));
            JTextField othercostField = new JTextField();
            othercostField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(othercostField);

            inputPanelins.add(new JLabel("Chiết khấu"));
            JTextField ckField = new JTextField();
            ckField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(ckField);

            inputPanelins.add(new JLabel("VAT"));
            JTextField vatField = new JTextField();
            vatField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(vatField);

            JButton submitButton = new JButton("Xác nhận");
            submitButton.addActionListener(submitEvent -> {
                
                try {
                    Boolean check = ImportDDetails.AddIDDetails(importID, devicField.getText(), loaitbField.getText(), 
                        Double.parseDouble(dongiaField.getText()), 
                        Double.parseDouble(othercostField.getText()), 
                        Double.parseDouble(ckField.getText()), 
                        Double.parseDouble(vatField.getText()));
                    if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                    else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(insdialog, "Định dạng sai", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                // Insert into database
                loadimportdevicedata(tableModel, importID); // Reload data
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
            String dongia = tableModel.getValueAt(selectedRow, 1).toString();
            String chiphikhac = tableModel.getValueAt(selectedRow, 2).toString();
            String cktm = tableModel.getValueAt(selectedRow, 3).toString();
            String vat = tableModel.getValueAt(selectedRow, 4).toString();

            if (importID != null) {
                JDialog updialog = new JDialog();
                updialog.setTitle("Cập nhật");
                updialog.setSize(350, 400);
                updialog.setLocationRelativeTo(null);
                updialog.setModal(true);
                updialog.setLayout(new FlowLayout(FlowLayout.CENTER));

                JPanel inputPanelup = new JPanel(new GridLayout(0, 2, 10, 10));
                inputPanelup.add(new JLabel("Đơn giá:"));
                JTextField cosField = new JTextField();
                cosField.setText(dongia);
                cosField.setPreferredSize(new Dimension(100, 30));
                inputPanelup.add(cosField);

                inputPanelup.add(new JLabel("Chi phí khác: "));
                JTextField cpkField = new JTextField();
                cpkField.setText(chiphikhac);
                cpkField.setPreferredSize(new Dimension(100,30));
                inputPanelup.add(cpkField);

                inputPanelup.add(new JLabel("Chiết khấu"));
                JTextField ckField = new JTextField();
                ckField.setText(cktm);
                ckField.setPreferredSize(new Dimension(100,30));
                inputPanelup.add(ckField);

                inputPanelup.add(new JLabel("VAT"));
                JTextField vatField = new JTextField();
                vatField.setText(vat);
                vatField.setPreferredSize(new Dimension(100, 30));
                inputPanelup.add(vatField);

                JButton submitButton = new JButton("Xác nhận");
                submitButton.addActionListener(submitEvent -> {
                    try {

                        // Update the expense in the database
                        ImportDDetails.UpdateIDDetails(
                            importID,
                            tableModel.getValueAt(selectedRow, 0).toString(),
                            Double.parseDouble(cosField.getText()),
                            Double.parseDouble(cpkField.getText()),
                            Double.parseDouble(ckField.getText()),
                            Double.parseDouble(vatField.getText())
                        );
                        // Reload the table data
                        loadimportdevicedata(tableModel,importID);
                        updialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(updialog, "Lỗi định dạng", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                updialog.add(inputPanelup);
                updialog.add(submitButton);
                updialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    private static void importgooddetail(String importID){
        JDialog dialog = new JDialog();
        ImportGoodsClass Detailobject = ImportGoods.SelectIGoodsById(importID);
        dialog.setTitle("Thêm");
        dialog.setSize(820, 580);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        String[] columnNames = {"Mã sản phẩm","Số lượng theo chứng từ","Số lượng thực nhập","Đơn giá","Chi phí khác","Chiết khấu","Thuế","Ngày nhập"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        JTable importTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(importTable);
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        inputPanel.add(new JLabel("Mã nhập hàng: " + Detailobject.getManh()));
        inputPanel.add(new JLabel("Mã chứng từ: " + Detailobject.getMachungtu()));
        inputPanel.add(new JLabel("Mã nhà cung cấp: "+ Detailobject.getMancc()));
        inputPanel.add(new JLabel("Mã nhân viên: "+ Detailobject.getManv()));
        inputPanel.add(new JLabel("Trị giá: "+ Detailobject.getTrigia()));
        inputPanel.add(new JLabel("Ngày nhập: "+ Detailobject.getCreateAt()));
        inputPanel.add(new JLabel("Loại nhập: " + Detailobject.getLoaiNhapHang()));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton insertButton = new JButton("Thêm");
        JButton updateButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        loadimportgooddata(tableModel, importID);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        insertButton.addActionListener(e -> {
            JDialog insdialog = new JDialog();
            insdialog.setTitle("Thêm");
            insdialog.setSize(350, 400);
            insdialog.setLocationRelativeTo(null);
            insdialog.setModal(true);
            insdialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            JPanel inputPanelins = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanelins.add(new JLabel("Mã sản phẩm:"));
            JTextField maspField = new JTextField();
            maspField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(maspField);

            inputPanelins.add(new JLabel("Số lượng theo chứng từ: "));
            JTextField slctField = new JTextField();
            slctField.setPreferredSize(new Dimension(100,30));
            inputPanelins.add(slctField);

            inputPanelins.add(new JLabel("Số lượng thực: "));
            JTextField slttField = new JTextField();
            slttField.setPreferredSize(new Dimension(100,30));
            inputPanelins.add(slttField);


            inputPanelins.add(new JLabel("Đơn giá"));
            JTextField dongiaField = new JTextField();
            dongiaField.setPreferredSize(new Dimension(100,30));
            inputPanelins.add(dongiaField);

            inputPanelins.add(new JLabel("Chí phí khác"));
            JTextField othercostField = new JTextField();
            othercostField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(othercostField);

            inputPanelins.add(new JLabel("Chiết khấu"));
            JTextField ckField = new JTextField();
            ckField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(ckField);

            inputPanelins.add(new JLabel("VAT"));
            JTextField vatField = new JTextField();
            vatField.setPreferredSize(new Dimension(100, 30));
            inputPanelins.add(vatField);

            JButton submitButton = new JButton("Xác nhận");
            submitButton.addActionListener(submitEvent -> {
                
                try {
                    Boolean check = ImportGDetails.AddIGDetails(
                        importID,
                        maspField.getText(),
                        Integer.parseInt(slctField.getText()),
                        Integer.parseInt(slttField.getText()),
                        Double.parseDouble(dongiaField.getText()),
                        Double.parseDouble(othercostField.getText()),
                        Double.parseDouble(ckField.getText()),
                        Double.parseDouble(vatField.getText())
                    );
                    Boolean check2 = Product.UpdateSLProduct(maspField.getText(), Integer.parseInt(slttField.getText()));
                    if (check && check2) JOptionPane.showMessageDialog(dialog, "Thành công!");
                    else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(insdialog, "Lỗi định dạng", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                // Insert into database
                loadimportgooddata(tableModel, importID); // Reload data
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
            String masp = tableModel.getValueAt(selectedRow, 0).toString();
            String slct = tableModel.getValueAt(selectedRow, 1).toString();
            String sltn = tableModel.getValueAt(selectedRow, 2).toString();

            String dongia = tableModel.getValueAt(selectedRow, 3).toString();
            String chiphikhac = tableModel.getValueAt(selectedRow, 4).toString();
            String cktm = tableModel.getValueAt(selectedRow, 5).toString();
            String vat = tableModel.getValueAt(selectedRow, 6).toString();

            if (importID != null) {
                JDialog updialog = new JDialog();
                updialog.setTitle("Cập nhật");
                updialog.setSize(350, 400);
                updialog.setLocationRelativeTo(null);
                updialog.setModal(true);
                updialog.setLayout(new FlowLayout(FlowLayout.CENTER));

                JPanel inputPanelup = new JPanel(new GridLayout(0, 2, 10, 10));
                inputPanelup.add(new JLabel("Mã sản phẩm:"));
                JTextField maspField = new JTextField();
                maspField.setText(masp);
                maspField.setPreferredSize(new Dimension(100, 30));
                inputPanelup.add(maspField);

                inputPanelup.add(new JLabel("Số lượng theo chứng từ: "));
                JTextField slctField = new JTextField();
                slctField.setText(slct);
                slctField.setPreferredSize(new Dimension(100,30));
                inputPanelup.add(slctField);

                inputPanelup.add(new JLabel("Số lượng thực: "));
                JTextField slttField = new JTextField();
                slttField.setText(sltn);
                slttField.setPreferredSize(new Dimension(100,30));
                inputPanelup.add(slttField);


                inputPanelup.add(new JLabel("Đơn giá"));
                JTextField dongiaField = new JTextField();
                dongiaField.setText(dongia);
                dongiaField.setPreferredSize(new Dimension(100,30));
                inputPanelup.add(dongiaField);

                inputPanelup.add(new JLabel("Chí phí khác"));
                JTextField othercostField = new JTextField();
                othercostField.setText(chiphikhac);
                othercostField.setPreferredSize(new Dimension(100, 30));
                inputPanelup.add(othercostField);

                inputPanelup.add(new JLabel("Chiết khấu"));
                JTextField ckField = new JTextField();
                ckField.setText(cktm);
                ckField.setPreferredSize(new Dimension(100, 30));
                inputPanelup.add(ckField);

                inputPanelup.add(new JLabel("VAT"));
                JTextField vatField = new JTextField();
                vatField.setText(vat);
                vatField.setPreferredSize(new Dimension(100, 30));
                inputPanelup.add(vatField);

                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(submitEvent -> {
                    try {

                        // Update the expense in the database
                        Boolean check = ImportGDetails.UpdateIGDetails(
                            Detailobject.getManh(),
                            masp,
                            Integer.parseInt(slctField.getText()),
                            Integer.parseInt(slttField.getText()),
                            Double.parseDouble(dongiaField.getText()),
                            Double.parseDouble(othercostField.getText()),
                            Double.parseDouble(ckField.getText()),
                            Double.parseDouble(vatField.getText())
                        );
                        if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                        else JOptionPane.showMessageDialog(dialog, "Lỗi", "Error", JOptionPane.ERROR_MESSAGE);
                        // Reload the table data
                        loadimportgooddata(tableModel,importID);
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
                                "Product ID: " + importgID + "\n" +
                                "Paper Number: " + tableModel.getValueAt(selectedRow, 1).toString() + "\n" +
                                "Exact Number: " + tableModel.getValueAt(selectedRow, 2).toString() + "\n" +
                                "Amount: " + tableModel.getValueAt(selectedRow, 3).toString() + "\n" +
                                "Other cost: " + tableModel.getValueAt(selectedRow, 4).toString() + "\n" +
                                "Discount: " + tableModel.getValueAt(selectedRow, 5).toString() + "\n" +
                                "Vat: " + tableModel.getValueAt(selectedRow, 6).toString() + "\n" +
                                "Date: " + tableModel.getValueAt(selectedRow, 7).toString() + "\n",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Delete the device
                    Boolean check = ImportGDetails.DeleteIGDetails(importID, importgID);
                    if (check) JOptionPane.showMessageDialog(mainPanel, "Thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(dialog, "Lỗi.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Refresh the table
                    loadimportgooddata(tableModel, importID);
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Lỗi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    private static void loadimportgooddata(DefaultTableModel tableModel, String importID){
        tableModel.setRowCount(0); // Clear existing rows

        // Fetch deposit records using SelectDeposit
        ArrayList<ImportGDetailsClass> importGood = ImportGDetails.SelectIGDetailsByID(importID);
        for (ImportGDetailsClass im : importGood) {
            tableModel.addRow(new Object[]{
                im.getMasp(),
                im.getSlTheoChungTu(),
                im.getSlThucNhap(),
                im.getDonGia(),
                im.getChiPhiKhac(),
                im.getCktm(),
                im.getVat(),
                im.getCreateat()
            });
        }
    }
    private static void loadimportdevicedata(DefaultTableModel tableModel, String importID){
        tableModel.setRowCount(0); // Clear existing rows

        // Fetch deposit records using SelectDeposit
        ArrayList<ImportDDetailsClass> importGood = ImportDDetails.SelectIDDetailsByID(importID);
        for (ImportDDetailsClass im : importGood) {
            tableModel.addRow(new Object[]{
                im.getMatb(),
                im.getDonGia(),
                im.getChiPhiKhac(),
                im.getCktm(),
                im.getVat(),
                im.getCreateat()
            });
        }
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
        btnAddDetail = new javax.swing.JButton();
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
        jLabel1.setText("Danh Sách Nhập Hàng");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhập Hàng", "Mã Chứng Từ", "Mã NCC", "Mã NV", "Trị Giá", "Ngày Nhập", "Loại Nhập Hàng"
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

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAddDetail.setText("Thêm Chi Tiết");
        btnAddDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDetailActionPerformed(evt);
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
                        .addComponent(btnAddDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
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
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        ImportGoodsClass good = new ImportGoodsClass();
        Insert insertForm = new Insert((UpdatableEntity) good, this::loadForm9);
        insertForm.setVisible(true);
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteUtil.deleteRecord(table, "NHAP_HANG", "MANH");
        loadForm9();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDetailActionPerformed
        int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String manh = table.getValueAt(selectedRow, 0).toString();
            if (table.getValueAt(selectedRow, 6).toString().equals("NHAPTB")){
                importdevicedetail(manh);
            }
            else importgooddetail(manh);
    }//GEN-LAST:event_btnAddDetailActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 bản ghi!");
            return;
        }

        // Get selected record details
        String importID = table.getValueAt(selectedRow, 0).toString();
        String mact = table.getValueAt(selectedRow, 1).toString();
        String mancc = table.getValueAt(selectedRow, 2).toString();
        String nhanvien = table.getValueAt(selectedRow, 3).toString();
        String trigia = table.getValueAt(selectedRow, 4).toString();
        String loainhap = table.getValueAt(selectedRow, 6).toString();

        if (importID != null) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Cập nhật");
            dialog.setSize(260, 280);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setLayout(new FlowLayout(FlowLayout.CENTER));

            JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            inputPanel.add(new JLabel("Mã chứng từ:"));
            JTextField mactField = new JTextField();
            mactField.setText(mact);
            mactField.setPreferredSize(new Dimension(100, 30));
            inputPanel.add(mactField);

            inputPanel.add(new JLabel("Mã nhà cung cấp: "));
            JTextField manccField = new JTextField();
            manccField.setText(mancc);
            manccField.setPreferredSize(new Dimension(100,30));
            inputPanel.add(manccField);

            inputPanel.add(new JLabel("Mã nhân viên"));
            JTextField manvField = new JTextField();
            manvField.setText(nhanvien);
            manvField.setPreferredSize(new Dimension(100,30));
            inputPanel.add(manvField);

            inputPanel.add(new JLabel("Trị giá"));
            JTextField costField = new JTextField();
            costField.setText(trigia);
            costField.setPreferredSize(new Dimension(100, 30));
            inputPanel.add(costField);

            inputPanel.add(new JLabel("Loại nhập"));
            String[] cacloainhap = {"NHAPTB","NHAPSP"};
            JComboBox loainhapupdate = new JComboBox<>(cacloainhap);
            loainhapupdate.setSelectedItem(loainhap);
            inputPanel.add(loainhapupdate);

            JButton submitButton = new JButton("Xác nhận");
            submitButton.addActionListener(submitEvent -> {
                try {

                    // Update the expense in the database
                    Boolean check = ImportGoods.UpdateIGoods(importID, mactField.getText(), manccField.getText(), manvField.getText(), Double.parseDouble(costField.getText()), (String) loainhapupdate.getSelectedItem());
                    if (check) JOptionPane.showMessageDialog(dialog, "Thành công!");
                    else JOptionPane.showMessageDialog(dialog, "Lỗi.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Reload the table data
                    loadForm9();
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Định dạng sai.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            dialog.add(inputPanel);
            dialog.add(submitButton);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(panel, "Unable to fetch expense details.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDetail;
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
