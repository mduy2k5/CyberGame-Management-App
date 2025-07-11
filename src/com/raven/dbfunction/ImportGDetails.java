package com.raven.dbfunction;
import com.raven.classes.ImportGDetailsClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportGDetails {
     

    // Function to add a new record to the CHI_TIET_NHAP_HANG table
    public static boolean AddIGDetails(String manh, String masp, int slTheoChungTu, int slThucNhap, double donGia, double chiPhiKhac, double cktm, double vat) {
        String sql = "INSERT INTO CHI_TIET_NHAP_HANG (MANH, MASP, SLTHEOCHUNGTU, SLTHUCNHAP, DONGIA, CHIPHIKHAC, CKTM, VAT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manh);
            stmt.setString(2, masp);
            stmt.setInt(3, slTheoChungTu);
            stmt.setInt(4, slThucNhap);
            stmt.setDouble(5, donGia);
            stmt.setDouble(6, chiPhiKhac);
            stmt.setDouble(7, cktm);
            stmt.setDouble(8, vat);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Import goods details record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding import goods details record: " + e.getMessage());
        }
        return false;
    }

    // Function to update a record in the CHI_TIET_NHAP_HANG table
    public static boolean UpdateIGDetails(String manh, String masp, int slTheoChungTu, int slThucNhap, double donGia, double chiPhiKhac, double cktm, double vat) {
        String sql = "UPDATE CHI_TIET_NHAP_HANG SET SLTHEOCHUNGTU = ?, SLTHUCNHAP = ?, DONGIA = ?, CHIPHIKHAC = ?, CKTM = ?, VAT = ? WHERE MANH = ? AND MASP = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, slTheoChungTu);
            stmt.setInt(2, slThucNhap);
            stmt.setDouble(3, donGia);
            stmt.setDouble(4, chiPhiKhac);
            stmt.setDouble(5, cktm);
            stmt.setDouble(6, vat);
            stmt.setString(7, manh);
            stmt.setString(8, masp);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Import goods details record updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating import goods details record: " + e.getMessage());
        }
        return false;
    }

    // Function to delete a record from the CHI_TIET_NHAP_HANG table by ID
    public static boolean DeleteIGDetails(String manh, String masp) {
        String sql = "UPDATE CHI_TIET_NHAP_HANG SET IS_DELETE = 1 WHERE MANH = ? AND MASP = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manh);
            stmt.setString(2, masp);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Import goods details record deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting import goods details record: " + e.getMessage());
        }
        return false;
    }

    // Function to select records from the CHI_TIET_NHAP_HANG table
    public static ArrayList<ImportGDetailsClass> SelectIGDetails() {
        String sql = "SELECT MANH, MASP, SLTHEOCHUNGTU, SLTHUCNHAP, DONGIA, CHIPHIKHAC, CKTM, VAT FROM CHI_TIET_NHAP_HANG WHERE IS_DELETE = 0";
        ArrayList<ImportGDetailsClass> importGDetailsList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ImportGDetailsClass importGDetails = new ImportGDetailsClass();
                importGDetails.setManh(rs.getString("MANH"));
                importGDetails.setMasp(rs.getString("MASP"));
                importGDetails.setSlTheoChungTu(rs.getInt("SLTHEOCHUNGTU"));
                importGDetails.setSlThucNhap(rs.getInt("SLTHUCNHAP"));
                importGDetails.setDonGia(rs.getDouble("DONGIA"));
                importGDetails.setChiPhiKhac(rs.getDouble("CHIPHIKHAC"));
                importGDetails.setCktm(rs.getDouble("CKTM"));
                importGDetails.setVat(rs.getDouble("VAT"));
                importGDetailsList.add(importGDetails);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching import goods details records: " + e.getMessage());
        }

        return importGDetailsList;
    }

    public static ArrayList<ImportGDetailsClass> SelectIGDetailsByID(String manh) {
        String sql = "SELECT MANH, MASP, SLTHEOCHUNGTU, SLTHUCNHAP, DONGIA, CHIPHIKHAC, CKTM, VAT, CREATED_AT FROM CHI_TIET_NHAP_HANG WHERE IS_DELETE = 0 AND MANH = ?";
        ArrayList<ImportGDetailsClass> importGDetailsList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, manh);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ImportGDetailsClass importGDetails = new ImportGDetailsClass();
                importGDetails.setManh(rs.getString("MANH"));
                importGDetails.setMasp(rs.getString("MASP"));
                importGDetails.setSlTheoChungTu(rs.getInt("SLTHEOCHUNGTU"));
                importGDetails.setSlThucNhap(rs.getInt("SLTHUCNHAP"));
                importGDetails.setDonGia(rs.getDouble("DONGIA"));
                importGDetails.setChiPhiKhac(rs.getDouble("CHIPHIKHAC"));
                importGDetails.setCktm(rs.getDouble("CKTM"));
                importGDetails.setVat(rs.getDouble("VAT"));
                importGDetails.setCrerateat(rs.getDate("CREATED_AT"));
                importGDetailsList.add(importGDetails);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching import goods details records: " + e.getMessage());
        }

        return importGDetailsList;
    }
}