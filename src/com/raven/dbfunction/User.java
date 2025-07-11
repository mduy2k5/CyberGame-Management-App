package com.raven.dbfunction;

import com.raven.classes.UsersClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {


    public static UsersClass SelectUserByMAKH(String maKH) {
        UsersClass user = null;

        String query = """
            SELECT U.USER_ID, U.HOTEN, U.QUYENHANG, U.SDT, U.NGAYSINH, U.EMAIL, U.DIACHI, U.CREATED_AT, U.IS_DELETE
            FROM USERS U
            JOIN KHACH_HANG KH ON U.USER_ID = KH.USER_ID
            WHERE KH.MAKH = ? AND U.IS_DELETE = 0
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, maKH);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new UsersClass(
                        rs.getString("USER_ID"),
                        rs.getString("HOTEN"),
                        rs.getString("QUYENHANG"),
                        rs.getString("SDT"),
                        rs.getDate("NGAYSINH"),
                        rs.getString("EMAIL"),
                        rs.getString("DIACHI"),
                        rs.getDate("CREATED_AT"),
                        rs.getInt("IS_DELETE")
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error in SelectUserByMAKH: " + e.getMessage());
        }

        return user;
    }
    public static UsersClass SelectUserByMANV(String manv) {
        UsersClass user = null;

        String query = """
            SELECT U.USER_ID, U.HOTEN, U.QUYENHANG, U.SDT, U.NGAYSINH, U.EMAIL, U.DIACHI, U.CREATED_AT, U.IS_DELETE
            FROM USERS U
            JOIN NHAN_VIEN NV ON U.USER_ID = NV.USER_ID
            WHERE NV.MANV = ? AND U.IS_DELETE = 0
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, manv);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new UsersClass(
                        rs.getString("USER_ID"),
                        rs.getString("HOTEN"),
                        rs.getString("QUYENHANG"),
                        rs.getString("SDT"),
                        rs.getDate("NGAYSINH"),
                        rs.getString("EMAIL"),
                        rs.getString("DIACHI"),
                        rs.getDate("CREATED_AT"),
                        rs.getInt("IS_DELETE")
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error in SelectUserByMANV: " + e.getMessage());
        }

        return user;
    }
    public static boolean UpdateUser(String userId, String hoten, String sdt, java.sql.Date ngaySinh, String email, String diaChi) {
        String sql = """
            UPDATE USERS
            SET HOTEN = ?, SDT = ?, NGAYSINH = ?, EMAIL = ?, DIACHI = ?
            WHERE USER_ID = ? AND IS_DELETE = 0
        """;

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the update query
            stmt.setString(1, hoten);
            stmt.setString(2, sdt);
            stmt.setDate(3, ngaySinh);
            stmt.setString(4, email);
            stmt.setString(5, diaChi);
            stmt.setString(6, userId);

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ User updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating user: " + e.getMessage());
        }
        return false; // Return false if the update was not successful
    }
    public static String SelectIDByUserID(String userId, String type) {
        String id = "";

        String query = """
            SELECT NV.MANV FROM USERS U JOIN NHAN_VIEN NV ON U.USER_ID = NV.USER_ID
            WHERE U.USER_ID = ? AND U.IS_DELETE = 0
        """;
        if (type.equals("KHACHHANG")) query = """
            SELECT KH.MAKH FROM USERS U JOIN KHACH_HANG KH ON U.USER_ID = KH.USER_ID
            WHERE U.USER_ID = ? AND U.IS_DELETE = 0
        """;
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getString(1);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error in SelectIDByUserID: " + e.getMessage());
        }

        return id;
    }
}