-- 1. Tabel Users (Untuk Fitur Login)
CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nama_lengkap VARCHAR(100) NOT NULL,
    role ENUM('Admin', 'Kasir') DEFAULT 'Kasir'
);

-- 2. Tabel Pelanggan (Untuk Fitur Master Pelanggan)
CREATE TABLE pelanggan (
    id_pelanggan INT AUTO_INCREMENT PRIMARY KEY,
    nama_pelanggan VARCHAR(100) NOT NULL,
    no_telepon VARCHAR(15),
    alamat TEXT
);

-- 3. Tabel Barang (Untuk Fitur Master Barang)
CREATE TABLE barang (
    id_barang INT AUTO_INCREMENT PRIMARY KEY,
    kode_barang VARCHAR(20) NOT NULL UNIQUE,
    nama_barang VARCHAR(100) NOT NULL,
    harga DECIMAL(12, 2) NOT NULL,
    stok INT NOT NULL DEFAULT 0
);

-- 4. Tabel Metode Pembayaran (Mendukung Strategy Pattern & Fitur Plugin)
-- Menyimpan daftar metode (Cash, QRIS, Transfer) agar dinamis
CREATE TABLE metode_pembayaran (
    id_metode INT AUTO_INCREMENT PRIMARY KEY,
    nama_metode VARCHAR(50) NOT NULL UNIQUE,
    status ENUM('Aktif', 'Nonaktif') DEFAULT 'Aktif'
);

-- 5. Tabel Transaksi (Header Riwayat Penjualan)
CREATE TABLE transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    no_faktur VARCHAR(50) NOT NULL UNIQUE,
    tanggal_waktu DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_user INT NOT NULL,
    id_pelanggan INT, 
    id_metode INT NOT NULL,
    subtotal DECIMAL(12, 2) NOT NULL,
    diskon DECIMAL(12, 2) DEFAULT 0,
    total_akhir DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE RESTRICT,
    FOREIGN KEY (id_pelanggan) REFERENCES pelanggan(id_pelanggan) ON DELETE SET NULL,
    FOREIGN KEY (id_metode) REFERENCES metode_pembayaran(id_metode) ON DELETE RESTRICT
);

-- 6. Tabel Detail Transaksi (Item yang dibeli dalam satu faktur)
CREATE TABLE detail_transaksi (
    id_detail INT AUTO_INCREMENT PRIMARY KEY,
    id_transaksi INT NOT NULL,
    id_barang INT NOT NULL,
    harga_satuan DECIMAL(12, 2) NOT NULL,
    jumlah INT NOT NULL,
    subtotal_item DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi) ON DELETE CASCADE,
    FOREIGN KEY (id_barang) REFERENCES barang(id_barang) ON DELETE RESTRICT
);

-- Insert Data Awal (Dummy Data) untuk Pengujian
INSERT INTO users (username, password, nama_lengkap, role) 
VALUES ('admin', 'admin123', 'Administrator Utama', 'Admin');

INSERT INTO metode_pembayaran (nama_metode, status) 
VALUES 
('Cash', 'Aktif'), 
('QRIS', 'Aktif'), 
('Tranfer Bank', 'Aktif');
