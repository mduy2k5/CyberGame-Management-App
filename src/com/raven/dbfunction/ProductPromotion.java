package com.raven.dbfunction;
import com.raven.classes.ProductPromotionClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPromotion {
    // Global variables for database connection
     

    // Helper function to check if MAKMSP exists
    public static boolean isPromotionExist(String MAKMSP) {
        String sql = "SELECT COUNT(*) FROM KHUYEN_MAI_SAN_PHAM WHERE MAKMSP = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, MAKMSP);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if MAKMSP exists
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking promotion existence: " + e.getMessage());
        }

        return false; // Return false if MAKMSP does not exist
    }

    // Function to add a new promotion
    public static boolean AddPPromotion(String maSP, String maCTR) {
        String sql = "INSERT INTO KHUYEN_MAI_SAN_PHAM (MASP, MACTR) VALUES (?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSP);
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
    public static boolean UpdatePPromotion(String MAKMSP, String maSP) {
        if (!isPromotionExist(MAKMSP)) {
            System.err.println("❌ Error: Promotion with MAKMSP " + MAKMSP + " does not exist.");
            return false;
        }

        String sql = "UPDATE KHUYEN_MAI_SAN_PHAM SET MASP = ? WHERE MAKMSP = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSP);
            stmt.setString(2, MAKMSP);

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
    public static boolean DeletePPromotion(String MAKMSP) {
        String sql = "UPDATE KHUYEN_MAI_SAN_PHAM SET IS_DELETE = 1 WHERE MAKMSP = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, MAKMSP);

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
    public static ArrayList<ProductPromotionClass> SelectPPromotion() {
        String sql = "SELECT MAKMSP, MASP, MACTR, CREATED_AT FROM KHUYEN_MAI_SAN_PHAM WHERE IS_DELETE = 0";
        ArrayList<ProductPromotionClass> promotions = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductPromotionClass promotion = new ProductPromotionClass();
                promotion.setMAKMSP(rs.getString("MAKMSP"));
                promotion.setmaSP(rs.getString("MASP"));
                promotion.setMaCTR(rs.getString("MACTR"));
                promotion.setCreateat(rs.getDate("CREATED_AT"));
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching promotions: " + e.getMessage());
        }

        return promotions;
    }

    // Function to select a promotion by MAKMSP and return an AreaPromotionClass object
    public static ProductPromotionClass SelectPPromotionById(String MAKMSP) {
        String sql = "SELECT MAKMSP, MASP, MACTR FROM KHUYEN_MAI_SAN_PHAM WHERE MAKMSP = ? AND IS_DELETE = 0";
        ProductPromotionClass promotion = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, MAKMSP);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                promotion = new ProductPromotionClass();
                promotion.setMAKMSP(rs.getString("MAKMSP"));
                promotion.setmaSP(rs.getString("MASP"));
                promotion.setMaCTR(rs.getString("MACTR"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching promotion by ID: " + e.getMessage());
        }

        return promotion;
    }
    public static ArrayList<ProductPromotionClass> SelectPPromotionByPId(String maCTR) {
        String sql = "SELECT MAKMSP, MASP, MACTR, CREATED_AT FROM KHUYEN_MAI_SAN_PHAM WHERE MACTR = ? AND IS_DELETE = 0";
        ArrayList<ProductPromotionClass> promotions = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maCTR);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductPromotionClass promotion = new ProductPromotionClass();
                promotion.setMAKMSP(rs.getString("MAKMSP"));
                promotion.setmaSP(rs.getString("MASP"));
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
