<?php
// creazione cartella uploads
if (!file_exists("uploads")) mkdir("uploads");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $target_dir = "uploads/";
    $target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
    $uploadOk = 1; // flag per l'upload

    // check esistenza file
    if (file_exists($target_file)) {
        echo "File già esistente.";
        $uploadOk = 0;
    }


    // check flag
    if ($uploadOk == 0) {
        echo "<br><b>Il file non è stato caricato.</b>";
    // tutto ok => carica file
    } else {
        if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
            echo "<b>File caricato con successo</b>";
        } else {
            echo "<br><b>Mi spiace, c'è stato un errore durante l'upload.</b>";
        }
    }
}
