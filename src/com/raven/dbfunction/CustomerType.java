package com.raven.dbfunction;
import com.raven.classes.CustomerTypeClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerType {
    // Global variables for database connection
     

    // Function to add a new customer type
    public static boolean AddCType(String tenHang, double tyLeNap) {
        String sql = "INSERT INTO HANG_KHACH_HANG (TENHANG, TYLENAP) VALUES (?, ?)";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenHang);
            stmt.setDouble(2, tyLeNap);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Customer type added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding customer type: " + e.getMessage());
        }
        return false; // Return false if the customer type was not added successfully
    }

    // Function to update customer type information
    public static boolean UpdateCType(String maHKH, String tenHang, double tyLeNap) {
        String sql = "UPDATE HANG_KHACH_HANG SET TENHANG = ?, TYLENAP = ? WHERE MAHKH = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenHang);
            stmt.setDouble(2, tyLeNap);
            stmt.setString(3, maHKH);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Customer type updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating customer type: " + e.getMessage());
        }
        return false; // Return false if the customer type was not updated successfully
    }

    // Function to delete a customer type by setting IS_DELETE to 1
    public static boolean DeleteCType(String maHKH) {
        String sql = "UPDATE HANG_KHACH_HANG SET IS_DELETE = 1 WHERE MAHKH = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maHKH);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Customer type deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting customer type: " + e.getMessage());
        }
        return false; // Return false if the customer type was not deleted successfully
    }

    // Function to select all customer types and return an ArrayList of CustomerTypeClass
    public static ArrayList<CustomerTypeClass> SelectCType() {
        String sql = "SELECT MAHKH, TENHANG, TYLENAP FROM HANG_KHACH_HANG WHERE IS_DELETE = 0";
        ArrayList<CustomerTypeClass> customerTypes = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CustomerTypeClass customerType = new CustomerTypeClass();
                customerType.setMaHKH(rs.getString("MAHKH"));
                customerType.setTenHang(rs.getString("TENHANG"));
                customerType.setTyLeNap(rs.getDouble("TYLENAP"));

                customerTypes.add(customerType);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customer types: " + e.getMessage());
        }

        return customerTypes;
    }
    public static CustomerTypeClass SelectCTypeByID(String maHKH) {
        String sql = "SELECT MAHKH, TENHANG, TYLENAP FROM HANG_KHACH_HANG WHERE MAHKH = ? AND IS_DELETE = 0";
        CustomerTypeClass customerType = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maHKH);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customerType = new CustomerTypeClass();
                customerType.setMaHKH(rs.getString("MAHKH"));
                customerType.setTenHang(rs.getString("TENHANG"));
                customerType.setTyLeNap(rs.getDouble("TYLENAP"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching customer type by ID: " + e.getMessage());
        }

        return customerType;
    }
}