<?php
require "fruit.php";

// percorso del magazzino json
$MAGAZZINO_PATH = __DIR__ . DIRECTORY_SEPARATOR . "magazzino.json";

// --- gestione pulsanti + / - (aggiorna il json e ricarica la pagina) ---
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $fruitName = isset($_POST["fruit_name"]) ? trim((string)$_POST["fruit_name"]) : "";
    $deltaRaw  = isset($_POST["delta"]) ? (string)$_POST["delta"] : "0";
    $delta     = (int)$deltaRaw;

    // accetto solo +1 o -1
    if ($fruitName !== "" && ($delta === 1 || $delta === -1)) {
        $jsonText = @file_get_contents($MAGAZZINO_PATH);
        $data = is_string($jsonText) ? json_decode($jsonText, true) : null;

        if (is_array($data) && isset($data["fruits"]) && is_array($data["fruits"])) {
            for ($i = 0; $i < count($data["fruits"]); $i++) {
                if (isset($data["fruits"][$i]["name"]) && $data["fruits"][$i]["name"] === $fruitName) {
                    $currentQty = isset($data["fruits"][$i]["quantity"]) ? (int)$data["fruits"][$i]["quantity"] : 0;
                    $newQty = $currentQty + $delta;
                    if ($newQty < 0) $newQty = 0; // non vado mai sotto 0
                    $data["fruits"][$i]["quantity"] = $newQty;
                    break;
                }
            }

            // riscrittura sul file (con lock)
            $encoded = json_encode($data, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
            if ($encoded !== false) {
                file_put_contents($MAGAZZINO_PATH, $encoded, LOCK_EX);
            }
        }
    }

    // redirect per evitare il "ri-invio" del form al refresh
    header("Location: " . $_SERVER["PHP_SELF"]);
    exit;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <title>Ortofrutta</title>
</head>
<body>
<header class="header">
    <h1>Ortofrutta</h1>
    <p>Magazzino frutta (dal JSON)</p>
</header>

<main class="container">
    <section class="grid">
        <?php
        // lettura del file json
        $jsonText = @file_get_contents($MAGAZZINO_PATH);
        $data = is_string($jsonText) ? json_decode($jsonText, true) : null;

        // se il file non esiste o il json è rotto, evito errori
        if (!is_array($data) || !isset($data["fruits"]) || !is_array($data["fruits"])) {
            $data = ["fruits" => []];
            echo "<p style='padding:12px; background:#ffe9e9; border-radius:10px;'>Errore: impossibile leggere <b>magazzino.json</b>.</p>";
        }

        // recupero elementi dal json e creazione oggetti
        $bancarella = [];
        foreach ($data["fruits"] as $f) {
            $bancarella[] = new Fruit($f["name"], $f["color"], $f["taste"], $f["quantity"]);
        }

        // stampa elementi
        foreach ($bancarella as $fruit) $fruit->print_info();
        ?>
    </section>
</main>
</body>
</html>
