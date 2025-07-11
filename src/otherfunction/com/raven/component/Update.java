/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.component;


import com.formdev.flatlaf.FlatLightLaf;
import java.sql.*;
import com.raven.interfaces.UpdatableEntity;
import com.raven.interfaces.UpdateCallback;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.html.HTML;

/**
 *
 * @author congd
 */
public class Update extends javax.swing.JFrame {

    private final UpdatableEntity currentEntity;
    private final JPanel formPanel;
    private final Map<String, JComponent> inputFields = new HashMap<>();
    // Danh sách các field không cần nhập vì đã có mặc định trong CSDL
    private final Set<String> defaultFields = Set.of("createdAt", "isDelete","createat", "create_at", "roleType", "ngaynhap");
    private final UpdateCallback callback;
    
    public Update(UpdatableEntity entity, UpdateCallback callback) {
        this.currentEntity = entity;
        this.callback = callback;
        this.formPanel = new JPanel();
        
        setupDialog();
        buildForm(entity);
        addButtons();
        
        setVisible(true);
    }

    private void setupDialog() {
        setTitle("Update Information");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // Set layout with padding
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);
        // Add scroll pane with padding
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
            
    private void buildForm(UpdatableEntity entity) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        int row = 0;

        try {
            Field[] fields = entity.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                String fieldName = field.getName();
                Object value = field.get(entity);

                // Hiển thị tên label đẹp hơn
                String displayName = fieldName.substring(0, 1).toUpperCase() +
                                     fieldName.substring(1).replaceAll("([A-Z])", "$1");

                JLabel label = new JLabel(displayName + ":");
                label.setFont(new Font("Arial", Font.BOLD, 12));
                label.setBackground(Color.WHITE);
                label.setOpaque(true);

                JComponent inputComponent;

                // ✅ Nếu là field đầu tiên → dùng JTextField không cho sửa
                if (i == 0 || defaultFields.contains(field.getName())) {
//                    JTextField textField = new JTextField(20);
//                    textField.setFont(new Font("Arial", Font.PLAIN, 12));
//                    textField.setBackground(Color.LIGHT_GRAY);
//                    textField.setEnabled(false);  // không cho sửa
//                    if (value != null) textField.setText(value.toString());
//                    inputComponent = textField;
                    continue;
                }

                // ✅ Nếu giá trị là RUNNING/PAUSING/FIXING → JComboBox
                else if (value instanceof String val &&
                        (val.equals("RUNNING") || val.equals("PAUSING") || val.equals("FIXING"))) {
                    JComboBox<String> comboBox = new JComboBox<>(new String[]{"RUNNING", "PAUSING", "FIXING"});
                    comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
                    comboBox.setSelectedItem(val);
                    inputComponent = comboBox;
                }
                else if (value instanceof String val &&
                        (val.equals("DALAP") || val.equals("ACTIVE") || val.equals("INACTIVE"))) {
                    JComboBox<String> comboBox = new JComboBox<>(new String[]{"DALAP", "ACTIVE", "INACTIVE"});
                    comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
                    comboBox.setSelectedItem(val);
                    inputComponent = comboBox;
                }
                    
                // ✅ Còn lại là JTextField bình thường
                else {
                    JTextField textField = new JTextField(20);
                    textField.setFont(new Font("Arial", Font.PLAIN, 12));
                    textField.setBackground(Color.WHITE);
                    if (value != null) textField.setText(value.toString());
                    inputComponent = textField;
                }

                // Thêm vào layout
                gbc.gridx = 0;
                gbc.gridy = row;
                formPanel.add(label, gbc);

                gbc.gridx = 1;
                formPanel.add(inputComponent, gbc);

                inputFields.put(fieldName, inputComponent);
                row++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error building form: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> saveAndUpdate());
        
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);
        
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveAndUpdate() {
        try {
            for (Field field : currentEntity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                JComponent comp = inputFields.get(field.getName());

                Object value = null;
                if (defaultFields.contains(field.getName())) {
                    continue;
                }
                if (comp instanceof JTextField tf) {
                    String text = tf.getText().trim();

                    // Validate empty field
                    if (text.isEmpty() && !defaultFields.contains(field.getName())) {
                        JOptionPane.showMessageDialog(this,
                            "Field '" + field.getName() + "' cannot be empty",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Parse to correct type
                    try {
                        Class<?> type = field.getType();
                        value = switch (type.getSimpleName()) {
                            case "int" -> Integer.parseInt(text);
                            case "double" -> Double.parseDouble(text);
                            case "boolean" -> Boolean.parseBoolean(text);
                            default -> text;
                        };
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                            "Invalid number format for field '" + field.getName() + "'",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                } else if (comp instanceof JComboBox cb) {
                    value = cb.getSelectedItem();
                }

                // Set value to field if parsed successfully
                if (value != null) {
                    field.set(currentEntity, value);
                }
            }

            // Update and close
            if (currentEntity.update()) {
                JOptionPane.showMessageDialog(this,
                    "Information updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                if (callback != null) callback.onUpdateSuccess();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Failed to update information. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
