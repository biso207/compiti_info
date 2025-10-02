<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="generator" content="AlterVista - Editor HTML"/>
        <title>Form Dati Utente</title>
        <style>
            input {
                border-radius: 5px;
                margin: 3px;
            }
        </style>
    </head>
    <body>

        <p>&nbsp;</p>

        <h1>Login 2</h1>
        <form method="post">
            <label for="username">Username</label><input name="username" id="username" placeholder="digit here" maxlength=10 required><br>
            <label for="password">Password</label><input name="password" id="password" placeholder="digit here" maxlength=20 minlength=4 required><br>

            <input type="submit" value="Invio">
        </form>

        <?php
            // recupera il contenuto del file json
            $json = file_get_contents('lista_utenti.json');

            if ($json === false) {
                die('Error reading the JSON file');
            }

            // legge tutti i dati del json
            $json_data = json_decode($json, true);

            if ($json_data === null) {
                die('Error decoding the JSON file');
            }

            echo "<pre>";
            print_r($json_data); // stampa tutti i dati del json
            echo "</pre>";

        ?>

    </body>
</html>
