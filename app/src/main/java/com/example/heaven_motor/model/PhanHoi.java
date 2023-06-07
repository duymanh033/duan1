package com.example.heaven_motor.model;

public class PhanHoi {
    String nd;
    String maKH;

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public PhanHoi(String nd, String maKH) {
        this.nd = nd;
        this.maKH = maKH;
    }

    public PhanHoi() {
    }
}
