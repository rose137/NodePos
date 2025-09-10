
package com.example.nodepos.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nodepos.R;
import com.example.nodepos.admin.adminDashboardActivity;
import com.example.nodepos.config.apiConfig;
import com.example.nodepos.helper.DBHelper;
import com.example.nodepos.kasir.kasirDashboarActivity;
import com.example.nodepos.model.userModel;
import com.example.nodepos.register.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
 Button btnTambahPengguna ;
 Button login;
 EditText nik,password, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView dummyNik = new TextView(this);
        TextView dummyNikAdmin = new TextView(this);
        TextView dummyPassword = new TextView(this);

        btnTambahPengguna = findViewById(R.id.btnRegister);
        nik = findViewById(R.id.etNIK);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        etPassword = findViewById(R.id.etPassword);

        dummyNik.setText("123");
        dummyNikAdmin.setText("1234");
        dummyPassword.setText("secret");




        etPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_END = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                    // Cek kondisi saat ini
                    if (etPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                        // Show password
                        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_eye_open, 0);
                    } else {
                        // Hide password
                        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_eye_closed, 0);
                    }

                    // Cursor tetap di akhir teks
                    etPassword.setSelection(etPassword.getText().length());
                    return true;
                }
            }
            return false;
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    String nikInput = nik.getText().toString();
                    String passwordInput = password.getText().toString();

                    if (nikInput.isEmpty() || passwordInput.isEmpty()) {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setMessage("Nik dan Password tidak boleh kosong!")
                                .setPositiveButton("OK", null)
                                .show();
                    } else {
                        startActivity(new Intent(LoginActivity.this, adminDashboardActivity.class));
                        finish();

                       // new LoginTask().execute(nikInput, passwordInput);
                    }
                } catch (Exception e) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Terjadi Kesalahan")
                            .setMessage("Error: " + e.getMessage())
                            .setPositiveButton("OK", null)
                            .show();
                    Log.e("LoginError", "Error saat login", e);
                }
            }

        });


        btnTambahPengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(LoginActivity.this, "Login", "Mohon tunggu...");
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

                    String fullName = userData.getString("fullName");
                    String role = userData.getString("role");
                    String uuid = userData.getString("uuid");
                    String nik = userData.getString("nik");
                    String firstName = userData.getString("firstName");
                    String lastName = userData.getString("lastName");
                    String password = userData.getString("password");
                    String email = userData.getString("email");
                    String position = userData.getString("position");
                    String isActive = userData.getString("isActive");

                    DBHelper dbHelper = new DBHelper(LoginActivity.this);
                    userModel user = new userModel();
                    user.setData(userData.toString()); // jika ada string JSON mentah
                    user.setUuid(uuid);
                    user.setNik(nik);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setFullName(fullName);
                    user.setEmail(email);
                    user.setPassword(password); // password sudah di-hash
                    user.setPosition(position);
                    user.setRole(role);
                    user.setIsActive(isActive);


                    dbHelper.clearAll(); // hapus user sebelumnya jika hanya 1 user
                    dbHelper.insertUser(user);

                    // Simpan ke SharedPreferences jika perlu
                    SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("uuid", uuid);
                    editor.putString("fullName", fullName);
                    editor.putString("role", role);
                    editor.apply();




                    Toast.makeText(LoginActivity.this, "Selamat datang, " + fullName, Toast.LENGTH_LONG).show();
                if(role.equals("R01")){
                    startActivity(new Intent(LoginActivity.this, adminDashboardActivity.class));
                    finish();
                }else if (role.equals("R02")){
                    startActivity(new Intent(LoginActivity.this, kasirDashboarActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "cek kembali role anda, ", Toast.LENGTH_LONG).show();

                }
                    // Contoh: lanjut ke halaman utama
                    // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    // finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Login gagal: " + status, Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Toast.makeText(LoginActivity.this, "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
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