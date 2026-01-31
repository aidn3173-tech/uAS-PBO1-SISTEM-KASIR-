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
 * ABSTRACT CLASS Produk
 * Merupakan superclass untuk semua jenis produk
 * Menerapkan konsep Abstract Class dan Encapsulation
 */
public abstract class Produk {

    // Atribut dibuat protected agar dapat diakses subclass
    protected String kode;
    protected String nama;
    protected Double harga;   // Wrapper Class
    protected Integer stok;   // Wrapper Class
    protected StatusProduk status;

    // Constructor
    public Produk(String kode, String nama, Double harga,
                  Integer stok, StatusProduk status) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.status = status;
    }

    // Abstract method (wajib dioverride subclass)
    public abstract String getJenis();

    // Getter (Encapsulation)
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public Double getHarga() { return harga; }
    public Integer getStok() { return stok; }
    public StatusProduk getStatus() { return status; }

    // Setter (Encapsulation)
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    /*
     * Setter stok
     * Jika stok 0, status otomatis HABIS
     */
    public void setStok(Integer stok) {
        this.stok = stok;
        if (stok <= 0) {
            this.stok = 0;
            this.status = StatusProduk.HABIS;
        } else {
            this.status = StatusProduk.TERSEDIA;
        }
    }

    /*
     * Mengurangi stok saat transaksi
     */
    public void kurangiStok(Integer jumlah) {
        stok -= jumlah;
        if (stok <= 0) {
            stok = 0;
            status = StatusProduk.HABIS;
        }
    }
}
