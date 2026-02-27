<?php
class DBSessions {
    // variabili membro (attributi) //
    private $conn, $connected,$msg;

    // costruttore //
    function __construct($serverName, $dbName, $email, $password) {
        $this->msg = "";
        try {
            // Fondamentale usare $this->conn
            $this->conn = new PDO("mysql:host=$serverName;dbname=$dbName", $email, $password);
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->connected = true;
        }
        catch (PDOException $e) {
            $this->connected = false;
            $this->msg = $e->getMessage();
        }
    }

    // GETTER //
    function get_conn(): PDO { return $this->conn; }
    function get_connected(): bool { return $this->connected; }
    function get_msg(): string { return $this->msg; }

    // METODI //

    // metodo per query generica
    public function query($sql, $params = [], $mode = PDO::FETCH_BOTH) {
        $this->msg = null;
        $stmt = $this->conn->prepare($sql);

        try {
            $stmt->execute($params);
        } catch (PDOException $e) {
            // errori => visualizzo messaggio di errore
            $this->msg = $e->getMessage();
            return null;
        }

        // se la query inizia con SELECT, restituisco i risultati, altrimenti restituisco il numero di righe coinvolte
        if (stripos($sql, "SELECT") !== false) return $stmt->fetchAll($mode);
        return $stmt->rowCount();
    }

    // salvataggio credenziali sessione sul db
    public function save_credentials($username, $password) : bool {
        // controllo esistenza utente
        $exist = $this->conn->query("SELECT username FROM sessione_utente WHERE username = '$username'");
        if ($exist->rowCount() > 0) return true; // username esistente => non creato

        $sql = "INSERT INTO sessione_utente (username, password_hash) VALUES (:username, :password)"; // query per db
        $cmd = $this->conn->prepare($sql);
        $cmd->bindParam(":username", $username);
        $cmd->bindParam(":password", $password);
        $cmd->execute();
        return false; // username non esistente => creato
    }

    // verifica credenziali sessione sul db
    public function check_credentials($username, $password) : bool {
        $sql = "SELECT password_hash FROM sessione_utente WHERE username = :username"; // query per db

        // preparazione comando
        $cmd = $this->conn->prepare($sql);

        $cmd->execute([':username' => $username]);
        $hash = $cmd->fetchColumn();

        if (!$hash) return false; // user non trovato

        return password_verify($password, $hash); // verifica password
    }
}