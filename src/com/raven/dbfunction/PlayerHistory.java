package com.raven.dbfunction;

import com.raven.classes.ComputerClass;
import com.raven.classes.PlayerHistoryClass;
import java.sql.*;
import java.util.ArrayList;

public class PlayerHistory {


    // SelectPHistory: Retrieve all records from LICH_SU_CHOI and return as ArrayList<PlayerHistoryClass>
    public static ArrayList<PlayerHistoryClass> SelectPHistory() {
        String sql = "SELECT MALS, MAKH, NGAYBD, NGAYKT, TONGTHOIGIAN, LOAITHUE, TRANGTHAI, IS_DELETE FROM LICH_SU_CHOI WHERE IS_DELETE = 0";
        ArrayList<PlayerHistoryClass> playerHistories = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PlayerHistoryClass history = new PlayerHistoryClass();
                history.setMaLS(rs.getString("MALS"));
                history.setMaKH(rs.getString("MAKH"));
                history.setNgayBD(rs.getDate("NGAYBD"));
                history.setNgayKT(rs.getDate("NGAYKT"));
                history.setStatus(rs.getString("TRANGTHAI"));

                playerHistories.add(history);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching PlayerHistory records: " + e.getMessage());
        }

        return playerHistories;
    }
    
    public static ArrayList<PlayerHistoryClass> SelectOnlinePHistory() {
        String sql = "SELECT MALS, MAKH, NGAYBD, NGAYKT, TRANGTHAI, IS_DELETE FROM LICH_SU_CHOI WHERE IS_DELETE = 0 AND TRANGTHAI = 'Online'";
        ArrayList<PlayerHistoryClass> playerHistories = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PlayerHistoryClass history = new PlayerHistoryClass();
                history.setMaLS(rs.getString("MALS"));
                history.setMaKH(rs.getString("MAKH"));
                history.setNgayBD(rs.getDate("NGAYBD"));
                history.setNgayKT(rs.getDate("NGAYKT"));
                history.setStatus(rs.getString("TRANGTHAI"));

                playerHistories.add(history);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching PlayerHistory records: " + e.getMessage());
        }

        return playerHistories;
    }
    
    
    // SelectPHistoryByID: Retrieve a record from LICH_SU_CHOI by MALS
    public static PlayerHistoryClass SelectPHistoryByID(String maLS) {
        String sql = "SELECT MALS, MAKH, NGAYBD, NGAYKT, TRANGTHAI, IS_DELETE FROM LICH_SU_CHOI WHERE MALS = ? AND IS_DELETE = 0";
        PlayerHistoryClass history = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maLS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                history = new PlayerHistoryClass();
                history.setMaLS(rs.getString("MALS"));
                history.setMaKH(rs.getString("MAKH"));
                history.setNgayBD(rs.getDate("NGAYBD"));
                history.setNgayKT(rs.getDate("NGAYKT"));
                history.setStatus(rs.getString("TRANGTHAI"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching PlayerHistory record by ID: " + e.getMessage());
        }

        return history;
    }

    // Function to return MALS where STATUS is "Online"
    public static ArrayList<String> SelectOnlinePlayerHistoryIDs() {
        String sql = "SELECT MALS FROM LICH_SU_CHOI WHERE TRANGTHAI = 'Online' AND IS_DELETE = 0";
        ArrayList<String> onlinePlayerHistoryIDs = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                onlinePlayerHistoryIDs.add(rs.getString("MALS"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching online PlayerHistory IDs: " + e.getMessage());
        }

        return onlinePlayerHistoryIDs;
    }
    // Function to check if a player (MAKH) has STATUS "Online"
    public static int CheckPHistoryByICusID(String maKH) {
        String sql = "SELECT TRANGTHAI FROM LICH_SU_CHOI WHERE MAKH = ? AND IS_DELETE = 0";
        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String status = rs.getString("TRANGTHAI");
                if ("Online".equalsIgnoreCase(status)) {
                    return 1; // Player is online
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking PlayerHistory by Customer ID: " + e.getMessage());
        }

        return 0; // Player is offline or no record found
    }
    // Function to check PlayerHistory by Customer ID (MAKH) and return PlayerHistoryClass
    public static PlayerHistoryClass SelectPHistoryByCusID(String maKH) {
        String sql = "SELECT MALS, MAKH, NGAYBD, NGAYKT, TRANGTHAI, IS_DELETE " +
                    "FROM LICH_SU_CHOI WHERE MAKH = ? AND IS_DELETE = 0 AND TRANGTHAI = 'Online'";
        PlayerHistoryClass history = null;

        try ( Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                history = new PlayerHistoryClass();
                history.setMaLS(rs.getString("MALS"));
                history.setMaKH(rs.getString("MAKH"));
                history.setNgayBD(rs.getDate("NGAYBD"));
                history.setNgayKT(rs.getDate("NGAYKT"));
                history.setStatus(rs.getString("TRANGTHAI"));
                history.setDelete(rs.getBoolean("IS_DELETE"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error checking PlayerHistory by Customer ID: " + e.getMessage());
        }

        return history; // Return the PlayerHistoryClass object or null if no record is found
    }
    // Function to call the CREATE_NEW_PLAYERTIME procedure
    public static boolean CreateNewPlayerTime(String p_MAKH, String p_MAPC) {
        String sql = "{CALL CREATE_NEW_PLAYERTIME(?, ?)}";

        try ( Connection conn = DBConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            // Set procedure parameters
            stmt.setString(1, p_MAKH);
            stmt.setString(2, p_MAPC);

            // Execute the procedure
            stmt.execute();
            System.out.println("✅ CREATE_NEW_PLAYERTIME procedure executed successfully for MAKH: " + p_MAKH + ", MAPC: " + p_MAPC);
            return true; // Indicate success
        } catch (SQLException e) {
            System.err.println("❌ Error executing CREATE_NEW_PLAYERTIME procedure: " + e.getMessage());
            return false; // Indicate failure
        }
    }
    // Function to call the FINISH_PLAYERTIME procedure
    public static boolean FinishPlayerTime(String p_MALS) {
        String sql = "{CALL FINISH_PLAYERTIME(?)}";

        try ( Connection conn = DBConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            // Set procedure parameter
            stmt.setString(1, p_MALS);

            // Execute the procedure
            stmt.execute();
            System.out.println("✅ FINISH_PLAYERTIME procedure executed successfully for MALS: " + p_MALS);
            return true; // Indicate success
        } catch (SQLException e) {
            System.err.println("❌ Error executing FINISH_PLAYERTIME procedure: " + e.getMessage());
            return false; // Indicate failure
        }
    }
    public static ArrayList<PlayerHistoryClass> SelectAllPHistoryByCusID(String maKH) {
        String sql = "SELECT MALS, MAKH, NGAYBD, NGAYKT, TRANGTHAI, IS_DELETE " +
                    "FROM LICH_SU_CHOI WHERE MAKH = ? AND IS_DELETE = 0";
        ArrayList<PlayerHistoryClass> playerHistories = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PlayerHistoryClass history = new PlayerHistoryClass();
                history.setMaLS(rs.getString("MALS"));
                history.setMaKH(rs.getString("MAKH"));
                history.setNgayBD(rs.getDate("NGAYBD"));
                history.setNgayKT(rs.getDate("NGAYKT"));
                history.setStatus(rs.getString("TRANGTHAI"));

                playerHistories.add(history);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching PlayerHistory records: " + e.getMessage());
        }

        return playerHistories;
    }
    public static ComputerClass SelectComputerByPHId(String mals) {
        String sql = "SELECT pc.MAPC, RAM, ROM, CPU, VGA, SOMAY, pc.TRANGTHAI, MAKV FROM QUANLYTIEMCYCBERGAME.PC pc JOIN THUE_MAY tm ON PC.MAPC = tm.MAPC JOIN LICH_SU_CHOI lsc ON TM.MALS = lsc.MALS WHERE lsc.MALS = ? AND pc.IS_DELETE = 0 AND tm.IS_DELETE = 0 AND lsc.IS_DELETE = 0";
        ComputerClass computer = null;

        try ( Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mals);
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
}