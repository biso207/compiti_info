<?php

require_once "DB.php";

$db = new db("localhost", "my_lucabiso", "lucabiso", "");

if(!$db->get_connected()){
    die("Errore connessione: " . $db->get_msg());
}

if(isset($_POST["table"])){

    $table = $_POST["table"];

    $sql = "SELECT * FROM `$table`";

    $result = $db->getResultSet($sql, PDO::FETCH_ASSOC);

}

?>

<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Risultati Query</title>
        <!--style sheet-->
        <link rel="stylesheet" href="../style.css">
        <!--icon-->
        <link rel="icon" href="../../../assets/img/favicons/page_icon.png" type="image/png">
    </head>

    <body>
        <h2>Risultati</h2>

        <?php if(!empty($result)): ?>

        <table>

        <tr>
        <?php foreach(array_keys($result[0]) as $col): ?>
        <th><?= $col ?></th>
        <?php endforeach; ?>
        </tr>

        <?php foreach($result as $row): ?>

        <tr>
        <?php foreach($row as $value): ?>
        <td><?= htmlspecialchars($value) ?></td>
        <?php endforeach; ?>
        </tr>

        <?php endforeach; ?>

        </table>

        <?php else: ?>

        <p>Nessun risultato</p>

        <?php endif; ?>

        <br>
        <a href="index.html">Torna indietro</a>
    </body>
</html>
