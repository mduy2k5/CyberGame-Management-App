package com.raven.dbfunction;

import OtherFunction.Hash;
import com.raven.classes.CustomerClass;
import java.sql.*;
import java.util.ArrayList;

public class Customer {

    // Function to select all customers
    public static ArrayList<CustomerClass> SelectCustomer() {
        ArrayList<CustomerClass> customerList = new ArrayList<>();
        String sql = "SELECT MAKH, USER_ID, MAHKH, SODIEMTICHLUY, SODUTK, TRANGTHAI FROM KHACH_HANG WHERE IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CustomerClass customer = new CustomerClass(
                    rs.getString("MAKH"),
                    rs.getString("USER_ID"),
                    rs.getString("MAHKH"),
                    rs.getInt("SODIEMTICHLUY"),
                    rs.getInt("SODUTK"),
                    rs.getString("TRANGTHAI")
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customers: " + e.getMessage());
        }

        return customerList;
    }
    public static ArrayList<CustomerClass> SelectCustomerTest(Connection conn) throws InterruptedException {
        ArrayList<CustomerClass> customerList = new ArrayList<>();
        String sql = "SELECT MAKH, USER_ID, MAHKH, SODIEMTICHLUY, SODUTK, TRANGTHAI FROM KHACH_HANG WHERE IS_DELETE = 0";
        if (conn != null){
            try (PreparedStatement stmt = conn.prepareStatement(sql); // Rs 1
                ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    CustomerClass customer = new CustomerClass(
                        rs.getString("MAKH"),
                        rs.getString("USER_ID"),
                        rs.getString("MAHKH"),
                        rs.getInt("SODIEMTICHLUY"),
                        rs.getInt("SODUTK"),
                        rs.getString("TRANGTHAI")
                    );
                    customerList.add(customer);
            }
            } catch (SQLException e) {
                System.err.println("❌ Error fetching customers: " + e.getMessage());
            }
        }
        
        return customerList;
    }
    // Function to select SODUTK by an array of MAKH
    public static ArrayList<Integer> SelectCustomerByPH(String[] makhArray) {
        ArrayList<Integer> sodutkList = new ArrayList<>();
        if (makhArray == null || makhArray.length == 0) {
            return sodutkList; // Return empty list if no MAKH values are provided
        }

        // Build the SQL query with placeholders for the array of MAKH
        StringBuilder sqlBuilder = new StringBuilder("SELECT SODUTK FROM KHACH_HANG WHERE MAKH IN (");
        for (int i = 0; i < makhArray.length; i++) {
            sqlBuilder.append("?");
            if (i < makhArray.length - 1) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the MAKH values in the prepared statement
            for (int i = 0; i < makhArray.length; i++) {
                stmt.setString(i + 1, makhArray[i]);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sodutkList.add(rs.getInt("SODUTK"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching SODUTK by MAKH: " + e.getMessage());
        }

        return sodutkList;
    }
    public static CustomerClass SelectCustomerByID(String maKH) {
        CustomerClass customer = null;

        String sql = """
            SELECT MAKH, USER_ID, MAHKH, SODIEMTICHLUY, SODUTK, TRANGTHAI
            FROM KHACH_HANG
            WHERE MAKH = ? AND IS_DELETE = 0
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the MAKH value in the prepared statement
            stmt.setString(1, maKH);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new CustomerClass(
                    rs.getString("MAKH"),
                    rs.getString("USER_ID"),
                    rs.getString("MAHKH"),
                    rs.getInt("SODIEMTICHLUY"),
                    rs.getInt("SODUTK"),
                    rs.getString("TRANGTHAI")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customer by MAKH: " + e.getMessage());
        }

        return customer;
    }
    public static boolean callCreateNewCustomer(String hoTen, String diaChi, String sdt, String ngaySinh, String maHKH, String email) {
        
        String sql = "{CALL CREATE_NEW_CUSTOMER(?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)}";

        try ( Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(true);
            // Set the input parameters for the procedure
            stmt.setString(1, hoTen);
            stmt.setString(2, diaChi);
            stmt.setString(3, sdt);
            stmt.setString(4, ngaySinh);
            stmt.setString(5, maHKH);
            stmt.setString(6, email);
            stmt.setString(7, Hash.change(sdt));
            
            // Execute the procedure
            stmt.execute();
            System.out.println("✅ Customer created successfully!");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error calling CREATE_NEW_CUSTOMER procedure: " + e.getMessage());
            return false;
        }
    }
    public static boolean callCreateNewCustomerTest(Connection conn,String hoTen, String diaChi, String sdt, String ngaySinh, String maHKH, String email) {
        
        String sql = "{CALL CREATE_NEW_CUSTOMER(?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(true);
            // Set the input parameters for the procedure
            stmt.setString(1, hoTen);
            stmt.setString(2, diaChi);
            stmt.setString(3, sdt);
            stmt.setString(4, ngaySinh);
            stmt.setString(5, maHKH);
            stmt.setString(6, email);
            stmt.setString(7, Hash.change(sdt));
            
            // Execute the procedure
            stmt.execute();
            System.out.println("✅ Customer created successfully!");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error calling CREATE_NEW_CUSTOMER procedure: " + e.getMessage());
            return false;
        }
    }
    public static CustomerClass SelectCustomerByPhone(String sdt) {
        CustomerClass customer = null;
    
        String sql = """
            SELECT MAKH, USER_ID, MAHKH, SODIEMTICHLUY, SODUTK, TRANGTHAI
            FROM KHACH_HANG
            WHERE USER_ID IN (
                SELECT USER_ID
                FROM USERS
                WHERE SDT = ? AND IS_DELETE = 0
            ) AND IS_DELETE = 0
        """;
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            // Set the SDT value in the prepared statement
            stmt.setString(1, sdt);
    
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                customer = new CustomerClass(
                    rs.getString("MAKH"),
                    rs.getString("USER_ID"),
                    rs.getString("MAHKH"),
                    rs.getInt("SODIEMTICHLUY"),
                    rs.getInt("SODUTK"),
                    rs.getString("TRANGTHAI")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customer by SDT: " + e.getMessage());
        }
    
        return customer;
    }
    public static CustomerClass SelectCustomerByEmail(String email) {
        CustomerClass customer = null;
    
        String sql = """
            SELECT MAKH, USER_ID, MAHKH, SODIEMTICHLUY, SODUTK, TRANGTHAI
            FROM KHACH_HANG
            WHERE USER_ID IN (
                SELECT USER_ID
                FROM USERS
                WHERE EMAIL = ? AND IS_DELETE = 0
            ) AND IS_DELETE = 0
        """;
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            // Set the EMAIL value in the prepared statement
            stmt.setString(1, email);
    
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                customer = new CustomerClass(
                    rs.getString("MAKH"),
                    rs.getString("USER_ID"),
                    rs.getString("MAHKH"),
                    rs.getInt("SODIEMTICHLUY"),
                    rs.getInt("SODUTK"),
                    rs.getString("TRANGTHAI")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customer by EMAIL: " + e.getMessage());
        }
    
        return customer;
    }
    public static ArrayList<CustomerClass> SelectCustomerByName(String name) {
        ArrayList<CustomerClass> customerList = new ArrayList<>();
        String sql = """
            SELECT KH.MAKH, KH.USER_ID, MAHKH, SODIEMTICHLUY, SODUTK, TRANGTHAI
            FROM KHACH_HANG KH
            JOIN USERS U ON KH.USER_ID = U.USER_ID
            WHERE LOWER(U.HOTEN) LIKE ?
        """;

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the name parameter with wildcard for partial matching
            stmt.setString(1, "%" + name.toLowerCase() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CustomerClass customer = new CustomerClass(
                        rs.getString("MAKH"),
                        rs.getString("USER_ID"),
                        rs.getString("MAHKH"),
                        rs.getInt("SODIEMTICHLUY"),
                        rs.getInt("SODUTK"),
                        rs.getString("TRANGTHAI")
                    );
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customers by name: " + e.getMessage());
        }

        return customerList;
    }
    public static ArrayList<CustomerClass> SelectCustomerByNameAndPhone(String name, String phone) {
        ArrayList<CustomerClass> customerList = new ArrayList<>();
        String sql = """
            SELECT MAKH, USER_ID, MAHKH, SODIEMTICHLUY, SODUTK, TRANGTHAI
            FROM KHACH_HANG KH
            JOIN USERS U ON KH.USER_ID = U.USER_ID
            WHERE LOWER(U.HOTEN) LIKE ? AND U.SDT = ?
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the name and phone parameters
            stmt.setString(1, "%" + name.toLowerCase() + "%");
            stmt.setString(2, phone);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CustomerClass customer = new CustomerClass(
                        rs.getString("MAKH"),
                        rs.getString("USER_ID"),
                        rs.getString("MAHKH"),
                        rs.getInt("SODIEMTICHLUY"),
                        rs.getInt("SODUTK"),
                        rs.getString("TRANGTHAI")
                    );
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customers by name and phone: " + e.getMessage());
        }

        return customerList;
    }
    public static boolean UpdateCustomer(String makh, String mahkh){
        String sqlkh = "UPDATE KHACH_HANG SET MAHKH = ? WHERE MAKH = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            // Update USERS table
            try (PreparedStatement stmtUsers = conn.prepareStatement(sqlkh)) {
                stmtUsers.setString(1, mahkh);
                stmtUsers.setString(2, makh);
                stmtUsers.executeUpdate();
            }

            System.out.println("✅ Employee updated successfully!");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error updating employee: " + e.getMessage());
        }
        return false;
    }
    public static boolean DeleteCustomer(String makh, String userId) {
        String sqlNhanVien = "UPDATE KHACH_HANG SET IS_DELETE = 1 WHERE MAKH = ?";
        String sqlUsers = "UPDATE USERS SET IS_DELETE = 1 WHERE USER_ID = ?";

        try ( Connection conn = DBConnection.getConnection()) {
            // Delete from NHAN_VIEN table
            try (PreparedStatement stmtNhanVien = conn.prepareStatement(sqlNhanVien)) {
                stmtNhanVien.setString(1, makh);
                stmtNhanVien.executeUpdate();
            }

            // Delete from USERS table
            try (PreparedStatement stmtUsers = conn.prepareStatement(sqlUsers)) {
                stmtUsers.setString(1, userId);
                stmtUsers.executeUpdate();
            }

            System.out.println("✅ Employee deleted successfully!");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error deleting employee: " + e.getMessage());
        }
        return false;
    }
}