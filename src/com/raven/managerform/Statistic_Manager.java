package com.raven.managerform;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.raven.classes.AreaClass;
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
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import oracle.jdbc.driver.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public final class Statistic_Manager extends javax.swing.JPanel {
    private JFreeChart chart;
    public static void loadData(String dates, String type, String type2){
        dataset.clear();
        try{
            ArrayList<RevenueClass> revenues = Revenue.RevenueByDay(dates, type2);
            for (RevenueClass re : revenues){
                String[] date = re.getCreatedAt().toString().split(" ");
                String[] day = date[0].split("-");
                if (type.equals("A")){
                    dataset.addValue(re.getTongDoanhThu(),"Tổng Doanh Thu",day[2] +"-" + day[1]);
                    dataset.addValue(re.getTongdoanhthudv(),"Tổng Doanh Thu Dịch Vụ",day[2] +"-" + day[1]);
                } else if (type.equals("B")){
                    dataset.addValue(re.getTongsodv(),"Tổng Số Dịch Vụ",day[2] +"-" + day[1]);
                    dataset.addValue(re.getTongthoigian(),"Tổng Thời Gian Chơi",day[2] +"-" + day[1]);
                }
                        
            }
        } catch (Exception e){
            System.out.println(e);
        }
        chartpanel.revalidate();
        chartpanel.repaint();
        
    }
    public Statistic_Manager() {
        initComponents();
        dataset = new DefaultCategoryDataset();
        chart = ChartFactory.createLineChart("Amount","Dates","Values",dataset,PlotOrientation.VERTICAL,true, true, false);
        chartpanel= new ChartPanel(chart);
        chartpanel.setBackground(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);
        chartpanel.setDomainZoomable(false);
        chartpanel.setRangeZoomable(false);
        
        datecombobox.setModel(new DefaultComboBoxModel(datafordate));
        datecombobox.setSelectedIndex(0);
        
        typecombobox.setModel(new DefaultComboBoxModel(new String[] {"Tổng doanh thu - Doanh thu dịch vụ", "Tổng số dịch vụ - Tổng thời gian chơi"}));
        typecombobox.setSelectedIndex(0);
        
        typecombobox2.setModel(new DefaultComboBoxModel(new String[] {"Daily", "Monthly"}));
        typecombobox2.setSelectedIndex(0);
        
        // Tùy chỉnh giao diện plot 
        CategoryPlot plot = chart.getCategoryPlot(); 
        plot.setBackgroundPaint(Color.WHITE); 
        plot.setDomainGridlinePaint(Color.BLUE);
        plot.setRangeGridlinePaint(Color.YELLOW);
        //plot.setOutlineVisible(false); 
        plot.setRangeGridlinesVisible(true); 
        plot.setRangeGridlinePaint(new Color(200,200,200)); 
        plot.setDomainGridlinesVisible(false); 
        // Tùy chỉnh renderer 
        LineAndShapeRenderer renderer = new LineAndShapeRenderer(); 
        renderer.setSeriesPaint(0, new Color(33,150,243)); 
        renderer.setSeriesStroke(0, new BasicStroke(3f)); 
        renderer.setSeriesShapesVisible(0, true); 
        renderer.setSeriesShape(0, new Ellipse2D.Double(-4,-4,8,8)); 
        plot.setRenderer(renderer);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chartpanel, BorderLayout.CENTER);
        
        loadData("7","A","Daily");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        datecombobox = new javax.swing.JComboBox<>();
        typecombobox = new javax.swing.JComboBox<>();
        typecombobox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(241, 241, 241));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Danh Sách Doanh Thu");

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );

        datecombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        datecombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datecomboboxActionPerformed(evt);
            }
        });

        typecombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typecombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typecomboboxActionPerformed(evt);
            }
        });

        typecombobox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typecombobox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typecombobox2ActionPerformed(evt);
            }
        });

        jButton1.setText("Xuất");
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(datecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(typecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(typecombobox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(43, 43, 43))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(datecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(typecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(typecombobox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void getvalue(){
        String selected = (String) datecombobox.getSelectedItem();
        String days = "7";
        if (selected != null){
            if(selected.equals("15 ngày")){
                days = "15";
            } else if (selected.equals("30 ngày")){
                days = "30";
            } else if (selected.equals("60 ngày")){
                days = "60";
            } else if (selected.equals("3 tháng")){
                days = "3";
            } else if (selected.equals("6 tháng")){
                days = "6";
            } else if (selected.equals("12 tháng")){
                days = "12";
            } 
        }
        selected = (String) typecombobox.getSelectedItem();
        String type = "A";
        if (selected != null){
            if (selected.equals("Tổng số dịch vụ - Tổng thời gian chơi")) type = "B";
        }
        selected = (String) typecombobox2.getSelectedItem();
        loadData(days, type, selected);
    }

    private void datecomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datecomboboxActionPerformed
        getvalue();
    }//GEN-LAST:event_datecomboboxActionPerformed

    private void typecomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typecomboboxActionPerformed
        getvalue();
    }//GEN-LAST:event_typecomboboxActionPerformed

    private void typecombobox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typecombobox2ActionPerformed

        String selected = (String) typecombobox2.getSelectedItem();
        String days = "Daily";
        if (selected != null){
            if (selected.equals("Daily")){
                datecombobox.setModel(new DefaultComboBoxModel(datafordate));
            } 
            else if (selected.equals("Monthly")) datecombobox.setModel(new DefaultComboBoxModel(data2fordate));
        }
        
        getvalue();
    }//GEN-LAST:event_typecombobox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String selected = (String) datecombobox.getSelectedItem();
        String days = "7";
        if (selected != null){
            if(selected.equals("15 ngày")){
                days = "15";
            } else if (selected.equals("30 ngày")){
                days = "30";
            } else if (selected.equals("60 ngày")){
                days = "60";
            } else if (selected.equals("3 tháng")){
                days = "3";
            } else if (selected.equals("6 tháng")){
                days = "6";
            } else if (selected.equals("12 tháng")){
                days = "12";
            } 
        }
        selected = (String) typecombobox.getSelectedItem();
        String type = "A";
        if (selected != null){
            if (selected.equals("Tổng số dịch vụ - Tổng thời gian chơi")) type = "B";
        }
        selected = (String) typecombobox2.getSelectedItem();
        
        ArrayList<RevenueClass> revenues = Revenue.RevenueByDay(days, selected);
        
        
        
        try {
            int width = 500;
            int height = 320;
            BufferedImage chartImage = chart.createBufferedImage(width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(chartImage, "png", baos);
            baos.flush();
            byte[] imageInBytes = baos.toByteArray();
            baos.close();

//            // 4. Tạo PDF và chèn ảnh
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("bieu_do_kho.pdf"));
//            document.open();
//            Image image = Image.getInstance(imageInBytes);
//            document.add(image);
//            document.close();

            System.out.println("✔ Biểu đồ đã được chèn vào PDF!");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu biểu đồ PDF");
            fileChooser.setSelectedFile(new File("bieu_do_kho.pdf"));
            int userSelection = fileChooser.showSaveDialog(null);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File saveFile = fileChooser.getSelectedFile();

                // 5. Xuất ra PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(saveFile));
                document.open();
                // 2. Tiêu đề
                Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLUE);
                Paragraph title = new Paragraph("BÁO CÁO", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20f);
                document.add(title);
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
                // 3. Thêm biểu đồ
                Image image = Image.getInstance(imageInBytes);
                document.add(image);
                // 3. Tạo bảng 6 cột
                PdfPTable table = new PdfPTable(4);
                table.setWidths(new float[] { 1f, 3.0f, 3f, 3f });
                //table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);
                // 4. Định nghĩa header
                String[] headers = { "STT", "Ngay tao", "Tong So Dich Vu", "Tong Thoi Gian Choi"};
                if (type.equals("A")){headers = new String[] { "STT", "Ngay Tao", "Tong Doanh Thu", "Doanh Thu Dich Vu"};}
               
                
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Phrase(header,font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5f);
                    table.addCell(cell);
                }

                // 5. Dữ liệu giả lập (VD 3 dòng)
                int i = 1;
                double a = 0;
                double b = 0;
                for (RevenueClass re : revenues) {
                    table.addCell(String.valueOf(i));                   // STT
                    i++;
                    table.addCell(re.getCreatedAt().toString());
                    if (type.equals("A")) {
                        a += re.getTongDoanhThu();
                        table.addCell(String.valueOf(re.getTongDoanhThu()));
                        b += re.getTongdoanhthudv();
                        table.addCell(String.valueOf(re.getTongdoanhthudv()));
                    } else {
                        a += re.getTongsodv();
                        table.addCell(String.valueOf(re.getTongsodv()));
                        b += re.getTongthoigian();
                        table.addCell(String.valueOf(re.getTongthoigian()));
                    }
                    
                }
                table.addCell("");
                table.addCell("Tong");
                table.addCell(String.valueOf(a));
                table.addCell(String.valueOf(b));

                document.add(table);
                
                
                
                
                
                
                
                document.close();

                JOptionPane.showMessageDialog(null, "✔ Xuất biểu đồ thành công:\n" + saveFile.getAbsolutePath());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    private static DefaultCategoryDataset dataset;

    String[] datafordate = {"7 ngày", "15 ngày", "30 ngày", "60 ngày"};
    String[] data2fordate = {"3 tháng", "6 tháng", "12 tháng"};
    private static ChartPanel chartpanel;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> datecombobox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JComboBox<String> typecombobox;
    private javax.swing.JComboBox<String> typecombobox2;
    // End of variables declaration//GEN-END:variables
}
