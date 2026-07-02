/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.controller;

import com.pos.entity.Pelanggan;
import com.pos.service.PelangganService;
import com.pos.view.PelangganForm;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PelangganController {
    private final PelangganService pelangganService;
    private final PelangganForm pelangganForm;

    public PelangganController(PelangganForm pelangganForm) {
        this.pelangganForm = pelangganForm;
        this.pelangganService = new PelangganService();
    }

    public void muatDataTabel() {
        DefaultTableModel model = (DefaultTableModel) pelangganForm.getTabelPelanggan().getModel();
        model.setRowCount(0);

        List<Pelanggan> list = pelangganService.tampilkanSemua();
        for (Pelanggan p : list) {
            Object[] row = {p.getIdPelanggan(), p.getNamaPelanggan(), p.getNoTelepon(), p.getAlamat()};
            model.addRow(row);
        }
    }

    public void tambahPelanggan() {
        try {
            Pelanggan p = pelangganForm.getPelangganInput();
            pelangganService.simpan(p);
            JOptionPane.showMessageDialog(pelangganForm, "Pelanggan berhasil disimpan!");
            pelangganForm.bersihkanForm();
            muatDataTabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pelangganForm, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ubahPelanggan() {
        try {
            Pelanggan p = pelangganForm.getPelangganInput();
            if (p.getIdPelanggan() == 0) throw new IllegalArgumentException("Pilih pelanggan dari tabel terlebih dahulu!");
            
            pelangganService.ubah(p);
            JOptionPane.showMessageDialog(pelangganForm, "Data pelanggan berhasil diubah!");
            pelangganForm.bersihkanForm();
            muatDataTabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pelangganForm, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hapusPelanggan() {
        int id = pelangganForm.getIdPelangganTerpilih();
        if (id == 0) {
            JOptionPane.showMessageDialog(pelangganForm, "Pilih pelanggan yang ingin dihapus dari tabel!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(pelangganForm, "Yakin menghapus pelanggan ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                pelangganService.hapus(id);
                JOptionPane.showMessageDialog(pelangganForm, "Pelanggan berhasil dihapus!");
                pelangganForm.bersihkanForm();
                muatDataTabel();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pelangganForm, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
