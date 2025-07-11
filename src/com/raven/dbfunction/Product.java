package com.raven.dbfunction;
import com.raven.classes.*;
import java.sql.*;
import java.util.ArrayList;

public class Product {
    // Global variables for database connection
     

    // Function to add a new product
    public static boolean AddProduct(String tenSP, String dvt, String loaiSP, int soLuongTK, int soDiemTichLuy, double donGiaBQ, String producturl) {
        String sql = "INSERT INTO SAN_PHAM (TENSP, DVT, LOAISP, SOLUONGTK, SODIEMTICHLUY, DONGIABQ, URL) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenSP);
            stmt.setString(2, dvt);
            stmt.setString(3, loaiSP);
            stmt.setInt(4, soLuongTK);
            stmt.setInt(5, soDiemTichLuy);
            stmt.setDouble(6, donGiaBQ);
            stmt.setString(7, producturl); // Assuming URL is not provided at this point

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Product added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding product: " + e.getMessage());
        }
        return false; // Return false if the product was not added successfully
    }

    // Function to update product information
    public static boolean UpdateProduct(String maSP, String tenSP, String dvt, String loaiSP, int soLuongTK, int soDiemTichLuy, double donGiaBQ,String producturl) {
        String sql = "UPDATE SAN_PHAM SET TENSP = ?, DVT = ?, LOAISP = ?, SOLUONGTK = ?, SODIEMTICHLUY = ?, DONGIABQ = ?, URL = ? WHERE MASP = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenSP);
            stmt.setString(2, dvt);
            stmt.setString(3, loaiSP);
            stmt.setInt(4, soLuongTK);
            stmt.setInt(5, soDiemTichLuy);
            stmt.setDouble(6, donGiaBQ);
            stmt.setString(7, producturl);
            stmt.setString(8, maSP);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Product updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating product: " + e.getMessage());
        }
        return false; // Return false if the product was not updated successfully
    }
    public static boolean UpdateSLProduct(String maSP,int soLuongTK) {
        String sql = "UPDATE SAN_PHAM SET SOLUONGTK = SOLUONGTK + ? WHERE MASP = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, soLuongTK);
            stmt.setString(2, maSP);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Product updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating product: " + e.getMessage());
        }
        return false; // Return false if the product was not updated successfully
    }
    // Function to delete a product by setting IS_DELETE to 1
    public static boolean DeleteProduct(String maSP) {
        String sql = "UPDATE SAN_PHAM SET IS_DELETE = 1 WHERE MASP = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSP);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Product deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting product: " + e.getMessage());
        }
        return false; // Return false if the product was not deleted successfully
    }

    // Function to select all products and return an ArrayList of ProductClass
    public static ArrayList<ProductClass> SelectProduct() {
        String sql = "SELECT MASP, TENSP, DVT, LOAISP, SOLUONGTK, SODIEMTICHLUY, DONGIABQ, URL FROM SAN_PHAM WHERE IS_DELETE = 0";
        ArrayList<ProductClass> products = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductClass product = new ProductClass();
                product.setMaSP(rs.getString("MASP"));
                product.setTenSP(rs.getString("TENSP"));
                product.setDvt(rs.getString("DVT"));
                product.setLoaiSP(rs.getString("LOAISP"));
                product.setSoLuongTK(rs.getInt("SOLUONGTK"));
                product.setSoDiemTichLuy(rs.getInt("SODIEMTICHLUY"));
                product.setDonGiaBQ(rs.getDouble("DONGIABQ"));
                product.setUrl(rs.getString("URL"));

                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching products: " + e.getMessage());
        }

        return products;
    }
    // Function to select a product by its ID (MASP)
    public static ProductClass SelectProductByID(String maSP) {
        String sql = "SELECT MASP, TENSP, DVT, LOAISP, SOLUONGTK, SODIEMTICHLUY, DONGIABQ, URL FROM QUANLYTIEMCYCBERGAME.SAN_PHAM WHERE MASP = ? AND IS_DELETE = 0";
        ProductClass product = null;

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSP);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new ProductClass();
                product.setMaSP(rs.getString("MASP"));
                product.setTenSP(rs.getString("TENSP"));
                product.setDvt(rs.getString("DVT"));
                product.setLoaiSP(rs.getString("LOAISP"));
                product.setSoLuongTK(rs.getInt("SOLUONGTK"));
                product.setSoDiemTichLuy(rs.getInt("SODIEMTICHLUY"));
                product.setDonGiaBQ(rs.getDouble("DONGIABQ"));
                product.setUrl(rs.getString("URL"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching product by ID: " + e.getMessage());
        }

        return product;
    }
    public static ProductClass SelectProductByIDTest(Connection conn, String maSP) {
        String sql = "SELECT MASP, TENSP, DVT, LOAISP, SOLUONGTK, SODIEMTICHLUY, DONGIABQ, URL FROM QUANLYTIEMCYCBERGAME.SAN_PHAM WHERE MASP = ? AND IS_DELETE = 0";
        ProductClass product = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSP);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new ProductClass();
                product.setMaSP(rs.getString("MASP"));
                product.setTenSP(rs.getString("TENSP"));
                product.setDvt(rs.getString("DVT"));
                product.setLoaiSP(rs.getString("LOAISP"));
                product.setSoLuongTK(rs.getInt("SOLUONGTK"));
                product.setSoDiemTichLuy(rs.getInt("SODIEMTICHLUY"));
                product.setDonGiaBQ(rs.getDouble("DONGIABQ"));
                product.setUrl(rs.getString("URL"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching product by ID: " + e.getMessage());
        }

        return product;
    }
}