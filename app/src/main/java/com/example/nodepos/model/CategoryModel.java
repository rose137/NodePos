package com.example.nodepos.model;

public class CategoryModel {
    private int iconResId;
    private String KategoriId;
    private String KategoriName;
    private String KategoriDesc;


    public CategoryModel(int iconResId,String KategoriId, String KategoriName, String KategoriDesc) {
        this.iconResId = iconResId;
        this.KategoriId = KategoriId;
        this.KategoriName = KategoriName;
        this.KategoriDesc = KategoriDesc;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getKategoriId() { return KategoriId; }

    public String getKategoriName() {
        return KategoriName;
    }

    public String getSubtitle() {
        return KategoriDesc;
    }


    public int getImageResId() {
        return iconResId;
    }

    public String getProductName() {
        return KategoriName;
    }

    @Override
    public String toString() {
        return getKategoriId() + "    |    " + getKategoriName();
    }
}

