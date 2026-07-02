/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.view;

import com.pos.controller.TransaksiController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransaksiForm extends JDialog {
    private JTextField txtKodeBarang, txtJumlah;
    private JButton btnTambah, btnBayar;
    private JTable tabelKeranjang;
    private JLabel lblSubtotal;
    private JComboBox<String> cmbMetode;
    private TransaksiController controller;
    private int idUserAktif; 

    public TransaksiForm(Frame parent, int idUserAktif) {
        super(parent, "Mesin Kasir - Penjualan Baru", true);
        this.idUserAktif = idUserAktif;
        setSize(800, 500);
        setLocationRelativeTo(parent);
        
        // Komponen Input Kiri
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtKodeBarang = new JTextField(15);
        txtJumlah = new JTextField(5);
        txtJumlah.setText("1");
        btnTambah = new JButton("Tambah (Enter)");
        
        panelInput.add(new JLabel("Kode Barang/Barcode:"));
        panelInput.add(txtKodeBarang);
        panelInput.add(new JLabel("Qty:"));
        panelInput.add(txtJumlah);
        panelInput.add(btnTambah);

        // Tabel Keranjang Tengah
        String[] kolom = {"Nama Barang", "Harga Satuan", "Jumlah", "Subtotal"};
        tabelKeranjang = new JTable(new DefaultTableModel(kolom, 0));
        JScrollPane scrollPane = new JScrollPane(tabelKeranjang);

        // Komponen Bawah (Pembayaran)
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblSubtotal = new JLabel("Subtotal: Rp 0.0");
        lblSubtotal.setFont(new Font("Arial", Font.BOLD, 16));
        
        cmbMetode = new JComboBox<>(new String[]{"Cash", "QRIS"});
        btnBayar = new JButton("PROSES PEMBAYARAN");
        btnBayar.setBackground(Color.GREEN);

        panelBawah.add(lblSubtotal);
        panelBawah.add(new JLabel("  |  Metode:"));
        panelBawah.add(cmbMetode);
        panelBawah.add(btnBayar);

        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        // Inisialisasi Controller
        controller = new TransaksiController(this);

        // Event Listeners
        btnTambah.addActionListener(e -> controller.tambahBarangKeKeranjang());
        txtKodeBarang.addActionListener(e -> controller.tambahBarangKeKeranjang()); // Tekan Enter di txtKode langsung tambah
        btnBayar.addActionListener(e -> controller.prosesPembayaran(this.idUserAktif));
    }

    // Getter untuk diakses Controller
    public JTextField getTxtKodeBarang() { return txtKodeBarang; }
    public JTextField getTxtJumlah() { return txtJumlah; }
    public JTable getTabelKeranjang() { return tabelKeranjang; }
    public JLabel getLblSubtotal() { return lblSubtotal; }
    public JComboBox<String> getCmbMetode() { return cmbMetode; }
}
