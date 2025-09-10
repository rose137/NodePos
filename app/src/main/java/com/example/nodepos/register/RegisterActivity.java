package com.example.nodepos.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nodepos.R;
import com.example.nodepos.config.apiConfig;
import com.example.nodepos.login.LoginActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class RegisterActivity extends AppCompatActivity {
EditText password,konfrimPassword,nik,email,firstName,lastName,fullName;
Button daftar;
CardView errorMessage;
    URL url;

    {
        try {
            url = new URL(apiConfig.REGISTER_URL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    String endpoint = url.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.etRegisterFirstname);
        lastName = findViewById(R.id.etRegisterlastname);
        fullName = findViewById(R.id.etRegisterfullname);
        password = findViewById(R.id.etRegisterPassword);
        konfrimPassword = findViewById(R.id.etRegisterConfirm);
        errorMessage = findViewById(R.id.cardError);
        daftar = findViewById(R.id.btnRegisterSubmit);
        nik = findViewById(R.id.etRegisterNIK);
        email = findViewById(R.id.etRegisterEmail);
        Spinner spinnerRole = findViewById(R.id.spinnerRole);
        Spinner spinnerPosition = findViewById(R.id.spinnerPosition);


        ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.role_array,
                R.layout.spinner_item
        );

        roleAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerRole.setAdapter(roleAdapter);

        ArrayAdapter<CharSequence> positionAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.position_array,
                R.layout.spinner_item
        );

        positionAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerPosition.setAdapter(positionAdapter);


        daftar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if (nik.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() ||
                        fullName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show();

                }else{
                    if(!password.getText().toString().equals(konfrimPassword.getText().toString())){
                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("Peringatan")
                                .setMessage("Password dan Konfirmasi Password tidak cocok!")
                                .setPositiveButton("OK", null)
                                .show();
                    }else{
                        String positionCode = "";
                        String roleCode = "";
                        String position = spinnerPosition.getSelectedItem().toString();
                        String role = spinnerRole.getSelectedItem().toString();
                        if (position.equals("Kasir")) {
                            positionCode = "P01";
                        } else if (position.equals("Manager")) {
                            positionCode = "P02";
                        }else if (position.equals("Supervisor")) {
                            positionCode = "P03";
                        }  else {
                            positionCode = "P00"; // default jika tidak dikenali
                        }

                        if (role.equals("Admin")) {
                            roleCode = "R01";
                        } else if (role.equals("Kasir")) {
                            roleCode = "R02";
                        } else if (role.equals("Super Admin")) {
                            roleCode = "R03";
                        }else if (role.equals("Guest")) {
                            roleCode = "R04";
                        }else {
                            roleCode = "R00"; // default jika tidak dikenali
                        }
                        new RegisterTask(RegisterActivity.this).execute(
                                nik.getText().toString(),
                                firstName.getText().toString(),
                                lastName.getText().toString(),
                                fullName.getText().toString(),
                                password.getText().toString(),
                                email.getText().toString(),
                                positionCode,
                                roleCode
                        );
                    }
                }

            }
        });

    }

    private class RegisterTask extends AsyncTask<String, Void, String> {
        private Context context;
        private int responseCode = -1;
        private String responseMessage = "";

        public RegisterTask(Context context) {
            this.context = context;
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String postData = "nik=" + URLEncoder.encode(params[0], "UTF-8") +
                        "&firstName=" + URLEncoder.encode(params[1], "UTF-8") +
                        "&lastName=" + URLEncoder.encode(params[2], "UTF-8") +
                        "&fullName=" + URLEncoder.encode(params[3], "UTF-8") +
                        "&password=" + URLEncoder.encode(params[4], "UTF-8") +
                        "&email=" + URLEncoder.encode(params[5], "UTF-8") +
                        "&position=" + URLEncoder.encode(params[6], "UTF-8") +
                        "&role=" + URLEncoder.encode(params[7], "UTF-8");

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(postData);
                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
                } else {
                    return "Server Error: " + responseCode;
                }

            } catch (Exception e) {
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (responseCode == 200) {
                // Sukses: Tampilkan dialog dan kembali ke LoginActivity
                new AlertDialog.Builder(context)
                        .setTitle("Sukses")
                        .setMessage("Registrasi berhasil!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivity(intent);
                                ((Activity) context).finish();
                            }
                        })
                        .show();
            } else {
                // Gagal: Tampilkan pesan error dari server
                new AlertDialog.Builder(context)
                        .setTitle("Gagal")
                        .setMessage("Registrasi gagal:\n" + responseMessage)
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    }
}