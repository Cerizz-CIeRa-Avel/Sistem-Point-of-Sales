/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.service;

import com.pos.entity.Pelanggan;
import com.pos.repository.PelangganRepository;
import java.util.List;

public class PelangganService {
    private final PelangganRepository pelangganRepository;

    public PelangganService() {
        this.pelangganRepository = new PelangganRepository();
    }

    public List<Pelanggan> tampilkanSemua() {
        return pelangganRepository.getAll();
    }

    public void simpan(Pelanggan pelanggan) {
        if (pelanggan.getNamaPelanggan() == null || pelanggan.getNamaPelanggan().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pelanggan wajib diisi!");
        }
        boolean sukses = pelangganRepository.insert(pelanggan);
        if (!sukses) {
            throw new RuntimeException("Gagal menyimpan data pelanggan.");
        }
    }

    public void ubah(Pelanggan pelanggan) {
        if (pelanggan.getIdPelanggan() <= 0) {
            throw new IllegalArgumentException("ID Pelanggan tidak valid!");
        }
        if (pelanggan.getNamaPelanggan() == null || pelanggan.getNamaPelanggan().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pelanggan wajib diisi!");
        }
        boolean sukses = pelangganRepository.update(pelanggan);
        if (!sukses) {
            throw new RuntimeException("Gagal mengubah data pelanggan.");
        }
    }

    public void hapus(int idPelanggan) {
        if (idPelanggan <= 0) {
            throw new IllegalArgumentException("Pilih pelanggan yang valid untuk dihapus!");
        }
        boolean sukses = pelangganRepository.delete(idPelanggan);
        if (!sukses) {
            throw new RuntimeException("Gagal menghapus pelanggan. Data mungkin terhubung dengan transaksi.");
        }
    }
}
