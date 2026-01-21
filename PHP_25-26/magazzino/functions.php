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

/**
 * Costruisce WHERE + params per:
 * - filtro status (all/low/out)
 * - ricerca testuale q
 */
function buildWhere($status, $q, &$params) {
    $params = [];
    $conds = [];

    // filtro status
    if ($status === "out") $conds[] = "giacenza <= 0";
    else if ($status === "low") $conds[] = "giacenza BETWEEN 1 AND 5";

    // ricerca (LIKE su più colonne)
    $q = trim((string)$q);
    if ($q !== "") {
        $conds[] = "(codice_sku LIKE :q OR nome LIKE :q OR descrizione LIKE :q OR categoria LIKE :q)";
        $params[":q"] = "%" . $q . "%";
    }

    if (!$conds) return "";
    return " WHERE " . implode(" AND ", $conds);
}

// Conta i prodotti (serve per "Prodotti in archivio" + paginazione)
function countProducts($status = "all", $q = "") {
    global $conn;

    $where = buildWhere($status, $q, $params);
    $sql = "SELECT COUNT(*) FROM `5CIN_2526_Magazzino_Prodotti`" . $where;

    $stmt = $conn->prepare($sql);
    foreach ($params as $k => $v) $stmt->bindValue($k, $v, PDO::PARAM_STR);
    $stmt->execute();

    return (int)$stmt->fetchColumn();
}

// Prende i prodotti della pagina corrente (paginazione vera)
function getProducts($page, $perPage, $status = "all", $q = "") {
    global $conn;

    $page = max(1, (int)$page);
    $perPage = clamp_int((int)$perPage, 1, 100);
    $offset = ($page - 1) * $perPage;

    $where = buildWhere($status, $q, $params);

    $sql = "SELECT codice_sku, nome, descrizione, categoria, prezzo_vendita, giacenza
            FROM `5CIN_2526_Magazzino_Prodotti`" . $where . "
            ORDER BY nome ASC
            LIMIT :limit OFFSET :offset";

    $stmt = $conn->prepare($sql);

    // params ricerca
    foreach ($params as $k => $v) $stmt->bindValue($k, $v, PDO::PARAM_STR);

    // paging
    $stmt->bindValue(":limit", $perPage, PDO::PARAM_INT);
    $stmt->bindValue(":offset", $offset, PDO::PARAM_INT);

    $stmt->execute();
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}

// Badge disponibilità
function availabilityBadge($giacenza) {
    if ($giacenza <= 0) return "<span class='badge bg-danger'>Esaurito</span>";
    if ($giacenza <= 5) return "<span class='badge bg-warning text-dark'>Sotto Scorta</span>";
    return "<span class='badge bg-success'>Disponibile</span>";
}

// Stampa 1 riga della tabella
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

// Paginazione Bootstrap mantenendo status + q
function renderPagination($page, $pages, $status = "all", $q = "") {
    if ($pages <= 1) return "";

    $page = clamp_int((int)$page, 1, (int)$pages);

    $base = "index.php?status=" . urlencode($status)
        . "&q=" . urlencode((string)$q)
        . "&page=";

    $html = "<ul class='pagination pagination-sm m-0'>";

    $prevDisabled = ($page <= 1) ? " disabled" : "";
    $html .= "<li class='page-item{$prevDisabled}'><a class='page-link' href='{$base}".($page-1)."'>Precedente</a></li>";

    $start = max(1, $page - 2);
    $end   = min($pages, $page + 2);

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

    $nextDisabled = ($page >= $pages) ? " disabled" : "";
    $html .= "<li class='page-item{$nextDisabled}'><a class='page-link' href='{$base}".($page+1)."'>Successiva</a></li>";

    $html .= "</ul>";
    return $html;
}
