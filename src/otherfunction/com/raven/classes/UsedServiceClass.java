package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.UsedService;

public class UsedServiceClass implements UpdatableEntity {
    private String maDVSD; // Unique ID for the used service
    private String maLS;   // ID of the play history (MALS)
    private String maSP;   // ID of the product/service (MASP)
    private int soLuong;   // Quantity of the service used (SL)
    private String status; // Status of the service (e.g., "Serviced", "Non-serviced")
    private Date create_at; // Creation timestamp


    public UsedServiceClass() {}


    public UsedServiceClass(String maDVSD, String maLS, String maSP, int soLuong, String status) {
        this.maDVSD = maDVSD;
        this.maLS = maLS;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.status = status;
    }

    // Getters and Setters

    public String getMaDVSD() {
        return maDVSD;
    }

    public void setMaDVSD(String maDVSD) {
        this.maDVSD = maDVSD;
    }

    public String getMaLS() {
        return maLS;
    }

    public void setMaLS(String maLS) {
        this.maLS = maLS;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCreatedat() {
        return create_at;
    }
    public void setCreatedat(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "UsedServiceClass{" +
                "maDVSD='" + maDVSD + '\'' +
                ", maLS='" + maLS + '\'' +
                ", maSP='" + maSP + '\'' +
                ", soLuong=" + soLuong +
                ", status='" + status + '\'' +
                '}';
    }
    @Override
    public boolean update() {
        return UsedService.UpdateUService(maDVSD, maSP, soLuong, status);
    }
    @Override
    public boolean insert() {
        return UsedService.AddUService(maLS, maSP, soLuong, status);
    }
}