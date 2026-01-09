<?php
declare(strict_types=1);
require_once __DIR__ . "/dbManager/db.php";
require_once __DIR__ . "/dbManager/functions.php";

$stmt = $pdo->query("SELECT username, vittorie, sconfitte FROM classifica ORDER BY vittorie DESC, sconfitte ASC, username ASC");
$rows = $stmt->fetchAll();
?>
<!doctype html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <title>Scoreboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/style.css">
</head>
<body class="bg-soft">
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="h4 mb-0">Scoreboard</h1>
        <div class="d-flex gap-2">
            <a class="btn btn-outline-secondary btn-sm" href="home.php">Home</a>
            <a class="btn btn-outline-secondary btn-sm" href="ratio.php">Ratio</a>
        </div>
    </div>

    <div class="card shadow-sm p-3">
        <table class="table table-striped mb-0">
            <thead>
            <tr>
                <th>Username</th>
                <th>Vittorie</th>
                <th>Sconfitte</th>
            </tr>
            </thead>
            <tbody>
            <?php foreach ($rows as $r): ?>
                <tr>
                    <td><?= h($r["username"]) ?></td>
                    <td><?= (int)$r["vittorie"] ?></td>
                    <td><?= (int)$r["sconfitte"] ?></td>
                </tr>
            <?php endforeach; ?>
            <?php if (count($rows) === 0): ?>
                <tr><td colspan="3" class="text-muted">Nessun dato disponibile.</td></tr>
            <?php endif; ?>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
