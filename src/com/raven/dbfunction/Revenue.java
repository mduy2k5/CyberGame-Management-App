package com.raven.dbfunction;


import com.raven.classes.RevenueClass;
import java.sql.*;
import java.util.ArrayList;

public class Revenue {
    // Global variables for database connection
     

    // AddRevenue: Insert a new revenue record (MADT is auto-generated)
    public static boolean AddRevenue(String noiDung) {
        String sql = "INSERT INTO DOANH_THU (MADT, NOIDUNG) VALUES (SYS_GUID(), ?)";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noiDung);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Revenue added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding revenue: " + e.getMessage());
        }
        return false; // Return false if the revenue was not added successfully
    }

    // UpdateRevenue: Update an existing revenue record
    public static boolean UpdateRevenue(String maDT, String noiDung) {
        String sql = "UPDATE DOANH_THU SET NOIDUNG = ? WHERE MADT = ? AND IS_DELETE = 0";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, noiDung);
            stmt.setString(2, maDT);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Revenue updated successfully!");
                return true;
            } else {
                System.out.println("⚠️ No revenue record found with MADT: " + maDT);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating revenue: " + e.getMessage());
        }
        return false; // Return false if the revenue was not updated successfully
    }

    // DeleteRevenue: Mark a revenue record as deleted
    public static boolean DeleteRevenue(String maDT) {
        String sql = "UPDATE DOANH_THU SET IS_DELETE = 1 WHERE MADT = ?";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maDT);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Revenue deleted successfully!");
                return true;
            } else {
                System.out.println("⚠️ No revenue record found with MADT: " + maDT);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting revenue: " + e.getMessage());
        }
        return false; // Return false if the revenue was not deleted successfully
    }

    // SelectRevenue: Retrieve all revenue records
    public static ArrayList<RevenueClass> SelectRevenue() {
        String sql = "SELECT MADT, TONGDOANHTHU, NOIDUNG, CREATED_AT, TONGDOANHTHUDV, TONGSODV, TONGTHOIGIANCHOI FROM DOANH_THU WHERE IS_DELETE = 0";
        ArrayList<RevenueClass> revenues = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RevenueClass revenue = new RevenueClass();
                revenue.setMaDT(rs.getString("MADT"));
                revenue.setTongDoanhThu(rs.getDouble("TONGDOANHTHU"));
                revenue.setNoiDung(rs.getString("NOIDUNG"));
                revenue.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                revenue.setTongsodv(rs.getDouble("TONGSODV"));
                revenue.setTongdoanhthudv(rs.getDouble("TONGDOANHTHUDV"));
                revenue.setTongthoigian(rs.getDouble("TONGTHOIGIANCHOI"));

                revenues.add(revenue);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching revenues: " + e.getMessage());
        }

        return revenues;
    }

    // SelectRevenueByID: Retrieve a revenue record by MADT
    public static RevenueClass SelectRevenueByID(String maDT) {
        String sql = "SELECT MADT, TONGDOANHTHU, NOIDUNG, CREATED_AT, TONGDOANHTHUDV, TONGSODV, TONGTHOIGIANCHOI FROM DOANH_THU WHERE MADT = ? AND IS_DELETE = 0";
        RevenueClass revenue = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maDT);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                revenue = new RevenueClass();
                revenue.setMaDT(rs.getString("MADT"));
                revenue.setTongDoanhThu(rs.getDouble("TONGDOANHTHU"));
                revenue.setNoiDung(rs.getString("NOIDUNG"));
                revenue.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                revenue.setTongsodv(rs.getDouble("TONGSODDV"));
                revenue.setTongdoanhthudv(rs.getDouble("TONGDOANHTHUDV"));
                revenue.setTongthoigian(rs.getDouble("TONGTHOIGIANCHOI"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching revenue by ID: " + e.getMessage());
        }

        return revenue;
    }
    public static boolean callCalculateRevenue(String Noidung, String type) {
        String sql;
        if (type.equals("DAILY")) sql =  "{CALL CALCULATE_DAILY_REVENUE(?) }";
        else sql = "{CALL CALCULATE_MONTHLY_REVENUE(?) }";
        try ( Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, Noidung);
            stmt.execute();
            System.out.println("✅ Revenue calculation completed successfully!");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error calculating revenue: " + e.getMessage());
        }
        return false; // Return false if the revenue calculation failed
    }
    public static ArrayList<RevenueClass> RevenueByDay(String Day, String type){
        String sql2 = "SELECT TONGDOANHTHU, TONGDOANHTHUDV, CREATED_AT, TONGSODV, TONGTHOIGIANCHOI FROM DOANH_THU WHERE TRUNC(CREATED_AT) >= ADD_MONTHS(TRUNC(SYSDATE, 'MM'), -?) AND IS_DELETE = 0 AND TRUNC(CREATED_AT) < TRUNC(SYSDATE, 'MM') + INTERVAL '1' MONTH AND LOAIDT = 'MONTHLY' ORDER BY CREATED_AT";
        String sql = "SELECT TONGDOANHTHU, TONGDOANHTHUDV, CREATED_AT, TONGSODV, TONGTHOIGIANCHOI FROM DOANH_THU WHERE TRUNC(CREATED_AT) >= TRUNC(SYSDATE) - ? AND TRUNC(CREATED_AT) <= TRUNC(SYSDATE) AND LOAIDT = 'DAILY' AND IS_DELETE = 0 ORDER BY CREATED_AT";
        if (type.equals("Monthly")) sql = sql2;
        ArrayList<RevenueClass> revenues = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, Day);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RevenueClass revenue = new RevenueClass();
                revenue.setTongDoanhThu(rs.getDouble("TONGDOANHTHU"));
                revenue.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                revenue.setTongdoanhthudv(rs.getDouble("TONGDOANHTHUDV"));
                revenue.setTongsodv(rs.getDouble("TONGSODV"));
                revenue.setTongthoigian(rs.getDouble("TONGTHOIGIANCHOI"));
                revenues.add(revenue);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching revenues: " + e.getMessage());
        }

        return revenues;
    }
}