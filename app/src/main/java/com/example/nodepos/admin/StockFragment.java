package com.example.nodepos.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodepos.R;
import com.example.nodepos.adapter.CategoryAdapter;
import com.example.nodepos.model.CategoryModel;
import com.example.nodepos.model.productModel;
import com.example.nodepos.produk.ProductListActivity;
import com.example.nodepos.repository.CategoryRepository;
import com.example.nodepos.repository.ProductDummyRepository;

import java.util.List;

public class StockFragment extends Fragment {



    public StockFragment() {
        // Diperlukan constructor kosong
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Ganti layout ini dengan layout fragment kamu
        View view = inflater.inflate(R.layout.fragment_stock, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 kolom

        List<CategoryModel> categoryList = getCategoryList();
        List<productModel> allProducts = ProductDummyRepository.getProductDummyList();
        CategoryAdapter adapter = new CategoryAdapter(categoryList, new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CategoryModel category) {

                // Klik kategori → buka ProductListActivity sesuai category_id
                Intent intent = new Intent(getContext(), ProductListActivity.class);
                intent.putExtra("category_id", category.getKategoriId()); // P001, P002, dll
                startActivity(intent);
//
//                Intent intent = new Intent(getContext(), ProductListActivity.class);
//                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<CategoryModel> getCategoryList() {
        List<CategoryModel> list = CategoryRepository.getCategoryList();
        return list;
    }


}
