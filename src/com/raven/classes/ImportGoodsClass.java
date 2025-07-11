package com.raven.classes;
import java.util.*;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.ImportGoods;
public class ImportGoodsClass implements UpdatableEntity {
    private String manh;
    private String machungtu;
    private String mancc;
    private String manv;
    private String loainhaphang;
    private double trigia;
    private Date createAt;

    public Date getCreateAt(){
        return createAt;
    }
    public void setCreateAt(Date date){
        this.createAt = date;
    }
    // Getters and Setters
    public String getManh() {
        return manh;
    }

    public void setManh(String manh) {
        this.manh = manh;
    }


    public String getMachungtu() {
        return machungtu;
    }

    public void setMachungtu(String machungtu) {
        this.machungtu = machungtu;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getLoaiNhapHang() {
        return loainhaphang;
    }

    public void setLoaiNhapHang(String loainhaphang) {
        this.loainhaphang = loainhaphang;
    }

    public double getTrigia() {
        return trigia;
    }

    public void setTrigia(double trigia) {
        this.trigia = trigia;
    }
    @Override
    public boolean update() {
        return ImportGoods.UpdateIGoods(manh, machungtu, mancc, manv, trigia,loainhaphang);
    }
    @Override
    public boolean insert() {
        return ImportGoods.AddIGoods(machungtu, mancc, manv, trigia,loainhaphang);
    }
}
