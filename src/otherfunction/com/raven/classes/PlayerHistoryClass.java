package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.PlayerHistory;

public class PlayerHistoryClass implements UpdatableEntity {
    private String maLS; // Mã lịch sử chơi
    private String maKH; // Mã khách hàng
    private Date ngayBD; // Ngày bắt đầu
    private Date ngayKT; // Ngày kết thúc
    private int tongThoiGian; // Tổng thời gian chơi
    private String loaiThue; // Loại thuê
    private String status; // Trạng thái
    private boolean isDelete; // Trạng thái xóa

    // Constructor
    public PlayerHistoryClass() {}

    public PlayerHistoryClass(String maLS, String maKH, Date ngayBD, Date ngayKT, int tongThoiGian, String loaiThue, String status, boolean isDelete) {
        this.maLS = maLS;
        this.maKH = maKH;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.tongThoiGian = tongThoiGian;
        this.loaiThue = loaiThue;
        this.status = status;
        this.isDelete = isDelete;
    }

    // Getters and Setters
    public String getMaLS() {
        return maLS;
    }

    public void setMaLS(String maLS) {
        this.maLS = maLS;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getTongThoiGian() {
        return tongThoiGian;
    }

    public void setTongThoiGian(int tongThoiGian) {
        this.tongThoiGian = tongThoiGian;
    }

    public String getLoaiThue() {
        return loaiThue;
    }

    public void setLoaiThue(String loaiThue) {
        this.loaiThue = loaiThue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }


    // toString method for debugging
    @Override
    public String toString() {
        return "PlayerHistoryClass{" +
                "maLS='" + maLS + '\'' +
                ", maKH='" + maKH + '\'' +
                ", ngayBD=" + ngayBD +
                ", ngayKT=" + ngayKT +
                ", tongThoiGian=" + tongThoiGian +
                ", loaiThue='" + loaiThue + '\'' +
                ", status='" + status + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
    @Override
    public boolean update() {
        return false;
    }
    @Override
    public boolean insert() {
        return PlayerHistory.CreateNewPlayerTime(maKH, maLS);
    }
}