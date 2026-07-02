/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.repository;

import com.pos.database.DatabaseConnection;
import com.pos.entity.DetailTransaksi;
import com.pos.entity.Transaksi;
import java.sql.*;

public class TransaksiRepository {
    private final Connection conn;

    public TransaksiRepository() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public boolean simpanTransaksi(Transaksi transaksi) throws SQLException {
        String sqlHeader = "INSERT INTO transaksi (no_faktur, id_user, id_pelanggan, id_metode, subtotal, diskon, total_akhir) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlDetail = "INSERT INTO detail_transaksi (id_transaksi, id_barang, harga_satuan, jumlah, subtotal_item) VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateStok = "UPDATE barang SET stok = stok - ? WHERE id_barang = ?";

        try {
            // MATIKAN AUTO-COMMIT UNTUK MEMULAI DATABASE TRANSACTION (ACID)
            conn.setAutoCommit(false); 

            // 1. Simpan Header Transaksi
            int idTransaksiGenerated = 0;
            try (PreparedStatement psHeader = conn.prepareStatement(sqlHeader, Statement.RETURN_GENERATED_KEYS)) {
                psHeader.setString(1, transaksi.getNoFaktur());
                psHeader.setInt(2, transaksi.getIdUser());
                
                if (transaksi.getIdPelanggan() > 0) {
                    psHeader.setInt(3, transaksi.getIdPelanggan());
                } else {
                    psHeader.setNull(3, java.sql.Types.INTEGER);
                }
                
                psHeader.setInt(4, transaksi.getIdMetodePembayaran());
                psHeader.setDouble(5, transaksi.getSubtotal());
                psHeader.setDouble(6, transaksi.getDiskon());
                psHeader.setDouble(7, transaksi.getTotalAkhir());
                psHeader.executeUpdate();

                // Ambil ID Transaksi yang baru saja dibuat oleh MySQL (AUTO_INCREMENT)
                try (ResultSet rs = psHeader.getGeneratedKeys()) {
                    if (rs.next()) {
                        idTransaksiGenerated = rs.getInt(1);
                    }
                }
            }

            // 2. Simpan Detail Transaksi dan Update Stok secara looping
            try (PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                 PreparedStatement psUpdateStok = conn.prepareStatement(sqlUpdateStok)) {
                 
                for (DetailTransaksi dt : transaksi.getDaftarBarang()) {
                    // Batch Insert Detail
                    psDetail.setInt(1, idTransaksiGenerated);
                    psDetail.setInt(2, dt.getIdBarang());
                    psDetail.setDouble(3, dt.getHargaSatuan());
                    psDetail.setInt(4, dt.getJumlah());
                    psDetail.setDouble(5, dt.getSubtotalItem());
                    psDetail.addBatch();

                    // Batch Update Stok
                    psUpdateStok.setInt(1, dt.getJumlah());
                    psUpdateStok.setInt(2, dt.getIdBarang());
                    psUpdateStok.addBatch();
                }
                
                psDetail.executeBatch();
                psUpdateStok.executeBatch();
            }

            // JIKA SEMUA BERHASIL, COMMIT PERUBAHAN KE DATABASE
            conn.commit(); 
            return true;

        } catch (SQLException e) {
            // JIKA ADA 1 SAJA YANG GAGAL, KEMBALIKAN DATA SEPERTI SEMULA
            conn.rollback(); 
            throw new SQLException("Transaksi gagal, Rollback dilakukan: " + e.getMessage());
        } finally {
            // KEMBALIKAN STATUS AUTO-COMMIT KE NORMAL
            conn.setAutoCommit(true); 
        }
    }
}
