<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestionale Magazzino ITI</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        /* Piccoli ritocchi custom */
        body { background-color: #f8f9fa; }
        .img-thumb { width: 50px; height: 50px; object-fit: contain; background: #fff; border: 1px solid #dee2e6; border-radius: 4px; }
        .table-action-btn { margin-right: 5px; }
        /* Allinea i numeri a destra (standard contabile) */
        .text-num { text-align: right; font-family: 'Courier New', monospace; font-weight: bold; }
    </style>
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
            <div class="card-header bg-white py-3">
                <div class="row">
                    <div class="col-md-6 pt-2">
                        <strong>Prodotti in archivio:</strong> 100
                    </div>
                    <div class="col-md-6 text-end">
                        <div class="btn-group btn-group-sm">
                            <button class="btn btn-outline-secondary active">Tutti</button>
                            <button class="btn btn-outline-secondary">Sottoscorta</button>
                            <button class="btn btn-outline-secondary">Esauriti</button>
                        </div>
                    </div>
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
                            	require "functions.php";
                                foreach (getTable() as $row) {
                                	echo printRow($row);
                                }
                            ?>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer bg-white d-flex justify-content-between align-items-center py-3">
                <div class="text-muted small">
                    Mostrando 1 a 10 di 100 risultati
                </div>
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-sm m-0">
                        <li class="page-item disabled"><a class="page-link" href="#">Precedente</a></li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">...</a></li>
                        <li class="page-item"><a class="page-link" href="#">10</a></li>
                        <li class="page-item"><a class="page-link" href="#">Successiva</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>