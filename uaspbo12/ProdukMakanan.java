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
 * CLASS ProdukMakanan
 * Merupakan turunan (inheritance) dari Produk
 * Memiliki atribut khusus expiredDate
 */
public class ProdukMakanan extends Produk {

    private String expiredDate;

    public ProdukMakanan(String kode, String nama, Double harga,
                          Integer stok, StatusProduk status,
                          String expiredDate) {
        super(kode, nama, harga, stok, status);
        this.expiredDate = expiredDate;
    }

    // Method overriding
    @Override
    public String getJenis() {
        return "Makanan";
    }

    public String getExpiredDate() {
        return expiredDate;
    }
}



