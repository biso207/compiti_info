<?php
// creazione cartella uploads
if (!file_exists("uploads")) mkdir("uploads");

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $num_file = count($_FILES["fileToUpload"]["name"]); // numero file caricati

    for ($i = 0; $i < $num_file; $i++) {
        $target_dir = "uploads/";
        $target_file = $target_dir . basename($_FILES["fileToUpload"]["name"][$i]);
        $uploadOk = true; // flag per l'upload

        // check esistenza file
        if (file_exists($target_file)) {
            echo "<br>File già esistente.";
            $uploadOk = false;
        }


        // check flag
        if (!$uploadOk) {
            echo "<br><p style='color:red'><b>Il file non è stato caricato.</b></p>";
            // tutto ok => carica file
        } else {
            if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"][$i], $target_file)) {
                echo "<br><p style='color:green'><b>File caricato con successo</b></p>";
            } else {
                echo "<br><p style='color:red'><b>Mi spiace, c'è stato un errore durante l'upload.</b></p>";
            }
        }
    }
}


