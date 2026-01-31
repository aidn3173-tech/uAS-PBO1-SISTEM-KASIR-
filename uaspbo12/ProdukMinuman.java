/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uaspbo12;

/**
 *
 * @author User
 */
/*
 * CLASS ProdukMinuman
 * Turunan dari Produk
 * Memiliki atribut khusus volume
 */
public class ProdukMinuman extends Produk {

    private Integer volume;

    public ProdukMinuman(String kode, String nama, Double harga,
                          Integer stok, StatusProduk status,
                          Integer volume) {
        super(kode, nama, harga, stok, status);
        this.volume = volume;
    }

    // Polymorphism
    @Override
    public String getJenis() {
        return "Minuman";
    }

    public Integer getVolume() {
        return volume;
    }
}


