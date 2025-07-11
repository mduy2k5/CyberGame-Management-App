package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.LeaveWork;

public class LeaveWorkClass implements UpdatableEntity {
    private String manv;
    private String userId;
    private java.sql.Date ngvl;
    private String malnv;
    private String masothuecn;
    private String sobhyt;
    private String manp;
    private String manvnp;
    private String manvthaythe;
    private String maca;
    private String noidung;
    private java.sql.Date thoigiantb;
    private String leavetype;

    // Constructor
    public LeaveWorkClass(String manv, String userId, java.sql.Date ngvl, String malnv, String masothuecn,
                          String sobhyt, String manp, String manvnp, String manvthaythe, String maca,
                          String noidung, java.sql.Date thoigiantb, String leavetype) {
        this.manv = manv;
        this.userId = userId;
        this.ngvl = ngvl;
        this.malnv = malnv;
        this.masothuecn = masothuecn;
        this.sobhyt = sobhyt;
        this.manp = manp;
        this.manvnp = manvnp;
        this.manvthaythe = manvthaythe;
        this.maca = maca;
        this.noidung = noidung;
        this.thoigiantb = thoigiantb;
        this.leavetype = leavetype;
    }
    // Default constructor
    public LeaveWorkClass() {
    
    }
    // Getters and Setters
    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public java.sql.Date getNgvl() {
        return ngvl;
    }

    public void setNgvl(java.sql.Date ngvl) {
        this.ngvl = ngvl;
    }

    public String getMalnv() {
        return malnv;
    }

    public void setMalnv(String malnv) {
        this.malnv = malnv;
    }

    public String getMasothuecn() {
        return masothuecn;
    }

    public void setMasothuecn(String masothuecn) {
        this.masothuecn = masothuecn;
    }

    public String getSobhyt() {
        return sobhyt;
    }

    public void setSobhyt(String sobhyt) {
        this.sobhyt = sobhyt;
    }

    public String getManp() {
        return manp;
    }

    public void setManp(String manp) {
        this.manp = manp;
    }

    public String getManvnp() {
        return manvnp;
    }

    public void setManvnp(String manvnp) {
        this.manvnp = manvnp;
    }

    public String getManvthaythe() {
        return manvthaythe;
    }

    public void setManvthaythe(String manvthaythe) {
        this.manvthaythe = manvthaythe;
    }

    public String getMaca() {
        return maca;
    }

    public void setMaca(String maca) {
        this.maca = maca;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public java.sql.Date getThoigiantb() {
        return thoigiantb;
    }

    public void setThoigiantb(java.sql.Date thoigiantb) {
        this.thoigiantb = thoigiantb;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }
    @Override
    public boolean update() {
        return LeaveWork.UpdateLWork(manp, manvnp, manvthaythe, maca, noidung, thoigiantb, leavetype);
    }
    @Override
    public boolean insert() {
        return LeaveWork.AddLWork(manvnp, manvthaythe, maca, noidung, thoigiantb, leavetype);
    }
}
