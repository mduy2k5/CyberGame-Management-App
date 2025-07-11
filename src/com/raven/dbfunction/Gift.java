package com.raven.dbfunction;
import com.raven.classes.GiftClass;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gift {
    // Global variables for database connection
     

    // Function to add a new gift
    public static boolean AddGift(String noiDung, int soDiemTieuHao) {
        String sql = "INSERT INTO QUA_TANG (NOIDUNG, SODIEMTIEUHAO) VALUES (?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noiDung);
            stmt.setInt(2, soDiemTieuHao);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Gift added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding gift: " + e.getMessage());
        }
        return false; // Return false if the gift was not added successfully
    }

    // Function to update gift information
    public static boolean UpdateGift(String maQT, String noiDung, int soDiemTieuHao) {
        String sql = "UPDATE QUA_TANG SET NOIDUNG = ?, SODIEMTIEUHAO = ? WHERE MAQT = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noiDung);
            stmt.setInt(2, soDiemTieuHao);
            stmt.setString(3, maQT);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Gift updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating gift: " + e.getMessage());
        }
        return false; // Return false if the gift was not updated successfully
    }

    // Function to delete a gift by setting IS_DELETE to 1
    public static boolean DeleteGift(String maQT) {
        String sql = "UPDATE QUA_TANG SET IS_DELETE = 1 WHERE MAQT = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maQT);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Gift deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting gift: " + e.getMessage());
        }
        return false; // Return false if the gift was not deleted successfully
    }

    // Function to select all gifts and return an ArrayList of GiftClass
    public static ArrayList<GiftClass> SelectGift() {
        String sql = "SELECT MAQT, NOIDUNG, SODIEMTIEUHAO FROM QUA_TANG WHERE IS_DELETE = 0";
        ArrayList<GiftClass> gifts = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GiftClass gift = new GiftClass();
                gift.setMaQT(rs.getString("MAQT"));
                gift.setNoiDung(rs.getString("NOIDUNG"));
                gift.setSoDiemTieuHao(rs.getInt("SODIEMTIEUHAO"));

                gifts.add(gift);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching gifts: " + e.getMessage());
        }

        return gifts;
    }
    // Function to select a gift by MAQT and return a GiftClass object
    public static GiftClass SelectGiftById(String maQT) {
        String sql = "SELECT MAQT, NOIDUNG, SODIEMTIEUHAO FROM QUA_TANG WHERE MAQT = ? AND IS_DELETE = 0";
        GiftClass gift = null;

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maQT); // Set the MAQT parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                gift = new GiftClass();
                gift.setMaQT(rs.getString("MAQT"));
                gift.setNoiDung(rs.getString("NOIDUNG"));
                gift.setSoDiemTieuHao(rs.getInt("SODIEMTIEUHAO"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching gift by ID: " + e.getMessage());
        }

        return gift;
    }
    public static boolean callExchangeGift(String maDQ) {
        String sql = "{CALL EXCHANGE_GIFT(?)}";

        try ( Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Set the input parameters for the procedure
            stmt.setString(1, maDQ);

            // Execute the procedure
            stmt.execute();
            System.out.println("✅ Gift exchanged successfully!");
            return true;

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            if (e.getErrorCode() == 20001) {
                System.err.println("❌ Không đủ điểm để đổi quà.");
            } else {
                System.err.println("❌ Error calling EXCHANGE_GIFT procedure: " + e.getMessage());
            }
        }
        return false;
    }
}