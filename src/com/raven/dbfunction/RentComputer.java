package com.raven.dbfunction;

import com.raven.classes.RentComputerClass;
import java.sql.*;
import java.util.ArrayList;

public class RentComputer {



    // AddRComputer: Insert a new record into THUE_MAY
    public static boolean AddRComputer(String maPC, String maLS) {
        String sql = "INSERT INTO THUE_MAY (MATM, MAPC, MALS) VALUES (SYS_GUID(), ?, ?)";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maPC);
            stmt.setString(2, maLS);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ RentComputer record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding RentComputer record: " + e.getMessage());
        }
        return false; // Return false if the record was not added successfully
    }

    // UpdateRComputer: Update an existing record in THUE_MAY
    public static boolean UpdateRComputer(String maTM, String maPC, String maLS) {
        String sql = "UPDATE THUE_MAY SET MAPC = ?, MALS = ? WHERE MATM = ?";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maPC);
            stmt.setString(2, maLS);
            stmt.setString(3, maTM);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ RentComputer record updated successfully!");
                return true;
            } else {
                System.out.println("⚠️ No RentComputer record found with MATM: " + maTM);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating RentComputer record: " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }

    // SelectRComputer: Retrieve all records from THUE_MAY and return as ArrayList<RentComputerClass>
    public static ArrayList<RentComputerClass> SelectRComputer() {
        String sql = "SELECT MATM, MAPC, MALS FROM THUE_MAY";
        ArrayList<RentComputerClass> rentComputers = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RentComputerClass rentComputer = new RentComputerClass();
                rentComputer.setMaTM(rs.getString("MATM"));
                rentComputer.setMaPC(rs.getString("MAPC"));
                rentComputer.setMaLS(rs.getString("MALS"));

                rentComputers.add(rentComputer);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching RentComputer records: " + e.getMessage());
        }

        return rentComputers;
    }

    // SelectRComputerByID: Retrieve a record from THUE_MAY by MATM
    public static RentComputerClass SelectRComputerByID(String maLS) {
        String sql = "SELECT MATM, MAPC, MALS FROM THUE_MAY WHERE MALS = ?";
        RentComputerClass rentComputer = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rentComputer = new RentComputerClass();
                rentComputer.setMaTM(rs.getString("MATM"));
                rentComputer.setMaPC(rs.getString("MAPC"));
                rentComputer.setMaLS(rs.getString("MALS"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching RentComputer record by ID: " + e.getMessage());
        }

        return rentComputer;
    }
    public static RentComputerClass SelectRComputerByIDTest(Connection conn, String maLS) {
        String sql = "SELECT MATM, MAPC, MALS FROM THUE_MAY WHERE MALS = ?";
        RentComputerClass rentComputer = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rentComputer = new RentComputerClass();
                rentComputer.setMaTM(rs.getString("MATM"));
                rentComputer.setMaPC(rs.getString("MAPC"));
                rentComputer.setMaLS(rs.getString("MALS"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching RentComputer record by ID: " + e.getMessage());
        }

        return rentComputer;
    }

    // SelectRComputerByIDs: Retrieve all records by an array of MALS
    public static ArrayList<RentComputerClass> SelectRComputerByIDs(String[] maLSArray) {
        ArrayList<RentComputerClass> rentComputers = new ArrayList<>();
        if (maLSArray == null || maLSArray.length == 0) {
            return rentComputers; // Return empty list if no IDs are provided
        }

        // Build the SQL query with placeholders for the array of MALS
        StringBuilder sqlBuilder = new StringBuilder("SELECT MATM, MAPC, MALS FROM THUE_MAY WHERE MALS IN (");
        for (int i = 0; i < maLSArray.length; i++) {
            sqlBuilder.append("?");
            if (i < maLSArray.length - 1) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the MALS values in the prepared statement
            for (int i = 0; i < maLSArray.length; i++) {
                stmt.setString(i + 1, maLSArray[i]);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RentComputerClass rentComputer = new RentComputerClass();
                rentComputer.setMaTM(rs.getString("MATM"));
                rentComputer.setMaPC(rs.getString("MAPC"));
                rentComputer.setMaLS(rs.getString("MALS"));

                rentComputers.add(rentComputer);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching RentComputer records by IDs: " + e.getMessage());
        }

        return rentComputers;
    }
}