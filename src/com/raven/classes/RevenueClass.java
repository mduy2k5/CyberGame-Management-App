package com.raven.classes;

import com.raven.dbfunction.Revenue;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

public class RevenueClass implements UpdatableEntity {
    private String maDT; // Mã doanh thu
    private double tongDoanhThu; // Tổng doanh thu
    private double tongdoanhthudv;
    private double tongsodv;
    private double tongthoigian;
    private boolean isDelete; // Trạng thái xóa
    private String noidung;
    private Date createdAt;      // CREATED_AT

    // Constructor
    public RevenueClass() {}

    public RevenueClass(String maDT, double tongDoanhThu, boolean isDelete) {
        this.maDT = maDT;
        this.tongDoanhThu = tongDoanhThu;
        this.isDelete = isDelete;
    }
    public double getTongdoanhthudv(){
        return tongdoanhthudv;
    }
    public void setTongdoanhthudv(double tongdoanhthudv){
        this.tongdoanhthudv = tongdoanhthudv;
    }
    public double getTongsodv(){
        return tongsodv;
    }
    public void setTongsodv(double tongsodv){
        this.tongsodv = tongsodv;
    }
    public double getTongthoigian(){
        return tongthoigian;
    }
    public void setTongthoigian(double tongthoigian){
        this.tongthoigian = tongthoigian;
    }

    public void setNoiDung(String noidung) {
        this.noidung = noidung;
    }
    public String getNoiDung() {
        return noidung;
    }

    // Getters and Setters
    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
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
        return "RevenueClass{" +
                "maDT='" + maDT + '\'' +
                ", tongDoanhThu=" + tongDoanhThu +
                ", isDelete=" + isDelete +
                '}';
    }
    @Override
    public boolean update() {
        return Revenue.UpdateRevenue(maDT,noidung);
    }
    @Override
    public boolean insert() {
        return Revenue.AddRevenue(noidung);
    }
}
