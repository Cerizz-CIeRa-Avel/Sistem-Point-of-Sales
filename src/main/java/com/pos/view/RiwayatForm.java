/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.view;

import com.pos.controller.RiwayatController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RiwayatForm extends JDialog {
    private JTable tabelRiwayat;
    private RiwayatController controller;

    public RiwayatForm(Frame parent) {
        super(parent, "Riwayat Transaksi - POS", true);
        setSize(700, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Membuat Tabel Data
        String[] kolom = {"No Faktur", "Kasir yang Bertugas", "Pelanggan", "Total Belanja"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mencegah sel tabel diedit secara manual
            }
        };
        
        tabelRiwayat = new JTable(model);
        tabelRiwayat.setRowHeight(25);
        tabelRiwayat.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(tabelRiwayat);

        // Header Panel
        JPanel panelAtas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblJudul = new JLabel("Laporan Riwayat Penjualan");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 18));
        panelAtas.add(lblJudul);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Inisialisasi Controller dan muat data
        controller = new RiwayatController(this);
        controller.muatDataRiwayat();
    }

    public JTable getTabelRiwayat() {
        return tabelRiwayat;
    }
}
