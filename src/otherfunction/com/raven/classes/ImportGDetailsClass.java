package com.raven.classes;
import java.util.*;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.ImportGDetails;
public class ImportGDetailsClass implements UpdatableEntity {
    private String manh;
    private String masp;
    private int slTheoChungTu;
    private int slThucNhap;
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

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSlTheoChungTu() {
        return slTheoChungTu;
    }

    public void setSlTheoChungTu(int slTheoChungTu) {
        this.slTheoChungTu = slTheoChungTu;
    }

    public int getSlThucNhap() {
        return slThucNhap;
    }

    public void setSlThucNhap(int slThucNhap) {
        this.slThucNhap = slThucNhap;
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
        return ImportGDetails.UpdateIGDetails(manh, masp, slTheoChungTu, slThucNhap, donGia, chiPhiKhac, cktm, vat);
    }
    @Override
    public boolean insert() {
        return ImportGDetails.AddIGDetails(manh, masp, slTheoChungTu, slThucNhap, donGia, chiPhiKhac, cktm, vat);
    }
}