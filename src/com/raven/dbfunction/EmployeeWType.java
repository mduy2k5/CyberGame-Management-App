package com.raven.dbfunction;
import com.raven.classes.EmployeeTypeClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeWType {
    // Global variables for database connection
     

    // Function to add a new employee type
    public static boolean AddEWType(String tenLoaiNV, int moTa) {
        String sql = "INSERT INTO LOAI_NHAN_VIEN (VITRI, MUCLUONG) VALUES (?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenLoaiNV);
            stmt.setInt(2, moTa);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Employee type added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding employee type: " + e.getMessage());
        }
        return false; // Return false if the employee type was not added successfully
    }

    // Function to update employee type information
    public static boolean UpdateEWType(String maLoaiNV, String tenLoaiNV, int moTa) {
        String sql = "UPDATE LOAI_NHAN_VIEN SET VITRI = ?, MUCLUONG = ? WHERE MALNV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenLoaiNV);
            stmt.setInt(2, moTa);
            stmt.setString(3, maLoaiNV);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Employee type updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating employee type: " + e.getMessage());
        }
        return false; // Return false if the employee type was not updated successfully
    }

    // Function to delete an employee type by setting IS_DELETE to 1
    public static boolean DeleteEWType(String maLoaiNV) {
        String sql = "UPDATE LOAI_NHAN_VIEN SET IS_DELETE = 1 WHERE MALNV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLoaiNV);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Employee type deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting employee type: " + e.getMessage());
        }
        return false; // Return false if the employee type was not deleted successfully
    }

    // Function to select all employee types and return an ArrayList of EmployeeTypeClass
    public static ArrayList<EmployeeTypeClass> SelectEWType() {
        String sql = "SELECT MALNV, VITRI, MUCLUONG FROM LOAI_NHAN_VIEN WHERE IS_DELETE = 0";
        ArrayList<EmployeeTypeClass> employeeTypes = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeTypeClass employeeType = new EmployeeTypeClass();
                employeeType.setMaLoaiNV(rs.getString("MALNV"));
                employeeType.setTenLoaiNV(rs.getString("VITRI"));
                employeeType.setMucLuong(rs.getInt("MUCLUONG"));

                employeeTypes.add(employeeType);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching employee types: " + e.getMessage());
        }

        return employeeTypes;
    }
}