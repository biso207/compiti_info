<?php
// recupera la frase dalla form ed esegue delle azioni su essa
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $frase = $_POST["parola"];
    echo "<p>Hai digitato la frase:<br> <i>$frase</i></p>";

    $frase = strtolower($frase); // frase tutta in minuscolo
    $parole = explode(" ", $frase); // divido la frase in array di parole dividendola per spazi

    // 1) stampa numero consonanti e vocali della parola
    $vocali = "aeiou"; // stringa vocali
    $consonanti = "bcdfghjklmnpqrstvwxyz"; // stringa consonanti

    // conteggio vocali e consonanti
    $num_vocali=0; $num_consonanti=0;
    for ($i=0; $i < strlen($frase); $i++) {
        if(strpos($vocali, $frase[$i]) !== false) $num_vocali++; // check vocali
        elseif(strpos($consonanti, $frase[$i]) !== false) $num_consonanti++; // check consonanti
    }
    // stampa risultati
    echo "<p>Hai $num_vocali vocali e $num_consonanti consonati nella frase</p>";

    // 2) stampa numero caratteri numerici della parola
    $numeri=0;
    for ($i=0; $i < strlen($frase); $i++) if(is_numeric($frase[$i])) $numeri++;
    // stampa risultati
    echo "<p>Hai $numeri numeri nella frase</p>";

    // 3) stampa numero di parole rilevate
    $num_parole=count($parole);
    if ($num_parole==1) $word="parola";
    else $word="parole";
    echo "<p>Hai $num_parole $word nella frase</p>";

    // 4) stampa frequenza ogni parola nella frase
    $frequenza = array_count_values($parole); // conto occorrenze => conto quante volte l'elemento si trova nell'array
    // stampa risultati
    echo "<p>Frequenza parole:</p>";
    echo "<ul>";
    foreach ($frequenza as $parola => $occorrenze) {
        if ($occorrenze==1) $word2="volta";
        else $word2="volte";
        echo "<li>'$parola': $occorrenze $word2</li>";
    }
    echo "</ul>";
    
}
