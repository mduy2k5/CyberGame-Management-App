package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.Gift;

public class GiftClass implements UpdatableEntity {
    private String maQT;
    private String noiDung;
    private int soDiemTieuHao;

    // Constructor
    public GiftClass() {}

    public GiftClass(String maQT, String noiDung, int soDiemTieuHao) {
        this.maQT = maQT;
        this.noiDung = noiDung;
        this.soDiemTieuHao = soDiemTieuHao;
    }

    // Getters and Setters
    public String getMaQT() {
        return maQT;
    }

    public void setMaQT(String maQT) {
        this.maQT = maQT;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getSoDiemTieuHao() {
        return soDiemTieuHao;
    }

    public void setSoDiemTieuHao(int soDiemTieuHao) {
        this.soDiemTieuHao = soDiemTieuHao;
    }
    @Override
    public boolean update() {
        return Gift.UpdateGift(maQT, noiDung, soDiemTieuHao);
    }
    @Override
    public boolean insert() {
        return Gift.AddGift(noiDung, soDiemTieuHao);
    }
}
