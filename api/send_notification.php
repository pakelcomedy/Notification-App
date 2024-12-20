<?php
// Include necessary files
require_once __DIR__ . '/../src/Notification.php';

// Check if the request is POST
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get raw POST data
    $inputData = json_decode(file_get_contents('php://input'), true);

    // Check if the data was successfully decoded
    if (json_last_error() !== JSON_ERROR_NONE) {
        echo json_encode(['status' => 'error', 'message' => 'Invalid JSON format']);
        exit;
    }

    // Get the message from the decoded JSON
    $message = isset($inputData['message']) ? trim($inputData['message']) : '';

    // Check if the message is empty
    if (empty($message)) {
        echo json_encode(['status' => 'error', 'message' => 'Message cannot be empty']);
        exit;
    }

    // Proceed with sending the notification (no title required)
    try {
        $notification = new Notification();
        $result = $notification->sendNotification($message);

        // Send success response if notification was successfully sent
        if ($result) {
            echo json_encode(['status' => 'success', 'message' => 'Notification sent successfully']);
        } else {
            // Send error response if notification failed
            echo json_encode(['status' => 'error', 'message' => 'Failed to send notification']);
        }
    } catch (Exception $e) {
        // Catch and handle any exceptions, then return an error
        echo json_encode(['status' => 'error', 'message' => 'Error: ' . $e->getMessage()]);
    }
} else {
    // Send error response if the request method is not POST
    echo json_encode(['status' => 'error', 'message' => 'Invalid request method']);
}
