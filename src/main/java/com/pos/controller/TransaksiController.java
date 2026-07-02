/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.controller;

import com.pos.entity.Barang;
import com.pos.entity.DetailTransaksi;
import com.pos.entity.Transaksi;
import com.pos.repository.BarangRepository;
import com.pos.service.TransaksiService;
import com.pos.service.payment.CashPayment;
import com.pos.service.payment.PaymentStrategy;
import com.pos.service.payment.QrisPayment;
import com.pos.view.TransaksiForm;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TransaksiController {
    private final TransaksiForm form;
    private final TransaksiService transaksiService;
    private final BarangRepository barangRepository; // Untuk mengecek kode barang
    private final Transaksi transaksiAktif;

    public TransaksiController(TransaksiForm form) {
        this.form = form;
        this.transaksiService = new TransaksiService();
        this.barangRepository = new BarangRepository();
        this.transaksiAktif = new Transaksi();
    }

    public void tambahBarangKeKeranjang() {
        String kodeBarang = form.getTxtKodeBarang().getText().trim();
        int jumlah;
        
        try {
            jumlah = Integer.parseInt(form.getTxtJumlah().getText().trim());
            if (jumlah <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(form, "Jumlah harus berupa angka lebih dari 0!");
            return;
        }

        // Cari barang di database
        List<Barang> semuaBarang = barangRepository.getAll();
        Barang barangDitemukan = null;
        for (Barang b : semuaBarang) {
            if (b.getKodeBarang().equalsIgnoreCase(kodeBarang)) {
                barangDitemukan = b;
                break;
            }
        }

        if (barangDitemukan == null) {
            JOptionPane.showMessageDialog(form, "Barang dengan kode " + kodeBarang + " tidak ditemukan!");
            return;
        }

        if (barangDitemukan.getStok() < jumlah) {
            JOptionPane.showMessageDialog(form, "Stok tidak mencukupi! Sisa stok: " + barangDitemukan.getStok());
            return;
        }

        // Tambahkan ke entitas Transaksi
        DetailTransaksi dt = new DetailTransaksi(
            barangDitemukan.getIdBarang(), 
            barangDitemukan.getNamaBarang(), 
            barangDitemukan.getHarga(), 
            jumlah
        );
        transaksiAktif.tambahBarang(dt);

        muatKeranjangKeTabel();
        form.getTxtKodeBarang().setText("");
        form.getTxtJumlah().setText("1");
        form.getTxtKodeBarang().requestFocus(); // Fokus kembali ke inputan kode
    }

    private void muatKeranjangKeTabel() {
        DefaultTableModel model = (DefaultTableModel) form.getTabelKeranjang().getModel();
        model.setRowCount(0);

        for (DetailTransaksi dt : transaksiAktif.getDaftarBarang()) {
            Object[] row = {
                dt.getNamaBarang(),
                dt.getHargaSatuan(),
                dt.getJumlah(),
                dt.getSubtotalItem()
            };
            model.addRow(row);
        }
        
        form.getLblSubtotal().setText("Subtotal: Rp " + transaksiAktif.getSubtotal());
    }

    public void prosesPembayaran(int idUserAktif) {
        if (transaksiAktif.getDaftarBarang().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Keranjang kosong!");
            return;
        }

        // Tentukan Payment Strategy dari ComboBox
        String metodeDipilih = form.getCmbMetode().getSelectedItem().toString();
        PaymentStrategy strategy;
        if (metodeDipilih.equals("QRIS")) {
            strategy = new QrisPayment();
        } else {
            strategy = new CashPayment();
        }

        transaksiAktif.setIdUser(idUserAktif); // Dari data login

        try {
            transaksiService.prosesTransaksi(transaksiAktif, strategy);
            JOptionPane.showMessageDialog(form, "Transaksi Berhasil! No Faktur: " + transaksiAktif.getNoFaktur());
            form.dispose(); // Tutup form transaksi setelah selesai
        } catch (Exception e) {
            JOptionPane.showMessageDialog(form, "Gagal memproses transaksi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
