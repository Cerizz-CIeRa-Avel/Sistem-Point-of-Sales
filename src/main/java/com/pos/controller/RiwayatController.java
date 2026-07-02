/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.controller;

import com.pos.repository.TransaksiRepository;
import com.pos.view.RiwayatForm;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RiwayatController {
    private final RiwayatForm riwayatForm;
    private final TransaksiRepository transaksiRepository;

    public RiwayatController(RiwayatForm riwayatForm) {
        this.riwayatForm = riwayatForm;
        this.transaksiRepository = new TransaksiRepository();
    }

    public void muatDataRiwayat() {
        DefaultTableModel model = (DefaultTableModel) riwayatForm.getTabelRiwayat().getModel();
        model.setRowCount(0); // Bersihkan tabel

        List<Object[]> daftarRiwayat = transaksiRepository.getSemuaRiwayat();
        for (Object[] baris : daftarRiwayat) {
            model.addRow(baris);
        }
    }
}
