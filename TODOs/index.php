<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>My TODOs</title>
            <link rel="stylesheet" href="style.css">
        </head>
        <body>
        <h1>Lista TODO</h1>
        <p class="subtitle">Visualizza le attività salvate nel database</p>

        <div class="todo-container">
            <!-- TODOs verranno visualizzati qui -->
            <?php
                // parametri del db
                $servername = "localhost";
                $username = "lucabiso";
                $password = "";
                $database = "my_lucabiso";

                // connessione al database
                try {
                    $conn = new PDO("mysql:host=$servername;dbname=$database", $username, $password);
                    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                } catch(PDOException $e) {
                    echo "<p style='color:red;'>Connection failed: " . $e->getMessage() . "</p>";
                    die();
                }

                // recupero dei TODOs dal database
                try {
                    $stmt = $conn->prepare("SELECT * FROM TODOs"); // preparazione della query
                    $stmt->execute(); // esecuzione della query
                } catch(PDOException $e) {
                    echo "<p style='color:red;'>Query failed: " . $e->getMessage() . "</p>";
                    die();
                }

                // --- visualizzazione dei TODOs --- //
                $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

                if (count($result) === 0) {
                    echo "<p>Nessun TODO presente.</p>";
                }

                foreach($result as $row) {
                    // operatore ternario per stampare il bottone "Completa" o "Annulla"
                    $isCompleted = $row['completato'] ?
                            "<a class='status completed' href='aggiornaTODO.php?id={$row['id']}&stato=0'>Annulla</a>" :
                            "<a class='status pending' href='aggiornaTODO.php?id={$row['id']}&stato=1'>Completa</a>";

                    echo "
                        <div class='todo'>
                            <h2>{$row['titolo']}</h2>
                            <p class='description'>{$row['descrizione']}</p>
                            $isCompleted
                        </div>
                        ";
                }
            ?>
        </div><br>

        <a class='status newTODO' href='nuovoTODO.php'>Crea TODO</a>
    </body>
</html>
