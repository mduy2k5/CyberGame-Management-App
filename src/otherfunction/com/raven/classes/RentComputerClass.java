package com.raven.classes;
import com.raven.interfaces.UpdatableEntity;
import com.raven.dbfunction.RentComputer;

public class RentComputerClass implements UpdatableEntity {
    private String maTM; // Mã thuê máy
    private String maPC; // Mã PC
    private String maLS; // Mã lịch sử chơi
    // Constructor
    public RentComputerClass() {}

    public RentComputerClass(String maTM, String maPC, String maLS) {
        this.maTM = maTM;
        this.maPC = maPC;
        this.maLS = maLS;
    }

    // Getters and Setters
    public String getMaTM() {
        return maTM;
    }

    public void setMaTM(String maTM) {
        this.maTM = maTM;
    }

    public String getMaPC() {
        return maPC;
    }

    public void setMaPC(String maPC) {
        this.maPC = maPC;
    }

    public String getMaLS() {
        return maLS;
    }

    public void setMaLS(String maLS) {
        this.maLS = maLS;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "RentComputerClass{" +
                "maTM='" + maTM + '\'' +
                ", maPC='" + maPC + '\'' +
                ", maLS='" + maLS + '\'' +
                '}';
    }
    @Override
    public boolean update() {
        return RentComputer.UpdateRComputer(maTM, maPC, maLS);
    }
    @Override
    public boolean insert() {
        return RentComputer.AddRComputer(maPC, maLS);
    }
}