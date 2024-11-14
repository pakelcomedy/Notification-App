<?php
class Database {
    private $conn;
    private $host;
    private $db_name;
    private $username;
    private $password;

    // Constructor to load configuration from a file
    public function __construct($configFilePath = __DIR__ . '/../config/db_config.php') {
        // Ensure the config file exists
        if (!file_exists($configFilePath)) {
            die("Database configuration file not found.");
        }
        
        $config = include($configFilePath);

        // Check if the configuration is valid
        if (!isset($config['host'], $config['dbname'], $config['username'], $config['password'])) {
            die("Invalid database configuration.");
        }

        // Set the database configuration
        $this->host = $config['host'];
        $this->db_name = $config['dbname'];
        $this->username = $config['username'];
        $this->password = $config['password'];
    }

    // Get database connection
    public function getConnection() {
        $this->conn = null;

        try {
            // Create PDO instance
            $this->conn = new PDO(
                "mysql:host={$this->host};dbname={$this->db_name};charset=utf8mb4",
                $this->username,
                $this->password
            );

            // Set PDO error mode to exception
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            return $this->conn;
        } catch (PDOException $exception) {
            // Log error and return null if unable to connect
            error_log("Database connection error: " . $exception->getMessage());
            echo json_encode(['status' => 'error', 'message' => 'Database connection failed.']);
            exit;  // Exit script after failure
        }
    }

    // Close database connection
    public function closeConnection() {
        $this->conn = null; // Explicitly close the PDO connection
    }
}
?>
