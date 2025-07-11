package com.raven.dbfunction;

import com.raven.classes.ComputerDDetailsClass;
import java.sql.*;
import java.util.ArrayList;

public class ComputerDDetails {


    // Function to add a new record
    public static boolean AddCDDetails(String maNH, String tenTB, String loaiTB, double donGia, double chiPhiKhac, double cktm, double vat) {
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

    // Function to select records by MAPC
    public static ArrayList<ComputerDDetailsClass> SelectCDDetailsByMAPC(String maPC) {
        ArrayList<ComputerDDetailsClass> detailsList = new ArrayList<>();
        String sql = "SELECT MAPC, MATB, IS_DELETE, CREATED_AT FROM THIET_BI_TUNG_MAY WHERE MAPC = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maPC);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ComputerDDetailsClass details = new ComputerDDetailsClass(
                        rs.getString("MAPC"),
                        rs.getString("MATB"),
                        rs.getInt("IS_DELETE"),
                        rs.getTimestamp("CREATED_AT")
                    );
                    detailsList.add(details);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error selecting records by MAPC: " + e.getMessage());
        }

        return detailsList;
    }

    // Function to change IS_DELETE to 1 by MAPC
    public static boolean DeleteCDDetails(String matb) {
        String sql = "UPDATE THIET_BI_TUNG_MAY SET IS_DELETE = 1 WHERE MATB = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matb);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error deleting record in THIET_BI_TUNG_MAY: " + e.getMessage());
        }

        return false;
    }

    // Function to select records by MATB
    public static ComputerDDetailsClass SelectCDDetailsByMATB(String maTB) {
        ComputerDDetailsClass detailsList = new ComputerDDetailsClass();
        String sql = "SELECT MAPC, MATB, IS_DELETE, CREATED_AT FROM THIET_BI_TUNG_MAY WHERE MATB = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maTB);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    detailsList = new ComputerDDetailsClass(
                        rs.getString("MAPC"),
                        rs.getString("MATB"),
                        rs.getInt("IS_DELETE"),
                        rs.getTimestamp("CREATED_AT")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error selecting records by MATB: " + e.getMessage());
        }

        return detailsList;
    }
    public static boolean AddComputerDDetails(String maPC, String maTB) {
        String sql = "INSERT INTO THIET_BI_TUNG_MAY (MAPC, MATB, IS_DELETE, CREATED_AT) VALUES (?, ?, 0, SYSDATE)";
        String sql2 = "UPDATE THIET_BI SET TRANGTHAI = 'DALAP' WHERE MATB = ?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement stmt1 = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2)) {

                // Thêm thiết bị vào máy
                stmt1.setString(1, maPC);
                stmt1.setString(2, maTB);
                int rows1 = stmt1.executeUpdate();

                // Cập nhật trạng thái thiết bị
                stmt2.setString(1, maTB);
                int rows2 = stmt2.executeUpdate();

                if (rows1 > 0 && rows2 > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("❌ Error adding device to PC or updating status: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("❌ Error connecting to DB: " + e.getMessage());
        }
        return false;
    }
}