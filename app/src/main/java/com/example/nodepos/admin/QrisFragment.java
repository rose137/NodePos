package com.example.nodepos.admin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nodepos.R;
import com.example.nodepos.model.CategoryModel;
import com.example.nodepos.model.productModel;
import com.example.nodepos.repository.ProductDummyRepository;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class QrisFragment extends Fragment {
    private boolean isDropdownVisible = false;

    private MaterialButton btnGenerate, btnScan;
    private LinearLayout containerQR; // Bukan MaterialCardView
    private AutoCompleteTextView dropdownKategori, dropdownProduk;
    private ImageView ivQRCode;

    private List<CategoryModel> categoryList = new ArrayList<>();
    private Map<String, List<productModel>> productMap;

    private productModel selectedProduct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qris, container, false);

        // Init views
        btnGenerate = view.findViewById(R.id.btnGenerate);
        btnScan = view.findViewById(R.id.btnScan);
        containerQR = view.findViewById(R.id.containerQR);
        dropdownKategori = view.findViewById(R.id.dropdownKategori);
        dropdownProduk = view.findViewById(R.id.dropdownProduk);
        ivQRCode = view.findViewById(R.id.ivQRCode);


        // Dummy kategori dari produk
        productMap = ProductDummyRepository.getProductsByCategoryMap();
        for (String catId : productMap.keySet()) {
            List<productModel> list = productMap.get(catId);
            if (list != null && !list.isEmpty()) {
                // ambil kategoriId + kategoriName dari produk pertama
                String namaKategori = list.get(0).getKategoriName();
                categoryList.add(new CategoryModel(R.drawable.ic_category, catId, namaKategori, ""));
            }
        }
        setupKategoriDropdown();


        btnGenerate.setOnClickListener(v -> {
            if (!isDropdownVisible) {
                // Tampilkan container dengan dropdown
                containerQR.setVisibility(View.VISIBLE);
                isDropdownVisible = true;
            } else {
                // Dropdown sudah muncul, cek produk
                if (selectedProduct != null) {
                    generateQRCode(selectedProduct.getId());
                } else {
                    Toast.makeText(getContext(), "Pilih produk terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Tombol Scan QR
        btnScan.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new QrisScanFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void generateQRCode(String text) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            Bitmap bitmap;
            int size = 512;
            com.google.zxing.common.BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size);
            bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            ivQRCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Gagal membuat QR Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupKategoriDropdown() {
        ArrayAdapter<CategoryModel> kategoriAdapter =
                new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, categoryList);
        dropdownKategori.setAdapter(kategoriAdapter);

        dropdownKategori.setThreshold(1); // mulai search setelah 1 karakter

        dropdownKategori.setOnItemClickListener((parent, view, position, id) -> {
            CategoryModel selectedCategory = (CategoryModel) parent.getItemAtPosition(position);
            setupProdukDropdown(selectedCategory.getKategoriId());
        });
    }

    private void setupProdukDropdown(String kategoriId) {
        List<productModel> produkList = ProductDummyRepository.getProductsByCategory(kategoriId);
        ArrayAdapter<productModel> produkAdapter =
                new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, produkList);
        dropdownProduk.setAdapter(produkAdapter);

        dropdownProduk.setThreshold(1); // mulai search setelah 1 karakter


        dropdownProduk.setOnItemClickListener((parent, view, position, id) -> {
            selectedProduct = (productModel) parent.getItemAtPosition(position);
        });
    }
}
