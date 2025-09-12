package com.example.nodepos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nodepos.R;
import com.example.nodepos.model.riwayatModel;
import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.ViewHolder> {

    private List<riwayatModel> data;

    public LaporanAdapter(List<riwayatModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_riwayat, parent, false); // gunakan layout item yang sudah ada
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        riwayatModel item = data.get(position);
        holder.tvOrderId.setText("Order ID : " + item.getOrderId());
        holder.tvStatus.setText(item.getStatus());
        holder.tvTotal.setText("Rp. " + item.getTotalAmount());
        holder.tvDate.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvStatus, tvTotal, tvDate;
        ViewHolder(View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvStatus  = itemView.findViewById(R.id.tvStatus);
            tvTotal   = itemView.findViewById(R.id.tvTotal);
            tvDate    = itemView.findViewById(R.id.tvDate);
        }
    }
}
