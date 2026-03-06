<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="utf-8">
        <meta name="generator" content="AlterVista - Editor HTML"/>
        <title>DB Amici</title>
        <!--style sheet-->
        <link rel="stylesheet" href="style.css">
        <!--icon-->
        <link rel="icon" href="../../../assets/img/favicons/page_icon.png" type="image/png">
    </head>
    <body>

        <!-barra di navigazione-->
        <nav class="navbar">
            <ul>
                <li><a class="backIndex" href="../../index.html">Progetti 5CIN</a></li>
            </ul>
        </nav>

        <?php
        $conn = null;
        try {
            $conn = new PDO("mysql:host=localhost;dbname=my_lucabiso", 'lucabiso', '');
            // set the PDO error mode to exception
            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            echo "Connected successfully";
        } catch(PDOException $e) {
            echo "Connection failed: " . $e->getMessage();
        }

        $sql = "SELECT * FROM amici";
        echo "<p>Nome e cognome di amici presenti nel DBSessions :</p>";
        foreach ($conn->query($sql) as $row){
            echo "<p>"."- ".$row['nome']." ".$row['cognome']."</p>";
        }
        ?>


    </body>
</html>
