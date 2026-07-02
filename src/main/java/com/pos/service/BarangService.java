/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.service;

import com.pos.entity.Barang;
import com.pos.repository.BarangRepository;
import java.util.List;

public class BarangService {
    private final BarangRepository barangRepository;

    public BarangService() {
        this.barangRepository = new BarangRepository();
    }

    public List<Barang> tampilkanSemua() {
        return barangRepository.getAll();
    }

    public void simpan(Barang barang) {
        // Validasi Aturan Bisnis (Aturan 5)
        if (barang.getKodeBarang() == null || barang.getKodeBarang().trim().isEmpty()) {
            throw new IllegalArgumentException("Kode barang wajib diisi!");
        }
        if (barang.getNamaBarang() == null || barang.getNamaBarang().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang wajib diisi!");
        }
        if (barang.getHarga() <= 0) {
            throw new IllegalArgumentException("Harga harus lebih besar dari 0!");
        }
        if (barang.getStok() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif!");
        }
        
        boolean sukses = barangRepository.insert(barang);
        if (!sukses) {
            throw new RuntimeException("Gagal menyimpan barang. Pastikan kode barang tidak duplikat.");
        }
    }

    public void ubah(Barang barang) {
        if (barang.getNamaBarang() == null || barang.getNamaBarang().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang tidak boleh kosong!");
        }
        if (barang.getHarga() <= 0) {
            throw new IllegalArgumentException("Harga harus lebih besar dari 0!");
        }
        if (barang.getStok() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif!");
        }

        boolean sukses = barangRepository.update(barang);
        if (!sukses) {
            throw new RuntimeException("Gagal mengubah data barang.");
        }
    }

    public void hapus(String kodeBarang) {
        if (kodeBarang == null || kodeBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode barang harus ditentukan untuk dihapus!");
        }
        boolean sukses = barangRepository.delete(kodeBarang);
        if (!sukses) {
            throw new RuntimeException("Gagal menghapus barang. Barang mungkin sedang digunakan dalam transaksi.");
        }
    }
}
