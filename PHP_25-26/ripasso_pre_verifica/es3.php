<html>
    <head>
        <title>Es con lavoro sul DB</title>
    </head>

    <body>
        <form action="es3.php" method="POST">
            <h2><b>Crea Utente</b></h2>

            <label>Nome</label>
            <input name="nome"><br>

            <label>Cognome</label>
            <input name="cognome"><br>

            <label>Anno di Nascita</label>
            <input type="number" name="anno"><br>

            <input type="submit" value="Crea">
        </form>
    </body>
</html>


<?php
ini_set("display_errors", 1);

// connessione database
$conn = null;
try {
    $conn = new PDO("mysql:host=localhost;dbname=my_lucabiso", 'lucabiso', '');
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully";
} catch (PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $nome = $_POST["nome"];
    $cognome = $_POST["cognome"];
    $annoNascita = intval($_POST["anno"]);


    // check validità nome e cognome
    $isValid=true;
    if (!is_string($nome) || !is_string($cognome)) $isValid=false;
    if ($annoNascita < 1930 || $annoNascita > 2025) $isValid=false;

    // query per inviare i dati
    if ($isValid) {
        $query = "INSERT into esRipasso (nome, cognome, annoNascita) VALUES ($nome, $cognome, $annoNascita)";

        if ($conn->query($query)) {
            echo "Utente creato con successo";
        }
        else echo "Errore: $conn->error";
    }
    else echo "Input invalidi";
}

?>