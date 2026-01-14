<?php
require __DIR__ . "/db.php";

// Escape HTML (evita problemi con caratteri speciali)
function h($s) {
    return htmlspecialchars($s, ENT_QUOTES | ENT_SUBSTITUTE, "UTF-8");
}

// Limita un intero dentro un range
function clamp_int($v, $min, $max) {
    if ($v < $min) return $min;
    if ($v > $max) return $max;
    return $v;
}

// Ritorna la condizione WHERE per il filtro "status"
// all = tutti, low = sottoscorta (1..5), out = esauriti (0)
function whereByStatus($status) {
    if ($status === "out") return " WHERE giacenza <= 0";
    if ($status === "low") return " WHERE giacenza BETWEEN 1 AND 5";
    return "";
}

// Conta i prodotti (serve per "Prodotti in archivio" + paginazione)
function countProducts($status = "all") {
    global $conn;

    $where = whereByStatus($status);
    $sql = "SELECT COUNT(*) FROM `5CIN_2526_Magazzino_Prodotti`" . $where;

    $stmt = $conn->query($sql);
    return (int)$stmt->fetchColumn();
}

// Prende i prodotti della pagina corrente (paginazione vera)
function getProducts($page, $perPage, $status = "all") {
    global $conn;

    $page = max(1, $page);
    $perPage = clamp_int($perPage, 1, 100);
    $offset = ($page - 1) * $perPage;

    $where = whereByStatus($status);

    $sql = "SELECT codice_sku, nome, descrizione, categoria, prezzo_vendita, giacenza
            FROM `5CIN_2526_Magazzino_Prodotti`" . $where . "
            ORDER BY nome ASC
            LIMIT :limit OFFSET :offset";

    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":limit", $perPage, PDO::PARAM_INT);
    $stmt->bindValue(":offset", $offset, PDO::PARAM_INT);
    $stmt->execute();

    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

// Badge disponibilità (come visto in classe: dipende dalla giacenza)
function availabilityBadge($giacenza) {
    if ($giacenza <= 0) return "<span class='badge bg-danger'>Esaurito</span>";
    if ($giacenza <= 5) return "<span class='badge bg-warning text-dark'>Sottoscorta</span>";
    return "<span class='badge bg-success'>Disponibile</span>";
}

// Stampa 1 riga della tabella (HTML)
function printRow($row) {
    $sku  = h((string)$row["codice_sku"]);
    $nome = h((string)$row["nome"]);
    $desc = h((string)$row["descrizione"]);
    $cat  = h((string)$row["categoria"]);

    $prezzo = number_format((float)$row["prezzo_vendita"], 2, ",", ".");
    $gia = (int)$row["giacenza"];

    $badge = availabilityBadge($gia);
    $rowClass = ($gia <= 0) ? "table-secondary" : "";

    return "
    <tr class='{$rowClass}'>
        <td class='ps-3 fw-bold text-muted'>{$sku}</td>
        <td><img src='https://via.placeholder.com/50' class='img-thumb' alt='foto'></td>
        <td>
            <div class='fw-bold'>{$nome}</div>
            <small class='text-muted'>{$desc}</small>
        </td>
        <td><span class='badge bg-secondary'>{$cat}</span></td>
        <td class='text-num'>€ {$prezzo}</td>
        <td class='text-center fw-bold'>{$gia}</td>
        <td class='text-center'>{$badge}</td>
        <td class='text-end pe-3'>
            <button class='btn btn-sm btn-primary table-action-btn' title='Modifica'>
                <i class='fa-solid fa-pen'></i>
            </button>
            <button class='btn btn-sm btn-danger' title='Elimina'>
                <i class='fa-solid fa-trash'></i>
            </button>
        </td>
    </tr>
    ";
}

// Genera la paginazione Bootstrap (i numeri cambiano davvero)
function renderPagination($page, $pages, $status = "all") {
    if ($pages <= 1) return "";

    $page = clamp_int($page, 1, $pages);

    // manteniamo il filtro nella url
    $base = "index.php?status=" . urlencode($status) . "&page=";

    $html = "<ul class='pagination pagination-sm m-0'>";

    // precedente
    $prevDisabled = ($page <= 1) ? " disabled" : "";
    $html .= "<li class='page-item{$prevDisabled}'><a class='page-link' href='{$base}".($page-1)."'>Precedente</a></li>";

    // numeri (finestra semplice: max 7 numeri)
    $start = max(1, $page - 3);
    $end   = min($pages, $page + 3);

    if ($start > 1) {
        $html .= "<li class='page-item'><a class='page-link' href='{$base}1'>1</a></li>";
        if ($start > 2) $html .= "<li class='page-item disabled'><span class='page-link'>...</span></li>";
    }

    for ($i = $start; $i <= $end; $i++) {
        $active = ($i === $page) ? " active" : "";
        $html .= "<li class='page-item{$active}'><a class='page-link' href='{$base}{$i}'>{$i}</a></li>";
    }

    if ($end < $pages) {
        if ($end < $pages - 1) $html .= "<li class='page-item disabled'><span class='page-link'>...</span></li>";
        $html .= "<li class='page-item'><a class='page-link' href='{$base}{$pages}'>{$pages}</a></li>";
    }

    // successiva
    $nextDisabled = ($page >= $pages) ? " disabled" : "";
    $html .= "<li class='page-item{$nextDisabled}'><a class='page-link' href='{$base}".($page+1)."'>Successiva</a></li>";

    $html .= "</ul>";
    return $html;
}
