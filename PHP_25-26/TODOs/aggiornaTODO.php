<?php
// parametri del db
$servername = "localhost";
$username = "lucabiso";
$password = "";
$database = "my_lucabiso";

// validazione dei parametri GET
if (!isset($_GET['id']) || !isset($_GET['stato'])) {
    die("Parametri mancanti");
}

$id = filter_var($_GET['id'], FILTER_VALIDATE_INT);
$stato = filter_var($_GET['stato'], FILTER_VALIDATE_INT);

// connessione al database
try {
    $conn = new PDO("mysql:host=$servername;dbname=$database", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Connection failed: " . $e->getMessage());
}

// aggiornamento della tabella
try {
    $stmt = $conn->prepare("UPDATE TODOs SET completato = :stato WHERE id = :id"); // preparazione della query
    $stmt->execute(['stato' => $stato, 'id' => $id]); // esecuzione della query
    header("Location: index.php?error=db"); // ritorno alla pagina principale
    die(); // fondamentale perché alcuni browser continuano a eseguire il codice dopo il redirect e rischiano il crash
} catch (PDOException $e) {
    die("Update failed: " . $e->getMessage());
}

