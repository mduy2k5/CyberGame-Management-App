package com.raven.dbfunction;
import com.raven.classes.SupplierClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Supplier {
     
    public static boolean AddSupplier(String tenNCC, String sdt, String email, String website, String diaChi) {
        String sql = "INSERT INTO NHA_CUNG_CAP (TENNCC, SDT, EMAIL, WEBSITE, DIACHI) VALUES (?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenNCC);
            stmt.setString(2, sdt);
            stmt.setString(3, email);
            stmt.setString(4, website);
            stmt.setString(5, diaChi);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding record: " + e.getMessage());
            
        }
        return false; // Return false if the record was not added successfully
    }

    // Function to update a record in NHA_CUNG_CAP
    public static boolean UpdateSupplier(String maNCC, String tenNCC, String sdt, String email, String website, String diaChi) {
        String sql = "UPDATE NHA_CUNG_CAP SET TENNCC = ?, SDT = ?, EMAIL = ?, WEBSITE = ?, DIACHI = ? WHERE MANCC = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenNCC);
            stmt.setString(2, sdt);
            stmt.setString(3, email);
            stmt.setString(4, website);
            stmt.setString(5, diaChi);
            stmt.setString(6, maNCC);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating record: " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }

    // Function to delete a record from NHA_CUNG_CAP by ID
    public static boolean DeleteSupplier(String maNCC) {
        String sql = "UPDATE NHA_CUNG_CAP SET IS_DELETE = 1 WHERE MANCC = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNCC);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting record: " + e.getMessage());
        }
        return false; // Return false if the record was not deleted successfully
    }
    public static ArrayList<SupplierClass> SelectSupplier() {
        String sql = "SELECT MANCC, TENNCC, SDT, EMAIL, WEBSITE, DIACHI FROM NHA_CUNG_CAP WHERE IS_DELETE = 0";
        ArrayList<SupplierClass> suppliers = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SupplierClass supplier = new SupplierClass();
                supplier.setMaNCC(rs.getString("MANCC"));
                supplier.setTenNCC(rs.getString("TENNCC"));
                supplier.setSdt(rs.getString("SDT"));
                supplier.setEmail(rs.getString("EMAIL"));
                supplier.setWebsite(rs.getString("WEBSITE"));
                supplier.setDiaChi(rs.getString("DIACHI"));

                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching suppliers: " + e.getMessage());
        }

        return suppliers;
    }
}
