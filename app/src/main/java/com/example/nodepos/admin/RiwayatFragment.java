package com.example.nodepos.admin;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nodepos.R;
import com.example.nodepos.adapter.RiwayatPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RiwayatFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public RiwayatFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        // Set adapter ViewPager
        RiwayatPagerAdapter pagerAdapter = new RiwayatPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // Hubungkan TabLayout & ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) tab.setText("Ongoing");
                    else tab.setText("History");
                }).attach();

        return view;
    }
}
