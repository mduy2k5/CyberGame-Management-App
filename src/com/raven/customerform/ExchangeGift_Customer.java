/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.customerform;


import com.raven.classes.ExchangeGDetailsClass;
import com.raven.classes.GiftClass;
import com.raven.dbfunction.Customer;
import com.raven.dbfunction.ExchangeGiftDetails;
import com.raven.dbfunction.Gift;
import com.raven.model.ModelUser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class ExchangeGift_Customer extends javax.swing.JPanel {
    private String maKH; 
    /**
     * Creates new form Form_1
     */
    public ExchangeGift_Customer(String MaKH) {
        initComponents();
        maKH = MaKH;
        txtPoint.setText(String.valueOf(Customer.SelectCustomerByID(MaKH).getSodiemtichluy()));
        txtPoint.setEditable(false);
        // 1. Cấu hình layout dạng lưới cuộn được cho leftPanel
        leftPanel.setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // 3 cột
        gridPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(450, 600));
        
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        
        // 2. Tạo danh sách sản phẩm mẫu
        ArrayList<GiftClass> gifts = Gift.SelectGift();
        
        // 3. Bổ sung dữ liệu sản phẩm vào bảng và cập nhật tổng tiền
        java.util.Map<String, Integer> productMap = new java.util.HashMap<>();

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) selectedGiftTable.getModel();
        model.setRowCount(0);  // Xóa dữ liệu cũ

        // 4. Tạo panel sản phẩm và sự kiện
        for (GiftClass gift : gifts) {
            JPanel giftPanel = new JPanel();
            giftPanel.setLayout(new BoxLayout(giftPanel, BoxLayout.Y_AXIS));
            giftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            giftPanel.setBackground(Color.WHITE);
            giftPanel.setPreferredSize(new Dimension(150, 180));
            giftPanel.setMaximumSize(new Dimension(150, 180));

            // Tên sản phẩm
            JLabel nameLabel = new JLabel(gift.getNoiDung());
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Giá tiền
            JLabel pointLabel = new JLabel(String.format("Điểm: %d", gift.getSoDiemTieuHao()));
            pointLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            pointLabel.setForeground(Color.DARK_GRAY);
            pointLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel imageLabel = new JLabel();
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imageLabel.setPreferredSize(new Dimension(100, 100));
            imageLabel.setMaximumSize(new Dimension(100, 100));
            imageLabel.setMinimumSize(new Dimension(100, 100));

            try {
                ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/raven/images/gaubong.jpg"));
                Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                imageLabel.setText("No Image");
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
            }

            
            // Căn giữa theo chiều dọc
            giftPanel.add(imageLabel);
            giftPanel.add(Box.createVerticalGlue());
            giftPanel.add(nameLabel);
            giftPanel.add(Box.createVerticalStrut(5));
            giftPanel.add(pointLabel);
            giftPanel.add(Box.createVerticalGlue());
            // Sự kiện khi click chọn sản phẩm
            giftPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String key = gift.getMaQT();
                    int diemMoi = gift.getSoDiemTieuHao();  // điểm cho 1 món
                    boolean found = false;

                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 0).equals(key)) {
                            // Tăng số lượng
                            int oldSoLuong = (int) model.getValueAt(i, 3);
                            int newSoLuong = oldSoLuong + 1;
                            model.setValueAt(newSoLuong, i, 3);

                            // Cập nhật lại điểm = số lượng * điểm/món
                            int newTongDiem = newSoLuong * diemMoi;
                            model.setValueAt(newTongDiem, i, 2);

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        // Nếu chưa có thì thêm mới, số lượng là 1
                        model.addRow(new Object[]{
                            gift.getMaQT(),
                            gift.getNoiDung(),
                            diemMoi,    // tổng điểm ban đầu = 1 * điểm mỗi món
                            1           // số lượng
                        });
                    }

                    // Cập nhật tổng điểm tất cả
                    int totalPoint = 0;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        totalPoint += (int) model.getValueAt(i, 2);
                    }
                    txtTotalPoint.setText(String.valueOf(totalPoint));
                }
            });

            gridPanel.add(giftPanel);
        }
        SwingUtilities.invokeLater(() -> {
            int numProducts = gifts.size();
            int numCols = 3;
            int numRows = (int) Math.ceil(numProducts / (double) numCols);
            int panelWidth = numCols * (150 + 10);
            int panelHeight = numRows * (190 + 10);
            gridPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            gridPanel.revalidate();  // cập nhật layout
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnExchange = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTotalPoint = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        point = new javax.swing.JLabel();
        txtPoint = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedGiftTable = new javax.swing.JTable();
        btnHistory = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(915, 620));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(395, 100));

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnExchange.setText("Đổi");
        btnExchange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExchangeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Điểm tiêu hao");

        txtTotalPoint.setEditable(false);
        txtTotalPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPointActionPerformed(evt);
            }
        });

        point.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        point.setText("Điểm hiện có:");

        txtPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPointActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(point)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExchange, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(point, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotalPoint)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExchange, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        selectedGiftTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã QT", "Nội Dung", "Số Điểm", "SL"
            }
        ));
        jScrollPane1.setViewportView(selectedGiftTable);

        btnHistory.setText("Lịch sử");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPointActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPointActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        DefaultTableModel model = (DefaultTableModel) selectedGiftTable.getModel();
        model.setRowCount(0);  // Xóa tất cả dòng trong bảng

        txtTotalPoint.setText("0");  // Reset tổng điểm về 0 nếu có
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnExchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExchangeActionPerformed
        int tongDiemCanDoi = Integer.parseInt(txtTotalPoint.getText());
        int diemHienTai = Customer.SelectCustomerByID(maKH).getSodiemtichluy();
        if (tongDiemCanDoi == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một món quà để đổi.");
            return;
        }

        if (diemHienTai < tongDiemCanDoi) {
            JOptionPane.showMessageDialog(null, "Số điểm hiện tại không đủ để đổi quà.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đổi quà không?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean allSuccess = true;
            for (int i = 0; i < selectedGiftTable.getRowCount(); i++) {
                String maQT = selectedGiftTable.getValueAt(i, 0).toString(); // Mã QT ở cột 0
                int soLuong = Integer.parseInt(selectedGiftTable.getValueAt(i, 3).toString());
                boolean result = ExchangeGiftDetails.addExchangeGiftDetails(maKH, maQT, soLuong);
                if (!result) {
                    allSuccess = false;
                    JOptionPane.showMessageDialog(null, "Đổi quà thất bại với mã: " + maQT);
                }
            }

            if (allSuccess) {
                JOptionPane.showMessageDialog(null, "Đổi quà thành công!");
                DefaultTableModel model = (DefaultTableModel) selectedGiftTable.getModel();
                model.setRowCount(0);
                txtPoint.setText(String.valueOf(Customer.SelectCustomerByID(maKH).getSodiemtichluy() - tongDiemCanDoi));
            }
        }
    }//GEN-LAST:event_btnExchangeActionPerformed

    private void txtPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPointActionPerformed
        
    }//GEN-LAST:event_txtPointActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Lịch sử đổi quà", true);
        dialog.setSize(800, 400);
        dialog.setLayout(new BorderLayout());

        // Add a title label
        JLabel titleLabel = new JLabel("Lịch sử đổi quà của bạn", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        dialog.add(titleLabel, BorderLayout.NORTH);

        // Create a table to display device details
        String[] columnNames = {"Mã đổi quà","Mã quà tặng", "Tên quà", "Số lượng","Thời gian đặt","Trạng thái"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Fetch device details using SelectCDDetailsByMAPC
        ArrayList<ExchangeGDetailsClass> uservices = ExchangeGiftDetails.SelectAllExchangeGiftDetailsByID(maKH);
        for (ExchangeGDetailsClass uservice : uservices) {
            // Fetch device name and type using SelectDeviceByID
            GiftClass name = Gift.SelectGiftById(uservice.getMaQT());
            tableModel.addRow(new Object[]{
                uservice.getMaDQ(),
                uservice.getMaQT(),
                name.getNoiDung(),
                uservice.getSoLuong(),
                uservice.getCreatedat(),
                uservice.getTrangThai()
            });
        }

        // Add a "Close" button
        JButton closeButton = new JButton("Đóng");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setBackground(new Color(52, 152, 219)); // Blue color
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        closeButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnHistoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExchange;
    private javax.swing.JButton btnHistory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel point;
    private javax.swing.JTable selectedGiftTable;
    private javax.swing.JTextField txtPoint;
    private javax.swing.JTextField txtTotalPoint;
    // End of variables declaration//GEN-END:variables
}
