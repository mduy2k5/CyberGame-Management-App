package com.raven.classes;
import com.raven.dbfunction.Expense;
import com.raven.interfaces.UpdatableEntity;

public class ExpenseClass implements UpdatableEntity {
    private String machiphi;
    private String noidung;
    private java.sql.Date ngaychi;
    private String manv;
    private String hinhthuc;
    private double sotien;
    private String trangthai;
    // Getters and Setters
    public String getMachiphi() {
        return machiphi;
    }

    public void setMachiphi(String machiphi) {
        this.machiphi = machiphi;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public java.sql.Date getNgaychi() {
        return ngaychi;
    }

    public void setNgaychi(java.sql.Date ngaychi) {
        this.ngaychi = ngaychi;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHinhthuc() {
        return hinhthuc;
    }

    public void setHinhthuc(String hinhthuc) {
        this.hinhthuc = hinhthuc;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }
    public String getTrangthai() {
        return trangthai;
    }
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    @Override
    public boolean update() {
        return Expense.UpdateExpense(machiphi, noidung, ngaychi, manv, hinhthuc, sotien, trangthai);
    }
    @Override
    public boolean insert() {
        return Expense.AddExpense(noidung, ngaychi, manv, hinhthuc, sotien, trangthai);
    }
}
