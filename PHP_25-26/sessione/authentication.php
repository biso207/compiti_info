<?php
require "DB.php";
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // variabili
    $username = $_POST['username'];
    $password = $_POST['password'];

    // tipo di accesso
    $access= isset($_POST['login']) ? "login" : "signup";

    // hash della password con bcrypt
    $psw_hashed = password_hash($password, PASSWORD_BCRYPT);


    // creazione oggetto DB
    $db = new DB("localhost", "my_lucabiso", "lucabiso", ""); // credenziali utente altervista

    // connessione al DB
    $conn = $db->get_conn();

    // controllo connessione
    if (!$db->get_connected()) die("ERRORE: " . $db->get_msg());
    else echo "Connessione OK";

    // switch per tipo di accesso
    switch ($access) {
        // controllo credenziali
        case "login":
            if ($db->check_credentials($username, $password)) header("Location: result.php?status=correct");
            else header("Location: result.php?status=invalid");
            die();

        // salvataggio credenziali sessione sul db
        case "signup":
            $user_exist = $db->save_credentials($username, $psw_hashed);
            if ($user_exist) header("Location: result.php?status=uncreated");
            else header("Location: result.php?status=created");
            die();
    }
}
