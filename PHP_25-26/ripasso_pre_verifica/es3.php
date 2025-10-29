<?php

// connessione database bar
$conn = null;
try {
    $conn = new PDO("mysql:host=localhost;dbname=my_lucabiso", 'lucabiso', '');
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully";
} catch (PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}

$sql = "SELECT * FROM ordini_bar";
echo "<p>Descrizione e prezzo dei prodotti del bar:</p>";
foreach ($conn->query($sql) as $row) {
    echo "<p>" . "- " . $row['desc'] . " " . $row['prezzo'] . "</p>";
}

