<?php
declare(strict_types=1);

session_start();

require_once __DIR__ . "/dbManager/db.php";
require_once __DIR__ . "/dbManager/functions.php";

$username = normalize($_POST["username"] ?? $_GET["username"] ?? "");
$action   = $_POST["action"] ?? "";

if ($username === "") {
    http_response_code(400);
    die("Username mancante. Torna alla <a href='home.php'>home</a>.");
}
if (!preg_match('/^[a-z0-9_]{3,20}$/i', $username)) {
    http_response_code(400);
    die("Username non valido. Torna alla <a href='home.php'>home</a>.");
}

$guess = normalize($_POST["parola"] ?? "");
$hasGuess = ($_SERVER["REQUEST_METHOD"] === "POST" && $action === "guess");

$jsonPath = __DIR__ . "/data/words.json";

// ---------------- DB helpers ----------------
function get_player(PDO $pdo, string $username): ?array {
    $stmt = $pdo->prepare("SELECT * FROM partite WHERE username = ?");
    $stmt->execute([$username]);
    $row = $stmt->fetch();
    return $row ?: null;
}

function create_new_game(PDO $pdo, string $username, string $jsonPath): array {
    $sol = pick_word_from_json($jsonPath);
    $par = mask_word($sol);

    $_SESSION["used_letters"] = [];

    $stmt = $pdo->prepare("
        INSERT INTO partite (username, tentativi, soluzione, parola)
        VALUES (?, 0, ?, ?)
        ON DUPLICATE KEY UPDATE tentativi=0, soluzione=VALUES(soluzione), parola=VALUES(parola)
    ");
    $stmt->execute([$username, $sol, $par]);

    return [
        "username" => $username,
        "tentativi" => 0,
        "soluzione" => $sol,
        "parola" => $par
    ];
}

// ---------------- SESSION ----------------
$_SESSION["used_letters"] = $_SESSION["used_letters"] ?? [];
$_SESSION["end_state"]   = $_SESSION["end_state"] ?? "";

// ---------------- LOGICA GIOCO ----------------
try {
    // Quit partita
    if ($action === "quit") {
        unset($_SESSION["used_letters"], $_SESSION["end_state"]);
        header("Location: home.php");
        exit;
    }

    $player = get_player($pdo, $username);

    if ($player === null) {
        $player = create_new_game($pdo, $username, $jsonPath);
    } else {
        $ended = is_win($player) || is_loss($player);

        // Mostra VITTORIA / SCONFITTA e avvia nuova partita
        if ($ended) {
            $_SESSION["end_state"] = is_win($player) ? "VITTORIA" : "SCONFITTA";
            $player = create_new_game($pdo, $username, $jsonPath);
        }

        // Tentativo
        if ($hasGuess) {

            if (mb_strlen($guess) === 1 && in_array($guess, $_SESSION["used_letters"], true)) {
                header("Location: game.php?username=" . urlencode($username));
                exit;
            }

            if (mb_strlen($guess) === 1) {
                $_SESSION["used_letters"][] = $guess;
            }

            if ($guess === $player["soluzione"]) {
                $pdo->prepare("UPDATE partite SET parola = soluzione WHERE username = ?")
                    ->execute([$username]);
                add_win($pdo, $username);
                $_SESSION["end_state"] = "VITTORIA";
                header("Location: game.php?username=" . urlencode($username));
                exit;
            }

            $newParola = reveal_letters($player["soluzione"], $player["parola"], $guess);

            if ($newParola !== $player["parola"]) {
                $pdo->prepare("UPDATE partite SET parola = ? WHERE username = ?")
                    ->execute([$newParola, $username]);
            } else {
                $pdo->prepare("UPDATE partite SET tentativi = tentativi + 1 WHERE username = ?")
                    ->execute([$username]);
            }

            $player = get_player($pdo, $username);

            if (is_win($player)) {
                add_win($pdo, $username);
                $_SESSION["end_state"] = "VITTORIA";
            }
            if (is_loss($player)) {
                add_loss($pdo, $username);
                $_SESSION["end_state"] = "SCONFITTA";
            }

            header("Location: game.php?username=" . urlencode($username));
            exit;
        }
    }

    $player = get_player($pdo, $username);

} catch (Throwable $e) {
    http_response_code(500);
    die("Errore: " . htmlspecialchars($e->getMessage()));
}

// ---------------- UI helpers ----------------
$tentativi = (int)$player["tentativi"];
$imgPath = "assets/img/hangman{$tentativi}.png";
$usedLetters = $_SESSION["used_letters"];
$endState = $_SESSION["end_state"];

// reset messaggio fine dopo visualizzazione
$_SESSION["end_state"] = "";
?>

<!doctype html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <title>Impiccato</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/style.css">
</head>
<body class="bg-soft">

<div class="container py-4">

    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="h4 mb-0">Impiccato – <?= h($username) ?></h1>
        <form method="post" class="d-flex gap-2">
            <input type="hidden" name="username" value="<?= h($username) ?>">
            <button name="action" value="quit" class="btn btn-outline-danger btn-sm">Quit</button>
            <a href="home.php" class="btn btn-outline-secondary btn-sm">Home</a>
        </form>
    </div>

    <?php if ($endState): ?>
        <div class="alert alert-<?= $endState === "VITTORIA" ? "success" : "danger" ?> text-center fw-bold">
            <?= $endState ?>
        </div>
    <?php endif; ?>

    <div class="row g-3">
        <div class="col-12 col-lg-5">
            <div class="card shadow-sm p-3">
                <div class="mb-2 text-muted">
                    Tentativi: <strong><?= MAX_TENTATIVI - $tentativi ?></strong>/<?= MAX_TENTATIVI ?>
                </div>
                <img src="<?= h($imgPath) ?>" class="img-fluid rounded">
            </div>
        </div>

        <div class="col-12 col-lg-7">
            <div class="card shadow-sm p-3 mb-3">
                <div class="word-view mb-3">
                    <?= h(implode(" ", preg_split('//u', $player["parola"], -1, PREG_SPLIT_NO_EMPTY))) ?>
                </div>

                <form method="post" class="d-flex gap-2">
                    <input type="hidden" name="username" value="<?= h($username) ?>">
                    <input type="hidden" name="action" value="guess">
                    <input class="form-control" name="parola" placeholder="Lettera o parola..." required>
                    <button class="btn btn-primary">Prova</button>
                </form>
            </div>

            <div class="card shadow-sm p-3">
                <div class="text-muted small mb-1">Lettere già usate:</div>
                <div>
                    <?php if ($usedLetters): ?>
                        <?php foreach ($usedLetters as $l): ?>
                            <span class="badge bg-secondary me-1"><?= h(strtoupper($l)) ?></span>
                        <?php endforeach; ?>
                    <?php else: ?>
                        <span class="text-muted">nessuna</span>
                    <?php endif; ?>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
