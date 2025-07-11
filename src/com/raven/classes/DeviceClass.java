package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.Device;

public class DeviceClass implements UpdatableEntity {
    private String matb;
    private String tentb;
    private java.sql.Date ngaynhap;
    private String trangthai;
    private String loaitb; // New field for device type
    private Date createdAt;      // CREATED_AT

    // Getters and Setters
    public String getMatb() {
        return matb;
    }

    public void setMatb(String matb) {
        this.matb = matb;
    }

    public String getTentb() {
        return tentb;
    }

    public void setTentb(String tentb) {
        this.tentb = tentb;
    }

    public java.sql.Date getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(java.sql.Date ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    public  void setLoaitb(String loaitb) {
        this.loaitb = loaitb;
    }
    public String getLoaitb() {
        return loaitb;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public boolean update() {
        return Device.UpdateDevice(matb, tentb, trangthai, loaitb);
    }
    @Override
    public boolean insert() {
        return false;
    }
}