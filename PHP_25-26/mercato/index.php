<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="style.css">
        <link rel="icon" type="image/x-icon" href="favicon.ico">
        <title>Ortofrutta</title>
    </head>
    <body>
        <header class="header">
            <h1>Ortofrutta</h1>
            <p>Magazzino frutta (dal JSON)</p>
        </header>

        <main class="container">
            <section class="grid">
                <?php
                    require "fruit.php";

                    $fp = fopen("magazzino.json", "r");
                    $data = json_decode(fread($fp, filesize("magazzino.json")), true);
                    fclose($fp);

                    $bancarella = [];
                    foreach ($data["fruits"] as $f) {
                        $bancarella[] = new Fruit($f["name"], $f["color"], $f["taste"], $f["quantity"]);
                    }

                    foreach ($bancarella as $fruit) $fruit->print_info();
                ?>
            </section>
        </main>
    </body>
</html>