package com.raven.classes;
import com.raven.dbfunction.Supplier;
import com.raven.interfaces.UpdatableEntity;
public class SupplierClass implements UpdatableEntity {
    private String maNCC;
    private String tenNCC;
    private String sdt;
    private String email;
    private String website;
    private String diaChi;

    // Constructor
    public SupplierClass() {}

    public SupplierClass(String maNCC, String tenNCC, String sdt, String email, String website, String diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdt = sdt;
        this.email = email;
        this.website = website;
        this.diaChi = diaChi;
    }

    // Getters and Setters
    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    @Override
    public boolean update() {
        return Supplier.UpdateSupplier(maNCC, tenNCC, sdt, email, website, diaChi);
    }
    @Override
    public boolean insert() {
        return Supplier.AddSupplier(tenNCC, sdt, email, website, diaChi);
    }
}