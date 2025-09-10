package com.example.nodepos.config;

public class apiConfig {
//    cd D:\cloudflared
    // .\cloudflared.exe tunnel --url http://localhost:8080
//    cloudflared.exe tunnel --url http://localhost:80

    // Gunakan 10.0.2.2 untuk emulator mengakses localhost PC
    public static final String BASE_URL = "https://shadows-pen-documentation-devel.trycloudflare.com/nodePOSApi/";
    public static final String LOGIN_URL = BASE_URL + "login.php";
    public static final String REGISTER_URL = BASE_URL + "register_user.php";
}
