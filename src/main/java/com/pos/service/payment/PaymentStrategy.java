/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pos.service.payment;

public interface PaymentStrategy {
    double hitungTotalAkhir(double totalBelanja);
    int getIdMetode();
    String getNamaMetode();
}