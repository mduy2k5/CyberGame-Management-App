/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.managermain;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raven.event.EventMenuSelected;
import com.raven.managerform.*;
import com.raven.service.ServiceLogout;
import com.raven.technicalmain.createTechnicalMainPanel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.UIManager;

/**
 *
 * @author RAVEN
 */
public class createManagerMainPanel extends javax.swing.JFrame {

    /**
     * Creates new form createManagerMainPanel
     */
    private Customer_Manager form1;
    private CustomerType_Manager form1_1;
    private Deposit_Manager form1_2;
    private Financial_Manager form2;
    private Revenue_Manager form2_1;
    private Statistic_Manager form2_2;
    private Employee_Manager form3;
    private WorkingHours_Manager form3_1;
    private LeaveDay_Manager form3_2;
    private Leave_Manager form3_3;
    private Expense_Manager form3_4;
    private EmployeeType_Manager form3_5;
    private Device_Manager form4;
    private Computer_Manager form4_1;
    private ReplaceDevice_Manager form4_2;
    private Area_Manager form5;
    private AreaType_Manager form5_1;
    private Promotion_Manager form6;
    private Product_Manager form7;
    private UsedService_Manager form7_1;
    private Event_Manager form8;
    private Import_Manager form9;
    private Supllier_Manager form9_1;
    private Infomation_Manager form12;
    private PlayerHistory_Manager form13;
    public createManagerMainPanel(String manv) throws InterruptedException, SQLException {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        form1 = new Customer_Manager(header2);
        form1_1 = new CustomerType_Manager();
        form1_2 = new Deposit_Manager();
        form2 = new Financial_Manager();
        form2_1 = new Revenue_Manager();
        form2_2 = new Statistic_Manager();
        form3 = new Employee_Manager();
        form3_1 = new WorkingHours_Manager();
        form3_2 = new LeaveDay_Manager();
        form3_3 = new Leave_Manager();
        form3_4 = new Expense_Manager();
        form3_5 = new EmployeeType_Manager();
        form4 = new Device_Manager();
        form4_1 = new Computer_Manager();
        form4_2 = new ReplaceDevice_Manager();
        form5 = new Area_Manager();
        form5_1 = new AreaType_Manager();
        form6 = new Promotion_Manager();
        form7 = new Product_Manager();
        form7_1 = new UsedService_Manager();
        form8 = new Event_Manager();
        form9 = new Import_Manager();
        form9_1 = new Supllier_Manager();
        form12 = new Infomation_Manager(manv);
        form13 = new PlayerHistory_Manager();
        menu.initMoving(createManagerMainPanel.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(String index) {
                if (index == "1.1") {
                    setForm(form4);
                } else if (index == "1.2") {
                    setForm(form4_1);
                } else if (index == "1.3") {
                    setForm(form4_2);
                } else if (index == "2.1") {
                    setForm(form1);
                } else if (index == "2.2") {
                    setForm(form1_1);
                } else if (index == "2.3") {
                    setForm(form1_2);
                } else if (index == "2.4") {
                    setForm(form13);
                } else if (index == "3.1") {
                    setForm(form2);
                } else if (index == "3.2") {
                    setForm(form2_1);
                } else if (index == "3.3") {
                    setForm(form2_2);
                } else if (index == "4.1") {
                    setForm(form5);
                } else if (index == "4.2") {
                    setForm(form5_1);
                } else if (index == "5.1") {
                    setForm(form3);
                } else if (index == "5.2") {
                    setForm(form3_1);
                }else if (index == "5.3") {
                    setForm(form3_2);
                }else if (index == "5.4") {
                    setForm(form3_3);
                }else if (index == "5.5") {
                    setForm(form3_4);
                }else if (index == "5.6") {
                    setForm(form3_5);
                } else if (index == "6") {
                    setForm(form6);
                } else if (index == "7.1") {
                    setForm(form7);
                } else if (index == "7.2") {
                    setForm(form7_1);
                } else if (index == "8") {
                    setForm(form8);
                } else if (index == "9.1") {
                    setForm(form9);
                } else if (index == "9.2") {
                    setForm(form9_1);
                } else if (index == "10") {
                    setForm(form12);
                } else if (index == "11") {
                    try {
                        ServiceLogout.logout(createManagerMainPanel.this);
                    } catch (SQLException ex) {
                        Logger.getLogger(createManagerMainPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        //  set when system open start with home form
        setForm(new Device_Manager());
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        menu = new com.raven.component.ManageMenu();
        header2 = new com.raven.component.Header();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        header2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String manv) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(createManagerMainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createManagerMainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createManagerMainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createManagerMainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf()); // hoặc FlatDarkLaf, FlatLightLaf
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new createManagerMainPanel(manv).setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(createManagerMainPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(createManagerMainPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Header header2;
    private javax.swing.JPanel mainPanel;
    private com.raven.component.ManageMenu menu;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
