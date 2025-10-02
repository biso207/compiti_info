<!DOCTYPE html>
<html lang="eng">
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

        <h1>Login</h1>
        <form method="post">
            <!-- 'id' serve all'HTML, CSS e js; 'name' serve ai server php in backend -->
            <label for="username">Username</label><input name="username" id="username" placeholder="digit here" maxlength=10 required><br>
            <label for="password">Password</label><input name="password" id="password" placeholder="digit here" maxlength=20 minlength=4 required><br>

            <input type="submit" value="Invio">
        </form>

        <?php
        /*
        POST copre i dati sensibili, GET li mostra nell'URL.
        Se qualcuno "ascolta" la conversazione può intercettare i dati se scambiati con GET
        */
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            // variabili
            $password = "abracadabra"; // password utente => recuperabile da server remoto
            $digited_psw = isset($_POST['password']) ? $_POST['password'] : ''; // password digitata
            $username = isset($_POST['username']) ? $_POST['username'] : ''; // nickname digitato

            // controllo correttezza password
            if ($digited_psw === $password) echo "<p style='color:green'>Ciao $username, sei dentro!</p>";
            else echo "<p style='color:red'>Password errata!</p>";
        }
        ?>

    </body>
</html>
