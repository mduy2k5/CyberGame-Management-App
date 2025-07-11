package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.AreaPromotion;

public class AreaPromotionClass implements UpdatableEntity {
    private String maKMKV;
    private String maKV;
    private String maCTR;
    private Date createat;
    // Constructor
    public AreaPromotionClass() {}

    public AreaPromotionClass(String maKMKV, String maKV, String maCTR, boolean isDelete) {
        this.maKMKV = maKMKV;
        this.maKV = maKV;
        this.maCTR = maCTR;
    }
        public Date getCreateat(){
        return createat;
    }
    public void setCreateat(Date createat){
        this.createat = createat;
    }
    // Getters and Setters
    public String getMaKMKV() {
        return maKMKV;
    }

    public void setMaKMKV(String maKMKV) {
        this.maKMKV = maKMKV;
    }

    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getMaCTR() {
        return maCTR;
    }

    public void setMaCTR(String maCTR) {
        this.maCTR = maCTR;
    }
    @Override
    public boolean update() {
        return AreaPromotion.UpdateAPromotion(maKMKV, maKV);
    }
    @Override
    public boolean insert() {
        return AreaPromotion.AddAPromotion(maKV, maCTR);
    }
}
