package com.example.nodepos.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodepos.R;
import com.example.nodepos.model.riwayatModel;
import com.google.android.material.button.MaterialButton;

import android.widget.TextView;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    private Context context;
    private List<riwayatModel> transactionList;



    public RiwayatAdapter(Context context, List<riwayatModel> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        riwayatModel transaction = transactionList.get(position);

        holder.tvOrderId.setText("ORDER ID : " + transaction.getOrderId());
        holder.tvStatus.setText("Status : " + transaction.getStatus());
        holder.tvTotal.setText("Total Amount : " + transaction.getTotalAmount());
        holder.tvDate.setText("Date : " + transaction.getDate());
        View indicator = holder.statusIndicator;

        // Ganti warna border sesuai status

        switch (transaction.getStatus()) {
            case "Delivered":
                indicator.setBackgroundTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.status_delivered))
                );
                break;
            case "gagal":
            case "Dibatalkan":
                indicator.setBackgroundTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.status_failed))
                );
                break;
            case "Processing":
                indicator.setBackgroundTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.status_process))
                );
                break;
            case "On Delivery":
                indicator.setBackgroundTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.status_deliver))
                );
                break;
            default:
                indicator.setBackgroundTintList(
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.status_default))
                );
                break;
        }

        // Event tombol tetap
        holder.btnDetails.setOnClickListener(v -> { /* TODO */ });
        holder.btnReorder.setOnClickListener(v -> { /* TODO */ });
//        holder.btnInvoice.setOnClickListener(v -> { /* TODO */ });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvStatus, tvTotal, tvDate;
        MaterialButton btnDetails, btnReorder, btnInvoice;
        View statusIndicator;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvDate = itemView.findViewById(R.id.tvDate);

            btnDetails = itemView.findViewById(R.id.btnDetails);
            btnReorder = itemView.findViewById(R.id.btnReorder);
//            btnInvoice = itemView.findViewById(R.id.btnInvoice);
            statusIndicator = itemView.findViewById(R.id.statusIndicator);
        }
    }
}