/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.view;

import com.pos.controller.PelangganController;
import com.pos.entity.Pelanggan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PelangganForm extends JDialog {
    private JTextField txtNama, txtTelepon;
    private JTextArea txtAlamat;
    private JButton btnTambah, btnUbah, btnHapus, btnBersih;
    private JTable tabelPelanggan;
    private PelangganController controller;
    private int idPelangganTerpilih = 0; // Menyimpan ID saat baris tabel diklik

    public PelangganForm(Frame parent) {
        super(parent, "Master Pelanggan - POS", true);
        setSize(600, 450);
        setLocationRelativeTo(parent);
        
        txtNama = new JTextField(20);
        txtTelepon = new JTextField(20);
        txtAlamat = new JTextArea(3, 20);
        txtAlamat.setLineWrap(true);

        btnTambah = new JButton("Tambah");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnBersih = new JButton("Bersihkan");

        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data Pelanggan"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; panelInput.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panelInput.add(txtNama, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; panelInput.add(new JLabel("No Telepon:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panelInput.add(txtTelepon, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; panelInput.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panelInput.add(new JScrollPane(txtAlamat), gbc);

        JPanel panelTombol = new JPanel(new FlowLayout());
        panelTombol.add(btnTambah); panelTombol.add(btnUbah); panelTombol.add(btnHapus); panelTombol.add(btnBersih);

        JPanel panelUtara = new JPanel(new BorderLayout());
        panelUtara.add(panelInput, BorderLayout.CENTER);
        panelUtara.add(panelTombol, BorderLayout.SOUTH);

        String[] kolom = {"ID", "Nama Pelanggan", "No Telepon", "Alamat"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelPelanggan = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelPelanggan);

        setLayout(new BorderLayout());
        add(panelUtara, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        controller = new PelangganController(this);
        controller.muatDataTabel();

        btnTambah.addActionListener(e -> controller.tambahPelanggan());
        btnUbah.addActionListener(e -> controller.ubahPelanggan());
        btnHapus.addActionListener(e -> controller.hapusPelanggan());
        btnBersih.addActionListener(e -> bersihkanForm());

        tabelPelanggan.getSelectionModel().addListSelectionListener(e -> {
            int row = tabelPelanggan.getSelectedRow();
            if (row != -1) {
                idPelangganTerpilih = Integer.parseInt(tabelPelanggan.getValueAt(row, 0).toString());
                txtNama.setText(tabelPelanggan.getValueAt(row, 1).toString());
                txtTelepon.setText(tabelPelanggan.getValueAt(row, 2) != null ? tabelPelanggan.getValueAt(row, 2).toString() : "");
                txtAlamat.setText(tabelPelanggan.getValueAt(row, 3) != null ? tabelPelanggan.getValueAt(row, 3).toString() : "");
            }
        });
    }

    public JTable getTabelPelanggan() { return tabelPelanggan; }
    public int getIdPelangganTerpilih() { return idPelangganTerpilih; }
    
    public Pelanggan getPelangganInput() {
        return new Pelanggan(
            idPelangganTerpilih,
            txtNama.getText().trim(),
            txtTelepon.getText().trim(),
            txtAlamat.getText().trim()
        );
    }

    public void bersihkanForm() {
        idPelangganTerpilih = 0;
        txtNama.setText("");
        txtTelepon.setText("");
        txtAlamat.setText("");
        tabelPelanggan.clearSelection();
    }
}
