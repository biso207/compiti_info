<?php
// Connessione al database con PDO (base)
$servername = "localhost";
$username   = "lucabiso";
$password   = "";
$dbName     = "my_lucabiso";

try {
    $conn = new PDO(
        "mysql:host=$servername;dbname=$dbName;charset=utf8mb4",
        $username,
        $password,
        [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
    );
} catch (PDOException $e) {
    die("Connection failed: " . $e->getMessage());
}
