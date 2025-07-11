package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.sql.Timestamp;
import java.util.Date;

public class ExchangeGDetailsClass implements UpdatableEntity {
    private String maDQ; // Exchange ID
    private String maKH;   // Customer ID
    private String maQT;   // Gift ID
    private Timestamp ngayDoi; // Exchange Date
    private int soLuong;   // Quantity
    private Date create_at;
    private String trangThai;
    public ExchangeGDetailsClass() {
        // Default constructor
    }
    // Constructor
    public ExchangeGDetailsClass(String maDQ, String maKH, String maQT, Timestamp ngayDoi, int soLuong, Date create_at, String trangThai) {
        this.maDQ = maDQ;
        this.maKH = maKH;
        this.maQT = maQT;
        this.ngayDoi = ngayDoi;
        this.soLuong = soLuong;
        this.create_at = create_at;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getMaDQ() {
        return maDQ;
    }

    public void setMaDQ(String maLSDQ) {
        this.maDQ = maLSDQ;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaQT() {
        return maQT;
    }

    public void setMaQT(String maQT) {
        this.maQT = maQT;
    }

    public Timestamp getNgayDoi() {
        return ngayDoi;
    }

    public void setNgayDoi(Timestamp ngayDoi) {
        this.ngayDoi = ngayDoi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public Date getCreatedat() {
        return create_at;
    }
    public void setCreatedat(Date create_at) {
        this.create_at = create_at;
    }
    public String getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    // toString method for debugging
    @Override
    public String toString() {
        return "ExchangeGDetailsClass{" +
                "maLSDQ='" + maDQ + '\'' +
                ", maKH='" + maKH + '\'' +
                ", maQT='" + maQT + '\'' +
                ", ngayDoi=" + ngayDoi +
                ", soLuong=" + soLuong +
                '}';
    }
    @Override
    public boolean update() {
        return false;
    }
    @Override
    public boolean insert() {
        return false;
    }
}