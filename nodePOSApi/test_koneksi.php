<?php
$serverName = "Rosi"; // Atau IP/hostname
$connectionOptions = array(
    "Database" => "temp",
    "Uid" => "adminuser",
    "PWD" => "123456",
    "Encrypt" => false, // untuk hindari masalah SSL
    "TrustServerCertificate" => true
);

// Koneksi
$conn = sqlsrv_connect($serverName, $connectionOptions);

// Uji koneksi
if ($conn) {
    echo "✔ Koneksi berhasil ke SQL Server!";
    sqlsrv_close($conn);
} else {
    echo "❌ Koneksi gagal.<br>";
    die(print_r(sqlsrv_errors(), true));
}
?>
