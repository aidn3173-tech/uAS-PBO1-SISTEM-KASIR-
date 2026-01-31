/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uaspbo12;

/**
 *
 * @author User
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/*
 * CLASS KasirGUI
 * Berfungsi sebagai antarmuka pengguna (GUI) aplikasi kasir
 * Menggunakan Java Swing
 * Class ini hanya menangani tampilan dan event,
 * sedangkan logika bisnis tetap berada di class OOP lainnya
 */
public class KasirGUI extends JFrame {

    // COLLECTION
    // Menyimpan daftar produk menggunakan konsep polymorphism
    private ArrayList<Produk> daftarProduk = new ArrayList<>();

    // KOMPONEN INPUT
    private JTextField txtKode, txtNama, txtHarga, txtStok, txtJumlah;
    private JComboBox<String> cmbJenis;

    // KOMPONEN OUTPUT
    private JTextArea area;

    /*
     * CONSTRUCTOR
     * Mengatur properti awal window GUI
     */
    public KasirGUI() {
        setTitle("Sistem Kasir UMKM");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // window ke tengah layar

        buatMenu();        // Membuat menu bar
        buatLayoutUtama(); // Membuat layout utama GUI
    }

    // ====================== MENU BAR ======================
    /*
     * Method untuk membuat menu bar aplikasi
     * Menu dipisahkan menjadi File, Produk, dan Transaksi
     */
    private void buatMenu() {

        JMenuBar menuBar = new JMenuBar();

        // ===== MENU FILE =====
        JMenu menuFile = new JMenu("File");
        JMenuItem miStruk = new JMenuItem("Lihat Struk");
        JMenuItem miKeluar = new JMenuItem("Keluar");

        // Event menu File
        miStruk.addActionListener(e -> bacaStruk());
        miKeluar.addActionListener(e -> System.exit(0));

        menuFile.add(miStruk);
        menuFile.add(miKeluar);

        // ===== MENU PRODUK =====
        JMenu menuProduk = new JMenu("Produk");
        JMenuItem miTambah = new JMenuItem("Tambah");
        JMenuItem miUbah = new JMenuItem("Ubah");
        JMenuItem miHapus = new JMenuItem("Hapus");
        JMenuItem miLihat = new JMenuItem("Lihat");

        // Event menu Produk
        miTambah.addActionListener(e -> tambahProduk());
        miUbah.addActionListener(e -> ubahProduk());
        miHapus.addActionListener(e -> hapusProduk());
        miLihat.addActionListener(e -> tampilProduk());

        menuProduk.add(miTambah);
        menuProduk.add(miUbah);
        menuProduk.add(miHapus);
        menuProduk.add(miLihat);

        // ===== MENU TRANSAKSI =====
        JMenu menuTransaksi = new JMenu("Transaksi");
        JMenuItem miTransaksi = new JMenuItem("Proses Transaksi");
        miTransaksi.addActionListener(e -> transaksi());

        menuTransaksi.add(miTransaksi);

        // Menambahkan semua menu ke menu bar
        menuBar.add(menuFile);
        menuBar.add(menuProduk);
        menuBar.add(menuTransaksi);

        setJMenuBar(menuBar);
    }

