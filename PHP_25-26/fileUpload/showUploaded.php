<?php
$dir = "uploads";

if (is_dir($dir)) {
    $files = scandir($dir);

    echo "<div style='display: flex; flex-wrap: wrap; gap: 15px;'>";

    foreach ($files as $file) {
        if ($file !== '.' && $file !== '..') {
            $url = $dir . '/' . urlencode($file);
            $file_ext = strtolower(pathinfo($file, PATHINFO_EXTENSION));

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
} else {
    echo "<p style='color:red'><b>La cartella $dir non esiste.</b></p>";
}


