package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
public class CustomerClass implements UpdatableEntity {
    String makh;
    String userid;
    String mahkh;
    int sodiemtichluy;
    int sdutk;
    String trangthai;
    public String getMakh() {
        return this.makh;
    }
    public void setMakh(String makh) {
        this.makh = makh;
    }
    public CustomerClass() {
        this.userid = null;
        this.mahkh = null;
        this.sodiemtichluy = 0;
        this.sdutk = 0;
        this.trangthai = null;
    }
    public CustomerClass(String maKH, String userId, String maHKH, int soDiemTichLuy, int soDuTK, String trangThai) {
        this.makh = maKH;
        this.userid = userId;
        this.mahkh = maHKH;
        this.sodiemtichluy = soDiemTichLuy;
        this.sdutk = soDuTK;
        this.trangthai = trangThai;
    }
    public void setMahkh(String mahkh) {
        this.mahkh = mahkh;
    }

    public void setSdutk(int sdutk) {
        this.sdutk = sdutk;
    }

    public String getMahkh() {
        return this.mahkh;
    }
    public void setSodiemtichluy(int sodiemtichluy) {
        this.sodiemtichluy = sodiemtichluy;
    }
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public int getSdutk() {
        return this.sdutk;
    }
    public int getSodiemtichluy() {
        return this.sodiemtichluy;
    }
    public String getTrangthai() {
        return this.trangthai;
    }
    public String getUserid() {
        return this.userid;
    }
    @Override
    public boolean update() {
        return false;
    }
    @Override
    public boolean insert() {
        return false;
    }
}
