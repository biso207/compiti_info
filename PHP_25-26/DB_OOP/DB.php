<?php
class DB {
    // variabili membro (attributi) //
    private $conn, $connected,$msg;

    // costruttore //
    function __construct($serverName, $dbName, $username, $password) {
        $this->msg = "";
        try {
            // Fondamentale usare $this->conn
            $this->conn = new PDO("mysql:host=$serverName;dbname=$dbName", $username, $password);
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->connected = true;
        }
        catch (PDOException $e) {
            $this->connected = false;
            $this->msg = $e->getMessage();
        }
    }

    public function getResultSet($sql, $mode=PDO::FETCH_BOTH) {
        $cmd = $this->conn->prepare($sql);
        $cmd->execute();
        return $cmd->fetchAll($mode);
    }

    // getter //
    function get_conn() { return $this->conn; }
    function get_connected() { return $this->connected; }
    function get_msg() { return $this->msg; }
}