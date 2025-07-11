package com.raven.dbfunction;
import com.raven.classes.TotalLeaveClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TotalLeave {
     

    // Function to add a new record to the SO_NGAY_PHEP table
    public static boolean AddTLeave(String manv, int totalLeaves, int usedLeaves, int remainingLeaves, int nam) {
        String sql = "INSERT INTO SO_NGAY_PHEP (MANV, TONGSONGAYPHEP, NGAYPHEPDADUNG, SONGAYNGHICONLAI, NAM) VALUES (?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manv);
            stmt.setInt(2, totalLeaves);
            stmt.setInt(3, usedLeaves);
            stmt.setInt(4, remainingLeaves);
            stmt.setInt(5, nam);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Total leave record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding total leave record: " + e.getMessage());
        }
        return false; // Return false if the record was not added successfully
    }

    // Function to update a record in the SO_NGAY_PHEP table
    public static boolean UpdateTLeave(String manp, String manv, int totalLeaves, int usedLeaves, int remainingLeaves, int nam) {
        String sql = "UPDATE SO_NGAY_PHEP SET MANV = ?, TONGSONGAYPHEP = ?, NGAYPHEPDADUNG = ?, SONGAYNGHICONLAI = ?, NAM = ? WHERE MANP = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manv);
            stmt.setInt(2, totalLeaves);
            stmt.setInt(3, usedLeaves);
            stmt.setInt(4, remainingLeaves);
            stmt.setInt(5, nam);
            stmt.setString(6, manp);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Total leave record updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating total leave record: " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }

    // Function to delete a record from the SO_NGAY_PHEP table by ID
    public static boolean DeleteTLeave(String manp) {
        String sql = "UPDATE SO_NGAY_PHEP SET IS_DELETE = 1 WHERE MANP = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manp);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Total leave record deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting total leave record: " + e.getMessage());
        }
        return false; // Return false if the record was not deleted successfully
    }

        // Function to select records from SO_NGAY_PHEP
    public static ArrayList<TotalLeaveClass> SelectTLeave() {
        String sql = "SELECT MANP, MANV, TONGSONGAYPHEP, NGAYPHEPDADUNG, SONGAYNGHICONLAI, NAM " +
                    "FROM SO_NGAY_PHEP " +
                    "WHERE IS_DELETE = 0";
        ArrayList<TotalLeaveClass> totalLeaveList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TotalLeaveClass totalLeave = new TotalLeaveClass();
                totalLeave.setManp(rs.getString("MANP"));
                totalLeave.setManv(rs.getString("MANV"));
                totalLeave.setTotalLeaves(rs.getInt("TONGSONGAYPHEP"));
                totalLeave.setUsedLeaves(rs.getInt("NGAYPHEPDADUNG"));
                totalLeave.setRemainingLeaves(rs.getInt("SONGAYNGHICONLAI"));
                totalLeave.setNam(rs.getInt("NAM"));
                totalLeaveList.add(totalLeave);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching total leave records: " + e.getMessage());
        }

        return totalLeaveList;
    }
}
