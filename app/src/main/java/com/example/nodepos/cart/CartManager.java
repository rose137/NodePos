package com.example.nodepos.cart;

import android.os.Build;

import com.example.nodepos.model.productModel;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<productModel> cartList;

    private CartManager() {
        cartList = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public List<productModel> getCartList() {
        return cartList;
    }

    public void addCart(productModel product) {
        // cek apakah sudah ada di cart
        for (productModel p : cartList) {
            if (p.getId().equals(product.getId())) {
                p.setQty(p.getQty() + 1); // tambah qty jika sudah ada
                return;
            }
        }
        // jika belum ada, tambahkan baru
        product.setQty(1); // qty awal 1
        cartList.add(product);
    }

    // method untuk update cart (qty bisa diubah manual)
    public void updateCart(productModel product) {
        for (int i = 0; i < cartList.size(); i++) {
            productModel p = cartList.get(i);
            if (p.getId().equals(product.getId())) {
                cartList.set(i, product); // update qty & info lain
                return;
            }
        }
        // jika belum ada, tambahkan
        cartList.add(product);
    }

    // hitung total harga
    public int getTotalPrice() {
        int total = 0;
        for (productModel p : cartList) {
            total += p.getPrice() * p.getQty();
        }
        return total;
    }

    // hapus produk
    public void removeCart(productModel product) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cartList.removeIf(p -> p.getId().equals(product.getId()));
        }
    }

    // clear cart
    public void clearCart() {
        cartList.clear();
    }
}
