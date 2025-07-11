package com.raven.dbfunction;
import com.raven.classes.EventClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Event {
     

    // Function to add a new record to the SU_KIEN table
    public static boolean AddEvent(String tensk, java.sql.Date tgbd, java.sql.Date tgkt, String makv, String noidung, String manv) {
        String sql = "INSERT INTO SU_KIEN (TENSK, TGBD, TGKT, MAKV, NOIDUNG, MANV) VALUES (?, ?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tensk);
            stmt.setDate(2, tgbd);
            stmt.setDate(3, tgkt);
            stmt.setString(4, makv);
            stmt.setString(5, noidung);
            stmt.setString(6, manv);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Event added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding event: " + e.getMessage());
        }
        return false;
    }

    // Function to update a record in the SU_KIEN table
    public static boolean UpdateEvent(String mask, String tensk, java.sql.Date tgbd, java.sql.Date tgkt, String makv, String noidung, String manv) {
        String sql = "UPDATE SU_KIEN SET TENSK = ?, TGBD = ?, TGKT = ?, MAKV = ?, NOIDUNG = ?, MANV = ? WHERE MASK = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tensk);
            stmt.setDate(2, tgbd);
            stmt.setDate(3, tgkt);
            stmt.setString(4, makv);
            stmt.setString(5, noidung);
            stmt.setString(6, manv);
            stmt.setString(7, mask);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Event updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating event: " + e.getMessage());
        }
        return false;
    }

    // Function to delete a record from the SU_KIEN table by ID
    public static boolean DeleteEvent(String mask) {
        String sql = "UPDATE SU_KIEN SET IS_DELETE = 1 WHERE MASK = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mask);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Event deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting event: " + e.getMessage());
        }
        return false;
    }

    // Function to select records from the SU_KIEN table
    public static ArrayList<EventClass> SelectEvent() {
        String sql = "SELECT MASK, TENSK, TGBD, TGKT, MAKV, NOIDUNG, MANV, CREATED_AT FROM SU_KIEN WHERE IS_DELETE = 0";
        ArrayList<EventClass> eventList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EventClass event = new EventClass();
                event.setMask(rs.getString("MASK"));
                event.setTensk(rs.getString("TENSK"));
                event.setTgbd(rs.getDate("TGBD"));
                event.setTgkt(rs.getDate("TGKT"));
                event.setMakv(rs.getString("MAKV"));
                event.setNoidung(rs.getString("NOIDUNG"));
                event.setManv(rs.getString("MANV"));
                event.setcreateat(rs.getDate("CREATED_AT"));
                eventList.add(event);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching events: " + e.getMessage());
        }

        return eventList;
    }
}