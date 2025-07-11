package com.raven.classes;
import java.util.Date;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.*;

public class ProductPromotionClass implements UpdatableEntity {
    private String MAKMSP;
    private String maSP;
    private String maCTR;
    private Date createat;

    // Constructor
    public ProductPromotionClass() {}

    public ProductPromotionClass(String MAKMSP, String maSP, String maCTR, boolean isDelete) {
        this.MAKMSP = MAKMSP;
        this.maSP = maSP;
        this.maCTR = maCTR;
    }
    public Date getCreateat(){
        return createat;
    }
    public void setCreateat(Date createat){
        this.createat = createat;
    }
    // Getters and Setters
    public String getMAKMSP() {
        return MAKMSP;
    }

    public void setMAKMSP(String MAKMSP) {
        this.MAKMSP = MAKMSP;
    }

    public String getmaSP() {
        return maSP;
    }

    public void setmaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaCTR() {
        return maCTR;
    }

    public void setMaCTR(String maCTR) {
        this.maCTR = maCTR;
    }
    @Override
    public boolean update() {
        return ProductPromotion.UpdatePPromotion(MAKMSP, maSP);
    }
    @Override
    public boolean insert() {
        return ProductPromotion.AddPPromotion(maSP, maCTR);
    }
}
