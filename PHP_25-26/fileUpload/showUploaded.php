<?php
$dir = "uploads";

if (is_dir($dir)) {
    $files = scandir($dir);

    echo "<ul>"; // inizio elenco puntato
    foreach ($files as $file) {
        if ($file !== '.' && $file !== '..') {
            $url = $dir . '/' . urlencode($file); // per link sicuri
            echo "<li><a href='$url' target='_blank'>$file</a></li>";
        }
    }
    echo "</ul>"; // fine elenco puntato
} else {
    echo "<p style='color:red'><b>La cartella $dir non esiste.</b></p>";
}

