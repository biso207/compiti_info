<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="generator" content="AlterVista - Editor HTML"/>
        <title>Advanced Form</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--style sheet-->
        <link rel="stylesheet" href="../login.css">
        <!--icon-->
        <link rel="icon" href="../assets/favicons/page_icon.png" type="image/png">
    </head>
    <body>
        <!-barra di navigazione-->
        <nav class="navbar">
            <ul>
                <li><a class="backIndex" href="../../index.html">Home Esercizi</a></li>
                <li><a href="../login.php">Login Base</a></li>
            </ul>
        </nav>

        <h1>Login 2</h1>
        <form method="post">
            <label for="username">Username</label><input name="username" id="username" placeholder="digit here" maxlength=10 required><br>
            <label for="password">Password</label><input name="password" id="password" placeholder="digit here" maxlength=20 minlength=4 required><br>

            <input type="submit" value="Invio">
        </form>

        <?php
            // permetta la visualizzazione degli errori
            ini_set('display_errors', 1);
            error_reporting(E_ALL);

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

            if ($_SERVER['REQUEST_METHOD'] === 'POST') {

                // recupera i dati utente
                $username = $_POST['username'];
                $password = $_POST['password'];
                $flag = false; // flag per gli errori

                // for-each per stampare tutti i dati del json
                /*
                users è un array associativo che contiene tutti gli utenti
                nel file json la chiave è 'users' che contiene dentro i field 'username' e 'password'
                */
                foreach ($json_data['users'] as $key => $value) {
                    if ($value['username'] == $username) {
                        $flag=true;
                        if ($value['password'] == $password) echo "<p style='color:green'>Ciao $username, sei dentro!</p>";
                        else echo "<p style='color:red'>Password errata!</p>";
                    }
                }

                if (!$flag) echo "<p style='color:red'>Username non trovato!</p>";
            }
        ?>

    </body>
</html>
