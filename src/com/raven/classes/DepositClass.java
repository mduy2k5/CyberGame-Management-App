package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.Depositer;

public class DepositClass implements UpdatableEntity {
    private String maGD;        // Transaction ID
    private String maKH;        // Customer ID
    private Date thoiGian;      // Transaction time
    private String hinhThuc;    // Transaction method
    private double soTien;      // Amount
    private String trangThai;   // Status
    private boolean isDelete;   // Soft delete flag
    private Date createdAt;     // Creation timestamp

    // Constructor
    public DepositClass(String maGD, String maKH, Date thoiGian, String hinhThuc, double soTien, String trangThai, boolean isDelete, Date createdAt) {
        this.maGD = maGD;
        this.maKH = maKH;
        this.thoiGian = thoiGian;
        this.hinhThuc = hinhThuc;
        this.soTien = soTien;
        this.trangThai = trangThai;
        this.isDelete = isDelete;
        this.createdAt = createdAt;
    }

    // Default constructor
    public DepositClass() {}

    // Getters and Setters
    public String getMaGD() {
        return maGD;
    }

    public void setMaGD(String maGD) {
        this.maGD = maGD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "GiaoDichClass{" +
                "maGD='" + maGD + '\'' +
                ", maKH='" + maKH + '\'' +
                ", thoiGian=" + thoiGian +
                ", hinhThuc='" + hinhThuc + '\'' +
                ", soTien=" + soTien +
                ", trangThai='" + trangThai + '\'' +
                ", isDelete=" + isDelete +
                ", createdAt=" + createdAt +
                '}';
    }
    @Override
    public boolean update() {
        return Depositer.Deposit(maKH, (int) soTien, hinhThuc, trangThai);
    }
    @Override
    public boolean insert() {
        return false;
    }
}