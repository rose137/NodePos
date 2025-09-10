package com.example.nodepos.absen;

// AbsenLogAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nodepos.R;
import com.example.nodepos.model.absenModel;

import java.util.List;

public class absenLogAdapter extends RecyclerView.Adapter<absenLogAdapter.LogViewHolder> {
    private List<absenModel> logList;

    public absenLogAdapter(List<absenModel> logList) {
        this.logList = logList;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absen_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        absenModel log = logList.get(position);
        holder.textJenis.setText(log.getJenis());
        holder.textTanggal.setText(log.getTanggal());
        holder.textWaktu.setText(log.getWaktu());
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView textJenis,  textTanggal,textWaktu;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            textJenis = itemView.findViewById(R.id.textJenis);
            textTanggal = itemView.findViewById(R.id.textTanggal);
            textWaktu = itemView.findViewById(R.id.textWaktu);
        }
    }
    public void setData(List<absenModel> newLogs) {
        this.logList.clear();
        this.logList.addAll(newLogs);
        notifyDataSetChanged();
    }

}
