package com.example.nodepos.model;

// AbsenLog.java
public class absenModel {
    private String jenis; // "Check-In" / "Check-Out"
    private String tanggal;
    private String waktu;

    public absenModel(String jenis, String tanggal,String waktu) {
        this.jenis = jenis;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public String getJenis() {
        return jenis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }
}
