/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.repository;

import com.pos.database.DatabaseConnection;
import com.pos.entity.Pelanggan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganRepository {
    private final Connection conn;

    public PelangganRepository() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public List<Pelanggan> getAll() {
        List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan ORDER BY id_pelanggan DESC";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pelanggan p = new Pelanggan(
                    rs.getInt("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("no_telepon"),
                    rs.getString("alamat")
                );
                listPelanggan.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error getAll Pelanggan: " + e.getMessage());
        }
        return listPelanggan;
    }

    public boolean insert(Pelanggan pelanggan) {
        String sql = "INSERT INTO pelanggan (nama_pelanggan, no_telepon, alamat) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pelanggan.getNamaPelanggan());
            ps.setString(2, pelanggan.getNoTelepon());
            ps.setString(3, pelanggan.getAlamat());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insert Pelanggan: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Pelanggan pelanggan) {
        String sql = "UPDATE pelanggan SET nama_pelanggan = ?, no_telepon = ?, alamat = ? WHERE id_pelanggan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pelanggan.getNamaPelanggan());
            ps.setString(2, pelanggan.getNoTelepon());
            ps.setString(3, pelanggan.getAlamat());
            ps.setInt(4, pelanggan.getIdPelanggan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update Pelanggan: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int idPelanggan) {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPelanggan);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error delete Pelanggan: " + e.getMessage());
            return false;
        }
    }
}