package com.raven.dbfunction;
import com.raven.classes.ImportDDetailsClass;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportDDetails {
     

    // Function to add a new record to the CHI_TIET_NHAP_THIET_BI table
    public static boolean AddIDDetails(String maNH, String tenTB, String loaiTB, double donGia, double chiPhiKhac, double cktm, double vat) {
        String sql = "{CALL ADD_NEW_DEVICE_DETAIL(?, ?, ?, ?, ?, ?, ?)}";

        try ( Connection conn = DBConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            // Set parameters for the stored procedure
            stmt.setString(1, maNH);         // p_MANH
            stmt.setString(2, tenTB);        // p_TENTB
            stmt.setString(3, loaiTB);       // p_LOAITB
            stmt.setDouble(4, donGia);       // p_DONGIA
            stmt.setDouble(5, chiPhiKhac);   // p_CHIPHIKHAC
            stmt.setDouble(6, cktm);         // P_CKTM
            stmt.setDouble(7, vat);          // P_VAT

            // Execute the stored procedure
            stmt.execute();
            System.out.println("✅ Device detail added successfully using stored procedure!");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error adding device detail: " + e.getMessage());
        }

        return false;
    }

    // Function to update a record in the CHI_TIET_NHAP_THIET_BI table
    public static boolean UpdateIDDetails(String manh, String matb, double donGia, double chiPhiKhac, double cktm, double vat) {
        String sql = "UPDATE CHI_TIET_NHAP_TB SET DONGIA = ?, CHIPHIKHAC = ?, CKTM = ?, VAT = ? WHERE MANH = ? AND MATB = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, donGia);
            stmt.setDouble(2, chiPhiKhac);
            stmt.setDouble(3, cktm);
            stmt.setDouble(4, vat);
            stmt.setString(5, manh);
            stmt.setString(6, matb);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Import device details record updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating import device details record: " + e.getMessage());
        }
        return false;
    }

    // Function to delete a record from the CHI_TIET_NHAP_THIET_BI table by ID
    public static boolean DeleteIDDetails(String manh, String matb) {
        String sql = "UPDATE CHI_TIET_NHAP_TB SET IS_DELETE = 1 WHERE MANH = ? AND MATB = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manh);
            stmt.setString(2, matb);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Import device details record deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting import device details record: " + e.getMessage());
        }
        return false;
    }

    // Function to select records from the CHI_TIET_NHAP_THIET_BI table
    public static ArrayList<ImportDDetailsClass> SelectIDDetails() {
        String sql = "SELECT MANH, MATB, DONGIA, CHIPHIKHAC, CKTM, VAT FROM CHI_TIET_NHAP_TB WHERE IS_DELETE = 0";
        ArrayList<ImportDDetailsClass> importDDetailsList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ImportDDetailsClass importDDetails = new ImportDDetailsClass();
                importDDetails.setManh(rs.getString("MANH"));
                importDDetails.setMatb(rs.getString("MATB"));
                importDDetails.setDonGia(rs.getDouble("DONGIA"));
                importDDetails.setChiPhiKhac(rs.getDouble("CHIPHIKHAC"));
                importDDetails.setCktm(rs.getDouble("CKTM"));
                importDDetails.setVat(rs.getDouble("VAT"));
                importDDetailsList.add(importDDetails);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching import device details records: " + e.getMessage());
        }

        return importDDetailsList;
    }
    public static ArrayList<ImportDDetailsClass> SelectIDDetailsByID(String importID) {
        String sql = "SELECT MANH, MATB, DONGIA, CHIPHIKHAC, CKTM, VAT, CREATED_AT FROM CHI_TIET_NHAP_TB WHERE IS_DELETE = 0 AND MANH = ?";
        ArrayList<ImportDDetailsClass> importDDetailsList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, importID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ImportDDetailsClass importDDetails = new ImportDDetailsClass();
                importDDetails.setManh(rs.getString("MANH"));
                importDDetails.setMatb(rs.getString("MATB"));
                importDDetails.setDonGia(rs.getDouble("DONGIA"));
                importDDetails.setChiPhiKhac(rs.getDouble("CHIPHIKHAC"));
                importDDetails.setCktm(rs.getDouble("CKTM"));
                importDDetails.setVat(rs.getDouble("VAT"));
                importDDetails.setCrerateat(rs.getDate("CREATED_AT"));
                importDDetailsList.add(importDDetails);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching import device details records: " + e.getMessage());
        }

        return importDDetailsList;
    }
}