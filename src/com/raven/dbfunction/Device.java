package com.raven.dbfunction;
import com.raven.classes.DeviceClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Device {
     


    // Function to update a record in the THIET_BI table

    public static boolean UpdateDevice(String matb, String tentb, String trangthai,String loaitb) {
        String sql = "UPDATE THIET_BI SET TENTB = ?, TRANGTHAI = ?, LOAITB = ? WHERE MATB = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tentb);
            stmt.setString(2, trangthai);
            stmt.setString(3, loaitb);
            stmt.setString(4, matb);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Device updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating device: " + e.getMessage());
        }
        return false; // Return false if the device was not updated successfully
    }
    public static boolean UpdateDeviceStatus(String matb,String trangthai) {
        String sql = "UPDATE THIET_BI SET TRANGTHAI = ? WHERE MATB = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trangthai);
            stmt.setString(2, matb);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Device updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating device: " + e.getMessage());
        }
        return false; // Return false if the device was not updated successfully
    }

    // Function to delete a record from the THIET_BI table by ID
    public static boolean DeleteDevice(String matb) {
        String sql = "UPDATE THIET_BI SET IS_DELETE = 1 WHERE MATB = ?";
        String sql2 = "UPDATE CHI_TIET_NHAP_TB SET IS_DELETE = 1 WHERE MATB = ?";

        try ( Connection conn = DBConnection.getConnection()) {
            // Start a transaction
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2)) {

                // Execute the first query
                stmt1.setString(1, matb);
                int rowsUpdated1 = stmt1.executeUpdate();

                // Execute the second query
                stmt2.setString(1, matb);
                int rowsUpdated2 = stmt2.executeUpdate();

                // Commit the transaction if both queries succeed
                conn.commit();

                if (rowsUpdated1 > 0 || rowsUpdated2 > 0) {
                    System.out.println("✅ Device and related details deleted successfully!");
                    return true;
                }
            } catch (SQLException e) {
                // Rollback the transaction in case of an error
                conn.rollback();
                System.err.println("❌ Error deleting device: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("❌ Error establishing database connection: " + e.getMessage());
        }
        return false; // Return false if the device was not deleted successfully
    }

    // Function to select records from the THIET_BI table
    public static ArrayList<DeviceClass> SelectDevice() {
        String sql = "SELECT MATB, TENTB, TRANGTHAI, LOAITB, CREATED_AT FROM THIET_BI WHERE IS_DELETE = 0";
        ArrayList<DeviceClass> deviceList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DeviceClass device = new DeviceClass();
                device.setMatb(rs.getString("MATB"));
                device.setTentb(rs.getString("TENTB"));
                device.setTrangthai(rs.getString("TRANGTHAI"));
                device.setLoaitb(rs.getString("LOAITB"));
                device.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                deviceList.add(device);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching devices: " + e.getMessage());
        }

        return deviceList;
    }
    public static DeviceClass SelectDeviceByID(String matb) {
        String sql = "SELECT MATB, TENTB, TRANGTHAI, LOAITB, CREATED_AT FROM THIET_BI WHERE MATB = ? AND IS_DELETE = 0";
        DeviceClass device = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matb);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    device = new DeviceClass();
                    device.setMatb(rs.getString("MATB"));
                    device.setTentb(rs.getString("TENTB"));
                    device.setTrangthai(rs.getString("TRANGTHAI"));
                    device.setLoaitb(rs.getString("LOAITB"));
                    device.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching device by ID: " + e.getMessage());
        }

        return device;
    }
    public static ArrayList<DeviceClass> SelectDeviceByName(String tentb) {
        String sql = "SELECT MATB, TENTB, TRANGTHAI, LOAITB, CREATED_AT FROM THIET_BI WHERE LOWER(TENTB) LIKE ? AND IS_DELETE = 0";
        ArrayList<DeviceClass> deviceList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tentb + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DeviceClass device = new DeviceClass();
                    device.setMatb(rs.getString("MATB"));
                    device.setTentb(rs.getString("TENTB"));
                    device.setTrangthai(rs.getString("TRANGTHAI"));
                    device.setLoaitb(rs.getString("LOAITB"));
                    device.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                    deviceList.add(device);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching devices by name: " + e.getMessage());
        }

        return deviceList;
    }
    public static ArrayList<DeviceClass> SelectDeviceByType(String loaitb) {
        String sql = "SELECT MATB, TENTB, TRANGTHAI, LOAITB, CREATED_AT FROM THIET_BI WHERE LOWER(LOAITB) LIKE ? AND IS_DELETE = 0";
        ArrayList<DeviceClass> deviceList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + loaitb + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DeviceClass device = new DeviceClass();
                    device.setMatb(rs.getString("MATB"));
                    device.setTentb(rs.getString("TENTB"));
                    device.setTrangthai(rs.getString("TRANGTHAI"));
                    device.setLoaitb(rs.getString("LOAITB"));
                    device.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                    deviceList.add(device);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching devices by type: " + e.getMessage());
        }

        return deviceList;
    }
}
