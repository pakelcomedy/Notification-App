```
notification_app/
├── public/
│   ├── index.html           # Tampilan utama untuk pengguna mengakses notifikasi
│   ├── notifications.js      # Script JavaScript untuk menampilkan dan mengelola notifikasi di frontend
├── src/
│   ├── Notification.php      # Class untuk menangani logika notifikasi
│   ├── Database.php          # Class untuk koneksi database
├── config/
│   └── db_config.php         # Konfigurasi database
└── api/
    └── send_notification.php # Endpoint untuk mengirim notifikasi baru
    └── get_notifications.php # Endpoint untuk mengambil semua notifikasi
├── vendor/                   # Folder untuk dependensi Composer (misalnya, jika menggunakan library)
├── .env                      # File konfigurasi untuk menyimpan kredensial
└── composer.json             # Jika menggunakan Composer untuk PHP dependency management
```
