/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.controller;

import com.pos.entity.Barang;
import com.pos.service.BarangService;
import com.pos.view.BarangForm;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BarangController {
    private final BarangService barangService;
    private final BarangForm barangForm;

    public BarangController(BarangForm barangForm) {
        this.barangForm = barangForm;
        this.barangService = new BarangService();
    }

    // Mengisi data dari database ke JTable di Form
    public void muatDataTabel() {
        DefaultTableModel model = (DefaultTableModel) barangForm.getTabelBarang().getModel();
        model.setRowCount(0); // Bersihkan tabel terlebih dahulu

        List<Barang> list = barangService.tampilkanSemua();
        for (Barang b : list) {
            Object[] row = {b.getKodeBarang(), b.getNamaBarang(), b.getHarga(), b.getStok()};
            model.addRow(row);
        }
    }

    public void tambahBarang() {
        try {
            Barang b = barangForm.getBarangInput();
            barangService.simpan(b);
            JOptionPane.showMessageDialog(barangForm, "Barang berhasil disimpan!");
            barangForm.bersihkanForm();
            muatDataTabel();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(barangForm, "Harga dan Stok harus berupa angka valid!", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(barangForm, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ubahBarang() {
        try {
            Barang b = barangForm.getBarangInput();
            barangService.ubah(b);
            JOptionPane.showMessageDialog(barangForm, "Data barang berhasil diubah!");
            barangForm.bersihkanForm();
            muatDataTabel();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(barangForm, "Harga dan Stok harus berupa angka valid!", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(barangForm, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hapusBarang() {
        String kode = barangForm.getKodeInput();
        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(barangForm, "Pilih barang yang ingin dihapus dari tabel!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(barangForm, "Apakah Anda yakin ingin menghapus barang " + kode + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                barangService.hapus(kode);
                JOptionPane.showMessageDialog(barangForm, "Barang berhasil dihapus!");
                barangForm.bersihkanForm();
                muatDataTabel();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(barangForm, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
