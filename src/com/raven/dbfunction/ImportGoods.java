package com.raven.dbfunction;
import com.raven.classes.ImportGoodsClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportGoods {
     

    // Function to add a new record to the NHAP_HANG table
    public static boolean AddIGoods(String machungtu, String mancc, String manv, double trigia, String loainhaphang) {
        String sql = "INSERT INTO NHAP_HANG (MACHUNGTU, MANCC, MANV, TRIGIA, LOAINHAPHANG) VALUES (?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, machungtu);
            stmt.setString(2, mancc);
            stmt.setString(3, manv);
            stmt.setDouble(4, trigia);
            stmt.setString(5, loainhaphang);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Import goods record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding import goods record: " + e.getMessage());
        }
        return false;
    }

    // Function to update a record in the NHAP_HANG table
    public static boolean UpdateIGoods(String manh, String machungtu, String mancc, String manv, double trigia, String loainhaphang) {
        String sql = "UPDATE NHAP_HANG SET MACHUNGTU = ?, MANCC = ?, MANV = ?, TRIGIA = ?, LOAINHAPHANG = ? WHERE MANH = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, machungtu);
            stmt.setString(2, mancc);
            stmt.setString(3, manv);
            stmt.setDouble(4, trigia);
            stmt.setString(6, manh);
            stmt.setString(5,loainhaphang);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Import goods record updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating import goods record: " + e.getMessage());
        }
        return false;
    }

    // Function to delete a record from the NHAP_HANG table by ID
    public static boolean DeleteIGoods(String manh) {
        String sql = "UPDATE NHAP_HANG SET IS_DELETE = 1 WHERE MANH = ?";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, manh);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Import goods record deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting import goods record: " + e.getMessage());
        }
        return false;
    }

    // Function to select records from the NHAP_HANG table
    public static ArrayList<ImportGoodsClass> SelectIGoods() {
        String sql = "SELECT MANH, MACHUNGTU, MANCC, MANV, TRIGIA, CREATED_AT, LOAINHAPHANG FROM NHAP_HANG WHERE IS_DELETE = 0";
        ArrayList<ImportGoodsClass> importGoodsList = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ImportGoodsClass importGoods = new ImportGoodsClass();
                importGoods.setManh(rs.getString("MANH"));
                importGoods.setMachungtu(rs.getString("MACHUNGTU"));
                importGoods.setMancc(rs.getString("MANCC"));
                importGoods.setManv(rs.getString("MANV"));
                importGoods.setTrigia(rs.getDouble("TRIGIA"));
                importGoods.setCreateAt(rs.getDate("CREATED_AT"));
                importGoods.setLoaiNhapHang(rs.getString("LOAINHAPHANG"));
                importGoodsList.add(importGoods);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching import goods records: " + e.getMessage());
        }

        return importGoodsList;
    }
    public static ImportGoodsClass SelectIGoodsById(String importID) {
        String sql = "SELECT MANH, MACHUNGTU, MANCC, MANV, TRIGIA, CREATED_AT, LOAINHAPHANG FROM NHAP_HANG WHERE IS_DELETE = 0 AND MANH = ?";
        ImportGoodsClass importGoods = new ImportGoodsClass();

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setString(1, importID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                importGoods.setManh(rs.getString("MANH"));
                importGoods.setMachungtu(rs.getString("MACHUNGTU"));
                importGoods.setMancc(rs.getString("MANCC"));
                importGoods.setManv(rs.getString("MANV"));
                importGoods.setTrigia(rs.getDouble("TRIGIA"));
                importGoods.setCreateAt(rs.getDate("CREATED_AT"));
                importGoods.setLoaiNhapHang(rs.getString("LOAINHAPHANG"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching import goods records: " + e.getMessage());
        }

        return importGoods;
    }
}