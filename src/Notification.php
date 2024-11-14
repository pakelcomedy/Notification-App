<?php
// Include the Database class file to ensure it's accessible
require_once __DIR__ . '/../src/Database.php';

class Notification {
    private $db;

    public function __construct() {
        // Initialize the database connection using the Database class
        $this->db = new Database();
    }

    /**
     * Send a notification message (without title).
     *
     * @param string $message The message to be sent.
     * @return bool True on success, false on failure.
     */
    public function sendNotification($message) {
        if (empty($message)) {
            return false; // Reject empty messages
        }

        try {
            // Prepare the SQL query to insert the notification (message only)
            $query = "INSERT INTO notifications (message, created_at) VALUES (:message, NOW())";
            $stmt = $this->db->getConnection()->prepare($query);

            // Bind the message parameter to the query
            $stmt->bindParam(':message', $message);

            // Execute the query and check if it was successful
            return $stmt->execute();
        } catch (PDOException $e) {
            // Log any database-related errors
            error_log("Database error while inserting notification: " . $e->getMessage());
            return false;
        } catch (Exception $e) {
            // Log any other general errors
            error_log("Error while inserting notification: " . $e->getMessage());
            return false;
        }
    }

    /**
     * Get all notifications from the database.
     *
     * @return array An array of notifications (empty if none).
     */
    public function getNotifications() {
        try {
            // Prepare the SQL query to fetch all notifications, ordered by creation time
            $query = "SELECT * FROM notifications ORDER BY created_at DESC";
            $stmt = $this->db->getConnection()->prepare($query);
            $stmt->execute();

            // Fetch all notifications and return them as an associative array
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (PDOException $e) {
            // Log any database-related errors
            error_log("Database error while fetching notifications: " . $e->getMessage());
            return [];
        } catch (Exception $e) {
            // Log any other general errors
            error_log("Error while fetching notifications: " . $e->getMessage());
            return [];
        }
    }
}
?>
