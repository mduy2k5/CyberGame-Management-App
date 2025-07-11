package com.raven.dbfunction;

import com.raven.classes.PaymentClass;
import java.sql.*;
import java.util.ArrayList;

public class Payment {
    // Global variables for database connection
     

    public static ArrayList<PaymentClass> SelectPayment() {
        ArrayList<PaymentClass> paymentList = new ArrayList<>();
        String sql = """
            SELECT MAPHIEU, MAKH, TONGSODV, TRANGTHAI, TONGTIEN, CREATED_AT, TONGTIENDV, TONGTHOIGIANCHOI
            FROM PHIEU_THANH_TOAN
            WHERE IS_DELETE = 0
        """;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PaymentClass payment = new PaymentClass();
                payment.setMaPhieu(rs.getString("MAPHIEU"));
                payment.setMaKH(rs.getString("MAKH"));
                payment.setTongSoDV(rs.getInt("TONGSODV"));
                payment.setTrangThai(rs.getString("TRANGTHAI"));
                payment.setTongTien(rs.getInt("TONGTIEN"));
                payment.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                payment.setTiendv(rs.getDouble("TONGTIENDV"));
                payment.setTongtg(rs.getDouble("TONGTHOIGIANCHOI"));
                paymentList.add(payment);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching payments: " + e.getMessage());
        }

        return paymentList;
    }
}