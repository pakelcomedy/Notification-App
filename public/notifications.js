document.addEventListener("DOMContentLoaded", () => {
    const notificationsContainer = document.querySelector(".notifications");
    const inputField = document.querySelector(".form-container input[type='text']");
    const sendButton = document.querySelector(".form-container button");

    // Event listener untuk tombol "Send"
    sendButton.addEventListener("click", () => {
        const message = inputField.value.trim();

        // Cek jika ada pesan yang dimasukkan
        if (message) {
            addNotification(message);
            inputField.value = ""; // Bersihkan field input setelah menambahkan notifikasi
        }
    });

    // Fungsi untuk menambahkan notifikasi baru ke dalam kontainer
    function addNotification(message) {
        // Format tanggal dan waktu
        const timestamp = new Date().toLocaleString();

        // Buat elemen notifikasi
        const notificationCard = document.createElement("div");
        notificationCard.classList.add("notification-card");

        const notificationMessage = document.createElement("div");
        notificationMessage.classList.add("notification-message");
        notificationMessage.textContent = message;

        const notificationTimestamp = document.createElement("div");
        notificationTimestamp.classList.add("notification-timestamp");
        notificationTimestamp.textContent = timestamp;

        // Tambahkan elemen pesan dan timestamp ke dalam notifikasi
        notificationCard.appendChild(notificationMessage);
        notificationCard.appendChild(notificationTimestamp);

        // Tambahkan notifikasi ke dalam kontainer dan tampilkan kontainernya jika tersembunyi
        notificationsContainer.appendChild(notificationCard);
        notificationsContainer.style.display = "flex";
    }
});
