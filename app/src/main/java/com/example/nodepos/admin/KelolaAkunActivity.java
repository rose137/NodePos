package com.example.nodepos.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nodepos.R;
import com.example.nodepos.config.apiConfig;
import com.example.nodepos.helper.DBHelper;
import com.example.nodepos.kasir.kasirDashboarActivity;
import com.example.nodepos.login.LoginActivity;
import com.example.nodepos.model.userModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class KelolaAkunActivity extends AppCompatActivity {
String nikInput;
EditText nik,email,firstName,lastName,fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_akun);
        DBHelper dbHelper = new DBHelper(KelolaAkunActivity.this);
        List<userModel> users = dbHelper.getUsers(); // ambil semua user

        nik = findViewById(R.id.etNIK);
        email = findViewById(R.id.etEmail);
        firstName = findViewById(R.id.etFirstname);
        lastName = findViewById(R.id.etlastname);
        fullName = findViewById(R.id.etfullname);
        Spinner spinnerRole = findViewById(R.id.spinnerRole);
        Spinner spinnerPosition = findViewById(R.id.spinnerPosition);
        //new getUserTask().execute(nikInput);
        if(users != null && !users.isEmpty()){
            userModel user = users.get(0); // ambil user pertama

            nik.setText(user.getNik());
            email.setText(user.getEmail());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            fullName.setText(user.getFullName());
        }



        ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.role_array,
                R.layout.spinner_item
        );


        roleAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerRole.setAdapter(roleAdapter);

//        // Set default sesuai posisi user
//        if(user != null){
//            int spinnerPositionIndex = roleAdapter.getPosition(user.getPosition());
//            spinnerPosition.setSelection(spinnerPositionIndex);
//        }

        ArrayAdapter<CharSequence> positionAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.position_array,
                R.layout.spinner_item
        );

        positionAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerPosition.setAdapter(positionAdapter);

    }

    private class getUserTask extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(KelolaAkunActivity.this, "Login", "Mohon tunggu...");
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(result);
                String responseCode = jsonObject.getString("responseCode");
                String responseMessage = jsonObject.getString("responseMessage");
                String status = jsonObject.getString("status");
//                String data = jsonObject.getString("data");

                if (responseCode.equals("200") && responseMessage.equals("Success")) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    JSONObject userData = dataArray.getJSONObject(0);






                } else {
                    Toast.makeText(KelolaAkunActivity.this, "gagal: " + status, Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Toast.makeText(KelolaAkunActivity.this, "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String nik = params[0];
            String password = params[1];

            try {
                URL url = new URL(apiConfig.LOGIN_URL);

//                URL url = new URL("https://outdoor-promoting-mobility-genes.trycloudflare.com/nodePOSApi/login.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Buat JSON
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nik", nik);
                jsonParam.put("password", password);

                // Kirim JSON ke server
                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes("UTF-8"));
                os.flush();
                os.close();

                // Baca respon dari server
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString(); // bisa berupa "success" / JSON lain
                } else {
                    return "Server Error: " + responseCode;
                }

            } catch (Exception e) {
                return "Exception: " + e.getMessage();
            }
        }


        }

}