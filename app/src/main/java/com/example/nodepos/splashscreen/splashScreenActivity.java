package com.example.nodepos.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.nodepos.MainActivity;
import com.example.nodepos.R;
import com.example.nodepos.admin.adminDashboardActivity;
import com.example.nodepos.login.LoginActivity;

public class splashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 2000; // durasi splash: 2 detik
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Handler untuk delay ke MainActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, adminDashboardActivity.class);

            startActivity(intent);
            finish(); // agar tidak bisa kembali ke splash
        }, SPLASH_DELAY);

    }
}