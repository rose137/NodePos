package com.example.nodepos.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nodepos.admin.HistoryFragment;
import com.example.nodepos.admin.OngoingFragment;

public class RiwayatPagerAdapter extends FragmentStateAdapter {

    public RiwayatPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new OngoingFragment(); // Tab 1
        } else {
            return new HistoryFragment(); // Tab 2
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Ongoing + History
    }
}
