package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import java.util.Date;

public class ComputerDDetailsClass implements UpdatableEntity {
    // Attributes
    private String maPC;       // MAPC
    private String maTB;       // MATB
    private int isDelete;      // IS_DELETE
    private Date createdAt;    // CREATED_AT

    // Constructor
    public ComputerDDetailsClass(String maPC, String maTB, int isDelete, Date createdAt) {
        this.maPC = maPC;
        this.maTB = maTB;
        this.isDelete = isDelete;
        this.createdAt = createdAt;
    }

    // Default Constructor
    public ComputerDDetailsClass() {}

    // Getters and Setters
    public String getMaPC() {
        return maPC;
    }

    public void setMaPC(String maPC) {
        this.maPC = maPC;
    }

    public String getMaTB() {
        return maTB;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // toString Method
    @Override
    public String toString() {
        return "ThietBiTungMayClass{" +
                "maPC='" + maPC + '\'' +
                ", maTB='" + maTB + '\'' +
                ", isDelete=" + isDelete +
                ", createdAt=" + createdAt +
                '}';
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
