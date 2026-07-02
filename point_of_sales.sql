-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jul 02, 2026 at 06:31 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `point_of_sales`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `kode_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `harga` decimal(12,2) NOT NULL,
  `stok` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `kode_barang`, `nama_barang`, `harga`, `stok`) VALUES
(1, 'BRG-01', 'Laptop Asus TUF Gaming A15', 12000000.00, 46),
(2, 'BRG-02', 'Macbook Pro Air', 26000000.00, 38),
(3, 'BRG-03', 'Indomie Goreng', 3000.00, 100),
(4, 'BRG-04', 'Chitato 50g', 12000.00, 50),
(5, 'BRG-05', 'Aqua Botol 600ml', 4500.00, 80),
(6, 'BRG-06', 'Teh Botol Sosro', 5000.00, 60),
(7, 'BRG-07', 'Kopi Kapal Api', 1500.00, 200),
(8, 'BRG-08', 'Susu Ultra Milk', 7000.00, 39),
(9, 'BRG-09', 'Roti Sari Roti', 6000.00, 30),
(10, 'BRG-10', 'Sabun Lifebuoy', 3500.00, 90),
(11, 'BRG-11', 'Shampoo Pantene', 25000.00, 25),
(12, 'BRG-12', 'Pasta Gigi Pepsodent', 12000.00, 45),
(13, 'BRG-13', 'Buku Tulis Sinar', 4000.00, 150),
(14, 'BRG-14', 'Pulpen Pilot', 3000.00, 200),
(15, 'BRG-15', 'Pensil 2B', 2000.00, 120),
(16, 'BRG-16', 'Penghapus Joyko', 1500.00, 80),
(17, 'BRG-17', 'Penggaris 30cm', 2500.00, 60),
(18, 'BRG-18', 'Tisu Paseo', 15000.00, 30),
(19, 'BRG-19', 'Minyak Goreng 1L', 18000.00, 19),
(20, 'BRG-20', 'Gula Pasir 1kg', 16000.00, 40),
(21, 'BRG-21', 'Kopi Nescafe', 6000.00, 55),
(22, 'BRG-22', 'Susu Kaleng Indomilk', 13000.00, 35),
(23, 'BRG-23', 'Keju Kraft', 22000.00, 25),
(24, 'BRG-24', 'Mentega BlueBand', 9000.00, 45),
(25, 'BRG-25', 'Biskuit Roma', 8000.00, 60),
(26, 'BRG-26', 'Permen Kopiko', 500.00, 500);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `harga_satuan` decimal(12,2) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal_item` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail`, `id_transaksi`, `id_barang`, `harga_satuan`, `jumlah`, `subtotal_item`) VALUES
(1, 1, 19, 18000.00, 1, 18000.00),
(2, 1, 8, 7000.00, 1, 7000.00);

-- --------------------------------------------------------

--
-- Table structure for table `metode_pembayaran`
--

CREATE TABLE `metode_pembayaran` (
  `id_metode` int(11) NOT NULL,
  `nama_metode` varchar(50) NOT NULL,
  `status` enum('Aktif','Nonaktif') DEFAULT 'Aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `metode_pembayaran`
--

INSERT INTO `metode_pembayaran` (`id_metode`, `nama_metode`, `status`) VALUES
(1, 'Cash', 'Aktif'),
(2, 'QRIS', 'Aktif'),
(3, 'Transfer Bank', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telepon` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telepon`, `alamat`) VALUES
(1, 'Muhammad Farrel Aqil Fauzy', '085347990923', 'Rajabasa'),
(2, 'Andi Wijaya', '081234567801', 'Jl. Merdeka No. 1, Jakarta'),
(3, 'Budi Santoso', '081234567802', 'Jl. Melati No. 5, Bandung'),
(4, 'Citra Lestari', '081234567803', 'Jl. Anggrek No. 10, Surabaya'),
(5, 'Dewi Sartika', '081234567804', 'Jl. Kenanga No. 2, Medan'),
(6, 'Eko Prasetyo', '081234567805', 'Jl. Mawar No. 7, Yogyakarta'),
(7, 'Fajar Hidayat', '081234567806', 'Jl. Tulip No. 12, Semarang'),
(8, 'Gita Permata', '081234567807', 'Jl. Dahlia No. 4, Malang'),
(9, 'Hadi Kusuma', '081234567808', 'Jl. Cempaka No. 9, Solo'),
(10, 'Indah Sari', '081234567809', 'Jl. Teratai No. 3, Bogor'),
(11, 'Joko Susilo', '081234567810', 'Jl. Flamboyan No. 15, Bali'),
(12, 'Kurniawati', '081234567811', 'Jl. Kamboja No. 6, Makassar'),
(13, 'Lutfi Hakim', '081234567812', 'Jl. Sakura No. 8, Palembang'),
(14, 'Maya Anggraini', '081234567813', 'Jl. Bougenville No. 20, Lampung'),
(15, 'Nanda Putra', '081234567814', 'Jl. Kelapa No. 11, Pontianak'),
(16, 'Oki Setiawan', '081234567815', 'Jl. Cemara No. 5, Balikpapan'),
(17, 'Putri Rahayu', '081234567816', 'Jl. Pinus No. 14, Samarinda'),
(18, 'Qori Anindya', '081234567817', 'Jl. Jati No. 22, Manado'),
(19, 'Rian Hidayat', '081234567818', 'Jl. Mahoni No. 9, Padang'),
(20, 'Siska Amelia', '081234567819', 'Jl. Akasia No. 1, Pekanbaru'),
(21, 'Toni Firmansyah', '081234567820', 'Jl. Bambu No. 3, Banjarmasin'),
(22, 'Umi Kalsum', '081234567821', 'Jl. Mangga No. 7, Jambi'),
(23, 'Vina Febrianti', '081234567822', 'Jl. Jeruk No. 18, Mataram'),
(24, 'Wawan Hermawan', '081234567823', 'Jl. Apel No. 4, Kupang'),
(25, 'Xena Natalia', '081234567824', 'Jl. Pepaya No. 10, Ambon');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `no_faktur` varchar(50) NOT NULL,
  `tanggal_waktu` datetime DEFAULT current_timestamp(),
  `id_user` int(11) NOT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `id_metode` int(11) NOT NULL,
  `subtotal` decimal(12,2) NOT NULL,
  `diskon` decimal(12,2) DEFAULT 0.00,
  `total_akhir` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `no_faktur`, `tanggal_waktu`, `id_user`, `id_pelanggan`, `id_metode`, `subtotal`, `diskon`, `total_akhir`) VALUES
(1, 'INV-D21EF3FE', '2026-07-02 11:26:50', 1, NULL, 2, 25000.00, 0.00, 25175.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `role` enum('Admin','Kasir') DEFAULT 'Kasir'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `nama_lengkap`, `role`) VALUES
(1, 'admin', 'admin123', 'Administrator Utama', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD UNIQUE KEY `kode_barang` (`kode_barang`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `metode_pembayaran`
--
ALTER TABLE `metode_pembayaran`
  ADD PRIMARY KEY (`id_metode`),
  ADD UNIQUE KEY `nama_metode` (`nama_metode`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD UNIQUE KEY `no_faktur` (`no_faktur`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_metode` (`id_metode`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `metode_pembayaran`
--
ALTER TABLE `metode_pembayaran`
  MODIFY `id_metode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE SET NULL,
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_metode`) REFERENCES `metode_pembayaran` (`id_metode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
