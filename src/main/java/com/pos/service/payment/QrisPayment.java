/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.service.payment;

public class QrisPayment implements PaymentStrategy {
    @Override
    public double hitungTotalAkhir(double totalBelanja) {
        // Contoh: ada admin fee QRIS 0.7% yang dibebankan ke pelanggan
        return totalBelanja + (totalBelanja * 0.007);
    }
    @Override public int getIdMetode() { return 2; }
    @Override public String getNamaMetode() { return "QRIS"; }
}
