/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.pos.repository;

import com.pos.database.DatabaseConnection;
import com.pos.entity.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangRepository {
    private final Connection conn;

    public BarangRepository() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    // 1. Ambil Semua Data Barang
    public List<Barang> getAll() {
        List<Barang> listBarang = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY id_barang DESC";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Barang b = new Barang(
                    rs.getInt("id_barang"),
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );
                listBarang.add(b);
            }
        } catch (SQLException e) {
            System.err.println("Error getAll Barang: " + e.getMessage());
        }
        return listBarang;
    }

    // 2. Tambah Barang Baru
    public boolean insert(Barang barang) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, barang.getKodeBarang());
            ps.setString(2, barang.getNamaBarang());
            ps.setDouble(3, barang.getHarga());
            ps.setInt(4, barang.getStok());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insert Barang: " + e.getMessage());
            return false;
        }
    }

    // 3. Ubah Data Barang
    public boolean update(Barang barang) {
        String sql = "UPDATE barang SET nama_barang = ?, harga = ?, stok = ? WHERE kode_barang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, barang.getNamaBarang());
            ps.setDouble(2, barang.getHarga());
            ps.setInt(3, barang.getStok());
            ps.setString(4, barang.getKodeBarang());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update Barang: " + e.getMessage());
            return false;
        }
    }

    // 4. Hapus Barang
    public boolean delete(String kodeBarang) {
        String sql = "DELETE FROM barang WHERE kode_barang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeBarang);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error delete Barang: " + e.getMessage());
            return false;
        }
    }
}