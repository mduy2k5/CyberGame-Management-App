package com.raven.dbfunction;
import OtherFunction.Hash;
import com.raven.classes.*;
import java.sql.*;
import java.util.ArrayList;

public class Employee {
     

    // Function to add a new record to both NHAN_VIEN and USERS tables
    public static boolean AddEmployee(String hoten, String sdt, java.sql.Date ngaysinh, String email, String diachi, String malnv, String masothuecn, String sobhyt,  String quyenhan) {
        String sql = "{CALL CREATE_NEW_EMPLOYEE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try ( Connection conn = DBConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            // Set parameters for the stored procedure
            stmt.setString(1, hoten);       // p_HOTEN
            stmt.setString(2, diachi);      // p_DIACHI
            stmt.setString(3, sdt);         // p_SDT
            stmt.setDate(4, ngaysinh);      // p_NGSINH
            stmt.setString(5, malnv);       // p_MALNV
            stmt.setString(6, email);       // p_EMAIL
            stmt.setString(7, masothuecn); // p_MATCN
            stmt.setString(8, sobhyt);      // p_SOBHYT
            stmt.setString(9, Hash.change(sdt));
            stmt.setString(10, quyenhan);

            // Execute the stored procedure
            stmt.execute();
            System.out.println("✅ Employee added successfully using stored procedure!");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error adding employee: " + e.getMessage());
        }
        return false;
    }

    // Function to update a record in both NHAN_VIEN and USERS tables
    public static boolean UpdateEmployee(String manv, String userId, String hoten, String sdt, java.sql.Date ngaysinh, String email, String diachi, String malnv, String masothuecn, String sobhyt) {
        String sqlUsers = "UPDATE USERS SET HOTEN = ?, SDT = ?, NGAYSINH = ?, EMAIL = ?, DIACHI = ? WHERE USER_ID = ?";
        String sqlNhanVien = "UPDATE NHAN_VIEN SET MALNV = ?, MASOTHUECN = ?, SOBHYT = ? WHERE MANV = ?";

        try ( Connection conn = DBConnection.getConnection()) {
            // Update USERS table
            try (PreparedStatement stmtUsers = conn.prepareStatement(sqlUsers)) {
                stmtUsers.setString(1, hoten);
                stmtUsers.setString(2, sdt);
                stmtUsers.setDate(3, ngaysinh);
                stmtUsers.setString(4, email);
                stmtUsers.setString(5, diachi);
                stmtUsers.setString(6, userId);
                stmtUsers.executeUpdate();
            }

            // Update NHAN_VIEN table
            try (PreparedStatement stmtNhanVien = conn.prepareStatement(sqlNhanVien)) {
                stmtNhanVien.setString(1, malnv);
                stmtNhanVien.setString(2, masothuecn);
                stmtNhanVien.setString(3, sobhyt);
                stmtNhanVien.setString(4, manv);
                stmtNhanVien.executeUpdate();
            }

            System.out.println("✅ Employee updated successfully!");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error updating employee: " + e.getMessage());
        }
        return false;
    }

    // Function to delete a record from both NHAN_VIEN and USERS tables
    public static boolean DeleteEmployee(String manv, String userId) {
        String sqlNhanVien = "UPDATE NHAN_VIEN SET IS_DELETE = 1 WHERE MANV = ?";
        String sqlUsers = "UPDATE USERS SET IS_DELETE = 1 WHERE USER_ID = ?";

        try ( Connection conn = DBConnection.getConnection()) {
            // Delete from NHAN_VIEN table
            try (PreparedStatement stmtNhanVien = conn.prepareStatement(sqlNhanVien)) {
                stmtNhanVien.setString(1, manv);
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

    // Function to select records from NHAN_VIEN joined with USERS
    public static ArrayList<EmployeeClass> SelectEmployee() {
        String sql = "SELECT NV.MANV, NV.USER_ID, NV.NGVL, NV.MALNV, NV.MASOTHUECN, NV.SOBHYT, NV.CREATED_AT, " +
                    "U.HOTEN, U.QUYENHANG, U.SDT, U.NGAYSINH, U.EMAIL, U.DIACHI " +
                    "FROM NHAN_VIEN NV " +
                    "JOIN USERS U ON NV.USER_ID = U.USER_ID " +
                    "WHERE NV.IS_DELETE = 0 AND U.IS_DELETE = 0";
        ArrayList<EmployeeClass> employeeList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeClass employee = new EmployeeClass();
                employee.setManv(rs.getString("MANV"));
                employee.setUserId(rs.getString("USER_ID"));
                employee.setNgvl(rs.getDate("NGVL"));
                employee.setMalnv(rs.getString("MALNV"));
                employee.setMasothuecn(rs.getString("MASOTHUECN"));
                employee.setSobhyt(rs.getString("SOBHYT"));
                employee.setHoten(rs.getString("HOTEN"));
                employee.setRoleType(rs.getString("QUYENHANG"));
                employee.setSdt(rs.getString("SDT"));
                employee.setNgaysinh(rs.getDate("NGAYSINH"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setDiachi(rs.getString("DIACHI"));
                employee.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching employees: " + e.getMessage());
        }

        return employeeList;
    }
    public static ArrayList<EmployeeClass> SelectEmployeeByName(String name) {
        String sql = "SELECT NV.MANV, NV.USER_ID, NV.NGVL, NV.MALNV, NV.MASOTHUECN, NV.SOBHYT, " +
                    "U.HOTEN, U.QUYENHANG, U.SDT, U.NGAYSINH, U.EMAIL, U.DIACHI " +
                    "FROM NHAN_VIEN NV " +
                    "JOIN USERS U ON NV.USER_ID = U.USER_ID " +
                    "WHERE NV.IS_DELETE = 0 AND U.IS_DELETE = 0 AND LOWER(U.HOTEN) LIKE ?";
        ArrayList<EmployeeClass> employeeList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setString(1, "%" + name.toLowerCase() + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EmployeeClass employee = new EmployeeClass();
                    employee.setManv(rs.getString("MANV"));
                    employee.setUserId(rs.getString("USER_ID"));
                    employee.setNgvl(rs.getDate("NGVL"));
                    employee.setMalnv(rs.getString("MALNV"));
                    employee.setMasothuecn(rs.getString("MASOTHUECN"));
                    employee.setSobhyt(rs.getString("SOBHYT"));
                    employee.setHoten(rs.getString("HOTEN"));
                    employee.setRoleType(rs.getString("QUYENHANG"));
                    employee.setSdt(rs.getString("SDT"));
                    employee.setNgaysinh(rs.getDate("NGAYSINH"));
                    employee.setEmail(rs.getString("EMAIL"));
                    employee.setDiachi(rs.getString("DIACHI"));
                    employeeList.add(employee);
            }
        }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching employees: " + e.getMessage());
        }

        return employeeList;
    }
    public static EmployeeClass SelectEmployeeByID(String id) {
        String sql = "SELECT NV.MANV, NV.USER_ID, NV.NGVL, NV.MALNV, NV.MASOTHUECN, NV.SOBHYT, " +
                    "U.HOTEN, U.QUYENHANG, U.SDT, U.NGAYSINH, U.EMAIL, U.DIACHI " +
                    "FROM NHAN_VIEN NV " +
                    "JOIN USERS U ON NV.USER_ID = U.USER_ID " +
                    "WHERE NV.IS_DELETE = 0 AND U.IS_DELETE = 0 AND NV.MANV = ?";
        EmployeeClass employee = new EmployeeClass();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee.setManv(rs.getString("MANV"));
                employee.setUserId(rs.getString("USER_ID"));
                employee.setNgvl(rs.getDate("NGVL"));
                employee.setMalnv(rs.getString("MALNV"));
                employee.setMasothuecn(rs.getString("MASOTHUECN"));
                employee.setSobhyt(rs.getString("SOBHYT"));
                employee.setHoten(rs.getString("HOTEN"));
                employee.setRoleType(rs.getString("QUYENHANG"));
                employee.setSdt(rs.getString("SDT"));
                employee.setNgaysinh(rs.getDate("NGAYSINH"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setDiachi(rs.getString("DIACHI"));
                return employee; // Return the employee as soon as it is found
            }

            } catch (SQLException e) {
                System.err.println("❌ Error fetching employees: " + e.getMessage());
            }
        return employee;
    }
}