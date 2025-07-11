package com.raven.dbfunction;

import com.raven.classes.UsedServiceClass;
import java.sql.*;
import java.util.ArrayList;

public class UsedService {



    // AddUService: Insert a new record into DICH_VU_DA_DUNG
    public static boolean AddUService(String maLS, String maSP, int soLuong, String status) {
        String sql = "INSERT INTO DICH_VU_DA_DUNG (MADVSD, MALS, MASP, SL, TRANGTHAI) VALUES ('', ?, ?, ?, ?)";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLS);
            stmt.setString(2, maSP);
            stmt.setInt(3, soLuong);
            stmt.setString(4, status);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ UsedService record added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error adding UsedService record: " + e.getMessage());
        }
        return false; // Return false if the record was not added successfully
    }

    // UpdateUService: Update an existing record in DICH_VU_DA_DUNG
    public static boolean UpdateUService(String maDVSD, String maSP, int soLuong, String status) {
        String sql = "UPDATE DICH_VU_DA_DUNG SET MASP = ?, SL = ?, TRANGTHAI = ? WHERE MADVSD = ?";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSP);
            stmt.setInt(2, soLuong);
            stmt.setString(3, status);
            stmt.setString(4, maDVSD);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ UsedService record updated successfully!");
                return true;
            } else {
                System.out.println("⚠️ No UsedService record found with MADVSD: " + maDVSD);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating UsedService record: " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }

    // DeleteUService: Delete a record from DICH_VU_DA_DUNG by MADVSD
    public static boolean DeleteUService(String maDVSD) {
        String sql = "DELETE FROM DICH_VU_DA_DUNG WHERE MADVSD = ?";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maDVSD);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ UsedService record deleted successfully!");
                return true;
            } else {
                System.out.println("⚠️ No UsedService record found with MADVSD: " + maDVSD);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting UsedService record: " + e.getMessage());
        }
        return false; // Return false if the record was not deleted successfully
    }

    // SelectUService: Retrieve all records from DICH_VU_DA_DUNG
    public static ArrayList<UsedServiceClass> SelectUService() {
        String sql = "SELECT MADVSD, MALS, MASP, SL, TRANGTHAI, CREATED_AT FROM DICH_VU_DA_DUNG";
        ArrayList<UsedServiceClass> usedServices = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UsedServiceClass usedService = new UsedServiceClass();
                usedService.setMaDVSD(rs.getString("MADVSD"));
                usedService.setMaLS(rs.getString("MALS"));
                usedService.setMaSP(rs.getString("MASP"));
                usedService.setSoLuong(rs.getInt("SL"));
                usedService.setStatus(rs.getString("TRANGTHAI"));
                usedService.setCreatedat(rs.getTimestamp("CREATED_AT"));

                usedServices.add(usedService);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching UsedService records: " + e.getMessage());
        }

        return usedServices;
    }
