<!DOCTYPE html>
    <html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Sessioni</title>
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <?php
            $status = $_GET['status'] ?? '';

            switch ($status) {

                case 'invalid':
                    echo '<h2 class="error">INVALID CREDENTIAL</h2>';
                    echo '<button onclick="window.location.href=\'auth.html\'">Back</button>';
                    break;

                case 'correct':
                    echo '<h2 class="success">CORRECT CREDENTIAL</h2>';
                    break;

                case 'created':
                    echo '<h2 class="success">USER CREATED</h2>';
                    break;

                case 'uncreated':
                    echo '<h2 class="success">USER ALREADY EXISTS</h2>';
                    break;

                default:
                    echo '<h2>UNKNOWN STATUS</h2>';
            }
        ?>
    </body>
</html>