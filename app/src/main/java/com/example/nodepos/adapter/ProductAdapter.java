package com.example.nodepos.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nodepos.R;
import com.example.nodepos.model.productModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<productModel> productList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(productModel product);
    }

    public ProductAdapter(List<productModel> productList, OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(productList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, price;
        ImageView image;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvProductName);
            category = itemView.findViewById(R.id.tvCategory);
            price = itemView.findViewById(R.id.tvPrice);
            image = itemView.findViewById(R.id.imgProduct);
        }

        public void bind(productModel product, OnItemClickListener listener) {
            name.setText(product.getName());
            category.setText(product.getKategoriId());
            price.setText("Rp " + product.getPrice());

            Glide.with(itemView.getContext()).load(product.getImageUrl()).into(image);

            itemView.setOnClickListener(v -> listener.onItemClick(product));
        }
    }
}
