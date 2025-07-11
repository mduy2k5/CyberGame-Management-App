package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.CustomerType;

public class CustomerTypeClass implements UpdatableEntity {
    private String maHKH;
    private String tenHang;
    private double tyLeNap;

    // Constructor
    public CustomerTypeClass() {}

    public CustomerTypeClass(String maHKH, String tenHang, double tyLeNap) {
        this.maHKH = maHKH;
        this.tenHang = tenHang;
        this.tyLeNap = tyLeNap;
    }

    // Getters and Setters
    public String getMaHKH() {
        return maHKH;
    }

    public void setMaHKH(String maHKH) {
        this.maHKH = maHKH;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public double getTyLeNap() {
        return tyLeNap;
    }

    public void setTyLeNap(double tyLeNap) {
        this.tyLeNap = tyLeNap;
    }
    @Override
    public boolean update() {
        return CustomerType.UpdateCType(maHKH, tenHang, tyLeNap);
    }
    @Override
    public boolean insert() {
        return CustomerType.AddCType(tenHang, tyLeNap);
    }
}
