<?php
include("koneksi.php");
// extract($_POST);

$fname = $_POST['fname'];
$lname = $_POST['lname'];
$password = $_POST['password'];
$email = $_POST['email'];
$id = $_POST['id'];

$stmt = $conn->prepare(
  "UPDATE user SET password=?, nama_depan=?, nama_belakang=?, email=? WHERE id=?"
);
$stmt->bind_param("sssss", $password, $fname, $lname, $email, $id);
$stmt->execute();

if ($stmt->execute()) {
  $arr=["result"=>"success"];
} else {
  $arr= ["result"=>"error","message"=>"Gagal update data"];
}
echo json_encode($arr);
$stmt->close();
$conn->close();

?>