<?php
header('Content-Type: application/json');

// Ambil data JSON dari body request
$input = json_decode(file_get_contents("php://input"), true);

// Include file konfigurasi database
require_once "db_config.php";

// Ambil nilai parameter
$nik = $input['nik'] ?? '';
$ip_address = $_SERVER['REMOTE_ADDR'] ?? 'unknown';
$user_agent = $_SERVER['HTTP_USER_AGENT'] ?? 'unknown';

// Panggil stored procedure
$sql = "{CALL sp_get_user_data(?, ?, ?)}";
$params = [$nik, $ip_address, $user_agent];

$stmt = sqlsrv_query($conn, $sql, $params);

if ($stmt === false) {
    echo json_encode([
        "status" => "error",
        "message" => "Gagal eksekusi stored procedure",
        "sqlsrv_errors" => sqlsrv_errors()
    ]);
    exit;
}

// Ambil hasil JSON dari stored procedure
$row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_NUMERIC);
echo $row[0]; // JSON langsung dari SQL Server
