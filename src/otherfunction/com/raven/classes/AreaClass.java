package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.Area;

public class AreaClass implements UpdatableEntity {
    private String maKV;
    private String tenKV;
    private String maLKV;
    private String trangThai;
    private int soTang;
    private int soLuongMayKV;
    private boolean isDelete;

    // Constructor
    public AreaClass() {}

    public AreaClass(String maKV, String tenKV, String maLKV, String trangThai, int soTang, int soLuongMayKV, boolean isDelete) {
        this.maKV = maKV;
        this.tenKV = tenKV;
        this.maLKV = maLKV;
        this.trangThai = trangThai;
        this.soTang = soTang;
        this.soLuongMayKV = soLuongMayKV;
        this.isDelete = isDelete;
    }

    // Getters and Setters
    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getTenKV() {
        return tenKV;
    }

    public void setTenKV(String tenKV) {
        this.tenKV = tenKV;
    }

    public String getMaLKV() {
        return maLKV;
    }

    public void setMaLKV(String maLKV) {
        this.maLKV = maLKV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoTang() {
        return soTang;
    }

    public void setSoTang(int soTang) {
        this.soTang = soTang;
    }

    public int getSoLuongMayKV() {
        return soLuongMayKV;
    }

    public void setSoLuongMayKV(int soLuongMayKV) {
        this.soLuongMayKV = soLuongMayKV;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    @Override
    public boolean update() {
        return Area.UpdateArea(maKV,tenKV,maLKV,trangThai,soTang,soLuongMayKV);
    }
    @Override
    public boolean insert() {
        return Area.AddArea(tenKV, maLKV, trangThai, soTang, soLuongMayKV);
    }
}
