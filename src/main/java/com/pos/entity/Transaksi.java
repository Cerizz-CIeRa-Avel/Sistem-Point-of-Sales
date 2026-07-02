/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.entity;

import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private String noFaktur;
    private int idUser;
    private int idPelanggan; 
    private int idMetodePembayaran;
    private double subtotal;
    private double diskon;
    private double totalAkhir;
    private List<DetailTransaksi> daftarBarang = new ArrayList<>();

    // Getter dan Setter
    public String getNoFaktur() { return noFaktur; }
    public void setNoFaktur(String noFaktur) { this.noFaktur = noFaktur; }

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }

    public int getIdMetodePembayaran() { return idMetodePembayaran; }
    public void setIdMetodePembayaran(int idMetodePembayaran) { this.idMetodePembayaran = idMetodePembayaran; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getDiskon() { return diskon; }
    public void setDiskon(double diskon) { this.diskon = diskon; }

    public double getTotalAkhir() { return totalAkhir; }
    public void setTotalAkhir(double totalAkhir) { this.totalAkhir = totalAkhir; }

    public List<DetailTransaksi> getDaftarBarang() { return daftarBarang; }
    public void tambahBarang(DetailTransaksi detail) {
        this.daftarBarang.add(detail);
        hitungUlang();
    }

    private void hitungUlang() {
        this.subtotal = 0;
        for (DetailTransaksi dt : daftarBarang) {
            this.subtotal += dt.getSubtotalItem();
        }
        // Total akhir sementara sebelum dikenakan admin fee dari Strategy Pattern
        this.totalAkhir = this.subtotal - this.diskon; 
    }
}
