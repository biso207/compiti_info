<?php
// dbManager/functions.php
declare(strict_types=1);

const MAX_TENTATIVI = 6;

function h(string $s): string {
    return htmlspecialchars($s, ENT_QUOTES, 'UTF-8');
}

function normalize(string $s): string {
    $s = trim($s);
    $s = mb_strtolower($s, 'UTF-8');
    return $s;
}

// accetta lettere + accenti italiani + spazi/apostrofo (se vuoi)
function is_valid_guess(string $s): bool {
    if ($s === "") return false;
    return (bool)preg_match('/^[a-zàèéìòù]+$/iu', $s);
}

function load_words(string $path): array {
    if (!file_exists($path)) {
        throw new RuntimeException("File JSON non trovato: $path");
    }
    $raw = file_get_contents($path);
    if ($raw === false) {
        throw new RuntimeException("Impossibile leggere il file JSON.");
    }
    $data = json_decode($raw, true);
    if (!is_array($data) || !isset($data["words"]) || !is_array($data["words"]) || count($data["words"]) === 0) {
        throw new RuntimeException("JSON non valido (manca 'words' o è vuoto).");
    }
    return $data["words"];
}

function pick_word_from_json(string $path): string {
    $words = load_words($path);
    // “Non random” spesso significa “non hardcoded”: qui prendiamo dal JSON e scegliamo un elemento.
    $w = $words[array_rand($words)];
    $w = normalize($w);
    // sicurezza: tieni solo caratteri ammessi
    if (!is_valid_guess($w)) {
        throw new RuntimeException("Nel JSON c'è una parola con caratteri non validi: '$w'");
    }
    return $w;
}

function mask_word(string $solution): string {
    // maschera con underscore, mantenendo stessa lunghezza
    $len = mb_strlen($solution, 'UTF-8');
    return str_repeat("_", $len);
}

function is_win(array $row): bool {
    return $row["parola"] === $row["soluzione"];
}

function is_loss(array $row): bool {
    return (int)$row["tentativi"] >= MAX_TENTATIVI && !is_win($row);
}

/**
 * Aggiorna la parola visibile rivelando le lettere contenute nel guess.
 * - Se guess è lungo 1 -> una lettera
 * - Se guess è lungo >1 -> tratta come “insieme di lettere” (come da consegna)
 */
function reveal_letters(string $solution, string $current, string $guess): string {
    $solutionChars = preg_split('//u', $solution, -1, PREG_SPLIT_NO_EMPTY);
    $currentChars  = preg_split('//u', $current,  -1, PREG_SPLIT_NO_EMPTY);
    $guessChars    = array_unique(preg_split('//u', $guess,   -1, PREG_SPLIT_NO_EMPTY));

    for ($i = 0; $i < count($solutionChars); $i++) {
        if ($currentChars[$i] !== "_" ) continue;
        if (in_array($solutionChars[$i], $guessChars, true)) {
            $currentChars[$i] = $solutionChars[$i];
        }
    }
    return implode("", $currentChars);
}

function ensure_score_row(PDO $pdo, string $username): void {
    $stmt = $pdo->prepare("INSERT IGNORE INTO classifica (username, vittorie, sconfitte) VALUES (?, 0, 0)");
    $stmt->execute([$username]);
}

function add_win(PDO $pdo, string $username): void {
    ensure_score_row($pdo, $username);
    $stmt = $pdo->prepare("UPDATE classifica SET vittorie = vittorie + 1 WHERE username = ?");
    $stmt->execute([$username]);
}

function add_loss(PDO $pdo, string $username): void {
    ensure_score_row($pdo, $username);
    $stmt = $pdo->prepare("UPDATE classifica SET sconfitte = sconfitte + 1 WHERE username = ?");
    $stmt->execute([$username]);
}
