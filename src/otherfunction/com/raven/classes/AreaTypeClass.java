package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.AreaType;

public class AreaTypeClass implements UpdatableEntity {
    private String maLKV;
    private String tenLoai;
    private double giaTien;
    private int soLuongMay;

    // Constructor
    public AreaTypeClass() {}

    public AreaTypeClass(String maLKV, String tenLoai, double giaTien, int soLuongMay) {
        this.maLKV = maLKV;
        this.tenLoai = tenLoai;
        this.giaTien = giaTien;
        this.soLuongMay = soLuongMay;
    }

    // Getters and Setters
    public String getMaLKV() {
        return maLKV;
    }

    public void setMaLKV(String maLKV) {
        this.maLKV = maLKV;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuongMay() {
        return soLuongMay;
    }

    public void setSoLuongMay(int soLuongMay) {
        this.soLuongMay = soLuongMay;
    }
    @Override
    public boolean update() {
        return AreaType.UpdateAType(maLKV, tenLoai, giaTien, soLuongMay);
    }
    @Override
    public boolean insert() {
        return AreaType.AddAType(tenLoai, giaTien, soLuongMay);
    }
}
