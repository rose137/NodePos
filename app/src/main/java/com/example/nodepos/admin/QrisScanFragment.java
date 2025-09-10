package com.example.nodepos.admin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nodepos.R;
import com.example.nodepos.cart.CartFragment;
import com.example.nodepos.cart.CartManager;
import com.example.nodepos.model.productModel;
import com.example.nodepos.receipt.ReceiptDialogFragment;
import com.example.nodepos.repository.ProductDummyRepository;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;


public class QrisScanFragment extends Fragment {
    private DecoratedBarcodeView barcodeScanner;
    private boolean isScanned = false;
    private static final int CAMERA_PERMISSION_REQUEST = 100;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qris_scan, container, false);

        barcodeScanner = view.findViewById(R.id.barcodeScanner);
        ImageButton btnCart = view.findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new CartFragment(), "CART")
                    .addToBackStack(null)
                    .commit();
        });

        startContinuousScan();
        // Continuous scan
//        barcodeScanner.decodeContinuous(new BarcodeCallback() {
//            @Override
//            public void barcodeResult(BarcodeResult result) {
//                if (!isScanned && result.getText() != null) {
//                    isScanned = true;
//                    String qrText = result.getText(); // misalnya "PRD025"
//
//                    productModel product = findProductById(qrText);
//
//                    if (product != null) {
//                        // Tambahkan ke cart
//                        CartManager.getInstance().addCart(product);
//
//                        Toast.makeText(getContext(),
//                                "Produk ditambahkan: " + product.getName() +
//                                        " (Rp " + product.getPrice() + ")",
//                                Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getContext(),
//                                "Produk tidak ditemukan untuk QR: " + qrText,
//                                Toast.LENGTH_SHORT).show();
//                    }
//
//                    resumeScanner();
//                }
//            }
//        });



        return view;
    }

    private void startContinuousScan() {
        barcodeScanner.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (!isScanned && result.getText() != null) {
                    isScanned = true;
                    String qrText = result.getText();

                    productModel product = findProductById(qrText);

                    if (product != null) {
                        CartManager.getInstance().addCart(product);
                        Toast.makeText(getContext(),
                                "Produk ditambahkan: " + product.getName() +
                                        " (Rp " + product.getPrice() + ")",
                                Toast.LENGTH_SHORT).show();

                        // Langsung pindah ke CartFragment
                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, new CartFragment(), "CART")
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Toast.makeText(getContext(),
                                "Produk tidak ditemukan untuk QR: " + qrText,
                                Toast.LENGTH_SHORT).show();
                    }

                    // delay 1 detik sebelum scan berikutnya
                    new Handler(Looper.getMainLooper()).postDelayed(() -> isScanned = false, 1000);
                }
            }
        });
    }
    private productModel findProductById(String id) {
        for (productModel p : ProductDummyRepository.getProductDummyList()) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null; // kalau tidak ketemu
    }


    private void showPaymentSuccessDialog(String qrText) {
        if (!isAdded() || getActivity() == null || getActivity().isFinishing()) {
            return; // jangan tampilkan dialog kalau fragment/activity sudah hilang
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Pembayaran Berhasil ✅");

        String receipt = "=== STRUK PEMBAYARAN ===\n\n" +
                "Metode : QRIS\n" +
                "Kode   : " + qrText + "\n" +
                "Tanggal: " + new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()) + "\n" +
                "Status : Sukses\n" +
                "=========================\n" +
                "Terima kasih 🙏";

        builder.setMessage(receipt);

        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            if (barcodeScanner != null) {
                barcodeScanner.resume();
            }
        });

        builder.show();
    }
//private void showPaymentSuccessScreen(String qrText) {
//    ReceiptDialogFragment receiptFragment = ReceiptDialogFragment.newInstance(qrText);
//    requireActivity().getSupportFragmentManager()
//            .beginTransaction()
//            .replace(R.id.fragmentContainer, receiptFragment) // ganti dengan ID container mu
//            .addToBackStack(null) // supaya bisa balik pakai tombol back
//            .commit();
//}


    // Tambahkan helper untuk dipanggil dari dialog
    public void resumeScanner() {
        isScanned = false;
        if (barcodeScanner != null) {
            barcodeScanner.resume();
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        // cek izin kamera
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            barcodeScanner.resume(); // langsung jalan
        } else {
            // minta izin kamera
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        barcodeScanner.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (barcodeScanner != null) {
            barcodeScanner.pause();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                barcodeScanner.resume(); // aktifkan scanner setelah izin diberikan
            } else {
                Toast.makeText(getContext(), "Izin kamera diperlukan", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // Contoh parsing QRIS sederhana
    private void parseQris(String qr) {
        // Biasanya QRIS pakai format EMVCo
        // contoh: 00020101021126680012COM.QRIS...
        if (qr.contains("COM.QRIS")) {
            Toast.makeText(getContext(), "✅ QRIS Valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "⚠️ Bukan QRIS", Toast.LENGTH_SHORT).show();
        }
    }


}