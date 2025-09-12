package com.example.nodepos.admin;

import android.os.Build;
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
import com.example.nodepos.repository.historyListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
//        List<riwayatModel> historyList = historyListRepository.getRiwayatList();
        List<riwayatModel> historyList;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            historyList = historyListRepository.getRiwayatList()
                    .stream()
                    .filter(item -> "Delivered".equals(item.getStatus()) ||
                            "Dibatalkan".equals(item.getStatus()))
                    .collect(Collectors.toList());
        } else {
            historyList = new ArrayList<>();
            for (riwayatModel item : historyListRepository.getRiwayatList()) {
                if ("Delivered".equals(item.getStatus()) || "Dibatalkan".equals(item.getStatus())) {
                    historyList.add(item);
                }
            }
        }
        adapter = new RiwayatAdapter(getContext(), historyList);
        rvHistory.setAdapter(adapter);

        return view;
    }
}
