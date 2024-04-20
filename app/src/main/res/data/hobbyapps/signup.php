<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
include("koneksi.php");

$username = $_POST['username'];
$password = $_POST['password'];
$fname = $_POST['nama_depan'];
$lname = $_POST['nama_belakang'];
$email = $_POST['email'];

$sql = "INSERT INTO user (username, password, nama_depan, nama_belakang, email) VALUES (?, ?, ?, ?, ?)";
$stmt = $conn->prepare($sql);

// Check if the statement was prepared properly
if ($stmt) {
    $stmt->bind_param("sssss", $username, $password, $fname, $lname, $email);

    if ($stmt->execute()) {
        echo json_encode([
            "status" => "success",
            "message" => "Berhasil menambahkan user"
        ]);
    } else {
        echo "Error: " . $stmt->error;
    }

    $stmt->close();
} else {
    echo "Error: Unable to prepare statement";
}

$conn->close();
?>
