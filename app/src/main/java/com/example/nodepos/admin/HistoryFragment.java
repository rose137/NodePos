package com.example.nodepos.admin;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nodepos.R;
import com.example.nodepos.adapter.RiwayatAdapter;
import com.example.nodepos.model.riwayatModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private RecyclerView rvHistory;
    private RiwayatAdapter adapter;
    private List<riwayatModel> historyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_order, container, false);

        rvHistory = view.findViewById(R.id.rvOrders);
        rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data History
        historyList = new ArrayList<>();
        historyList.add(new riwayatModel("ORD090", "Delivered", "15000", "28-08-2025"));
        historyList.add(new riwayatModel("ORD091", "Delivered", "32000", "27-08-2025"));

        historyList.add(new riwayatModel("ORD096", "Dibatalkan", "32000", "27-08-2025"));
        adapter = new RiwayatAdapter(getContext(), historyList);
        rvHistory.setAdapter(adapter);

        return view;
    }
}
