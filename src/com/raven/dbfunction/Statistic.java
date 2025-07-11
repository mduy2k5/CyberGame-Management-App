package com.raven.dbfunction;
import java.sql.*;
import java.util.ArrayList;

public class Statistic {
    public static ArrayList<String> thongKeThietBi() {
        ArrayList<String> result = new ArrayList<>();
        String sql = """
            SELECT 
                COUNT(*) AS TONG_THIET_BI,
                COUNT(CASE WHEN TRANGTHAI = 'HONG' THEN 1 END) AS SO_HONG,
                COUNT(CASE WHEN TRANGTHAI = 'HOATDONG' THEN 1 END) AS SO_DANG_SU_DUNG
            FROM THIET_BI
            WHERE IS_DELETE = 0
        """;
        return executeQuery(sql, result, "Tổng TB", "TB hỏng", "Đang dùng");
    }

    public static ArrayList<String> thongKeKhachHang() {
        ArrayList<String> result = new ArrayList<>();
        String sql = """
            SELECT COUNT(*),
                   COUNT(CASE WHEN TRANGTHAI = 'Online' THEN 1 END),
                   COUNT(DISTINCT MAHKH)
            FROM KHACH_HANG WHERE IS_DELETE = 0
        """;
        return executeQuery(sql, result, "Tổng KH", "KH hoạt động", "Số loại hạng");
    }

    public static ArrayList<String> thongKeLoaiKhachHang() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), ROUND(AVG(TYLENAP), 2), SUM(TYLENAP) FROM HANG_KHACH_HANG WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số loại KH", "Tỷ lệ TB", "Tổng tỷ lệ");
    }

    public static ArrayList<String> thongKeNapTien() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(SOTIEN), COUNT(DISTINCT MAKH) FROM GIAO_DICH WHERE IS_DELETE = 0 AND TRANGTHAI = 'Success'";
        return executeQuery(sql, result, "Số GD", "Tổng tiền", "Số KH");
    }

    public static ArrayList<String> thongKeTaiChinh() {
        ArrayList<String> result = new ArrayList<>();
        String sql = """
            SELECT 
              (SELECT SUM(SOTIEN) FROM GIAO_DICH WHERE IS_DELETE = 0 AND TRANGTHAI = 'Success'),
              (SELECT SUM(SOTIEN) FROM CHI_PHI WHERE IS_DELETE = 0),
              ((SELECT NVL(SUM(SOTIEN),0) FROM GIAO_DICH WHERE IS_DELETE = 0 AND TRANGTHAI = 'Success') - 
               (SELECT NVL(SUM(SOTIEN),0) FROM CHI_PHI WHERE IS_DELETE = 0))
            FROM DUAL
        """;
        return executeQuery(sql, result, "Tổng thu", "Tổng chi", "Lợi nhuận");
    }

    public static ArrayList<String> thongKeDoanhThu() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(TONGDOANHTHU), ROUND(AVG(TONGDOANHTHU), 2) FROM DOANH_THU WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Bản ghi", "Tổng DT", "TB DT");
    }

    public static ArrayList<String> thongKeKhuVuc() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(SOLUONGMAYKV), COUNT(CASE WHEN TRANGTHAI = 'HOATDONG' THEN 1 END) FROM KHU_VUC WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số KV", "Tổng máy", "KV hoạt động");
    }

    public static ArrayList<String> thongKeLoaiKhuVuc() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), ROUND(AVG(GIATIEN), 2), SUM(SOLUONGMAY) FROM LOAI_KHU_VUC WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số loại KV", "Giá TB", "Tổng máy");
    }

    public static ArrayList<String> thongKeNhanVien() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), COUNT(DISTINCT MALNV), COUNT(CASE WHEN NGVL >= ADD_MONTHS(SYSDATE, -1) THEN 1 END) FROM NHAN_VIEN WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Tổng NV", "Loại NV", "Mới tháng này");
    }

    public static ArrayList<String> thongKeCaLam() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(SOGIOLAM), SUM(THOIGIANTANGCA) FROM CA_LAM WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số ca", "Tổng giờ", "Tăng ca");
    }

    public static ArrayList<String> thongKeSoNgayPhep() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT SUM(TONGSONGAYPHEP), SUM(NGAYPHEPDADUNG), SUM(SONGAYNGHICONLAI) FROM SO_NGAY_PHEP WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Tổng phép", "Đã dùng", "Còn lại");
    }

    public static ArrayList<String> thongKeNghiPhep() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), COUNT(CASE WHEN TRANGTHAINGHI = 'Received' THEN 1 END), COUNT(DISTINCT LOAINGHIPHEP) FROM NGHI_PHEP WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Tổng đơn", "Chờ duyệt", "Loại nghỉ");
    }

    public static ArrayList<String> thongKeChiPhi() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(SOTIEN), COUNT(DISTINCT HINHTHUC) FROM CHI_PHI WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số chi", "Tổng tiền", "Hình thức");
    }

    public static ArrayList<String> thongKeLoaiNhanVien() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), ROUND(AVG(MUCLUONG), 2), SUM(MUCLUONG) FROM LOAI_NHAN_VIEN WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số loại", "Lương TB", "Tổng lương");
    }

    public static ArrayList<String> thongKeKhuyenMai() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), COUNT(CASE WHEN NGKT >= SYSDATE THEN 1 END), ROUND(AVG(CHIETKHAU), 2) FROM CHUONG_TRINH_KHUYEN_MAI WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Tổng KM", "Đang diễn ra", "CK TB");
    }

    public static ArrayList<String> thongKeSanPham() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(SOLUONGTK), ROUND(AVG(DONGIABQ), 2) FROM SAN_PHAM WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số SP", "Tồn kho", "Giá TB");
    }

    public static ArrayList<String> thongKeDichVuDaDung() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(SL * S.DONGIABQ), COUNT(DISTINCT MALS) FROM DICH_VU_DA_DUNG D JOIN SAN_PHAM S ON D.MASP = S.MASP WHERE D.IS_DELETE = 0";
        return executeQuery(sql, result, "Số lượt", "Lợi nhuận", "Số người dùng");
    }

    public static ArrayList<String> thongKeSuKien() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), COUNT(DISTINCT MAKV), COUNT(DISTINCT MANV) FROM SU_KIEN WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Sự kiện", "Khu vực", "Nhân viên");
    }

    public static ArrayList<String> thongKeNhapHang() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), SUM(TRIGIA), COUNT(DISTINCT MANCC) FROM NHAP_HANG WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Số phiếu", "Trị giá", "Số NCC");
    }

    public static ArrayList<String> thongKeNhaCungCap() {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT COUNT(*), COUNT(DISTINCT DIACHI), COUNT(CASE WHEN WEBSITE IS NOT NULL THEN 1 END) FROM NHA_CUNG_CAP WHERE IS_DELETE = 0";
        return executeQuery(sql, result, "Tổng NCC", "Địa chỉ khác nhau", "Có website");
    }

    private static ArrayList<String> executeQuery(String sql, ArrayList<String> result, String label1, String label2, String label3) {
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            result.add(label1);
            result.add(label2);
            result.add(label3);
            if (rs.next()) {
                result.add(rs.getString(1));
                result.add(rs.getString(2));
                result.add(rs.getString(3));
            }
        } catch (SQLException e) {
            result.add("Lỗi: " + e.getMessage());
        }
        return result;
    }
} 
