package com.raven.event;

import com.raven.dbfunction.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.JTable;

public class DeleteUtil {

    // Hàm xóa bản ghi khỏi table dựa theo tên bảng và JTable hiển thị
    public static void deleteRecord(JTable table, String tableName, String primaryKeyColumn) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a record to delete!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        // Giả định cột 0 là khóa chính
        String primaryKeyValue = table.getValueAt(selectedRow, 0).toString();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyColumn + " = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, primaryKeyValue);
                int affected = pstmt.executeUpdate();
                if (affected > 0) {
                    JOptionPane.showMessageDialog(null, "Delete complete!");
                } else {
                    JOptionPane.showMessageDialog(null, "Record not found or already deleted.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error during delete: " + ex.getMessage());
        }
    }
}