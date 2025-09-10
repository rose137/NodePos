package com.example.nodepos.repository;

import com.example.nodepos.R;
import com.example.nodepos.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    public static List<CategoryModel> getCategoryList() {
        List<CategoryModel> list = new ArrayList<>();
        list.add(new CategoryModel(R.drawable.ibubayianak, "P0001","Kebutuhan Ibu & Anak", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.produksegar2, "P0002","Produk Segar & Beku", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.minuman, "P0003","Minuman", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.kebutuhanrumahtangga,"P0004", "Kebutuhan Rumah Tangga", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.petfood,"P0005", "Pet Food", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.personalcare, "P0006","Personal Care", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.makanan,"P0007", "Makanan", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.kebutuhandapur, "P0008","Kebutuhan Dapur", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.kesehatan, "P0009","Kesehatan", "8000+ Stock"));
        list.add(new CategoryModel(R.drawable.lifestyle, "P0010","Life Style", "8000+ Stock"));
        return list;
    }
}
