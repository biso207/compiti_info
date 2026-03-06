<?php
class DB {
    private $conn, $connected, $msg;

    function __construct($serverName, $dbName, $username, $password) {
        $this->msg = "";

        try {
            $this->conn = new PDO("mysql:host=$serverName;dbname=$dbName", $username, $password);
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->connected = true;
        }
        catch (PDOException $e) {
            $this->connected = false;
            $this->msg = $e->getMessage();
        }
    }

    public function getResultSet($sql, $mode=PDO::FETCH_BOTH): array {
        $cmd = $this->conn->prepare($sql);
        $cmd->execute();
        return $cmd->fetchAll($mode);
    }

    function get_conn(): PDO { return $this->conn; }
    function get_connected(): bool { return $this->connected; }
    function get_msg(): string { return $this->msg; }

}
