<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>File Uploader</title>
        <!-- stylesheet -->
        <link rel="stylesheet" href="../style.css">
        <!--icon-->
        <link rel="icon" href="../../../assets/img/favicons/page_icon.png" type="image/png">
    </head>
    <body>

        <!-barra di navigazione-->
        <nav class="navbar">
            <ul>
                <li><a class="backIndex" href="../../../Progetti_5CIN/index.html">Home Esercizi</a></li>
            </ul>
        </nav>

        <h1>File Uploader</h1>
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="fileToUpload[]" multiple><br>
            <input type="submit" value="Invia">
            <input type="reset" value="Reset">
        </form>
        <?php include 'upload.php'; ?>

        <h3>Lista File Caricati</h3>
        <?php include 'showUploaded.php'; ?>
    </body>
</html>