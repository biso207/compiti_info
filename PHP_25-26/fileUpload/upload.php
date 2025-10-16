<?php
// creazione cartella uploads
if (!file_exists("uploads")) mkdir("uploads");

// se la richiesta è POST (caricamento file)
if ($_SERVER["REQUEST_METHOD"] === "POST") {

    // stampa per file inesistenti
    if (empty($_FILES["fileToUpload"]["name"][0])) {
        echo "<p style='color:red'>Nessun file selezionato.</p>";
    }

    $num_file = count($_FILES["fileToUpload"]["name"]);

    // for per iterare sui file caricati
    for ($i = 0; $i < $num_file; $i++) {

        $target_dir = "uploads/";
        $fileName = basename($_FILES["fileToUpload"]["name"][$i]);
        $imageFileType = strtolower(pathinfo($_FILES["fileToUpload"]["name"][$i], PATHINFO_EXTENSION));
        $target_file = $target_dir . $fileName;

        $uploadOk = true;

        // verifica se è davvero un'immagine
        if (is_uploaded_file($_FILES["fileToUpload"]["tmp_name"][$i])) {
            $check = getimagesize($_FILES["fileToUpload"]["tmp_name"][$i]);
            if ($check === false) {
                echo "<p style='color:red'>Il file non è un'immagine.</p>";
                $uploadOk = false;
            }
        } else {
            echo "<p style='color:red'>Errore nel caricamento del file.</p>";
            $uploadOk = false;
        }

        // controlla il tipo di file
        if (!in_array($imageFileType, ["jpg", "jpeg", "png", "gif", "webp"])) {
            echo "<p style='color:red'>Sono permessi solo JPG, JPEG, PNG, GIF e WEBP.</p>";
            $uploadOk = false;
        }

        // controlla dimensione max (5 MB)
        if ($_FILES["fileToUpload"]["size"][$i] > 5000000) {
            echo "<p style='color:red'>Il file è troppo grande (max 5MB).</p>";
            $uploadOk = false;
        }

        // controlla presenza di nome file duplicato
        if (file_exists($target_file)) {
            echo "<p style='color:red'>File già caricato.</p>";
            $uploadOk = false;
        }

        // salvataggio file
        if ($uploadOk) {
            if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"][$i], $target_file)) {
                echo "<p style='color:green'><b>File caricato con successo!</b></p>";
            } else {
                echo "<p style='color:red'><b>Errore durante il caricamento.</b></p>";
            }
        } else {
            echo "<b>Il file non è stato caricato.</b>";
        }
    }

    // dopo il caricamento, effettua un redirect per evitare duplicazioni al refresh
    header("Location: " . $_SERVER["PHP_SELF"] . "?success=1");
}