package com.example.nodepos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodepos.R;
import com.example.nodepos.cart.CartManager;
import com.example.nodepos.model.productModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<productModel> cartList;
    private final Context context;
    private OnCartChangeListener listener;
    // di dalam class CartAdapter
    private boolean checklistMode = false; // default false



    public interface OnCartChangeListener {
        void onCartChanged(int total);
    }

    public void setOnCartChangeListener(OnCartChangeListener listener) {
        this.listener = listener;
    }

    public CartAdapter(Context context, List<productModel> cartList) {
        this.context = context;
        this.cartList = cartList;
    }
    // method untuk toggle checklist mode
    public void setChecklistMode(boolean mode) {
        this.checklistMode = mode;
    }



    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        productModel product = cartList.get(position);

        holder.txtProductName.setText(product.getName());
        holder.txtQty.setText(String.valueOf(product.getQty()));
        // Checkbox visibility
        holder.checkBox.setVisibility(checklistMode ? View.VISIBLE : View.GONE);
        holder.checkBox.setChecked(product.isChecked());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            product.setChecked(isChecked);
        });


        if (product.getQty() == 0){
            holder.txtPrice.setText("Rp " + (product.getPrice()));

        }else{
            holder.txtPrice.setText("Rp " + (product.getPrice() * product.getQty()));

        }


        // tombol minus
        holder.btnMinus.setOnClickListener(v -> {
            int adapterPos = holder.getAdapterPosition();
            if (adapterPos != RecyclerView.NO_POSITION) {
                productModel p = cartList.get(adapterPos);
                if (p.getQty() > 0) {
                    p.setQty(p.getQty() - 1);
                    notifyItemChanged(adapterPos);
                    notifyCartChanged(); // update total
                }
            }
        });

        // tombol plus
        holder.btnPlus.setOnClickListener(v -> {
            int adapterPos = holder.getAdapterPosition();
            if (adapterPos != RecyclerView.NO_POSITION) {
                productModel p = cartList.get(adapterPos);
                p.setQty(p.getQty() + 1);
                p.getQty();
                notifyItemChanged(adapterPos);
                notifyCartChanged(); // update total
            }
        });

    }

    private void notifyCartChanged() {
        if (listener != null) {
            listener.onCartChanged(CartManager.getInstance().getTotalPrice());
        }
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtQty, txtPrice;
        Button btnPlus, btnMinus;
        CheckBox checkBox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            checkBox = itemView.findViewById(R.id.checkBox); // pastikan ada di layout item_cart.xml
        }
    }
}
