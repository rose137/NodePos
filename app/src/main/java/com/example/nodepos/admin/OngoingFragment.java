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

public class OngoingFragment extends Fragment {

    private RecyclerView rvOngoing;
    private RiwayatAdapter adapter;
    private List<riwayatModel> ongoingList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_order, container, false);

        rvOngoing = view.findViewById(R.id.rvOrders);
        rvOngoing.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data Ongoing
        List<riwayatModel> ongoingList;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ongoingList = historyListRepository.getRiwayatList()
                    .stream()
                    .filter(item -> "On Delivery".equals(item.getStatus()) ||
                            "Processing".equals(item.getStatus()))
                    .collect(Collectors.toList());
        } else {
            ongoingList = new ArrayList<>();
            for (riwayatModel item : historyListRepository.getRiwayatList()) {
                if ("On Delivery".equals(item.getStatus()) || "Processing".equals(item.getStatus())) {
                    ongoingList.add(item);
                }
            }
        }

        adapter = new RiwayatAdapter(getContext(), ongoingList);
        rvOngoing.setAdapter(adapter);

        return view;
    }
}
