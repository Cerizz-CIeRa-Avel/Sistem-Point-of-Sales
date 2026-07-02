/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.view;

import com.pos.controller.BarangController;
import com.pos.entity.Barang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BarangForm extends JDialog {
    private JTextField txtKode, txtNama, txtHarga, txtStok;
    private JButton btnTambah, btnUbah, btnHapus, btnBersih;
    private JTable tabelBarang;
    private BarangController controller;

    public BarangForm(Frame parent) {
        super(parent, "Master Barang - POS", true);
        setSize(700, 500);
        setLocationRelativeTo(parent);
        
        // Komponen Input
        txtKode = new JTextField(15);
        txtNama = new JTextField(15);
        txtHarga = new JTextField(15);
        txtStok = new JTextField(15);

        btnTambah = new JButton("Tambah");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnBersih = new JButton("Bersihkan");

        // Form Input Panel Layout
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data Barang"));
        panelInput.add(new JLabel("Kode Barang:")); panelInput.add(txtKode);
        panelInput.add(new JLabel("Nama Barang:")); panelInput.add(txtNama);
        panelInput.add(new JLabel("Harga:")); panelInput.add(txtHarga);
        panelInput.add(new JLabel("Stok:")); panelInput.add(txtStok);

        JPanel panelTombol = new JPanel(new FlowLayout());
        panelTombol.add(btnTambah); panelTombol.add(btnUbah); panelTombol.add(btnHapus); panelTombol.add(btnBersih);

        JPanel panelUtara = new JPanel(new BorderLayout());
        panelUtara.add(panelInput, BorderLayout.CENTER);
        panelUtara.add(panelTombol, BorderLayout.SOUTH);

        // Tabel Data
        String[] kolom = {"Kode Barang", "Nama Barang", "Harga", "Stok"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelBarang = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelBarang);

        setLayout(new BorderLayout());
        add(panelUtara, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Inisialisasi Controller & Muat Data
        controller = new BarangController(this);
        controller.muatDataTabel();

        // Event Listeners
        btnTambah.addActionListener(e -> controller.tambahBarang());
        btnUbah.addActionListener(e -> controller.ubahBarang());
        btnHapus.addActionListener(e -> controller.hapusBarang());
        btnBersih.addActionListener(e -> bersihkanForm());

        // Klik Baris Tabel mengisi form otomatis untuk update/delete
        tabelBarang.getSelectionModel().addListSelectionListener(e -> {
            int row = tabelBarang.getSelectedRow();
            if (row != -1) {
                txtKode.setText(tabelBarang.getValueAt(row, 0).toString());
                txtKode.setEditable(false); // Kode utama tidak boleh diganti saat edit
                txtNama.setText(tabelBarang.getValueAt(row, 1).toString());
                txtHarga.setText(tabelBarang.getValueAt(row, 2).toString());
                txtStok.setText(tabelBarang.getValueAt(row, 3).toString());
            }
        });
    }

    public JTable getTabelBarang() { return tabelBarang; }
    public String getKodeInput() { return txtKode.getText().trim(); }
    
    public Barang getBarangInput() {
        return new Barang(
            0,
            txtKode.getText().trim(),
            txtNama.getText().trim(),
            Double.parseDouble(txtHarga.getText().trim()),
            Integer.parseInt(txtStok.getText().trim())
        );
    }

    public void bersihkanForm() {
        txtKode.setText("");
        txtKode.setEditable(true);
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        tabelBarang.clearSelection();
    }
}
