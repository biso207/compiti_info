<!DOCTYPE html>

<?php
require "DB.php";

// creazione oggetto DBSessions
$db = new DBSessions("localhost", "my_lucabiso", "lucabiso", "");

$conn = $db->get_conn();

// controllo connessione
if (!$db->get_connected()) die("ERRORE: " . $db->get_msg());
else echo "Connessione OK";

$rs = $db->getResultSet("select * FROM comuni");
echo "<style>
table, th, td {
  border: 1px solid blue;
  border-collapse: collapse;
  padding: 10px;
}

</style>";

echo "<table>";
echo "<th>cod_com</th>";
echo "<th>des_com</th>";
echo "<th>des_reg</th>";
echo "<th>des_pro</th>";
echo "<th>sigla_pro</th>";
echo "<th>cod_com2</th>";
echo "<th>istat</th>";
foreach ($rs as $row) {
    echo "<tr>";
    echo "<td>".$row['cod_com']."</td>";
    echo "<td>".$row['des_com']."</td>";
    echo "<td>".$row['des_reg']."</td>";
    echo "<td>".$row['des_pro']."</td>";
    echo "<td>".$row['sigla_pro']."</td>";
    echo "<td>".$row['cod_com2']."</td>";
    echo "<td>".$row['istat']."</td>";
    echo "<tr>";
}