<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>New TODO</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1>Nuovo TODO</h1>
        <p class="subtitle">Crea una nuova attività</p>

        <form method="post" action="nuovoTODO.php">
            <label>
                <input type="text" name="titolo" placeholder="Titolo" required><br>
            </label>
            <label>
                <input type="text" name="descrizione" placeholder="Descrizione" required><br>
            </label>

            <button class='status newTODO' type="submit">Crea</button>
        </form>

        <div>
            <!-- TODOs verranno visualizzati qui -->
            <?php
                if ($_SERVER["REQUEST_METHOD"] === "POST") {
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

                    $titolo = $_POST["titolo"];
                    $descrizione = $_POST["descrizione"];
                    $completato = 0;

                    try {
                        $stmt = $conn->prepare("INSERT INTO TODOs (titolo, descrizione, completato) VALUES (:titolo, :descrizione, :completato)");
                        $stmt->execute(['titolo' => $titolo, 'descrizione' => $descrizione, 'completato' => $completato]);
                        header("Location: index.php");
                        die();
                    } catch (PDOException $e) {
                        echo "<p style='color:red;'>Insert failed: " . $e->getMessage() . "</p>";
                        die();
                    }

                }

            ?>
        </div><br>
    </body>
</html>
