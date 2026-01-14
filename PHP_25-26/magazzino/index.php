<?php
require __DIR__ . "/functions.php";

// pagina corrente (GET)
$page = isset($_GET["page"]) ? (int)$_GET["page"] : 1;
$perPage = 10;

// filtro (anche se non lo usi, lo lasciamo pronto)
$status = isset($_GET["status"]) ? (string)$_GET["status"] : "all";
if (!in_array($status, ["all", "low", "out"], true)) $status = "all";

// totale prodotti + numero pagine
$total = countProducts($status);
$pages = (int) max(1, ceil($total / $perPage));
$page  = clamp_int($page, 1, $pages);

// prodotti della pagina
$rows = getProducts($page, $perPage, $status);

// testo "Mostrando X a Y di Z"
$from = ($total > 0) ? (($page - 1) * $perPage + 1) : 0;
$to   = min($page * $perPage, $total);
?>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestionale Magazzino ITI</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <!--icon-->
    <link rel="icon" href="../../../assets/img/favicons/page_icon.png" type="image/png">
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <i class="fa-solid fa-boxes-stacked me-2"></i>WMS Magazzino
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMain">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarMain">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Prodotti</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Categorie</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Movimenti</a>
                    </li>
                </ul>
                
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Cerca SKU o nome..." aria-label="Search">
                    <button class="btn btn-outline-light" type="submit">Cerca</button>
                </form>
            </div>
        </div>
    </nav>
    
        <div class="container">
        
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Elenco Prodotti</h2>
            <a href="#" class="btn btn-success">
                <i class="fa-solid fa-plus me-1"></i> Nuovo Prodotto
            </a>
        </div>

        <div class="card shadow-sm">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <div>
                    <small class="text-muted">
                        <strong>Prodotti in archivio:</strong> <?= (int)$total ?>
                    </small>
                </div>
                <div class="btn-group btn-group-sm">
                    <!-- I bottoni ci stanno: filtro base con status -->
                    <a class="btn btn-outline-secondary <?= $status==='all' ? 'active' : '' ?>" href="index.php?status=all&page=1">Tutto</a>
                    <a class="btn btn-outline-secondary <?= $status==='low' ? 'active' : '' ?>" href="index.php?status=low&page=1">Sotto scorta</a>
                    <a class="btn btn-outline-secondary <?= $status==='out' ? 'active' : '' ?>" href="index.php?status=out&page=1">Esauriti</a>
                </div>
            </div>
            
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-hover table-striped align-middle mb-0">
                        <thead class="table-light">
                            <tr>
                                <th scope="col" class="ps-3">SKU</th>
                                <th scope="col">Foto</th>
                                <th scope="col">Prodotto</th>
                                <th scope="col">Categoria</th>
                                <th scope="col" class="text-end">Prezzo</th> <th scope="col" class="text-center">Giacenza</th>
                                <th scope="col" class="text-center">Stato</th>
                                <th scope="col" class="text-end pe-3">Azioni</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            if (!$rows) {
                                echo "<tr><td colspan='8' class='text-center text-muted py-4'>Nessun prodotto trovato.</td></tr>";
                            } else {
                                foreach ($rows as $row) echo printRow($row);
                            }
                            ?>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="card-footer bg-white d-flex justify-content-between align-items-center">
                <div class="text-muted small">
                    Mostrando <?= (int)$from ?> a <?= (int)$to ?> di <?= (int)$total ?> risultati
                </div>
                <nav aria-label="Page navigation">
                    <?= renderPagination($page, $pages, $status) ?>
                </nav>
            </div>
        </div>
    </div>
    
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
