package com.example.nodepos.receipt;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nodepos.R;
import com.example.nodepos.admin.QrisScanFragment;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReceiptDialogFragment extends DialogFragment {
    private static final String ARG_QR_TEXT = "qr_text";
    private TextView tvDetail;


    public static ReceiptDialogFragment newInstance(String qrText) {
        ReceiptDialogFragment fragment = new ReceiptDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QR_TEXT, qrText);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receipt_dialog, container, false);

        tvDetail = view.findViewById(R.id.tvDetail);
        Button btnBack = view.findViewById(R.id.btnBack);
        // Ambil data dari Bundle
        Bundle args = getArguments();
        if (args != null) {
            String kode = args.getString("kode", "-");
            String metode = args.getString("metode", "QRIS");
            double subtotal = args.getDouble("subtotal", 0);
            double diskon = args.getDouble("diskon", 0);
            double pajak = subtotal * 0.1; // 10% pajak
            double total = subtotal - diskon + pajak;
            double diterima = args.getDouble("diterima", total);
            double kembalian = diterima - total;

            String tanggal = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                    .format(new Date());

            NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

            String receipt =
                    "Merchant : NodePos Store\n" +
                            "Metode   : " + metode + "\n" +
                            "Kode     : " + kode + "\n" +
                            "Tanggal  : " + tanggal + "\n" +
                            "-----------------------------\n" +
                            "Subtotal : " + rupiah.format(subtotal) + "\n" +
                            "Diskon   : " + rupiah.format(diskon) + "\n" +
                            "Pajak 10%: " + rupiah.format(pajak) + "\n" +
                            "Total    : " + rupiah.format(total) + "\n" +
                            "-----------------------------\n" +
                            "Pembayaran: " + metode + "\n" +
                            "Diterima  : " + rupiah.format(diterima) + "\n" +
                            "Kembalian : " + rupiah.format(kembalian) + "\n" +
                            "-----------------------------\n" +
                            "Status   : Sukses ✅\n" +
                            "Terima kasih 🙏";

            tvDetail.setText(receipt);
        }

        // Tombol kembali
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}
