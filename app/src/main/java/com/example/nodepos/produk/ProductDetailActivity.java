package com.example.nodepos.produk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nodepos.R;

public class ProductDetailActivity  extends AppCompatActivity  {
    private TextView tvName, tvCategory, tvPrice, tvDesc, tvSku;
    private ImageView ivProduct;
    private Button btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        tvName = findViewById(R.id.tvDetailName);
        tvCategory = findViewById(R.id.tvDetailCategory);
        tvPrice = findViewById(R.id.tvDetailPrice);
        tvDesc = findViewById(R.id.tvDetailDesc);
        tvSku = findViewById(R.id.tvDetailSku);
        ivProduct = findViewById(R.id.imgProductDetail);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        // Ambil data dari Intent
        int id = getIntent().getIntExtra("product_id", -1);
        String name = getIntent().getStringExtra("product_name");
        String category = getIntent().getStringExtra("product_category");
        String sku = getIntent().getStringExtra("product_sku");
        String desc = getIntent().getStringExtra("product_desc");
        int price = getIntent().getIntExtra("product_price", 0);
        String imageUrl = getIntent().getStringExtra("product_image");
        tvName.setText(name);
        tvCategory.setText(category);
        tvPrice.setText("Rp " + price);

        tvName.setText(name);
        tvCategory.setText("Kategori: " + category);
        tvSku.setText("SKU: " + sku);
        tvDesc.setText(desc);
        tvPrice.setText("Rp " + price);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder_image) // fallback
                .into(ivProduct);

        btnEdit.setOnClickListener(v -> {
            // TODO: buka form edit
        });

        btnDelete.setOnClickListener(v -> {
            // TODO: hapus produk dari database
        });
    }
}
