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
            // creazione utente in caso non esista al login
            if(!$db->is_user($username)) signup($db, $username, $psw_hashed);
            else login($db, $username, $password);
            die();

        // salvataggio credenziali sessione sul db
        case "signup":
            // se esiste fa il login
            if ($db->is_user($username)) login($db, $username, $password);
            else header("Location: result.php?status=created");
            die();
    }
}

function login($db, $username, $password) {
    if ($db->check_credentials($username, $password)) header("Location: result.php?status=correct");
    else header("Location: result.php?status=invalid");
}

function signup($db, $username, $psw_hashed) {
    $db->save_credentials($username, $psw_hashed);
    header("Location: result.php?status=created");
}
