package com.raven.dbfunction;
import com.raven.classes.AreaTypeClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaType {
    // Global variables for database connection
     

    // Function to add a new area type
    public static boolean AddAType(String tenLoai, double giaTien, int soLuongMay) {
        String sql = "INSERT INTO LOAI_KHU_VUC (TENLOAI, GIATIEN, SOLUONGMAY) VALUES (?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenLoai);
            stmt.setDouble(2, giaTien);
            stmt.setInt(3, soLuongMay);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Area type added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding area type: " + e.getMessage());
        }
        return false;
    }

    // Function to update area type information
    public static boolean UpdateAType(String maLKV, String tenLoai, double giaTien, int soLuongMay) {
        String sql = "UPDATE LOAI_KHU_VUC SET TENLOAI = ?, GIATIEN = ?, SOLUONGMAY = ? WHERE MALKV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenLoai);
            stmt.setDouble(2, giaTien);
            stmt.setInt(3, soLuongMay);
            stmt.setString(4, maLKV);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Area type updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating area type: " + e.getMessage());
        }
        return false;
    }

    // Function to delete an area type by setting IS_DELETE to 1
    public static boolean DeleteAType(String maLKV) {
        String sql = "UPDATE LOAI_KHU_VUC SET IS_DELETE = 1 WHERE MALKV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLKV);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Area type deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting area type: " + e.getMessage());
        }
        return false;
    }

    // Function to select all area types and return an ArrayList of AreaTypeClass
    public static ArrayList<AreaTypeClass> SelectAType() {
        String sql = "SELECT MALKV, TENLOAI, GIATIEN, SOLUONGMAY FROM LOAI_KHU_VUC WHERE IS_DELETE = 0";
        ArrayList<AreaTypeClass> areaTypes = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AreaTypeClass areaType = new AreaTypeClass();
                areaType.setMaLKV(rs.getString("MALKV"));
                areaType.setTenLoai(rs.getString("TENLOAI"));
                areaType.setGiaTien(rs.getDouble("GIATIEN"));
                areaType.setSoLuongMay(rs.getInt("SOLUONGMAY"));

                areaTypes.add(areaType);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching area types: " + e.getMessage());
        }

        return areaTypes;
    }
    public static AreaTypeClass SelectAreaTypeById(String maLKV) {
        String sql = "SELECT MALKV, TENLOAI, GIATIEN, SOLUONGMAY FROM LOAI_KHU_VUC WHERE MALKV = ? AND IS_DELETE = 0";
        AreaTypeClass areaType = null;
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, maLKV);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                areaType = new AreaTypeClass();
                areaType.setMaLKV(rs.getString("MALKV"));
                areaType.setTenLoai(rs.getString("TENLOAI"));
                areaType.setGiaTien(rs.getDouble("GIATIEN"));
                areaType.setSoLuongMay(rs.getInt("SOLUONGMAY"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching area type by ID: " + e.getMessage());
        }
    
        return areaType;
    }
}