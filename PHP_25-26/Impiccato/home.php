<?php
declare(strict_types=1);
require_once __DIR__ . "/dbManager/functions.php";

$error = "";
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $username = normalize($_POST["username"] ?? "");
    if ($username === "") {
        $error = "Username mancante.";
    } elseif (!preg_match('/^[a-z0-9_]{3,20}$/i', $username)) {
        $error = "Username non valido. Usa 3–20 caratteri: lettere, numeri, underscore.";
    } else {
        header("Location: game.php?username=" . urlencode($username));
        exit;
    }
}
?>
<!doctype html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <title>Impiccato - Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/style.css">
    <!--icon-->
    <link rel="icon" href="../../../assets/img/favicons/page_icon.png" type="image/png">
</head>
<body class="bg-soft">
<div class="container py-5">
    <div class="card shadow-sm p-4 mx-auto" style="max-width:520px;">
        <h1 class="h4 mb-3">The Hangman</h1>
        <p class="text-muted">Inserisci uno username per iniziare o riprendere una partita.</p>

        <?php if ($error): ?>
            <div class="alert alert-danger"><?= h($error) ?></div>
        <?php endif; ?>

        <form method="post" class="d-flex gap-2">
            <input class="form-control" name="username" placeholder="username" required>
            <button class="btn btn-primary" type="submit">Entra</button>
        </form>

        <hr class="my-4">
        <div class="d-flex gap-2">
            <a class="btn btn-outline-secondary btn-sm" href="scoreboard.php">Scoreboard</a>
            <a class="btn btn-outline-secondary btn-sm" href="ratio.php">Ratio</a>
        </div>

        <p class="text-muted small mt-4 mb-0">Developed & Designed by Luca Bisognin</p>
    </div>
</div>
</body>
</html>
