<?php
// Include the necessary Notification class file
require_once __DIR__ . '/../src/Notification.php';

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    try {
        // Initialize the Notification class
        $notification = new Notification();

        // Fetch all notifications from the database
        $notifications = $notification->getNotifications();

        // Return success response with the notifications data
        if (empty($notifications)) {
            echo json_encode(['status' => 'success', 'message' => 'No notifications found']);
        } else {
            echo json_encode(['status' => 'success', 'data' => $notifications]);
        }
    } catch (Exception $e) {
        // Handle any errors during the process
        echo json_encode(['status' => 'error', 'message' => 'An error occurred while fetching notifications: ' . $e->getMessage()]);
    }
} else {
    // Return an error response for invalid request method
    echo json_encode(['status' => 'error', 'message' => 'Invalid request method']);
}
?>
