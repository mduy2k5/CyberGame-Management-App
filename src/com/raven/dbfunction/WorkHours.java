package com.raven.dbfunction;
import com.raven.classes.WorkHoursClass;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WorkHours {
     

    // Function to add a new record to the CA_LAM table
    public static boolean AddWHours(String manv, java.sql.Timestamp tgbd, java.sql.Timestamp tgkt) {
        String sql = "INSERT INTO CA_LAM (MACA, MANV, TGBD, TGKT, SOGIOLAM, THOIGIANTANGCA, IS_DELETE, CREATED_AT, NGAYCC, TRANGTHAI) " +
                    "VALUES (null, ?, ?, ?, ?, 0, 0, SYSDATE, null, 'Working')";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Calculate the difference in hours between TGKT and TGBD
            long milliseconds = tgkt.getTime() - tgbd.getTime();
            int workHours = (int) (milliseconds / (1000 * 60 * 60)); // Convert milliseconds to hours

            // Set the parameters
            stmt.setString(1, manv);
            stmt.setTimestamp(2, tgbd);
            stmt.setTimestamp(3, tgkt);
            stmt.setInt(4, workHours);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Work hours record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding work hours record: " + e.getMessage());
        }
        return false; // Return false if the record was not added successfully
    }

    // Function to update a record in the CA_LAM table
    public static boolean UpdateWHours(String maca, String manv, Date tgbd, Date tgkt, Date ngaycc, int workHours, int overtimeHours) {
        String sql = "UPDATE CA_LAM SET MANV = ?, TGBD = ?, TGKT = ?, NGAYCC = ?, SOGIOLAM = ?, THOIGIANTANGCA = ? WHERE MACA = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manv);
            stmt.setDate(2, new java.sql.Date(tgbd.getTime()));
            stmt.setDate(3, new java.sql.Date(tgkt.getTime()));
            stmt.setDate(4, new java.sql.Date(ngaycc.getTime()));
            stmt.setInt(5, workHours);
            stmt.setInt(6, overtimeHours);
            stmt.setString(7, maca);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Work hours record updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating work hours record: " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }

    // Function to delete a record from the CA_LAM table by ID
    public static boolean DeleteWHours(String maca) {
        String sql = "UPDATE CA_LAM SET IS_DELETE = 1 WHERE MACA = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maca);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Work hours record deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting work hours record: " + e.getMessage());
        }
        return false; // Return false if the record was not deleted successfully
    }

    // Function to select records from CA_LAM
    public static ArrayList<WorkHoursClass> SelectWHours() {
        String sql = "SELECT MACA, MANV, TGBD, TGKT, NGAYCC, SOGIOLAM, THOIGIANTANGCA, TRANGTHAI " +
                    "FROM CA_LAM " +
                    "WHERE IS_DELETE = 0";
        ArrayList<WorkHoursClass> workHoursList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WorkHoursClass workHours = new WorkHoursClass();
                workHours.setMaca(rs.getString("MACA"));
                workHours.setManv(rs.getString("MANV"));
                workHours.setTgbd(rs.getTimestamp("TGBD"));
                workHours.setTgkt(rs.getTimestamp("TGKT"));
                workHours.setNgaycc(rs.getTimestamp("NGAYCC"));
                workHours.setWorkHours(rs.getInt("SOGIOLAM"));
                workHours.setOvertimeHours(rs.getInt("THOIGIANTANGCA"));
                workHours.setTrangthai(rs.getString("TRANGTHAI"));
                workHoursList.add(workHours);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching work hours records: " + e.getMessage());
        }

        return workHoursList;
    }
    public static boolean FinishWHours(String manv) {
        String sql = "{CALL FINISH_WORK_HOUR(?)}";

        try ( Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Set the input parameter
            stmt.setString(1, manv);

            // Execute the procedure
            stmt.execute();
            System.out.println("✅ Work hours finished successfully for MANV: " + manv);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error finishing work hours for MANV: " + manv + ". " + e.getMessage());
        }
        return false; // Return false if the procedure was not executed successfully
    }
    public static WorkHoursClass SelectWHoursByMANV(String manv) {
        String sql = "SELECT MACA, MANV, TGBD, TGKT, NGAYCC, SOGIOLAM, THOIGIANTANGCA, TRANGTHAI " +
                     "FROM CA_LAM " +
                     "WHERE MANV = ? AND IS_DELETE = 0 AND TRANGTHAI = 'Working'";
        WorkHoursClass workHours = null;
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, manv);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    workHours = new WorkHoursClass();
                    workHours.setMaca(rs.getString("MACA"));
                    workHours.setManv(rs.getString("MANV"));
                    workHours.setTgbd(rs.getDate("TGBD"));
                    workHours.setTgkt(rs.getDate("TGKT"));
                    workHours.setNgaycc(rs.getDate("NGAYCC"));
                    workHours.setWorkHours(rs.getInt("SOGIOLAM"));
                    workHours.setOvertimeHours(rs.getInt("THOIGIANTANGCA"));
                    workHours.setTrangthai(rs.getString("TRANGTHAI"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching work hours for MANV: " + manv + ". " + e.getMessage());
        }
    
        return workHours;
    }
    public static ArrayList<WorkHoursClass> SelectAllWHoursByMANV(String manv) {
        String sql = "SELECT MACA, MANV, TGBD, TGKT, NGAYCC, SOGIOLAM, THOIGIANTANGCA, TRANGTHAI " +
                    "FROM CA_LAM " +
                    "WHERE MANV = ? AND IS_DELETE = 0";
        ArrayList<WorkHoursClass> workHoursList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manv);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    WorkHoursClass workHours = new WorkHoursClass();
                    workHours.setMaca(rs.getString("MACA"));
                    workHours.setManv(rs.getString("MANV"));
                    workHours.setTgbd(rs.getDate("TGBD"));
                    workHours.setTgkt(rs.getDate("TGKT"));
                    workHours.setNgaycc(rs.getDate("NGAYCC"));
                    workHours.setWorkHours(rs.getInt("SOGIOLAM"));
                    workHours.setOvertimeHours(rs.getInt("THOIGIANTANGCA"));
                    workHours.setTrangthai(rs.getString("TRANGTHAI"));

                    workHoursList.add(workHours);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching work hours for MANV: " + manv + ". " + e.getMessage());
        }

        return workHoursList;
    }
}