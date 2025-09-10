package com.example.nodepos.model;

public class riwayatModel {
    private String orderId;
    private String status;
    private String totalAmount;
    private String date;



    public riwayatModel(String orderId, String status,String totalAmount, String date) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getDate() {
        return date;
    }
}
