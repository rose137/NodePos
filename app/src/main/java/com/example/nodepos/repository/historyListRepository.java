package com.example.nodepos.repository;

import com.example.nodepos.R;
import com.example.nodepos.model.CategoryModel;
import com.example.nodepos.model.riwayatModel;

import java.util.ArrayList;
import java.util.List;

public class historyListRepository {
    public static List<riwayatModel> getRiwayatList() {
        List<riwayatModel> list = new ArrayList<>();
        list.add(new riwayatModel("ORD090", "Delivered", "15000", "28-08-2025"));
        list.add(new riwayatModel("ORD091", "Delivered", "32000", "27-08-2025"));
        list.add(new riwayatModel("ORD096", "Dibatalkan", "32000", "27-08-2025"));
        list.add(new riwayatModel("ORD123", "On Delivery", "25000", "02-09-2025"));
        list.add(new riwayatModel("ORD124", "Processing", "18000", "03-09-2025"));


        return list;
    }
}
