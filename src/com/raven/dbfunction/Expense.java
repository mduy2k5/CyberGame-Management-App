package com.raven.dbfunction;
import com.raven.classes.ExpenseClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Expense {
     

    // Function to add a new record to the CHI_PHI table
    public static boolean AddExpense(String noidung, java.sql.Date ngaychi, String manv, String hinhthuc, double sotien, String trangthai) {
        String sql = "INSERT INTO CHI_PHI (NOIDUNG, NGAYCHI, MANV, HINHTHUC, SOTIEN,TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?)";
        
        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noidung);
            stmt.setDate(2, ngaychi);
            stmt.setString(3, manv);
            stmt.setString(4, hinhthuc);
            stmt.setDouble(5, sotien);
            stmt.setString(6, trangthai);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Expense added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding expense: " + e.getMessage());
        }
        return false;
    }

    // Function to update a record in the CHI_PHI table
    public static boolean UpdateExpense(String machiphi, String noidung, java.sql.Date ngaychi, String manv, String hinhthuc, double sotien, String trangthai) {
        String sql = "UPDATE CHI_PHI SET NOIDUNG = ?, NGAYCHI = ?, MANV = ?, HINHTHUC = ?, SOTIEN = ?, TRANGTHAI = ? WHERE MACHIPHI = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noidung);
            stmt.setDate(2, ngaychi);
            stmt.setString(3, manv);
            stmt.setString(4, hinhthuc);
            stmt.setDouble(5, sotien);
            stmt.setString(6, trangthai);
            stmt.setString(7, machiphi);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Expense updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating expense: " + e.getMessage());
        }
        return false;
    }

    // Function to delete a record from the CHI_PHI table by ID
    public static boolean DeleteExpense(String machiphi) {
        String sql = "UPDATE CHI_PHI SET IS_DELETE = 1 WHERE MACHIPHI = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, machiphi);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Expense deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting expense: " + e.getMessage());
        }
        return false;
    }

    // Function to select records from the CHI_PHI table
    public static ArrayList<ExpenseClass> SelectExpense() {
        String sql = "SELECT MACHIPHI, NOIDUNG, NGAYCHI, MANV, HINHTHUC, SOTIEN, TRANGTHAI FROM CHI_PHI WHERE IS_DELETE = 0";
        ArrayList<ExpenseClass> expenseList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ExpenseClass expense = new ExpenseClass();
                expense.setMachiphi(rs.getString("MACHIPHI"));
                expense.setNoidung(rs.getString("NOIDUNG"));
                expense.setNgaychi(rs.getDate("NGAYCHI"));
                expense.setManv(rs.getString("MANV"));
                expense.setHinhthuc(rs.getString("HINHTHUC"));
                expense.setSotien(rs.getDouble("SOTIEN"));
                expense.setTrangthai(rs.getString("TRANGTHAI"));
                expenseList.add(expense);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching expenses: " + e.getMessage());
        }

        return expenseList;
    }
}
