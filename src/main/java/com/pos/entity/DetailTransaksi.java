/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.entity;

public class DetailTransaksi {
    private int idBarang;
    private String namaBarang; 
    private double hargaSatuan;
    private int jumlah;
    private double subtotalItem;

    public DetailTransaksi(int idBarang, String namaBarang, double hargaSatuan, int jumlah) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.hargaSatuan = hargaSatuan;
        this.jumlah = jumlah;
        this.subtotalItem = hargaSatuan * jumlah;
    }

    // Buat Getter untuk semua properti di atas
    public int getIdBarang() { return idBarang; }
    public String getNamaBarang() { return namaBarang; }
    public double getHargaSatuan() { return hargaSatuan; }
    public int getJumlah() { return jumlah; }
    public double getSubtotalItem() { return subtotalItem; }
}
