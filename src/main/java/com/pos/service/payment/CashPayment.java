/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.service.payment;

public class CashPayment implements PaymentStrategy {
    @Override
    public double hitungTotalAkhir(double totalBelanja) {
        return totalBelanja; // Cash tidak ada potongan atau biaya admin
    }
    @Override public int getIdMetode() { return 1; } // Sesuai ID di database.sql
    @Override public String getNamaMetode() { return "Cash"; }
}
