<?php
$dir = "uploads";

// controllo esistenza cartella
if (is_dir($dir)) {
    $files = scandir($dir);

    echo "<div style='display: flex; flex-wrap: wrap; gap: 15px;'>";

    // for-each per iterare sulla lista dei file scelti
    foreach ($files as $file) {
        if ($file !== '.' && $file !== '..') {
            $url = $dir . '/' . rawurldecode($file); // codifica per URL
            $file_ext = strtolower(pathinfo($file, PATHINFO_EXTENSION)); // estensione del file

            // mostra le immagini caricate
            if (in_array($file_ext, ['jpg', 'jpeg', 'png', 'gif', 'webp'])) {
                echo "
                <div style='text-align:center;'>
                    <a href='$url' target='_blank'>
                        <img src='$url' alt='$file' 
                             style='width:120px; height:120px; object-fit:cover; border-radius:10px; box-shadow:0 0 5px rgba(0,0,0,0.3);'>
                    </a>
                    <div><p>$file</p></div>
                </div>
                ";
            }
        }
    }

    echo "</div>";
// errore se la cartella non esiste
} else {
    echo "<p style='color:red'><b>La cartella $dir non esiste.</b></p>";
}


