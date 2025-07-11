package com.raven.dbfunction;
import com.raven.classes.AreaPromotionClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaPromotion {
    // Global variables for database connection
     

    // Helper function to check if MAKMKV exists
    public static boolean isPromotionExist(String maKMKV) {
        String sql = "SELECT COUNT(*) FROM KHUYEN_MAI_KHU_VUC WHERE MAKMKV = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKMKV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if MAKMKV exists
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking promotion existence: " + e.getMessage());
        }

        return false; // Return false if MAKMKV does not exist
    }

    // Function to add a new promotion
    public static boolean AddAPromotion(String maKV, String maCTR) {
        String sql = "INSERT INTO KHUYEN_MAI_KHU_VUC (MAKV, MACTR) VALUES (?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKV);
            stmt.setString(2, maCTR);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Promotion added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding promotion: " + e.getMessage());
        }
        return false; // Return false if the promotion was not added successfully
    }

    // Function to update promotion information
    public static boolean UpdateAPromotion(String maKMKV, String maKV) {
        if (!isPromotionExist(maKMKV)) {
            System.err.println("❌ Error: Promotion with MAKMKV " + maKMKV + " does not exist.");
            return false;
        }

        String sql = "UPDATE KHUYEN_MAI_KHU_VUC SET MAKV = ? WHERE MAKMKV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKV);
            stmt.setString(2, maKMKV);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Promotion updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating promotion: " + e.getMessage());
        }
        return false; // Return false if the promotion was not updated successfully
    }

    // Function to delete a promotion by setting IS_DELETE to 1
    public static boolean DeleteAPromotion(String maKMKV) {
        String sql = "UPDATE KHUYEN_MAI_KHU_VUC SET IS_DELETE = 1 WHERE MAKMKV = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKMKV);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Promotion deleted successfully!");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error deleting promotion: " + e.getMessage());
        }
        return false;
    }

    // Function to select all promotions and return an ArrayList of AreaPromotionClass
    public static ArrayList<AreaPromotionClass> SelectAPromotion() {
        String sql = "SELECT MAKMKV, MAKV, MACTR FROM KHUYEN_MAI_KHU_VUC WHERE IS_DELETE = 0";
        ArrayList<AreaPromotionClass> promotions = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AreaPromotionClass promotion = new AreaPromotionClass();
                promotion.setMaKMKV(rs.getString("MAKMKV"));
                promotion.setMaKV(rs.getString("MAKV"));
                promotion.setMaCTR(rs.getString("MACTR"));

                promotions.add(promotion);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching promotions: " + e.getMessage());
        }

        return promotions;
    }

    // Function to select a promotion by MAKMKV and return an AreaPromotionClass object
    public static AreaPromotionClass SelectAPromotionById(String maKMKV) {
        String sql = "SELECT MAKMKV, MAKV, MACTR, CREATED_AT FROM KHUYEN_MAI_KHU_VUC WHERE MAKMKV = ? AND IS_DELETE = 0";
        AreaPromotionClass promotion = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKMKV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                promotion = new AreaPromotionClass();
                promotion.setMaKMKV(rs.getString("MAKMKV"));
                promotion.setMaKV(rs.getString("MAKV"));
                promotion.setMaCTR(rs.getString("MACTR"));
                promotion.setCreateat(rs.getDate("CREATED_AT"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching promotion by ID: " + e.getMessage());
        }

        return promotion;
    }
    public static ArrayList<AreaPromotionClass> SelectAPromotionByPId(String maCTR) {
        String sql = "SELECT MAKMKV, MAKV, MACTR,CREATED_AT FROM KHUYEN_MAI_KHU_VUC WHERE MACTR = ? AND IS_DELETE = 0";
        ArrayList<AreaPromotionClass> promotions = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maCTR);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AreaPromotionClass promotion = new AreaPromotionClass();
                promotion.setMaKMKV(rs.getString("MAKMKV"));
                promotion.setMaKV(rs.getString("MAKV"));
                promotion.setMaCTR(rs.getString("MACTR"));
                promotion.setCreateat(rs.getDate("CREATED_AT"));
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching promotion by ID: " + e.getMessage());
        }

        return promotions;
    }
}
