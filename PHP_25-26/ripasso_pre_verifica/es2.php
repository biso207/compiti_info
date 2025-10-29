<html lang="it">
    <head>
        <title>Esercizio Ripasso 2</title>
    </head>

    <body>
        <form action="es2.php" method="POST">
            <label><b>Divertiti cliccando il tasto sotto!</b></label>
            <input type="submit" value="CASINOOO">
        </form>
    </body>
</html>


<?php
    function saluta($nome) {
        echo "ciao $nome<br>";
    }

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // stampa i numeri da 1 a 10, uno per riga.
        for ($i=1; $i<11; $i++) echo "$i<br>";

        // somma i numeri da 1 a 100 con un ciclo while.
        $num=0; $somma=0;
        while ($num<100) { $somma+=$num; $num++; }
        echo "<br>La somma dei numeri da 1 a 100 è <b>$somma</b><br><br>";

        // crea un array di nomi e stampali con foreach.
        // per semplicità faccio un for con 100 numeri e li stampo con un foreach
        $arrayNumeri = [];
        for ($i=1; $i<101; $i++) {
            $arrayNumeri[$i] = $i;
        }
        foreach ($arrayNumeri as $num) echo "$num<br>";

        // stampa 1000 volte il saluto utente
        for ($i=1; $i<1001; $i++) saluta("utente");
    }
?>
