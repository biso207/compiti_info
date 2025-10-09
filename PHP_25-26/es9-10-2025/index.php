<?php
// recupero stringa
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $frase = $_POST["stringa"];

    // stringa tutta minuscola
    $stringa_minuscola = strtolower($frase);
    echo "<p><b>Stringa minuscola</b><br> $stringa_minuscola</p>";
    // stringa tutta maiuscola
    $stringa_maiuscola = strtoupper($frase);
    echo "<p><b>Stringa maiuscola</b><br> $stringa_maiuscola</p>";


    // rimozione spazi dalla stringa
    $stringa_pulita = str_replace(" ", "", $stringa_minuscola);

    // lunghezza stringa
    $len = strlen($stringa_pulita);
    echo "<p><b>Numero caratteri</b><br> $len</p>";

    // palindroma o meno
    $isPalindroma = ($stringa_pulita == strrev($stringa_pulita)) ? "Si" : "No";
    echo "<p><b>È palindroma?</b><br> $isPalindroma</p>";

    // stampa numero consonanti e vocali della parola
    $vocali = "aeiou"; // stringa vocali
    $consonanti = "bcdfghjklmnpqrstvwxyz"; // stringa consonanti

    // conteggio vocali e consonanti
    $num_vocali=0; $num_consonanti=0;
    for ($i=0; $i < strlen($frase); $i++) {
        if(strpos($vocali, $frase[$i]) !== false) $num_vocali++; // check vocali
        elseif(strpos($consonanti, $frase[$i]) !== false) $num_consonanti++; // check consonanti
    }

    // stampa risultati
    echo "<p><b>$num_vocali vocali<br>$num_consonanti consonati</b></p>";

    // stampa numero caratteri numerici della parola
    $numeri=0;
    for ($i=0; $i < strlen($frase); $i++) if(is_numeric($frase[$i])) $numeri++;
    // stampa risultati
    echo "<p><b>$numeri numeri</b></p>";
}