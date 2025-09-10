package com.example.nodepos.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.nodepos.R;
import com.example.nodepos.absen.absensiActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashboardFragment extends Fragment {
    BarChart barChart;
    TextView textHari, textTanggal, textJam;
    ImageView imageTimeIcon;
    Handler timeHandler = new Handler();
    Runnable timeRunnable;
    CardView absen;

    public DashboardFragment() {
        // Diperlukan constructor kosong
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Ganti layout ini dengan layout fragment kamu
         View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        absen = view.findViewById(R.id.quickActionCard);
        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), absensiActivity.class);
                startActivity(intent);
            }
        });

         barChart = view.findViewById(R.id.barChart);
        setupBarChart();

        textHari = view.findViewById(R.id.textHari);
        textTanggal = view.findViewById(R.id.textTanggal);
        textJam = view.findViewById(R.id.textJam);
        imageTimeIcon = view.findViewById(R.id.imageTimeIcon);


        Date now = new Date();
        SimpleDateFormat sdfHari = new SimpleDateFormat("EEEE", new Locale("id", "ID"));
        SimpleDateFormat sdfTanggal = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        textHari.setText(sdfHari.format(now));
        textTanggal.setText(sdfTanggal.format(now));
        // Update jam setiap detik
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                Date current = new Date();
                SimpleDateFormat sdfJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                textJam.setText(sdfJam.format(current));

                // Ganti ikon jika perlu
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                if (hour >= 6 && hour < 18) {
                    imageTimeIcon.setImageResource(R.drawable.ic_sun); // Siang
                } else {
                    imageTimeIcon.setImageResource(R.drawable.ic_moon); // Malam
                }

                timeHandler.postDelayed(this, 1000);
            }
        };
        timeHandler.post(timeRunnable);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timeHandler.removeCallbacks(timeRunnable); // Hentikan handler saat View dihancurkan
    }


    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 10));
        entries.add(new BarEntry(1, 14));
        entries.add(new BarEntry(2, 8));
        entries.add(new BarEntry(3, 18));
        entries.add(new BarEntry(4, 12));
        entries.add(new BarEntry(5, 20));
        entries.add(new BarEntry(6, 9));

        BarDataSet dataSet = new BarDataSet(entries, "Profile Discovery");
        dataSet.setColor(getResources().getColor(R.color.white));
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.animateY(1000);

        String[] days = {"Apr 1", "Apr 2", "Apr 3", "Apr 4", "Apr 5", "Apr 6", "Apr 7"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.WHITE);
    }
}
