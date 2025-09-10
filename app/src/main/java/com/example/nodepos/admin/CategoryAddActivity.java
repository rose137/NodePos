package com.example.nodepos.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nodepos.R;
import com.example.nodepos.helper.DBHelper;
import com.example.nodepos.model.CategoryModel;
import com.example.nodepos.produk.ProductListActivity;
import com.example.nodepos.repository.CategoryRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class CategoryAddActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView ivThumbnail;
    private Button btnUpload, btnSave;
    private TextInputEditText etName, etPrice, etStock, etDescription, etKodeProduk;

    private Uri imageUri;
    private Bitmap selectedBitmap;
    private DBHelper dbHelper;
    String etKategoriId;
    String etKategoriName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);

        dbHelper = new DBHelper(this);

        ivThumbnail = findViewById(R.id.imgPreview);
        btnUpload = findViewById(R.id.btnUpload);
        btnSave = findViewById(R.id.btnSave);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etStock = findViewById(R.id.etStock);
        etDescription = findViewById(R.id.etDescription);
        etKodeProduk = findViewById(R.id.etKodeProduk); // tambahkan di XML

        btnUpload.setOnClickListener(v -> openGallery());
        btnSave.setOnClickListener(v -> saveProduct());

        AutoCompleteTextView etKategori = findViewById(R.id.etKategori);
        // Data kategori (id + nama)
        List<CategoryModel> list = CategoryRepository.getCategoryList();
        // Adapter pakai CategoryItem, tapi karena toString() return name → yang tampil hanya nama
        ArrayAdapter<CategoryModel> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                list
        );

        etKategori.setAdapter(adapter);

        // Event saat user memilih item
        etKategori.setOnItemClickListener((parent, view, position, id) -> {
            CategoryModel selected = (CategoryModel) parent.getItemAtPosition(position);
            etKategoriId = selected.getKategoriId();
            etKategoriName = selected.getProductName();
//            Toast.makeText(this,
//                    "Dipilih: " + selected.getId() + " (ID: " + selected.getName() + ")",
//                    Toast.LENGTH_SHORT).show();
        });
    }

    private String generateProductId(String kodeProduk) {
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM products WHERE ProdukId LIKE ?",
                new String[]{date + kodeProduk + "%"}
        );


        int sequence = 1;
        if (cursor.moveToFirst()) {
            sequence = cursor.getInt(0) + 1;
        }
        cursor.close();
        db.close();

        String seqStr = String.format("%04d", sequence);
        return date + kodeProduk + seqStr;
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar Produk"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                selectedBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivThumbnail.setImageBitmap(selectedBitmap); // preview
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProduct() {
        String kodeProduk = etKodeProduk.getText().toString().trim();
        if (kodeProduk.isEmpty()) kodeProduk = "PRD"; // default

        String produkId = generateProductId(kodeProduk);
        String name = etName.getText().toString().trim();
        String price = etPrice.getText().toString().trim();
        String stock = etStock.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (name.isEmpty() || price.isEmpty() || stock.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Lengkapi semua data produk!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert Bitmap → byte[]
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBytes = stream.toByteArray();

        boolean inserted = dbHelper.insertProduct(produkId, name,etKategoriId,etKategoriName, price, stock, description, imageBytes);

        if (inserted) {
            Toast.makeText(this, "Produk berhasil disimpan dengan ID: " + produkId, Toast.LENGTH_LONG).show();
            startActivity(new Intent(CategoryAddActivity.this, ProductListActivity.class));
            finish();

        } else {
            Toast.makeText(this, "Gagal menyimpan produk", Toast.LENGTH_SHORT).show();
        }
    }
}
