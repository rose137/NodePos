<?php
require_once 'db_config.php';

$nik = $_POST['nik'];
$firstName = $_POST['firstName'];
$lastName = $_POST['lastName'];
$fullName = $_POST['fullName'];
$password = $_POST['password'];
$email = $_POST['email'];
$position = $_POST['position'];
$role = $_POST['role'];
$ip_address = $_SERVER['REMOTE_ADDR'];
$user_agent = $_SERVER['HTTP_USER_AGENT'];

$sql = "{CALL sp_set_register_user(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
$params = array(
    array($nik, SQLSRV_PARAM_IN),
    array($firstName, SQLSRV_PARAM_IN),
    array($lastName, SQLSRV_PARAM_IN),
    array($fullName, SQLSRV_PARAM_IN),
    array($password, SQLSRV_PARAM_IN),
    array($email, SQLSRV_PARAM_IN),
    array($position, SQLSRV_PARAM_IN),
    array($role, SQLSRV_PARAM_IN),
    array($ip_address, SQLSRV_PARAM_IN),
    array($user_agent, SQLSRV_PARAM_IN)
);

$stmt = sqlsrv_query($conn, $sql, $params);

if ($stmt === false) {
    echo json_encode([
        "status" => "error",
        "message" => "Gagal eksekusi stored procedure",
        "sqlsrv_errors" => sqlsrv_errors()
    ]);
    exit;
}

$response = [];

while ($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)) {
    // Gunakan nama default SQL Server
    $jsonColumn = 'JSON_F52E2B61-18A1-11d1-B105-00805F49916B';

    if (isset($row[$jsonColumn])) {
        $json = json_decode($row[$jsonColumn], true);
        $response = $json;
    }
}

echo json_encode($response);

?>
