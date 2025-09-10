package com.example.nodepos.model;

public class productModel {
    private String id;
    private String name;
    private String kategoriId;
    private String kategoriName;
    private int price;
    private String imageUrl;
    private int stock; // tambahkan stock

    public productModel(String id, String name, String kategoriId,String kategoriName, int price, String imageUrl, int stock) {
        this.id = id;
        this.name = name;
        this.kategoriId = kategoriId;
        this.kategoriName = kategoriName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getKategoriId() { return kategoriId; }
    public String getKategoriName() { return kategoriName; }
    public int getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    // getter
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) { this.stock = stock; }
}
