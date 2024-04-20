<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
include("koneksi.php");
$username = $_POST['username'];
$password = $_POST['password'];

$stmt = $conn->prepare(
    "SELECT * FROM user WHERE username=? and password=?"
);

$stmt->bind_param("ss", $username, $password);
$stmt->execute();
$result=$stmt->get_result();
if ($result->num_rows > 0) {
    $r=mysqli_fetch_assoc($result);
    $arr = ["result" => "success", "data" => $r];
} else {
    $arr = ["result" => "Error", "message" => "Username atau password salah"];
}
echo json_encode($arr);
$stmt->close();
$conn->close();
?>