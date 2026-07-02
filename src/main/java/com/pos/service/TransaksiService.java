/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.service;

import com.pos.entity.Transaksi;
import com.pos.repository.TransaksiRepository;
import com.pos.service.payment.PaymentStrategy;
import java.sql.SQLException;
import java.util.UUID;

public class TransaksiService {
    private final TransaksiRepository transaksiRepository;

    public TransaksiService() {
        this.transaksiRepository = new TransaksiRepository();
    }

    public void prosesTransaksi(Transaksi transaksi, PaymentStrategy paymentStrategy) throws SQLException {
        // 1. Validasi keranjang belanja
        if (transaksi.getDaftarBarang().isEmpty()) {
            throw new IllegalArgumentException("Keranjang belanja masih kosong!");
        }

        // 2. Terapkan Strategy Pattern untuk menghitung total akhir berdasarkan metode pembayaran
        double totalBelanja = transaksi.getSubtotal() - transaksi.getDiskon();
        double totalAkhir = paymentStrategy.hitungTotalAkhir(totalBelanja);
        
        transaksi.setTotalAkhir(totalAkhir);
        transaksi.setIdMetodePembayaran(paymentStrategy.getIdMetode());

        // 3. Generate Nomor Faktur otomatis (Bisa menggunakan UUID atau kombinasi tanggal)
        String noFaktur = "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        transaksi.setNoFaktur(noFaktur);

        // 4. Simpan ke database melalui repositori (akan memicu ACID transaction)
        transaksiRepository.simpanTransaksi(transaksi);
    }
}
