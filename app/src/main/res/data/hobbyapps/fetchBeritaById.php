<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
include("koneksi.php");
$id = $_POST['id'];

$stmt = $conn->prepare(
    "SELECT * FROM berita WHERE id=?"
);

$stmt->bind_param("i", $id);
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