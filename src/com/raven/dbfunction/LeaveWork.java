package com.raven.dbfunction;
import com.raven.classes.LeaveWorkClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeaveWork {
     

    // Function to add a new record to the NGHI_PHEP table
    public static boolean AddLWork(String manvnp, String manvthaythe, String maca, String noidung, java.sql.Date thoigiantb, String leavetype) {
        String sql = "INSERT INTO NGHI_PHEP (MANVNP, MANVTHAYTHE, MACA, NOIDUNG, THOIGIANTB, LOAINGHIPHEP) VALUES (?, ?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manvnp);
            stmt.setString(2, manvthaythe);
            stmt.setString(3, maca);
            stmt.setString(4, noidung);
            stmt.setDate(5, thoigiantb);
            stmt.setString(6, leavetype);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Leave record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding leave record: " + e.getMessage());
        }
        return false; // Return false if the record was not added successfully
    }

    // Function to update a record in the NGHI_PHEP table
    public static boolean UpdateLWork(String manp, String manvnp, String manvthaythe, String maca, String noidung, java.sql.Date thoigiantb, String leavetype) {
        String sql = "UPDATE NGHI_PHEP SET MANVNP = ?, MANVTHAYTHE = ?, MACA = ?, NOIDUNG = ?, THOIGIANTB = ?, LOAINGHIPHEP = ? WHERE MANP = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manvnp);
            stmt.setString(2, manvthaythe);
            stmt.setString(3, maca);
            stmt.setString(4, noidung);
            stmt.setDate(5, thoigiantb);
            stmt.setString(6, leavetype);
            stmt.setString(7, manp);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Leave record updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating leave record: " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }

    // Function to delete a record from the NGHI_PHEP table by ID
    public static boolean DeleteLWork(String manp) {
        String sql = "UPDATE NGHI_PHEP SET IS_DELETE = 1 WHERE MANP = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manp);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Leave record deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting leave record: " + e.getMessage());
        }
        return false; // Return false if the record was not deleted successfully
    }

    // Function to select records from NHAN_VIEN joined with NGHI_PHEP
    public static ArrayList<LeaveWorkClass> SelectLWork() {
        String sql = "SELECT NV.MANV, NV.USER_ID, NV.NGVL, NV.MALNV, NV.MASOTHUECN, NV.SOBHYT, " +
                    "NP.MANP, NP.MANVNP, NP.MANVTHAYTHE, NP.MACA, NP.NOIDUNG, NP.THOIGIANTB, NP.LOAINGHIPHEP " +
                    "FROM NHAN_VIEN NV " +
                    "JOIN NGHI_PHEP NP ON NV.MANV = NP.MANVNP";
        ArrayList<LeaveWorkClass> leaveWorkList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LeaveWorkClass leaveWork = new LeaveWorkClass();
                leaveWork.setManv(rs.getString("MANV"));
                leaveWork.setUserId(rs.getString("USER_ID"));
                leaveWork.setNgvl(rs.getDate("NGVL"));
                leaveWork.setMalnv(rs.getString("MALNV"));
                leaveWork.setMasothuecn(rs.getString("MASOTHUECN"));
                leaveWork.setSobhyt(rs.getString("SOBHYT"));
                leaveWork.setManp(rs.getString("MANP"));
                leaveWork.setManvnp(rs.getString("MANVNP"));
                leaveWork.setManvthaythe(rs.getString("MANVTHAYTHE"));
                leaveWork.setMaca(rs.getString("MACA"));
                leaveWork.setNoidung(rs.getString("NOIDUNG"));
                leaveWork.setThoigiantb(rs.getDate("THOIGIANTB"));
                leaveWork.setLeavetype(rs.getString("LOAINGHIPHEP"));
                leaveWorkList.add(leaveWork);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching leave work records: " + e.getMessage());
        }

        return leaveWorkList;
    }
}