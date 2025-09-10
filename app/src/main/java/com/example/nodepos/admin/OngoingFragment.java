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
        ongoingList = new ArrayList<>();
        ongoingList.add(new riwayatModel("ORD123", "On Delivery", "25000", "02-09-2025"));
        ongoingList.add(new riwayatModel("ORD124", "Processing", "18000", "03-09-2025"));

        adapter = new RiwayatAdapter(getContext(), ongoingList);
        rvOngoing.setAdapter(adapter);

        return view;
    }
}