//   SelectUService: Retrieve all records from DICH_VU_DA_DUNG
    public static ArrayList<UsedServiceClass> SelectUServiceTest(Connection conn) {
        String sql = "SELECT MADVSD, MALS, MASP, SL, TRANGTHAI, CREATED_AT FROM DICH_VU_DA_DUNG";
        ArrayList<UsedServiceClass> usedServices = new ArrayList<>();

        try (
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UsedServiceClass usedService = new UsedServiceClass();
                usedService.setMaDVSD(rs.getString("MADVSD"));
                usedService.setMaLS(rs.getString("MALS"));
                usedService.setMaSP(rs.getString("MASP"));
                usedService.setSoLuong(rs.getInt("SL"));
                usedService.setStatus(rs.getString("TRANGTHAI"));
                usedService.setCreatedat(rs.getTimestamp("CREATED_AT"));

                usedServices.add(usedService);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching UsedService records: " + e.getMessage());
        }

        return usedServices;
    }
    // SelectUServiceByID: Retrieve a record from DICH_VU_DA_DUNG by MADVSD
    public static UsedServiceClass SelectUServiceByID(String maLS) {
        String sql = "SELECT MADVSD, MALS, MASP, SL, TRANGTHAI, CREATED_AT FROM DICH_VU_DA_DUNG WHERE MALS = ?";
        UsedServiceClass usedService = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usedService = new UsedServiceClass();
                usedService.setMaDVSD(rs.getString("MADVSD"));
                usedService.setMaLS(rs.getString("MALS"));
                usedService.setMaSP(rs.getString("MASP"));
                usedService.setSoLuong(rs.getInt("SL"));
                usedService.setStatus(rs.getString("TRANGTHAI"));
                usedService.setCreatedat(rs.getTimestamp("CREATED_AT"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching UsedService record by ID: " + e.getMessage());
        }

        return usedService;
    }

    // CheckUService: Check if a record with MALS has STATUS "Non-serviced" or "Serviced"
    public static int CheckUService(String maLS) {
        String sql = "SELECT TRANGTHAI FROM DICH_VU_DA_DUNG WHERE MALS = ?";
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("TRANGTHAI");
                if ("Non-serviced".equalsIgnoreCase(status)) {
                    return 1; // Non-serviced
                } else if ("Serviced".equalsIgnoreCase(status)) {
                    return 0; // Serviced
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking UsedService status: " + e.getMessage());
        }

        return -1; // Return -1 if no record is found or an error occurs
    }
    // Function to update the STATUS of a record in DICH_VU_DA_DUNG to "Serviced" by MADVSD
    public static boolean CompleteUService(String maDVSD) {
        String sql = "UPDATE DICH_VU_DA_DUNG SET TRANGTHAI = 'Serviced' WHERE MADVSD = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maDVSD);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ UsedService record with MADVSD " + maDVSD + " marked as 'Serviced' successfully!");
                return true;
            } else {
                System.out.println("⚠️ No UsedService record found with MADVSD: " + maDVSD);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating UsedService record to 'Serviced': " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }
    public static boolean CompleteUServiceTest(Connection conn,String maDVSD) {
        String sql = "UPDATE DICH_VU_DA_DUNG SET TRANGTHAI = 'Serviced' WHERE MADVSD = ? AND IS_DELETE = 0";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maDVSD);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ UsedService record with MADVSD " + maDVSD + " marked as 'Serviced' successfully!");
                return true;
            } else {
                System.out.println("⚠️ No UsedService record found with MADVSD: " + maDVSD);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating UsedService record to 'Serviced': " + e.getMessage());
        }
        return false; // Return false if the record was not updated successfully
    }
    // Function to execute the USE_SERVICE procedure
    public static boolean ExecuteUseServiceProcedure(String maLS, String maSP, int sl) {
        String sql = "{CALL USE_SERVICE(?, ?, ?)}";

        try ( Connection conn = DBConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            // Set procedure parameters
            stmt.setString(1, maLS);
            stmt.setString(2, maSP);
            stmt.setInt(3, sl);

            // Execute the procedure
            stmt.execute();
            System.out.println("✅ USE_SERVICE procedure executed successfully for MALS: " + maLS + ", MASP: " + maSP + ", SL: " + sl);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error executing USE_SERVICE procedure: " + e.getMessage());
        }
        return false; // Indicate failure
    }
    public static boolean ExecuteUseServiceProcedureTest(Connection conn,String maLS, String maSP, int sl) {
        String sql = "{CALL USE_SERVICE(?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {

            // Set procedure parameters
            stmt.setString(1, maLS);
            stmt.setString(2, maSP);
            stmt.setInt(3, sl);

            // Execute the procedure
            stmt.execute();
            System.out.println("✅ USE_SERVICE procedure executed successfully for MALS: " + maLS + ", MASP: " + maSP + ", SL: " + sl);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error executing USE_SERVICE procedure: " + e.getMessage());
        }
        return false; // Indicate failure
    }
    public static ArrayList<UsedServiceClass> SelectArrayUServiceByID(String maLS) {
        String sql = "SELECT MADVSD, MALS, MASP, SL, TRANGTHAI, CREATED_AT FROM DICH_VU_DA_DUNG WHERE MALS = ?";
        ArrayList<UsedServiceClass> usedServices = new ArrayList<>();
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, maLS);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                UsedServiceClass usedService = new UsedServiceClass();
                usedService.setMaDVSD(rs.getString("MADVSD"));
                usedService.setMaLS(rs.getString("MALS"));
                usedService.setMaSP(rs.getString("MASP"));
                usedService.setSoLuong(rs.getInt("SL"));
                usedService.setStatus(rs.getString("TRANGTHAI"));
                usedService.setCreatedat(rs.getTimestamp("CREATED_AT"));

    
                usedServices.add(usedService);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching UsedService records by ID: " + e.getMessage());
        }
    
        return usedServices;
    }
    public static int CheckUServiceRemain() {
        String sql = "SELECT COUNT(DVDD.MADVSD) FROM DICH_VU_DA_DUNG dvdd WHERE DVDD.TRANGTHAI = 'Non-serviced'";
        int count = 0;
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking UsedService status: " + e.getMessage());
        }

        return count; // Return -1 if no record is found or an error occurs
    }
    public static int CheckUServiceRemainTest(Connection conn) {
        String sql = "SELECT COUNT(DVDD.MADVSD) FROM DICH_VU_DA_DUNG dvdd WHERE DVDD.TRANGTHAI = 'Non-serviced'";
        int count = 0;
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking UsedService status: " + e.getMessage());
        }

        return count; // Return -1 if no record is found or an error occurs
    }
}