package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.Employee;
public class EmployeeClass implements UpdatableEntity{
    private String manv;
    private String userId;
    private java.sql.Date ngvl;
    private String malnv;
    private String masothuecn;
    private String sobhyt;
    private String hoten;
    private String roleType;
    private String sdt;
    private java.sql.Date ngaysinh;
    private String email;
    private String diachi;
    private java.util.Date createdAt; // CREATED_AT

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

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public java.sql.Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(java.sql.Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public java.util.Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public boolean update() {
        return Employee.UpdateEmployee(manv, userId, hoten, sdt, ngaysinh, email, diachi, malnv, masothuecn, sobhyt);
    }
    @Override
    public boolean insert() {
        return Employee.AddEmployee(hoten, sdt, ngaysinh, email, diachi, malnv, masothuecn, sobhyt, "");
    }
}