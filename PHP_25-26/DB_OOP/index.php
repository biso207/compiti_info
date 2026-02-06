<!DOCTYPE html>

<?php
require "DB.php";

// creazione oggetto DB
$db = new DB("localhost", "my_lucabiso", "lucabiso", "");

$conn = $db->get_conn();

// controllo connessione
if (!$db->get_connected()) die("ERRORE: " . $db->get_msg());
else echo "Connessione OK";

$rs = $db->getResultSet("select * FROM ts_com");
foreach ($rs as $row) {
    echo "<p>".$row['des_com']."</p>";
}
