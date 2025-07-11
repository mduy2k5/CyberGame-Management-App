package com.raven.component;

import com.raven.event.EventMenuSelected;
import com.raven.model.Model_Menu;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ManageMenu extends javax.swing.JPanel {

    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public ManageMenu() {
        initComponents();
        
        // Đặt thanh cuộn tùy chỉnh
        scrollPane.setVerticalScrollBar(new ScrollBar());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Làm trong suốt toàn bộ scrollPane
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBorder(null);
        scrollPane.setBorder(null);

        // Làm trong suốt component chứa menu
        listMenu1.setOpaque(false);
        listMenu1.setBorder(null);

        // Làm trong suốt chính `ManageMenu` nếu cần
        setOpaque(false);
        setBorder(null);
        init();
    }

    private void init() {
        listMenu1.addItem(new Model_Menu("1","1", "Quản Lý Thiết Bị", Model_Menu.MenuType.MENU,
                new Model_Menu("1.1","1", "Thiết Bị", Model_Menu.MenuType.MENU),
                new Model_Menu("1.2","1", "PC", Model_Menu.MenuType.MENU),
                new Model_Menu("1.3","1", "Thay Thế Thiết Bị", Model_Menu.MenuType.MENU)
        ));
                                         
        listMenu1.addItem(new Model_Menu("2","8", "Quản Lý Khách Hàng", Model_Menu.MenuType.MENU,
                new Model_Menu("2.1","8", "Khách Hàng", Model_Menu.MenuType.MENU),
                new Model_Menu("2.2","8", "Hạng Khách Hàng", Model_Menu.MenuType.MENU),
                new Model_Menu("2.3","8", "Nạp Tiền", Model_Menu.MenuType.MENU),
                new Model_Menu("2.4","8", "Lịch Sử Hoạt Động", Model_Menu.MenuType.MENU)
                ));
        listMenu1.addItem(new Model_Menu("3","2", "Quản Lý Tài Chính", Model_Menu.MenuType.MENU,
                new Model_Menu("3.1","2", "Tài Chính", Model_Menu.MenuType.MENU),
                new Model_Menu("3.2","2", "Doanh Thu", Model_Menu.MenuType.MENU),
                new Model_Menu("3.3","2", "Báo Cáo", Model_Menu.MenuType.MENU)
        ));
        listMenu1.addItem(new Model_Menu("4","4", "Quản Lý Khu Vực", Model_Menu.MenuType.MENU,
                new Model_Menu("4.1","4", "Khu Vực", Model_Menu.MenuType.MENU),
                new Model_Menu("4.2","4", "Loại Khu Vực", Model_Menu.MenuType.MENU)
        ));
        listMenu1.addItem(new Model_Menu("5","8", "Quản Lý Nhân Viên", Model_Menu.MenuType.MENU,
                    new Model_Menu("5.1","8", "Nhân Viên", Model_Menu.MenuType.MENU),
                    new Model_Menu("5.2","8", "Ca Làm", Model_Menu.MenuType.MENU),
                    new Model_Menu("5.3","8", "Số Ngày Nghỉ", Model_Menu.MenuType.MENU),
                    new Model_Menu("5.4","8", "Nghỉ Phép", Model_Menu.MenuType.MENU),
                    new Model_Menu("5.5","8", "Chi Phí", Model_Menu.MenuType.MENU),
                    new Model_Menu("5.6","8", "Loại Nhân Viên", Model_Menu.MenuType.MENU)
        ));
        listMenu1.addItem(new Model_Menu("6","6", "Quản Lý Khuyến Mãi", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("7","7", "Quản Lý Sản Phẩm", Model_Menu.MenuType.MENU,
                new Model_Menu("7.1","7", "Sản Phẩm", Model_Menu.MenuType.MENU),
                new Model_Menu("7.2","7", "Dịch Vụ Đã Dùng", Model_Menu.MenuType.MENU)
        ));
        listMenu1.addItem(new Model_Menu("8","2", "Quản Lý Sự Kiện", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9","2", "Quản Lý Nhập Hàng", Model_Menu.MenuType.MENU,
                new Model_Menu("9.1","2", "Nhập Hàng", Model_Menu.MenuType.MENU),
                new Model_Menu("9.2","2", "Nhà Cung Cấp", Model_Menu.MenuType.MENU)
        ));
        listMenu1.addItem(new Model_Menu("","", " ", Model_Menu.MenuType.EMPTY));

        listMenu1.addItem(new Model_Menu("","", "Thông Tin Nhân Viên", Model_Menu.MenuType.TITLE));
        

        listMenu1.addItem(new Model_Menu("","", " ", Model_Menu.MenuType.EMPTY));
        listMenu1.addItem(new Model_Menu("10","8", "Thông tin nhân viên", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("11","10", "Logout", Model_Menu.MenuType.MENU));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        listMenu1 = new com.raven.swing.ListMenu<>();

        panelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/logo2.png"))); // NOI18N
        jLabel1.setText("Vuon Sao Bang");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        listMenu1.setBorder(null);
        scrollPane.setViewportView(listMenu1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#C08081"), 0, getHeight(), Color.decode("#800000"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private com.raven.swing.ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
