package com.raven.dbfunction;
import com.raven.classes.AreaClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Area {
    // Global variables for database connection
    

    // Helper function to check if MALKV exists
    public static boolean isAreaTypeExist(String maLKV) {
        String sql = "SELECT COUNT(*) FROM LOAI_KHU_VUC WHERE MALKV = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLKV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if MALKV exists
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking area type existence: " + e.getMessage());
        }

        return false; // Return false if MALKV does not exist
    }

    // Function to add a new area
    public static boolean AddArea(String maLKV, String tenKV, String trangThai, int soTang, int soLuongMayKV) {
        if (!isAreaTypeExist(maLKV)) {
            System.err.println("❌ Error: Area type with MALKV " + maLKV + " does not exist.");
            return false;
        }

        String sql = "INSERT INTO KHU_VUC (MALKV, TENKV, TRANGTHAI, SOTANG, SOLUONGMAYKV) VALUES (?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLKV);
            stmt.setString(2, tenKV);
            stmt.setString(3, trangThai);
            stmt.setInt(4, soTang);
            stmt.setInt(5, soLuongMayKV);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Area added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding area: " + e.getMessage());
        }
        return false; // Return false if the area was not added successfully
    }

    // Function to update area information
    public static boolean  UpdateArea(String maKV, String maLKV, String tenKV, String trangThai, int soTang, int soLuongMayKV) {
        if (!isAreaTypeExist(maLKV)) {
            System.err.println("❌ Error: Area type with MALKV " + maLKV + " does not exist.");
            return false;
        }

        String sql = "UPDATE KHU_VUC SET MALKV = ?, TENKV = ?, TRANGTHAI = ?, SOTANG = ?, SOLUONGMAYKV = ? WHERE MAKV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLKV);
            stmt.setString(2, tenKV);
            stmt.setString(3, trangThai);
            stmt.setInt(4, soTang);
            stmt.setInt(5, soLuongMayKV);
            stmt.setString(6, maKV);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Area updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating area: " + e.getMessage());
        }
        return false; // Return false if the area was not updated successfully
    }
    // Function to update area status information
    public static boolean  UpdateAreaStatus(String maKV, String trangthai) {

        String sql = "UPDATE KHU_VUC SET TRANGTHAI = ? WHERE MAKV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trangthai);
            stmt.setString(2, maKV);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Area updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating area: " + e.getMessage());
        }
        return false; // Return false if the area was not updated successfully
    }
    // Function to delete an area by setting IS_DELETE to 1
    public static boolean DeleteArea(String maKV) {
        String sql = "UPDATE KHU_VUC SET IS_DELETE = 1 WHERE MAKV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKV);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Area deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting area: " + e.getMessage());
        }
        return false;
    }

    // Function to select all areas and return an ArrayList of AreaClass
    public static ArrayList<AreaClass> SelectArea() {
        String sql = "SELECT MAKV, MALKV, TENKV, TRANGTHAI, SOTANG, SOLUONGMAYKV, IS_DELETE FROM KHU_VUC WHERE IS_DELETE = 0";
        ArrayList<AreaClass> areas = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AreaClass area = new AreaClass();
                area.setMaKV(rs.getString("MAKV"));
                area.setMaLKV(rs.getString("MALKV"));
                area.setTenKV(rs.getString("TENKV"));
                area.setTrangThai(rs.getString("TRANGTHAI"));
                area.setSoTang(rs.getInt("SOTANG"));
                area.setSoLuongMayKV(rs.getInt("SOLUONGMAYKV"));
                area.setDelete(rs.getInt("IS_DELETE") == 1);

                areas.add(area);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching areas: " + e.getMessage());
        }

        return areas;
    }
    public static AreaClass SelectAreaById(String maKV) {
        String sql = "SELECT MAKV, MALKV, TENKV, TRANGTHAI, SOTANG, SOLUONGMAYKV FROM KHU_VUC WHERE MAKV = ? AND IS_DELETE = 0";
        AreaClass area = null;
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, maKV);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                area = new AreaClass();
                area.setMaKV(rs.getString("MAKV"));
                area.setMaLKV(rs.getString("MALKV"));
                area.setTenKV(rs.getString("TENKV"));
                area.setTrangThai(rs.getString("TRANGTHAI"));
                area.setSoTang(rs.getInt("SOTANG"));
                area.setSoLuongMayKV(rs.getInt("SOLUONGMAYKV"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching area by ID: " + e.getMessage());
        }
    
        return area;
    }
    public static AreaClass SelectAreaByIdTest(Connection conn, String maKV) {
        String sql = "SELECT MAKV, MALKV, TENKV, TRANGTHAI, SOTANG, SOLUONGMAYKV FROM KHU_VUC WHERE MAKV = ? AND IS_DELETE = 0";
        AreaClass area = null;
    
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, maKV);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                area = new AreaClass();
                area.setMaKV(rs.getString("MAKV"));
                area.setMaLKV(rs.getString("MALKV"));
                area.setTenKV(rs.getString("TENKV"));
                area.setTrangThai(rs.getString("TRANGTHAI"));
                area.setSoTang(rs.getInt("SOTANG"));
                area.setSoLuongMayKV(rs.getInt("SOLUONGMAYKV"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching area by ID: " + e.getMessage());
        }
    
        return area;
    }
}