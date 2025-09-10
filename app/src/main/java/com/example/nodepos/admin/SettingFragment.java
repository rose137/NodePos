package com.example.nodepos.admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.TextView;
import android.widget.Toast;

import com.example.nodepos.R;
import com.example.nodepos.login.LoginActivity;
import com.google.android.material.card.MaterialCardView;

public class SettingFragment extends Fragment {
    MaterialCardView cardKelolaAkun, cardGantiPassword, cardLogout;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardKelolaAkun = view.findViewById(R.id.cardKelolaAkun);
        cardGantiPassword = view.findViewById(R.id.cardGantiPassword);
        cardLogout = view.findViewById(R.id.cardLogout);

        // Handle klik
        cardKelolaAkun.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), KelolaAkunActivity.class);
            startActivity(intent);

            Toast.makeText(getContext(), "Kelola Akun diklik", Toast.LENGTH_SHORT).show();
        });

        cardGantiPassword.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Ganti Password diklik", Toast.LENGTH_SHORT).show();
        });

        cardLogout.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Logout diklik", Toast.LENGTH_SHORT).show();
        });


        // Logout
        cardLogout.setOnClickListener(v -> {
            // Hapus session dari SharedPreferences
            SharedPreferences prefs = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // hapus semua data (uuid, fullName, role)
            editor.apply();

            // Arahkan ke LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            Toast.makeText(getActivity(), "Logout berhasil", Toast.LENGTH_SHORT).show();
        });

    }

}