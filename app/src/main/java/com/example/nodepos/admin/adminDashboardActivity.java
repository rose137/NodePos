package com.example.nodepos.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nodepos.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.components.XAxis;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class adminDashboardActivity extends AppCompatActivity {
    BarChart barChart;
    TextView textHari, textTanggal, textJam;
    ImageView imageTimeIcon;
    Handler timeHandler = new Handler();
    Runnable timeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new DashboardFragment()) // ganti dengan Fragment kamu
                .commit();

//        barChart = findViewById(R.id.barChart);
//    a    setupBarChart();

//        textHari = findViewById(R.id.textHari);
//        textTanggal = findViewById(R.id.textTanggal);
//        textJam = findViewById(R.id.textJam);
//        imageTimeIcon = findViewById(R.id.imageTimeIcon);

        // Set hari dan tanggal
//        Date now = new Date();
//        SimpleDateFormat sdfHari = new SimpleDateFormat("EEEE", new Locale("id", "ID"));
//        SimpleDateFormat sdfTanggal = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
//        textHari.setText(sdfHari.format(now));
//        textTanggal.setText(sdfTanggal.format(now));

        // Update jam setiap detik
//        timeRunnable = new Runnable() {
//            @Override
//            public void run() {
//                Date current = new Date();
//                SimpleDateFormat sdfJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//                textJam.setText(sdfJam.format(current));
//
//                // Ganti ikon jika perlu
//                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//                if (hour >= 6 && hour < 18) {
//                    imageTimeIcon.setImageResource(R.drawable.ic_sun); // Siang
//                } else {
//                    imageTimeIcon.setImageResource(R.drawable.ic_moon); // Malam
//                }
//
//                timeHandler.postDelayed(this, 1000);
//            }
//        };
//        timeHandler.post(timeRunnable);

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_dashboard:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new DashboardFragment())
                            .commit();
                    return true;

                case R.id.nav_stock:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new StockFragment())
                            .commit();
                    return true;

                case R.id.nav_history:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new RiwayatFragment())
                            .commit();
                    return true;

                case R.id.nav_qrcode:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new QrisFragment())
                            .commit();
                    return true;

                case R.id.nav_pengaturan:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new SettingFragment())
                            .commit();
                    return true;
            }


            return false;
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        timeHandler.removeCallbacks(timeRunnable); // Stop update saat keluar
//    }

    private void setupBarChart() {
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(0, 10));
//        entries.add(new BarEntry(1, 14));
//        entries.add(new BarEntry(2, 8));
//        entries.add(new BarEntry(3, 18));
//        entries.add(new BarEntry(4, 12));
//        entries.add(new BarEntry(5, 20));
//        entries.add(new BarEntry(6, 9));
//
//        BarDataSet dataSet = new BarDataSet(entries, "Profile Discovery");
//        dataSet.setColor(getResources().getColor(R.color.white));
//        BarData data = new BarData(dataSet);
//        data.setBarWidth(0.9f);
//
//        barChart.setData(data);
//        barChart.setFitBars(true);
//        barChart.getDescription().setEnabled(false);
//        barChart.getAxisRight().setEnabled(false);
//        barChart.animateY(1000);
//
//        String[] days = {"Apr 1", "Apr 2", "Apr 3", "Apr 4", "Apr 5", "Apr 6", "Apr 7"};
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
//        xAxis.setDrawGridLines(false);
//        xAxis.setTextColor(Color.WHITE);
    }
}
