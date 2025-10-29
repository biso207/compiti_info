<?php
// array associativo per i messaggi di errore e loro conteggio
$error_counts = [];

// creazione cartella uploads
if (!file_exists("uploads")) mkdir("uploads");

// se la richiesta è POST (caricamento file)
if ($_SERVER["REQUEST_METHOD"] === "POST") {

    // controllo se non sono stati selezionati file
    if (empty($_FILES["fileToUpload"]["name"][0])) {
        $error_counts["<p style='color:red'>Nessun file selezionato.</p>"] = 1;
    }
    else {
        // conteggio file caricati
        $num_file = count($_FILES["fileToUpload"]["name"]);

        // for per iterare sui file caricati
        for ($i = 0; $i < $num_file; $i++) {

            // variabili per il caricamento del file (percorso, nome, tipo, dimensione, presenza duplicati)
            $target_dir = __DIR__ . "/uploads/"; // percorso globale per sicurezza
            $fileName = basename($_FILES["fileToUpload"]["name"][$i]);
            $imageFileType = strtolower(pathinfo($fileName, PATHINFO_EXTENSION));
            $target_file = $target_dir . $fileName;

            $uploadOk = true;

            // verifica se è davvero un'immagine
            if (!empty($_FILES["fileToUpload"]["tmp_name"][$i]) && file_exists($_FILES["fileToUpload"]["tmp_name"][$i])) {
                $check = @getimagesize($_FILES["fileToUpload"]["tmp_name"][$i]);
                if ($check === false) {
                    $msg = "<p style='color:red'>Il file non è un'immagine.</p>";
                    $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
                    $uploadOk = false;
                }
            } else {
                $msg = "<p style='color:red'>Errore nel caricamento del file.</p>";
                $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
                $uploadOk = false;
            }

            // controlla il tipo di file
            if (!in_array($imageFileType, ["jpg", "jpeg", "png", "gif", "webp"])) {
                $msg = "<p style='color:red'>Sono permessi solo JPG, JPEG, PNG, GIF e WEBP.</p>";
                $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
                $uploadOk = false;
            }

            // controlla dimensione massima (10 MB)
            if ($_FILES["fileToUpload"]["size"][$i] > 10000000) {
                $msg = "<p style='color:red'>Il file è troppo grande (max 5MB).</p>";
                $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
                $uploadOk = false;
            }

            // controlla presenza di nome file duplicato
            if (file_exists($target_file)) {
                $msg = "<p style='color:red'>File già caricato.</p>";
                $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
                $uploadOk = false;
            }

            // salvataggio file
            if ($uploadOk) {
                if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"][$i], $target_file)) {
                    $msg = "<p style='color:green'><b>File caricato con successo!</b></p>";
                    $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
                } else {
                    $msg = "<p style='color:red'><b>Errore durante il caricamento.</b></p>";
                    $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
                }
            } else {
                $msg = "<b>Il file non è stato caricato.</b>";
                $error_counts[$msg] = (isset($error_counts[$msg]) ? $error_counts[$msg] : 0) + 1;
            }
        }
    }

    // dopo il caricamento, effettua un redirect per evitare duplicazioni al refresh
    //header("Location: " . $_SERVER["PHP_SELF"]);
}

// stampa dei messaggi (unici, con numero di file coinvolti)
if (!empty($error_counts)) {
    foreach ($error_counts as $msg => $count) {
        if ($count > 1) {
            // aggiunge un contatore se più file hanno lo stesso errore
            echo str_replace("</p>", " ($count file)</p>", $msg);
        } else {
            echo $msg;
        }
    }
}
