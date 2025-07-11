package com.raven.classes;  
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

import com.raven.dbfunction.WorkHours;
public class WorkHoursClass implements UpdatableEntity {
    private String maca;
    private String manv;
    private Date tgbd;
    private Date tgkt;
    private Date ngaycc;
    private int workHours;
    private int overtimeHours;
    private String trangthai;
    public String getTrangthai() {
        return trangthai;
    }
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    // Getters and Setters
    public String getMaca() {
        return maca;
    }

    public void setMaca(String maca) {
        this.maca = maca;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public Date getTgbd() {
        return tgbd;
    }

    public void setTgbd(Date tgbd) {
        this.tgbd = tgbd;
    }

    public Date getTgkt() {
        return tgkt;
    }

    public void setTgkt(Date tgkt) {
        this.tgkt = tgkt;
    }

    public Date getNgaycc() {
        return ngaycc;
    }

    public void setNgaycc(Date ngaycc) {
        this.ngaycc = ngaycc;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }
    @Override
    public boolean update() {
        return WorkHours.UpdateWHours(maca, manv, tgbd, tgkt, ngaycc, workHours, overtimeHours);
    }
    @Override
    public boolean insert() {
        return WorkHours.AddWHours(manv, new java.sql.Timestamp(tgbd.getTime()), new java.sql.Timestamp(tgkt.getTime()));
    }
}
