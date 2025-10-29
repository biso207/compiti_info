<html lang="it">
    <head>
        <title>Ordini al Bar</title>
    </head>

    <body>

    </body>
</html>


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

$sql = "SELECT nome_prodotto, descrizione, prezzo FROM ordini_bar";
echo "<p><b>Prodotti del bar</b></p>";
foreach ($conn->query($sql) as $row) {
    echo "<p>$row[nome_prodotto] <i>$row[descrizione]</i>: <b>$row[prezzo] €</b></p>";
}

?>