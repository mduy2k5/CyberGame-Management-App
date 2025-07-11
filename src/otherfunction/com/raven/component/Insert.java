package com.raven.component;


import com.formdev.flatlaf.FlatLightLaf;
import java.sql.*;
import java.awt.Color;
import com.raven.interfaces.UpdatableEntity;
import com.raven.interfaces.UpdateCallback;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

/**
 *
 * @author congd
 */
public class Insert extends javax.swing.JFrame {
    private final UpdatableEntity entity;
    private final UpdateCallback callback;
    private final JPanel formPanel;
    private final Map<String, JComponent> inputFields = new HashMap<>();
    // Danh sách các field không cần nhập vì đã có mặc định trong CSDL
    private final Set<String> defaultFields = Set.of("createdAt","createat", "create_at", "createAt", "isDelete", "roleType", "ngaynhap");
    public Insert(UpdatableEntity entity, UpdateCallback callback) {
        this.entity = entity;
        this.callback = callback;
        this.formPanel = new JPanel();
        
        setupDialog();
        buildForm(entity);
        addButtons();
        
        setVisible(true);
    }

    private void setupDialog() {
        setTitle("Insert Information");
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
                else if (fieldName.equalsIgnoreCase("trangThai")) {
                    JComboBox<String> comboBox = new JComboBox<>(new String[]{"RUNNING", "PAUSING", "FIXING"});
                    comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
                    inputComponent = comboBox;
                }
                else if (fieldName.equalsIgnoreCase("loainhaphang")) {
                    JComboBox<String> comboBox = new JComboBox<>(new String[]{"NHAPSP", "NHAPTB"});
                    comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
                    inputComponent = comboBox;
                }
                else if (fieldName.toLowerCase().matches("ngaysinh|ngaybd|ngaykt|tgbd|tgkt|thoigian|ngaycc|ngvl")) {
                    JDateChooser dateChooser = new JDateChooser();
                    dateChooser.setDateFormatString("yyyy-MM-dd");
                    dateChooser.setFont(new Font("Arial", Font.PLAIN, 12));
                    inputComponent = dateChooser;
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
        btnSave.addActionListener(e -> saveAndInsert());
        
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);
        
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveAndInsert() {
        try {
            
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                JComponent input = inputFields.get(field.getName());

                Object value = null;
                if (defaultFields.contains(field.getName())) {
                    continue;
                }
                if (input instanceof JTextField tf) {
                    String text = tf.getText().trim();

                    // ✅ Bỏ qua kiểm tra nếu là trường mặc định
                        if (text.isEmpty() && !defaultFields.contains(field.getName())) {
                        JOptionPane.showMessageDialog(this,
                                "Field '" + field.getName() + "' cannot be empty",
                                "Validation Error",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    if (!text.isEmpty()) {
                        Class<?> type = field.getType();
                        value = switch (type.getSimpleName()) {
                            case "int" -> Integer.parseInt(text);
                            case "double" -> Double.parseDouble(text);
                            case "boolean" -> Boolean.parseBoolean(text);
                            default -> text;
                        };
                    }

                } else if (input instanceof JComboBox cb) {
                    Object selected = cb.getSelectedItem();
                    Class<?> type = field.getType();

                    if (type.isEnum()) {
                        value = Enum.valueOf((Class<Enum>) type, selected.toString());
                    } else {
                        value = selected;
                    }
                    
                } else if (input instanceof JDateChooser) {
                        java.util.Date selectedDate = ((JDateChooser) input).getDate();
                        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
                        if (sqlDate == null) {
                            JOptionPane.showMessageDialog(this,
                                    "Field '" + field.getName() + "' cannot be empty",
                                    "Validation Error",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        Class<?> type = field.getType();
                        if (type == java.sql.Date.class) {
                            value = new java.sql.Date(sqlDate.getTime());  // ✅ chuyển đúng kiểu
                        } else if (type == java.util.Date.class) {
                            value = sqlDate;
                        } else {
                            // ❌ Tránh ép sai kiểu
                            throw new IllegalArgumentException("Not support for: " + type.getName());
                        }
                    }

                if (value != null) {
                    field.set(entity, value);
                }
            }

            if (entity.insert()) {
                JOptionPane.showMessageDialog(this, "Inserted successfully!");
                if (callback != null) callback.onUpdateSuccess();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Insert failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Insert Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
