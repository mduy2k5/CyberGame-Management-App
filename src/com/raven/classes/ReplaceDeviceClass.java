package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.ReplaceDevice;

public class ReplaceDeviceClass implements UpdatableEntity {
    private String maTTTB;       // MATTTB
    private String maTB;         // MATB
    private String maTBThayThe;  // MATBTHAYTHE
    private String maNV;         // MANV
    private String trangThai;    // TRANGTHAI
    private int isDelete;        // IS_DELETE
    private Date createdAt;      // CREATED_AT

    // Constructor
    public ReplaceDeviceClass(String maTTTB, String maTB, String maTBThayThe, String maNV, String trangThai, int isDelete, Date createdAt) {
        this.maTTTB = maTTTB;
        this.maTB = maTB;
        this.maTBThayThe = maTBThayThe;
        this.maNV = maNV;
        this.trangThai = trangThai;
        this.isDelete = isDelete;
        this.createdAt = createdAt;
    }

    // Default Constructor
    public ReplaceDeviceClass() {}

    // Getters and Setters
    public String getMaTTTB() {
        return maTTTB;
    }

    public void setMaTTTB(String maTTTB) {
        this.maTTTB = maTTTB;
    }

    public String getMaTB() {
        return maTB;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public String getMaTBThayThe() {
        return maTBThayThe;
    }

    public void setMaTBThayThe(String maTBThayThe) {
        this.maTBThayThe = maTBThayThe;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // toString Method
    @Override
    public String toString() {
        return "ThayTheThietBiClass{" +
                "maTTTB='" + maTTTB + '\'' +
                ", maTB='" + maTB + '\'' +
                ", maTBThayThe='" + maTBThayThe + '\'' +
                ", maNV='" + maNV + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", isDelete=" + isDelete +
                ", createdAt=" + createdAt +
                '}';
    }
    @Override
    public boolean update() {
        return false;
    }
    @Override
    public boolean insert() {
        return ReplaceDevice.AddRDeviceByCus(maTB);
    }
}