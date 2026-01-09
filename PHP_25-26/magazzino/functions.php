<?php
require "db.php";
function getTable() {
	global $conn;
	$stmt = $conn->prepare("SELECT codice_sku, nome, descrizione, categoria, prezzo_vendita, giacenza FROM 5CIN_2526_Magazzino_Prodotti");
	$stmt->execute();
    
    $result = $stmt->fetch(PDO::FETCH_ASSOC);
    return $result;
}

function printRow($row) {
	$buff = "
    <tr>
      <td class='ps-3 fw-bold text-muted'>{$row["codice_sku"]}</td>
      <td><img src='https://via.placeholder.com/50' class='img-thumb' alt='foto'></td>
      <td>
          <div class='fw-bold'>{$row["nome"]}</div>
          <small class='text-muted'>{$row["descrizione"]}</small>
      </td>
      <td><span class='badge bg-secondary'>{$row["categoria"]}</span></td>
      <td class='text-num'>€ {$row["prezzo_vendita"]}</td>
      <td class='text-center fw-bold'>{$row["giacenza"]}</td>
      <td class='text-center'>
          <span class='badge bg-success'>Disponibile</span>
      </td>
      <td class='text-end pe-3'>
          <button class='btn btn-sm btn-primary table-action-btn' title='Modifica'><i class='fa-solid fa-pen'></i></button>
          <button class='btn btn-sm btn-danger' title='Elimina'><i class='fa-solid fa-trash'></i></button>
      </td>
   	</tr>
    ";
    return $buff;
}