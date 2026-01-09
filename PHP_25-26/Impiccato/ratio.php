<?php
declare(strict_types=1);
require_once __DIR__ . "/dbManager/db.php";
require_once __DIR__ . "/dbManager/functions.php";

$sql = "
  SELECT
    username,
    vittorie,
    sconfitte,
    (vittorie + sconfitte) AS tot,
    CASE
      WHEN sconfitte = 0 THEN vittorie
      ELSE (vittorie / sconfitte)
    END AS ratio
  FROM classifica
  WHERE (vittorie + sconfitte) > 10
  ORDER BY ratio DESC, tot DESC, username ASC
";

$stmt = $pdo->query($sql);
$rows = $stmt->fetchAll();
?>
<!doctype html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <title>Ratio</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/style.css">
</head>
<body class="bg-soft">
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="h4 mb-0">Classifica Ratio</h1>
        <div class="d-flex gap-2">
            <a class="btn btn-outline-secondary btn-sm" href="home.php">Home</a>
            <a class="btn btn-outline-secondary btn-sm" href="scoreboard.php">Scoreboard</a>
        </div>
    </div>

    <div class="card shadow-sm p-3">
        <div class="text-muted small mb-2">
            Mostro solo utenti con più di 10 partite totali.
        </div>

        <table class="table table-striped mb-0">
            <thead>
            <tr>
                <th>Username</th>
                <th>Vittorie</th>
                <th>Sconfitte</th>
                <th>Ratio</th>
            </tr>
            </thead>
            <tbody>
            <?php foreach ($rows as $r): ?>
                <tr>
                    <td><?= h($r["username"]) ?></td>
                    <td><?= (int)$r["vittorie"] ?></td>
                    <td><?= (int)$r["sconfitte"] ?></td>
                    <td><?= number_format((float)$r["ratio"], 2) ?></td>
                </tr>
            <?php endforeach; ?>
            <?php if (count($rows) === 0): ?>
                <tr><td colspan="4" class="text-muted">Nessun utente con più di 10 partite.</td></tr>
            <?php endif; ?>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
