package com.example.nodepos.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodepos.R;
import com.example.nodepos.adapter.CartAdapter;
import com.example.nodepos.model.productModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView totalText;
    private Button btnBayar;
    private CartAdapter adapter;
    private ImageButton btnBack, btnCheckListAll, btnDeleteAll;
    private boolean isChecklistMode = false;
    private List<productModel> cartList; // field utama

    public CartFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recyclerCart);
        totalText = view.findViewById(R.id.txtTotal);
        btnBayar = view.findViewById(R.id.btnBayar);

        // Toolbar & tombol
        Toolbar toolbar = view.findViewById(R.id.toolbarCart);
        btnBack = view.findViewById(R.id.btnBack);
        btnCheckListAll = view.findViewById(R.id.btnCheckListAll);
        btnDeleteAll = view.findViewById(R.id.btnDeleteAll);

        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());

        // Ambil list dari CartManager (FIX: pakai field, bukan variabel lokal)
        cartList = CartManager.getInstance().getCartList();

        adapter = new CartAdapter(getContext(), cartList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Checklist All Button
        btnCheckListAll.setOnClickListener(v -> {
            isChecklistMode = !isChecklistMode; // toggle mode
            adapter.setChecklistMode(isChecklistMode);

            if (isChecklistMode) {
                // Checklist semua item
                for (productModel p : cartList) {
                    p.setChecked(true);
                }
            } else {
                // Uncheck semua item
                for (productModel p : cartList) {
                    p.setChecked(false);
                }
            }

            adapter.notifyDataSetChanged();
        });

        // Delete All Button
        btnDeleteAll.setOnClickListener(v -> {
            List<productModel> toRemove = new ArrayList<>();
            for (productModel p : cartList) {
                if (p.isChecked()) {
                    toRemove.add(p);
                }
            }
            cartList.removeAll(toRemove);
            CartManager.getInstance().setCartList(cartList);
            adapter.notifyDataSetChanged();
            updateTotal();
        });

        btnBayar.setOnClickListener(v -> showReceipt());

        updateTotal();

        return view;
    }

    public void addProductToCart(productModel product) {
        // tambahkan langsung ke CartManager
        CartManager.getInstance().addCart(product);
        adapter.notifyDataSetChanged();
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (productModel p : CartManager.getInstance().getCartList()) {
            total += p.getPrice() * p.getQty();
        }
        totalText.setText("Total: Rp " + total);
    }

    private void showReceipt() {
        List<productModel> cartList = CartManager.getInstance().getCartList();

        double subtotal = 0;
        for (productModel p : cartList) {
            subtotal += p.getPrice() * p.getQty();
        }
        double discount = subtotal * 0.05; // contoh 5%
        double tax = subtotal * 0.10;
        double total = subtotal - discount + tax;

        StringBuilder receipt = new StringBuilder();
        receipt.append("===== STRUK PEMBAYARAN =====\n\n");
        for (productModel p : cartList) {
            receipt.append(p.getName())
                    .append(" - Rp ").append(p.getPrice() * p.getQty()).append("\n");
        }
        receipt.append("\nSubtotal : Rp ").append(subtotal);
        receipt.append("\nDiskon   : Rp ").append(discount);
        receipt.append("\nPajak 10%: Rp ").append(tax);
        receipt.append("\nTotal    : Rp ").append(total);
        receipt.append("\n\nPembayaran: Cash");
        receipt.append("\nKembalian : Rp 0");
        receipt.append("\n=============================");

        new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Struk")
                .setMessage(receipt.toString())
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        updateTotal();

        // Listener untuk update total saat qty diubah
        adapter.setOnCartChangeListener(total -> {
            totalText.setText("Total: Rp " + total);
        });
    }
}
