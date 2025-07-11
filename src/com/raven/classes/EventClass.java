package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.Event;

public class EventClass implements UpdatableEntity{
    private String mask;
    private String tensk;
    private java.sql.Date tgbd;
    private java.sql.Date tgkt;
    private String makv;
    private String noidung;
    private String manv;
    private Date createat;
    // Getters and Setters
    public Date getcreateat(){
        return createat;
    }
    public void setcreateat(Date createat){
        this.createat = createat;
    }
    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getTensk() {
        return tensk;
    }

    public void setTensk(String tensk) {
        this.tensk = tensk;
    }

    public java.sql.Date getTgbd() {
        return tgbd;
    }

    public void setTgbd(java.sql.Date tgbd) {
        this.tgbd = tgbd;
    }

    public java.sql.Date getTgkt() {
        return tgkt;
    }

    public void setTgkt(java.sql.Date tgkt) {
        this.tgkt = tgkt;
    }

    public String getMakv() {
        return makv;
    }

    public void setMakv(String makv) {
        this.makv = makv;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }
    @Override
    public boolean update() {
        return Event.UpdateEvent(mask, tensk, tgbd, tgkt, makv, noidung, manv);
    }
    @Override
    public boolean insert() {
        return Event.AddEvent(tensk, tgbd, tgkt, makv, noidung, manv);
    }
}