package com.example.nodepos.absen;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nodepos.R;
import com.example.nodepos.helper.DBHelper;
import com.example.nodepos.model.absenModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class absensiActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST = 1;
    private static final double LAT_KANTOR =-6.2229616;
    private static final double LNG_KANTOR = 106.6785014;
    private static final float RADIUS = 1000.0f;

    private FusedLocationProviderClient fusedLocationClient;
    private Button btnCheckIn, btnCheckOut;
    private TextView textLokasiStatus;
    private List<absenModel> logList = new ArrayList<>();
    private absenLogAdapter logAdapter;
    private DBHelper dbHelper;
    TextView textHari, textTanggal, textJam;
    ImageView imageTimeIcon;
    Handler timeHandler = new Handler();
    Runnable timeRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);


        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        textLokasiStatus = findViewById(R.id.textLokasiStatus);
        dbHelper = new DBHelper(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        textHari = findViewById(R.id.textHari);
        textTanggal = findViewById(R.id.textTanggal);
        textJam = findViewById(R.id.textJam);
        imageTimeIcon = findViewById(R.id.imageTimeIcon);

        Date now = new Date();
        SimpleDateFormat sdfHari = new SimpleDateFormat("EEEE", new Locale("id", "ID"));
        SimpleDateFormat sdfTanggal = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        textHari.setText(sdfHari.format(now));
        textTanggal.setText(sdfTanggal.format(now));
        // Update jam setiap detik


        List<absenModel> logList = dbHelper.getAllLogs();
    // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerRiwayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        logAdapter = new absenLogAdapter(logList);
        recyclerView.setAdapter(logAdapter);
        loadAbsensiLog();

        // Minta izin lokasi
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
        }

        btnCheckIn.setOnClickListener(v -> prosesAbsen("Check-In"));
        btnCheckOut.setOnClickListener(v -> prosesAbsen("Check-Out"));


        timeRunnable = new Runnable() {
            @Override
            public void run() {
                Date current = new Date();
                SimpleDateFormat sdfJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                textJam.setText(sdfJam.format(current));

                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                if (hour >= 6 && hour < 18) {
                    imageTimeIcon.setImageResource(R.drawable.ic_sun);
                } else {
                    imageTimeIcon.setImageResource(R.drawable.ic_moon);
                }

                timeHandler.postDelayed(this, 1000);
            }
        };

        timeHandler.post(timeRunnable);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timeHandler != null && timeRunnable != null) {
            timeHandler.removeCallbacks(timeRunnable);
        }
    }

    private void prosesAbsen(String jenisAbsen) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Izin lokasi belum diberikan!", Toast.LENGTH_SHORT).show();
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        float[] distance = new float[1];
                        Location.distanceBetween(
                                location.getLatitude(), location.getLongitude(),
                                LAT_KANTOR, LNG_KANTOR, distance
                        );
                        Date now = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
                        boolean dalamArea = distance[0] <= RADIUS;
                        String status = dalamArea ? "Dalam Area" : "Di Luar Area";
                        String waktu = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                        String tanggal = sdf.format(now);

                        textLokasiStatus.setText("Status: " + status + "\n" + jenisAbsen + " @ " + waktu);

                        if (dalamArea) {
                            // 1. Inisialisasi dbHelper terlebih dahulu
                            DBHelper dbHelper = new DBHelper(this);

                            // 2. Simpan absen ke database
                            dbHelper.insertAbsen(jenisAbsen,tanggal, waktu);

                            // 3. Ambil data dari database dan tampilkan di RecyclerView
                            List<absenModel> logList = dbHelper.getAllLogs();
                            absenLogAdapter logAdapter = new absenLogAdapter(logList);

                            RecyclerView recyclerView = findViewById(R.id.recyclerRiwayat);
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));
                            recyclerView.setAdapter(logAdapter);


                            // 4. Tampilkan toast
                            Toast.makeText(this, jenisAbsen + " berhasil pada " + waktu, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(this, "Anda berada di luar radius absen!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        textLokasiStatus.setText("Status: Gagal mendapatkan lokasi");
                    }
                })
                .addOnFailureListener(e -> textLokasiStatus.setText("Status: Gagal lokasi - " + e.getMessage()));
    }

    private void loadAbsensiLog() {
        List<absenModel> logs = dbHelper.getAllLogs(); // ambil data dari DB
        logAdapter.setData(logs); // panggil method khusus di adapter
    }

}
