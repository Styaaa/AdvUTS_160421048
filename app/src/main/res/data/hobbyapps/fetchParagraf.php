<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
include("koneksi.php");

$id = $_POST['id'];
$stmt = $conn->prepare("SELECT * FROM paragraf WHERE berita_id=?");
$stmt->bind_param("i", $id);
$stmt->execute();
$result=$stmt->get_result();
$data = array();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    echo json_encode(array('result' => 'success', 'data' => $data));
} else {
    echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
    die();
}
$conn->close();
?>