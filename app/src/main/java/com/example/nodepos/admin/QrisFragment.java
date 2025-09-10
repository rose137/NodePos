package com.example.nodepos.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nodepos.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class QrisFragment extends Fragment {

    private EditText etToken;
    private Button btnGenerate, btnScan;
    private ImageView ivQRCode;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qris, container, false);

        etToken = view.findViewById(R.id.etToken);
        btnGenerate = view.findViewById(R.id.btnGenerate);
        btnScan = view.findViewById(R.id.btnScan);
        ivQRCode = view.findViewById(R.id.ivQRCode);

        // Tombol Generate
        btnGenerate.setOnClickListener(v -> {
            // Tampilkan field input token
            etToken.setVisibility(View.VISIBLE);

            String text = etToken.getText().toString().trim();
            if (!text.isEmpty()) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(text, BarcodeFormat.QR_CODE, 400, 400);
                    ivQRCode.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "Masukkan token dulu!", Toast.LENGTH_SHORT).show();
            }
        });

        btnScan.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new QrisScanFragment())
                    .addToBackStack(null) // supaya bisa balik pakai tombol back
                    .commit();
        });


        return view;
    }

//    private void generateQRCode(String data) {
//        QRCodeWriter writer = new QRCodeWriter();
//        try {
//            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 500, 500);
//            Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565);
//
//            for (int x = 0; x < 500; x++) {
//                for (int y = 0; y < 500; y++) {
//                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
//                }
//            }
//
//            imgQRCode.setImageBitmap(bitmap);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//    }

}