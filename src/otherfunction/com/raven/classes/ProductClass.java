package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.Product;

public class ProductClass implements UpdatableEntity {
    private String maSP;
    private String tenSP;
    private String dvt;
    private String loaiSP;
    private int soLuongTK;
    private int soDiemTichLuy;
    private double donGiaBQ;
    private String url;

    // Constructor
    public ProductClass() {}

    public ProductClass(String maSP, String tenSP, String dvt, String loaiSP, int soLuongTK, int soDiemTichLuy, double donGiaBQ,String url) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dvt = dvt;
        this.loaiSP = loaiSP;
        this.soLuongTK = soLuongTK;
        this.soDiemTichLuy = soDiemTichLuy;
        this.donGiaBQ = donGiaBQ;
        this.url = url;
    }

    // Getters and Setters
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getSoLuongTK() {
        return soLuongTK;
    }

    public void setSoLuongTK(int soLuongTK) {
        this.soLuongTK = soLuongTK;
    }

    public int getSoDiemTichLuy() {
        return soDiemTichLuy;
    }

    public void setSoDiemTichLuy(int soDiemTichLuy) {
        this.soDiemTichLuy = soDiemTichLuy;
    }

    public double getDonGiaBQ() {
        return donGiaBQ;
    }

    public void setDonGiaBQ(double donGiaBQ) {
        this.donGiaBQ = donGiaBQ;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public boolean update() {
        return Product.UpdateProduct(maSP, tenSP, dvt, loaiSP, soLuongTK, soDiemTichLuy, donGiaBQ, url);
    }
    @Override
    public boolean insert() {
        return Product.AddProduct(tenSP, dvt, loaiSP, soLuongTK, soDiemTichLuy, donGiaBQ, url);
    }
}
