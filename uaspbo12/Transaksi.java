/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uaspbo12;

/**
 *
 * @author User
 */
import java.io.*;

/*
 * CLASS Transaksi
 * Mengimplementasikan interface OperasiData
 * Bertanggung jawab terhadap proses transaksi dan file handling
 */
public class Transaksi implements OperasiData {

    // Association dengan Produk
    private Produk produk;
    private Integer jumlah;

    public Transaksi(Produk produk, Integer jumlah) {
        this.produk = produk;
        this.jumlah = jumlah;
    }

    // Menghitung total harga
    public Double getTotal() {
        return produk.getHarga() * jumlah;
    }

    /*
     * Proses transaksi
     * Mengecek stok dan status produk
     */
    public boolean proses() {
        if (produk.getStatus() == StatusProduk.HABIS) {
            return false;
        }
        if (jumlah > produk.getStok()) {
            return false;
        }
        produk.kurangiStok(jumlah);
        return true;
    }

    // Cetak struk dalam bentuk String
    public String cetakStruk() {
        return """
        ===== STRUK BELANJA =====
        Produk : %s
        Jenis  : %s
        Harga  : %.0f
        Jumlah : %d
        Total  : %.0f
        ========================
        """.formatted(
                produk.getNama(),
                produk.getJenis(),
                produk.getHarga(),
                jumlah,
                getTotal()
        );
    }

    // Menyimpan struk ke file txt
    @Override
    public void simpanStruk() {
        try (FileWriter fw = new FileWriter("struk.txt", true)) {
            fw.write(cetakStruk());
            fw.write("\n");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk");
        }
    }

    // Membaca isi file struk
    @Override
    public void bacaStruk() {
        try (BufferedReader br = new BufferedReader(new FileReader("struk.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Belum ada struk");
        }
    }
}
