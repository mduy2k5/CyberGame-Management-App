package com.raven.dbfunction;
import com.raven.classes.ComputerClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Computer {

    // Helper function to check if MALKV exists
    public static boolean isAreaExist(String maKV) {
        String sql = "SELECT COUNT(*) FROM KHU_VUC WHERE MAKV = ? AND IS_DELETE = 0";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if MAKV exists
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking area existence: " + e.getMessage());
        }

        return false; // Return false if MAKV does not exist
    }

    public static boolean AddComputer(String RAM, String ROM, String CPU, 
    String VGA, int SOMAY, String TRANGTHAI, String MAKV) {

        String sql = "INSERT INTO PC (RAM, ROM, CPU, VGA, SOMAY, TRANGTHAI, MAKV) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            Class.forName("oracle.jdbc.OracleDriver");

            stmt.setString(1, RAM);
            stmt.setString(2, ROM);
            stmt.setString(3, CPU);
            stmt.setString(4, VGA);
            stmt.setInt(5, SOMAY);
            stmt.setString(6, TRANGTHAI);
            stmt.setString(7, MAKV);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Đăng ký máy tính thành công!");
                return true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Not found");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }
    // Function to update computer information
    public static boolean UpdateComputer(String mapc, String ram, String rom, String cpu, String vga, int soMay, String trangThai, String maKV) {
        if (!isAreaExist(maKV)) {
            System.err.println("❌ Error: Area with MAKV " + maKV + " does not exist.");
            return false;
        }

        String sql = "UPDATE PC SET RAM = ?, ROM = ?, CPU = ?, VGA = ?, SOMAY = ?, TRANGTHAI = ?, MAKV = ? WHERE MAPC = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ram);
            stmt.setString(2, rom);
            stmt.setString(3, cpu);
            stmt.setString(4, vga);
            stmt.setInt(5, soMay);
            stmt.setString(6, trangThai);
            stmt.setString(7, maKV);
            stmt.setString(8, mapc);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Computer updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating computer: " + e.getMessage());
        }
        return false; // Return false if the computer was not updated successfully
    }

    // Function to delete a computer by setting IS_DELETE to 1
    public static boolean DeleteComputer(String mapc) {
        String sql = "UPDATE PC SET IS_DELETE = 1 WHERE MAPC = ?";

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mapc);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Computer deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error deleting computer: " + e.getMessage());
        }
        return false;
    }

    // Function to select all computers and return an ArrayList of ComputerClass
    public static ArrayList<ComputerClass> SelectComputer() {
        String sql = "SELECT MAPC, RAM, ROM, CPU, VGA, SOMAY, TRANGTHAI, MAKV FROM PC WHERE IS_DELETE = 0";
        ArrayList<ComputerClass> computers = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ComputerClass computer = new ComputerClass();
                computer.setMaPC(rs.getString("MAPC"));
                computer.setRam(rs.getString("RAM"));
                computer.setRom(rs.getString("ROM"));
                computer.setCpu(rs.getString("CPU"));
                computer.setVga(rs.getString("VGA"));
                computer.setSoMay(rs.getInt("SOMAY"));
                computer.setTrangThai(rs.getString("TRANGTHAI"));
                computer.setMaKv(rs.getString("MAKV"));

                computers.add(computer);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching computers: " + e.getMessage());
        }

        return computers;
    }

    // Function to select a computer by MAPC and return a ComputerClass object
    public static ComputerClass SelectComputerById(String mapc) {
        String sql = "SELECT MAPC, RAM, ROM, CPU, VGA, SOMAY, TRANGTHAI, MAKV FROM QUANLYTIEMCYCBERGAME.PC WHERE MAPC = ? AND IS_DELETE = 0";
        ComputerClass computer = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mapc);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                computer = new ComputerClass();
                computer.setMaPC(rs.getString("MAPC"));
                computer.setRam(rs.getString("RAM"));
                computer.setRom(rs.getString("ROM"));
                computer.setCpu(rs.getString("CPU"));
                computer.setVga(rs.getString("VGA"));
                computer.setSoMay(rs.getInt("SOMAY"));
                computer.setTrangThai(rs.getString("TRANGTHAI"));
                computer.setMaKv(rs.getString("MAKV"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching computer by ID: " + e.getMessage());
        }

        return computer;
    }
    public static ComputerClass SelectComputerByIdTest(Connection conn, String mapc) {
        String sql = "SELECT MAPC, RAM, ROM, CPU, VGA, SOMAY, TRANGTHAI, MAKV FROM QUANLYTIEMCYCBERGAME.PC WHERE MAPC = ? AND IS_DELETE = 0";
        ComputerClass computer = null;

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mapc);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                computer = new ComputerClass();
                computer.setMaPC(rs.getString("MAPC"));
                computer.setRam(rs.getString("RAM"));
                computer.setRom(rs.getString("ROM"));
                computer.setCpu(rs.getString("CPU"));
                computer.setVga(rs.getString("VGA"));
                computer.setSoMay(rs.getInt("SOMAY"));
                computer.setTrangThai(rs.getString("TRANGTHAI"));
                computer.setMaKv(rs.getString("MAKV"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching computer by ID: " + e.getMessage());
        }

        return computer;
    }
    public static ArrayList<ComputerClass> SelectComputerByArea(String maKV) {
        String sql = "SELECT MAPC, RAM, ROM, CPU, VGA, SOMAY, TRANGTHAI, MAKV FROM PC WHERE MAKV = ? AND IS_DELETE = 0";
        ArrayList<ComputerClass> computers = new ArrayList<>();
    
        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, maKV);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                ComputerClass computer = new ComputerClass();
                computer.setMaPC(rs.getString("MAPC"));
                computer.setRam(rs.getString("RAM"));
                computer.setRom(rs.getString("ROM"));
                computer.setCpu(rs.getString("CPU"));
                computer.setVga(rs.getString("VGA"));
                computer.setSoMay(rs.getInt("SOMAY"));
                computer.setTrangThai(rs.getString("TRANGTHAI"));
                computer.setMaKv(rs.getString("MAKV"));
    
                computers.add(computer);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching computers by area: " + e.getMessage());
        }
    
        return computers;
    }
}
