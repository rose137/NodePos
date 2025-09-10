package com.example.nodepos.produk;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nodepos.R;
import com.example.nodepos.adapter.ProductAdapter;
import com.example.nodepos.admin.CategoryAddActivity;
import com.example.nodepos.model.productModel;
import com.example.nodepos.repository.ProductDummyRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<productModel> productList;
    private FloatingActionButton btnTambah;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        btnTambah = findViewById(R.id.fabAddProduct);
        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = findViewById(R.id.recyclerViewProducts);

        btnTambah.setOnClickListener(v -> tambah());
        // TODO: ganti dummy data dengan ambil dari database / API

        List<productModel> productList = ProductDummyRepository.getProductDummyList();

        // Ambil kategoriId dari Intent
        String categoryId = getIntent().getStringExtra("category_id");

        // Ambil produk sesuai kategori yang diklik
        List<productModel> filteredList = ProductDummyRepository.getProductsByCategory(categoryId);


        adapter = new ProductAdapter(filteredList, product -> {
            // klik item -> buka detail
            Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
            intent.putExtra("product_id", product.getId());
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_category", product.getKategoriId());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_image", product.getImageUrl());
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 3 kolom
        recyclerView.setAdapter(adapter);
    }

    private void tambah() {
        startActivity(new Intent(ProductListActivity.this, CategoryAddActivity.class));
        finish();
    }


    // Inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    // Handle klik menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            // TODO: refresh data produk
            return true;
        } else if (id == R.id.action_filter) {
            // TODO: filter produk
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}