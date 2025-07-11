package com.raven.dbfunction;

import com.raven.classes.ReplaceDeviceClass;
import java.sql.*;
import java.util.ArrayList;

public class ReplaceDevice {


    // Function to add a new record to THAY_THE_THIET_BI
    public static boolean AddRDeviceByCus(String maTB) {
        String sql = """
            INSERT INTO THAY_THE_THIET_BI (MATTTB, MATB, MATBTHAYTHE, MANV, TRANGTHAI, IS_DELETE, CREATED_AT)
            VALUES (NULL, ?, NULL, NULL, 'Received', 0, SYSDATE)
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the MATB value in the prepared statement
            stmt.setString(1, maTB);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error adding record to THAY_THE_THIET_BI: " + e.getMessage());
        }

        return false;
    }
    public static ArrayList<ReplaceDeviceClass> SelectRDevice() {
        ArrayList<ReplaceDeviceClass> records = new ArrayList<>();

        // SQL query to fetch all records
        String sql = "SELECT MATTTB, MATB, MATBTHAYTHE, MANV, TRANGTHAI, CREATED_AT FROM THAY_THE_THIET_BI WHERE IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ReplaceDeviceClass record = new ReplaceDeviceClass();
                record.setMaTTTB(rs.getString("MATTTB"));
                record.setMaTB(rs.getString("MATB"));
                record.setMaTBThayThe(rs.getString("MATBTHAYTHE"));
                record.setMaNV(rs.getString("MANV"));
                record.setTrangThai(rs.getString("TRANGTHAI"));
                record.setCreatedAt(rs.getTimestamp("CREATED_AT"));

                records.add(record);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching records from THAY_THE_THIET_BI: " + e.getMessage());
        }

        return records;
    }
    public static ArrayList<ReplaceDeviceClass> SelectRDeviceNonReplaced() {
        ArrayList<ReplaceDeviceClass> records = new ArrayList<>();
    
        // SQL query to fetch all records where TRANGTHAI is "Chua xu ly"
        String sql = "SELECT MATTTB, MATB, MATBTHAYTHE, MANV, TRANGTHAI, CREATED_AT " +
                     "FROM THAY_THE_THIET_BI " +
                     "WHERE TRANGTHAI = 'Received' AND IS_DELETE = 0";
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                ReplaceDeviceClass record = new ReplaceDeviceClass();
                record.setMaTTTB(rs.getString("MATTTB"));
                record.setMaTB(rs.getString("MATB"));
                record.setMaTBThayThe(rs.getString("MATBTHAYTHE"));
                record.setMaNV(rs.getString("MANV"));
                record.setTrangThai(rs.getString("TRANGTHAI"));
                record.setCreatedAt(rs.getTimestamp("CREATED_AT"));
    
                records.add(record);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching non-replaced records from THAY_THE_THIET_BI: " + e.getMessage());
        }
    
        return records;
    }
    public static boolean ChangeDevice(String matttb, String manv, String matbtt) {
        String sql = "{CALL CHANGE_DEVICE(?, ?, ?)}"; // SQL to call the stored procedure
    
        try ( Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
    
            // Set the input parameters for the stored procedure
            stmt.setString(1, matttb); // MATTTB
            stmt.setString(2, manv);   // MANV
            stmt.setString(3, matbtt); // MATBTT
    
            // Execute the stored procedure
            stmt.execute();
            System.out.println("✅ Device replacement successful for MATTTB: " + matttb);
            return true; // Indicate success
    
        } catch (SQLException e) {
            System.err.println("❌ Error executing CHANGE_DEVICE procedure: " + e.getMessage());
        }
        return false; // Indicate failure
    }
    public static boolean ChangeDeviceTest(Connection conn,String matttb, String manv, String matbtt) {
        String sql = "{CALL CHANGE_DEVICE(?, ?, ?)}"; // SQL to call the stored procedure
    
        try (CallableStatement stmt = conn.prepareCall(sql)) {
    
            // Set the input parameters for the stored procedure
            stmt.setString(1, matttb); // MATTTB
            stmt.setString(2, manv);   // MANV
            stmt.setString(3, matbtt); // MATBTT
    
            // Execute the stored procedure
            stmt.execute();
            System.out.println("✅ Device replacement successful for MATTTB: " + matttb);
            return true; // Indicate success
    
        } catch (SQLException e) {
            System.err.println("❌ Error executing CHANGE_DEVICE procedure: " + e.getMessage());
        }
        return false; // Indicate failure
    }
}