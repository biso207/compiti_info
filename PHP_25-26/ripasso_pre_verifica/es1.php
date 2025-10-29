<html lang="it">
    <head>
        <title>Esercizio Ripasso 1</title>
    </head>

    <body>
        <form method="POST" action="es1.php">
            <label>Nome</label>
            <input name="nomeUtente" required><br>

            <label>Data di nascita</label>
            <input type="date" name="eta"><br>

            <label>Numero giorno settimana (1-7)</label>
            <input type="number" name="numGiorno"><br><br>

            <label><b>Operazioni matematiche</b></label><br>
            <label>1° Numero</label>
            <input type="number" name="num1"><br>
            <label>2° Numero</label>
            <input type="number" name="num2"><br>
            <label>2° Numero</label>
            <input type="number" name="num3"><br>

            <input type="submit">
        </form>

        <?php
            ini_set('display_errors', 1);

            if ($_SERVER["REQUEST_METHOD"] == "POST") {
                $nomeUtente = $_POST["nomeUtente"];
                $date = $_POST["eta"];
                $numGiorno = $_POST["numGiorno"];
                $num1 = intval($_POST["num1"]);
                $num2 = intval($_POST["num2"]);
                $num3 = intval($_POST["num3"]);


                // stampa saluto all'utente
                echo "<p>Ciao $nomeUtente, benvenuto nell'es1</p>";

                // check età


                // giorno della settimana in base al numero inserito
                if (is_numeric($numGiorno)) {
                    if ($numGiorno>0 && $numGiorno <8) {
                        switch ($numGiorno) {
                            case 1:
                                echo "1=lunedì";
                                break;
                            case 2:
                                echo "2=martedì";
                                break;
                            case 3:
                                echo "3=mercoledì";
                                break;
                            case 4:
                                echo "4=giovedì";
                                break;
                            case 5:
                                echo "5=venerdì";
                                break;
                            case 6:
                                echo "6=sabato";
                                break;
                            case 7:
                                echo "7=domenica";
                                break;
                        }
                    }
                    else echo "Numero fuori range";
                }

                // operazioni matematiche
                if ($num1>0 || $num2>0 || $num3>0) {
                    $somma = $num1 + $num2 + $num3;
                    echo "<p>La somma dei 3 numeri è $somma</p>";

                    $media = $somma / 3;
                    echo "<p>La media tra i 3 numeri è $media</p>";

                    $resto = $num1 - $num2 - $num3;
                    echo "<p>Il resto tra i 3 numeri è $resto</p>";
                }
                else echo "<p>Tutti i numeri sono a 0</p>";
            }
        ?>
    </body>
</html>