package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.TotalLeave;

public class TotalLeaveClass implements UpdatableEntity{
    private String manp;
    private String manv;
    private int totalLeaves;
    private int usedLeaves;
    private int remainingLeaves;
    private int nam;

    // Getters and Setters
    public String getManp() {
        return manp;
    }

    public void setManp(String manp) {
        this.manp = manp;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(int totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public int getUsedLeaves() {
        return usedLeaves;
    }

    public void setUsedLeaves(int usedLeaves) {
        this.usedLeaves = usedLeaves;
    }

    public int getRemainingLeaves() {
        return remainingLeaves;
    }

    public void setRemainingLeaves(int remainingLeaves) {
        this.remainingLeaves = remainingLeaves;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
    // Constructor
    public TotalLeaveClass() {}
    public TotalLeaveClass(String manp, String manv, int totalLeaves, int usedLeaves, int remainingLeaves, int nam) {
        this.manp = manp;
        this.manv = manv;
        this.totalLeaves = totalLeaves;
        this.usedLeaves = usedLeaves;
        this.remainingLeaves = remainingLeaves;
        this.nam = nam;
    }
    @Override
    public boolean update() {
        return TotalLeave.UpdateTLeave(manp, manv, totalLeaves, usedLeaves, remainingLeaves, nam);
    }
    @Override
    public boolean insert() {
        return TotalLeave.AddTLeave(manv, totalLeaves, usedLeaves, remainingLeaves, nam);
    }
}