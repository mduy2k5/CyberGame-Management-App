package com.raven.classes;

import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

public class PaymentClass implements UpdatableEntity {
    private String maPhieu;       // MAPHIEU
    private String maKH;          // MAKH
    private int tongSoDV;         // TONGSODV
    private String trangThai;     // TRANGTHAI
    private double tongTien;      // TONGTIEN
    private Date createdAt;       // CREATED_AT
    private double tiendv;
    private double tongtiendv;
    private double tongthoigian;
    // Getters and Setters
    public double getTiendv(){
        return tiendv;
    }
    public void setTiendv(double tiendv){
        this.tiendv = tiendv;
    }
    public double getTongtiendv(){
        return tongtiendv;
    }
    public void setTongtiendv(double tdv){
        this.tongtiendv = tdv;
    }

    public double getTongtg(){
        return tongthoigian;
    }
    public void setTongtg(double tg){
        this.tongthoigian = tg;
    }

    public PaymentClass() {
        // Default constructor
    }
    // Constructor
    public PaymentClass(String maPhieu, String maKH, int tongSoDV, String trangThai, double tongTien, Date createdAt) {
        this.maPhieu = maPhieu;
        this.maKH = maKH;
        this.tongSoDV = tongSoDV;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getTongSoDV() {
        return tongSoDV;
    }

    public void setTongSoDV(int tongSoDV) {
        this.tongSoDV = tongSoDV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
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
        return "PaymentClass{" +
                "maPhieu='" + maPhieu + '\'' +
                ", maKH='" + maKH + '\'' +
                ", tongSoDV=" + tongSoDV +
                ", trangThai='" + trangThai + '\'' +
                ", tongTien=" + tongTien +
                ", createdAt=" + createdAt +
                '}';
    }
    @Override
    public boolean update() {
        return false;
    }
    @Override
    public boolean insert() {
        return false;
    }
}