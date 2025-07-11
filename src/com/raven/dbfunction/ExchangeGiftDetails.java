package com.raven.dbfunction;

import com.raven.classes.ExchangeGDetailsClass;
import java.sql.*;
import java.util.ArrayList;

public class ExchangeGiftDetails {


    public static ArrayList<ExchangeGDetailsClass> SelectAllExchangeGiftDetails() {
        ArrayList<ExchangeGDetailsClass> exchangeList = new ArrayList<>();

        String sql = """
            SELECT MADQ, MAKH, MAQT, SL, CREATED_AT, TRANGTHAI
            FROM LICH_SU_DOI_QUA
            WHERE IS_DELETE = 0
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ExchangeGDetailsClass exchange = new ExchangeGDetailsClass();
                exchange.setMaDQ(rs.getString("MADQ"));
                exchange.setMaKH(rs.getString("MAKH"));
                exchange.setMaQT(rs.getString("MAQT"));
                exchange.setSoLuong(rs.getInt("SL"));
                exchange.setCreatedat(rs.getDate("CREATED_AT"));
                exchange.setTrangThai(rs.getString("TRANGTHAI"));
                exchangeList.add(exchange);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching records from LICH_SU_DOI_QUA: " + e.getMessage());
        }

        return exchangeList;
    }
    public static ArrayList<ExchangeGDetailsClass> SelectAllReceivedExchangeGiftDetails() {
        ArrayList<ExchangeGDetailsClass> exchangeList = new ArrayList<>();

        String sql = """
            SELECT MADQ, MAKH, MAQT, SL, CREATED_AT, TRANGTHAI
            FROM LICH_SU_DOI_QUA
            WHERE IS_DELETE = 0 AND TRANGTHAI = 'Received'
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ExchangeGDetailsClass exchange = new ExchangeGDetailsClass();
                exchange.setMaDQ(rs.getString("MADQ"));
                exchange.setMaKH(rs.getString("MAKH"));
                exchange.setMaQT(rs.getString("MAQT"));
                exchange.setSoLuong(rs.getInt("SL"));
                exchange.setCreatedat(rs.getDate("CREATED_AT"));
                exchange.setTrangThai(rs.getString("TRANGTHAI"));
                exchangeList.add(exchange);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching records from LICH_SU_DOI_QUA: " + e.getMessage());
        }

        return exchangeList;
    }
    public static boolean addExchangeGiftDetails(String maKH, String maQT, int soLuong) {
        String sql = """
            INSERT INTO LICH_SU_DOI_QUA (MAKH, MAQT, SL,TRANGTHAI)
            VALUES (?, ?, ?, ?)
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKH);
            stmt.setString(2, maQT);
            stmt.setInt(3, soLuong);
            stmt.setString(4, "Received"); // Assuming default status is 'Pending'

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Exchange gift details added successfully!");
                return true;
            } else {
                System.out.println("❌ No records inserted.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error adding exchange gift details: " + e.getMessage());
        }
        return false; // Return false if the record was not added successfully
    }
    public static ArrayList<ExchangeGDetailsClass> selectExchangeGiftDetailsByMAKH(String maKH) {
        ArrayList<ExchangeGDetailsClass> exchangeList = new ArrayList<>();

        String sql = """
            SELECT MADQ, MAKH, MAQT, SL, CREATED_AT, TRANGTHAI
            FROM LICH_SU_DOI_QUA
            WHERE MAKH = ? AND IS_DELETE = 0 AND TRANGTHAI = 'Received'
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKH);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ExchangeGDetailsClass exchange = new ExchangeGDetailsClass();
                    exchange.setMaDQ(rs.getString("MADQ"));
                    exchange.setMaKH(rs.getString("MAKH"));
                    exchange.setMaQT(rs.getString("MAQT"));
                    exchange.setSoLuong(rs.getInt("SL"));
                    exchange.setCreatedat(rs.getDate("CREATED_AT"));
                    exchange.setTrangThai(rs.getString("TRANGTHAI"));
                    exchangeList.add(exchange);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error checking exchange gift details: " + e.getMessage());
        }

        return exchangeList;
    }
    public static boolean deleteExchangeGiftDetails(String maDQ) {
        String sql = """
            UPDATE LICH_SU_DOI_QUA
            SET IS_DELETE = 1
            WHERE MADQ = ?
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maDQ);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Exchange gift details deleted successfully!");
                return true;
            } else {
                System.out.println("❌ No records updated.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error deleting exchange gift details: " + e.getMessage());
        }
        return false; // Return false if the record was not deleted successfully
    }
    public static ArrayList<ExchangeGDetailsClass> SelectAllExchangeGiftDetailsByID(String MAKH) {
        ArrayList<ExchangeGDetailsClass> exchangeList = new ArrayList<>();

        String sql = """
            SELECT MADQ, MAKH, MAQT, SL, CREATED_AT, TRANGTHAI
            FROM LICH_SU_DOI_QUA
            WHERE IS_DELETE = 0 AND MAKH = ? 
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
            
            stmt.setString(1, MAKH);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ExchangeGDetailsClass exchange = new ExchangeGDetailsClass();
                exchange.setMaDQ(rs.getString("MADQ"));
                exchange.setMaKH(rs.getString("MAKH"));
                exchange.setMaQT(rs.getString("MAQT"));
                exchange.setSoLuong(rs.getInt("SL"));
                exchange.setCreatedat(rs.getDate("CREATED_AT"));
                exchange.setTrangThai(rs.getString("TRANGTHAI"));
                exchangeList.add(exchange);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching records from LICH_SU_DOI_QUA: " + e.getMessage());
        }

        return exchangeList;
    }
}