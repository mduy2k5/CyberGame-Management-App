package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.EmployeeWType;

public class EmployeeTypeClass implements UpdatableEntity {
    private String maLoaiNV;
    private String tenLoaiNV;
    private int mucluong;

    // Constructor
    public EmployeeTypeClass() {}

    public EmployeeTypeClass(String maLoaiNV, String tenLoaiNV, int mucluong) {
        this.maLoaiNV = maLoaiNV;
        this.tenLoaiNV = tenLoaiNV;
        this.mucluong = mucluong;
    }

    // Getters and Setters
    public String getMaLoaiNV() {
        return maLoaiNV;
    }

    public void setMaLoaiNV(String maLoaiNV) {
        this.maLoaiNV = maLoaiNV;
    }

    public String getTenLoaiNV() {
        return tenLoaiNV;
    }

    public void setTenLoaiNV(String tenLoaiNV) {
        this.tenLoaiNV = tenLoaiNV;
    }

    public int getMucLuong() {
        return mucluong;
    }

    public void setMucLuong(int mucluong) {
        this.mucluong = mucluong;
    }
    @Override
    public boolean update() {
        return EmployeeWType.UpdateEWType(maLoaiNV, tenLoaiNV, mucluong); // Implement the update logic here
    }
    @Override
    public boolean insert() {
        return EmployeeWType.AddEWType(tenLoaiNV, mucluong);
    }
}
