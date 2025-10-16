<style>
    table, th, td {
        border: 2px solid black;
    }
    #k {width: 300px;}
    #v {width: 1000px;}
    td:nth-child(even) {background-color: white;}
    td:nth-child(odd) {background-color: lightgrey;}
    td:hover {border-color: red;}
</style>

<?php
foreach ($_SERVER as $k => $v) {
    echo "<table>";
    echo "<tr>";
    echo "<td id='k'>$k</td>";
    echo "<td id='v'>$v</td>";
    echo "</tr>";
    echo "</table>";
}
?>