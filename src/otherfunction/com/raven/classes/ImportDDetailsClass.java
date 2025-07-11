package com.raven.classes;
import java.util.*;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.ImportDDetails;
public class ImportDDetailsClass implements UpdatableEntity {
    private String manh;
    private String matb;
    private double donGia;
    private double chiPhiKhac;
    private double cktm;
    private double vat;
    private Date createat;
    public Date getCreateat(){
        return createat;
    }
    public void setCrerateat(Date createat){
        this.createat = createat;
    }
    // Getters and Setters
    public String getManh() {
        return manh;
    }

    public void setManh(String manh) {
        this.manh = manh;
    }

    public String getMatb() {
        return matb;
    }

    public void setMatb(String matb) {
        this.matb = matb;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getChiPhiKhac() {
        return chiPhiKhac;
    }

    public void setChiPhiKhac(double chiPhiKhac) {
        this.chiPhiKhac = chiPhiKhac;
    }

    public double getCktm() {
        return cktm;
    }

    public void setCktm(double cktm) {
        this.cktm = cktm;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }
    @Override
    public boolean update() {
        return ImportDDetails.UpdateIDDetails(manh, matb, donGia, chiPhiKhac, cktm, vat);
    }
    @Override
    public boolean insert() {
        return ImportDDetails.AddIDDetails(manh, matb, matb, donGia, chiPhiKhac, cktm, vat);
    }
}