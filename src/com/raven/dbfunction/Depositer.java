package com.raven.dbfunction;
import com.raven.classes.DepositClass;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Depositer {
     
    public static boolean Deposit (String makh, int sotien, String hinhthuc, String trangthai){
        CallableStatement stmt = null;
        ResultSet rs = null;
        String sql = "{ call DEPOSIT(?, ?, ?, ?) }";
        try( Connection conn = DBConnection.getConnection())
        {
            Class.forName("oracle.jdbc.OracleDriver");
            stmt = conn.prepareCall(sql);
            stmt.setString(1,makh);
            stmt.setInt(2,sotien);
            stmt.setString(3,hinhthuc);
            stmt.setString(4,trangthai);

            
            stmt.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Not found");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close(); 
            } catch (SQLException e) {
                System.err.println("An error occurred while closing resources: " + e.getMessage());
            }
        }
        
        return true;
    }
    public static boolean DepositTest (Connection conn, String makh, int sotien, String hinhthuc, String trangthai){
        CallableStatement stmt = null;
        ResultSet rs = null;
        String sql = "{ call DEPOSIT(?, ?, ?, ?) }";
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            stmt = conn.prepareCall(sql);
            stmt.setString(1,makh);
            stmt.setInt(2,sotien);
            stmt.setString(3,hinhthuc);
            stmt.setString(4,trangthai);

            
            stmt.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Not found");
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        
        return true;
    }
    public static ArrayList<DepositClass> SelectDeposit(String maKH) {
        String sql = "SELECT MAGD, MAKH, HINHTHUC, SOTIEN, TRANGTHAI, IS_DELETE, CREATED_AT " +
                     "FROM QUANLYTIEMCYCBERGAME.GIAO_DICH WHERE MAKH = ? AND IS_DELETE = 0";
        ArrayList<DepositClass> deposits = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DepositClass deposit = new DepositClass();
                deposit.setMaGD(rs.getString("MAGD"));
                deposit.setMaKH(rs.getString("MAKH"));
                deposit.setHinhThuc(rs.getString("HINHTHUC"));
                deposit.setSoTien(rs.getDouble("SOTIEN"));
                deposit.setTrangThai(rs.getString("TRANGTHAI"));
                deposit.setDelete(rs.getBoolean("IS_DELETE"));
                deposit.setCreatedAt(rs.getTimestamp("CREATED_AT"));

                deposits.add(deposit);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching deposit records: " + e.getMessage());
        }

        return deposits;
    }
    public static ArrayList<DepositClass> SelectAllDeposits() {
        String sql = "SELECT MAGD, MAKH, HINHTHUC, SOTIEN, TRANGTHAI, CREATED_AT " +
                    "FROM GIAO_DICH WHERE IS_DELETE = 0";
        ArrayList<DepositClass> deposits = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DepositClass deposit = new DepositClass();
                deposit.setMaGD(rs.getString("MAGD"));
                deposit.setMaKH(rs.getString("MAKH"));
                deposit.setHinhThuc(rs.getString("HINHTHUC"));
                deposit.setSoTien(rs.getDouble("SOTIEN"));
                deposit.setTrangThai(rs.getString("TRANGTHAI"));
                deposit.setCreatedAt(rs.getTimestamp("CREATED_AT"));

                deposits.add(deposit);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching deposit records: " + e.getMessage());
        }

        return deposits;
    }
    public static ArrayList<DepositClass> SelectAllDepositsNonDeposited() {
        String sql = "SELECT MAGD, MAKH, HINHTHUC, SOTIEN, TRANGTHAI, CREATED_AT " +
                    "FROM GIAO_DICH WHERE IS_DELETE = 0 AND TRANGTHAI <> 'Success'";
        ArrayList<DepositClass> deposits = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DepositClass deposit = new DepositClass();
                deposit.setMaGD(rs.getString("MAGD"));
                deposit.setMaKH(rs.getString("MAKH"));
                deposit.setHinhThuc(rs.getString("HINHTHUC"));
                deposit.setSoTien(rs.getDouble("SOTIEN"));
                deposit.setTrangThai(rs.getString("TRANGTHAI"));
                deposit.setCreatedAt(rs.getTimestamp("CREATED_AT"));

                deposits.add(deposit);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching deposit records: " + e.getMessage());
        }

        return deposits;
    }
    public static boolean UpdateTransactionStatusToSuccess(String maGD) {
        String sql = "UPDATE GIAO_DICH SET TRANGTHAI = 'Success' WHERE MAGD = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the MAKH parameter
            stmt.setString(1, maGD);

            // Execute the update
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Transaction status updated to 'Success' for MAKH: " + maGD);
                return true;
            } else {
                System.out.println("⚠️ No transactions found for MAKH: " + maGD);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error updating transaction status: " + e.getMessage());
            return false;
        }
    }
}