    // ====================== LAYOUT UTAMA ======================
    /*
     * Method untuk mengatur layout utama GUI
     * Menggunakan BorderLayout
     */
    private void buatLayoutUtama() {

        setLayout(new BorderLayout(10, 10));

        // ===== PANEL FORM INPUT (KIRI) =====
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelForm.setBackground(new Color(245, 245, 245));

        // Inisialisasi komponen input
        txtKode = new JTextField();
        txtNama = new JTextField();
        txtHarga = new JTextField();
        txtStok = new JTextField();
        txtJumlah = new JTextField();
        cmbJenis = new JComboBox<>(new String[]{"Makanan", "Minuman"});

        // Menambahkan komponen ke panel form
        panelForm.add(new JLabel("Kode Produk"));
        panelForm.add(txtKode);

        panelForm.add(new JLabel("Nama Produk"));
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Harga"));
        panelForm.add(txtHarga);

        panelForm.add(new JLabel("Stok"));
        panelForm.add(txtStok);

        panelForm.add(new JLabel("Jenis"));
        panelForm.add(cmbJenis);

        panelForm.add(new JLabel("Jumlah Beli"));
        panelForm.add(txtJumlah);

        JButton btnTransaksi = new JButton("Proses Transaksi");
        btnTransaksi.addActionListener(e -> transaksi());

        panelForm.add(new JLabel(""));
        panelForm.add(btnTransaksi);

        // ===== PANEL OUTPUT (KANAN) =====
        JPanel panelOutput = new JPanel(new BorderLayout());
        panelOutput.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblOutput = new JLabel("Output / Struk");
        lblOutput.setFont(new Font("Arial", Font.BOLD, 14));

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 13));

        panelOutput.add(lblOutput, BorderLayout.NORTH);
        panelOutput.add(new JScrollPane(area), BorderLayout.CENTER);

        // Menambahkan panel ke frame
        add(panelForm, BorderLayout.WEST);
        add(panelOutput, BorderLayout.CENTER);
    }

    // ====================== LOGIKA MENU ======================

    /*
     * Method untuk menambah produk baru
     * Menggunakan konsep polymorphism
     */
    private void tambahProduk() {

        Produk produk;
        String kode = txtKode.getText();
        String nama = txtNama.getText();
        Double harga = Double.valueOf(txtHarga.getText());
        Integer stok = Integer.valueOf(txtStok.getText());

        // Polymorphism berdasarkan jenis produk
        if (cmbJenis.getSelectedItem().equals("Makanan")) {
            produk = new ProdukMakanan(
                    kode, nama, harga, stok,
                    StatusProduk.TERSEDIA, "2026-01-01"
            );
        } else {
            produk = new ProdukMinuman(
                    kode, nama, harga, stok,
                    StatusProduk.TERSEDIA, 500
            );
        }

        daftarProduk.add(produk);
        area.append("Produk ditambahkan: " + nama + "\n");
    }

    /*
     * Menampilkan semua data produk
     */
    private void tampilProduk() {
        area.setText("");
        for (Produk p : daftarProduk) {
            area.append(
                p.getKode() + " | " +
                p.getNama() + " | " +
                p.getJenis() +
                " | Stok: " + p.getStok() +
                " | Status: " + p.getStatus() + "\n"
            );
        }
    }

    /*
     * Mengubah data produk berdasarkan kode
     */
    private void ubahProduk() {
        String kode = txtKode.getText();

        for (Produk p : daftarProduk) {
            if (p.getKode().equalsIgnoreCase(kode)) {
                p.setNama(txtNama.getText());
                p.setHarga(Double.valueOf(txtHarga.getText()));
                p.setStok(Integer.valueOf(txtStok.getText()));
                area.append("Data produk berhasil diubah\n");
                return;
            }
        }
        area.append("Produk tidak ditemukan\n");
    }

    /*
     * Menghapus produk dari list
     */
    private void hapusProduk() {
        String kode = txtKode.getText();
        daftarProduk.removeIf(p -> p.getKode().equalsIgnoreCase(kode));
        area.append("Produk berhasil dihapus\n");
    }

    /*
     * Proses transaksi dan cetak struk
     * Menggunakan class Transaksi
     */
    private void transaksi() {
        String kode = txtKode.getText();
        Integer jumlah = Integer.valueOf(txtJumlah.getText());

        for (Produk p : daftarProduk) {
            if (p.getKode().equalsIgnoreCase(kode)) {
                Transaksi t = new Transaksi(p, jumlah);

                if (t.proses()) {
                    area.append(t.cetakStruk());
                    t.simpanStruk();
                } else {
                    area.append("Transaksi gagal (stok habis)\n");
                }
                return;
            }
        }
        area.append("Produk tidak ditemukan\n");
    }

    /*
     * Membaca data struk dari file txt
     * Menggunakan file handling
     */
    private void bacaStruk() {
        area.setText("");
        new Transaksi(null, 0).bacaStruk();
    }
}
