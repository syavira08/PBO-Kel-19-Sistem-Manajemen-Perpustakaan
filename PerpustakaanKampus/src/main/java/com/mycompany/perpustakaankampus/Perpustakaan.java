/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaankampus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 *
 * @author WINDOWS 11 PRO
 */
public class Perpustakaan {
    public static final int JUMLAH_LANTAI = 2;
    public static final int JUMLAH_RAK = 3;
    public static final int JUMLAH_POSISI = 4;

    // 1. ARRAY 1D: daftar kategori buku
    private String[] kategori = {"Pemrograman", "Matematika", "Jaringan Komputer", "Basis Data"};

    // Nama hari (juga Array 1D) untuk kolom pada Array 2D
    private String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};

    // 2. ARRAY 2D: jumlah peminjaman [kategori][hari], diisi data contoh
    private int[][] peminjaman = {
        {3, 2, 4, 1, 5, 2, 0},
        {1, 0, 2, 3, 1, 0, 0},
        {2, 4, 1, 2, 3, 1, 1},
        {4, 3, 2, 5, 2, 0, 1}
    };

    // 3. ARRAY 3D: lokasi buku [lantai][rak][posisi], berisi kode buku (null = kosong)
    private String[][][] lokasi = new String[JUMLAH_LANTAI][JUMLAH_RAK][JUMLAH_POSISI];

    // 4. ARRAYLIST: daftar buku yang bisa bertambah dan berkurang
    private ArrayList<Buku> daftarBuku = new ArrayList<>();

    // 5. STACK: riwayat transaksi (LIFO)
    private Stack<Transaksi> riwayat = new Stack<>();

    // 6. QUEUE: antrean mahasiswa (FIFO)
    private Queue<String> antrean = new LinkedList<>();

    private int nomorBuku = 1; // penghitung untuk membuat kode buku otomatis

    public Perpustakaan() {
        isiDataContoh();
    }

    // ---------------------------------------------------------------
    // Data awal
    // ---------------------------------------------------------------
    private void isiDataContoh() {
        tambahBukuAwal("Dasar Pemrograman Java", 0, 1, 1, 1);
        tambahBukuAwal("Struktur Data", 0, 1, 1, 2);
        tambahBukuAwal("Kalkulus Dasar", 1, 1, 2, 1);
        tambahBukuAwal("Aljabar Linear", 1, 1, 2, 2);
        tambahBukuAwal("Dasar Jaringan Komputer", 2, 2, 1, 1);
        tambahBukuAwal("Basis Data MySQL", 3, 2, 2, 1);
    }

    // Menambah buku awal TANPA mencatat transaksi
    private void tambahBukuAwal(String judul, int kat, int lantai, int rak, int posisi) {
        Buku b = new Buku(buatKode(), judul, kat, lantai, rak, posisi);
        daftarBuku.add(b);
        lokasi[lantai - 1][rak - 1][posisi - 1] = b.getKode();
    }

    private String buatKode() {
        String kode = String.format("B%03d", nomorBuku);
        nomorBuku++;
        return kode;
    }

    public int getJumlahKategori() {
        return kategori.length;
    }

    // ---------------------------------------------------------------
    // Array 1D: kategori
    // ---------------------------------------------------------------
    public void tampilkanKategori() {
        System.out.println("=== DAFTAR KATEGORI BUKU ===");
        for (int i = 0; i < kategori.length; i++) {
            System.out.println((i + 1) + ". " + kategori[i]);
        }
    }

    public void tampilkanHari() {
        for (int i = 0; i < hari.length; i++) {
            System.out.println((i + 1) + ". " + hari[i]);
        }
    }

    // ---------------------------------------------------------------
    // Array 2D: peminjaman per kategori dan hari
    // ---------------------------------------------------------------
    public void tampilkanPeminjaman() {
        System.out.println("=== JUMLAH PEMINJAMAN (KATEGORI x HARI) ===");
        System.out.printf("%-20s", "Kategori");
        for (int j = 0; j < hari.length; j++) {
            System.out.printf("%5s", hari[j].substring(0, 3));
        }
        System.out.printf("%7s%n", "Total");

        for (int i = 0; i < kategori.length; i++) {
            System.out.printf("%-20s", kategori[i]);
            int total = 0;
            for (int j = 0; j < hari.length; j++) {
                System.out.printf("%5d", peminjaman[i][j]);
                total = total + peminjaman[i][j];
            }
            System.out.printf("%7d%n", total);
        }
    }

    // ---------------------------------------------------------------
    // Array 3D: lokasi buku
    // ---------------------------------------------------------------
    public void tampilkanPetaLokasi() {
        System.out.println("=== PETA LOKASI BUKU ===");
        for (int l = 0; l < JUMLAH_LANTAI; l++) {
            System.out.println("Lantai " + (l + 1));
            for (int r = 0; r < JUMLAH_RAK; r++) {
                System.out.print("  Rak " + (r + 1) + ": ");
                for (int p = 0; p < JUMLAH_POSISI; p++) {
                    String isi = lokasi[l][r][p];
                    if (isi == null) {
                        isi = "---";
                    }
                    System.out.print("[" + (p + 1) + "] " + isi + "  ");
                }
                System.out.println();
            }
        }
    }

    public boolean lokasiKosong(int lantai, int rak, int posisi) {
        return lokasi[lantai - 1][rak - 1][posisi - 1] == null;
    }

    public void tampilkanLokasiBuku(String kode) {
        int i = cariBuku(kode);
        if (i == -1) {
            System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
            return;
        }
        Buku b = daftarBuku.get(i);
        System.out.println(b.getKode() + " - " + b.getJudul() + " berada di Lantai " + b.getLantai()
                + ", Rak " + b.getRak() + ", Posisi " + b.getPosisi());
    }

    public boolean pindahBuku(String kode, int lantai, int rak, int posisi) {
        int i = cariBuku(kode);
        if (i == -1) {
            System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
            return false;
        }
        if (!lokasiKosong(lantai, rak, posisi)) {
            System.out.println("Lokasi tujuan sudah terisi buku lain.");
            return false;
        }
        Buku b = daftarBuku.get(i);
        Transaksi t = new Transaksi("PINDAH", b);
        t.setLokasiLama(b.getLantai(), b.getRak(), b.getPosisi());
        t.setKeterangan("Dari L" + b.getLantai() + "-R" + b.getRak() + "-P" + b.getPosisi()
                + " ke L" + lantai + "-R" + rak + "-P" + posisi);

        lokasi[b.getLantai() - 1][b.getRak() - 1][b.getPosisi() - 1] = null;
        b.setLokasi(lantai, rak, posisi);
        lokasi[lantai - 1][rak - 1][posisi - 1] = b.getKode();

        riwayat.push(t);
        System.out.println("Buku berhasil dipindahkan.");
        return true;
    }

    // ---------------------------------------------------------------
    // ArrayList: kelola buku
    // ---------------------------------------------------------------
    // Mencari buku berdasarkan kode, mengembalikan indeks atau -1 jika tidak ada
    private int cariBuku(String kode) {
        for (int i = 0; i < daftarBuku.size(); i++) {
            if (daftarBuku.get(i).getKode().equalsIgnoreCase(kode)) {
                return i;
            }
        }
        return -1;
    }

    public Buku tambahBuku(String judul, int kat, int lantai, int rak, int posisi) {
        Buku b = new Buku(buatKode(), judul, kat, lantai, rak, posisi);
        daftarBuku.add(b);
        lokasi[lantai - 1][rak - 1][posisi - 1] = b.getKode();

        Transaksi t = new Transaksi("TAMBAH", b);
        t.setKeterangan("Lokasi L" + lantai + "-R" + rak + "-P" + posisi);
        riwayat.push(t);
        return b;
    }

    public void tampilkanBuku() {
        System.out.println("=== DAFTAR BUKU (" + daftarBuku.size() + " buku) ===");
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku.");
            return;
        }
        System.out.printf("%-6s %-26s %-18s %-9s %s%n", "Kode", "Judul", "Kategori", "Status", "Lokasi");
        for (Buku b : daftarBuku) {
            String status = b.isTersedia() ? "Tersedia" : "Dipinjam";
            System.out.printf("%-6s %-26s %-18s %-9s L%d-R%d-P%d%n", b.getKode(), b.getJudul(),
                    kategori[b.getKategori()], status, b.getLantai(), b.getRak(), b.getPosisi());
        }
    }

    public boolean hapusBuku(String kode) {
        int i = cariBuku(kode);
        if (i == -1) {
            System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
            return false;
        }
        Buku b = daftarBuku.get(i);
        if (!b.isTersedia()) {
            System.out.println("Buku sedang dipinjam, tidak bisa dihapus.");
            return false;
        }
        daftarBuku.remove(i);
        lokasi[b.getLantai() - 1][b.getRak() - 1][b.getPosisi() - 1] = null;

        Transaksi t = new Transaksi("HAPUS", b);
        t.setIndeks(i); // disimpan agar saat dibatalkan buku kembali ke urutan semula
        riwayat.push(t);
        System.out.println("Buku " + b.getKode() + " berhasil dihapus.");
        return true;
    }

    // ---------------------------------------------------------------
    // Peminjaman dan pengembalian
    // ---------------------------------------------------------------
    public boolean pinjamBuku(String kode, String mahasiswa, int hariIdx) {
        int i = cariBuku(kode);
        if (i == -1) {
            System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
            return false;
        }
        Buku b = daftarBuku.get(i);
        if (!b.isTersedia()) {
            System.out.println("Buku sedang dipinjam.");
            return false;
        }
        b.setTersedia(false);
        peminjaman[b.getKategori()][hariIdx]++; // Array 2D bertambah

        Transaksi t = new Transaksi("PINJAM", b);
        t.setMahasiswa(mahasiswa);
        t.setHari(hariIdx);
        t.setKeterangan("Peminjam: " + mahasiswa + ", Hari: " + hari[hariIdx]);
        riwayat.push(t);
        System.out.println(mahasiswa + " berhasil meminjam buku " + b.getJudul() + ".");
        return true;
    }

    public boolean kembalikanBuku(String kode) {
        int i = cariBuku(kode);
        if (i == -1) {
            System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
            return false;
        }
        Buku b = daftarBuku.get(i);
        if (b.isTersedia()) {
            System.out.println("Buku ini tidak sedang dipinjam.");
            return false;
        }
        b.setTersedia(true);
        riwayat.push(new Transaksi("KEMBALI", b));
        System.out.println("Buku " + b.getJudul() + " berhasil dikembalikan.");
        return true;
    }

    // ---------------------------------------------------------------
    // Stack: riwayat transaksi
    // ---------------------------------------------------------------
    public void lihatTransaksiTerakhir() {
        if (riwayat.isEmpty()) {
            System.out.println("Riwayat transaksi masih kosong.");
            return;
        }
        System.out.println("Transaksi terakhir: " + riwayat.peek());
    }

    public void tampilkanRiwayat() {
        System.out.println("=== RIWAYAT TRANSAKSI (terbaru di atas) ===");
        if (riwayat.isEmpty()) {
            System.out.println("Riwayat transaksi masih kosong.");
            return;
        }
        int nomor = 1;
        for (int i = riwayat.size() - 1; i >= 0; i--) {
            System.out.println(nomor + ". " + riwayat.get(i));
            nomor++;
        }
    }

    // Membatalkan transaksi terakhir dan MENGEMBALIKAN perubahan datanya
    public void batalkanTransaksi() {
        if (riwayat.isEmpty()) {
            System.out.println("Tidak ada transaksi yang bisa dibatalkan.");
            return;
        }
        Transaksi t = riwayat.pop();
        Buku b = t.getBuku();
        String jenis = t.getJenis();

        switch (jenis) {
            case "TAMBAH":
                // kebalikan dari tambah: hapus buku dan kosongkan lokasinya
                daftarBuku.remove(cariBuku(b.getKode()));
                lokasi[b.getLantai() - 1][b.getRak() - 1][b.getPosisi() - 1] = null;
                break;
            case "HAPUS":
                // kebalikan dari hapus: masukkan lagi ke urutan semula dan isi lokasinya
                daftarBuku.add(t.getIndeks(), b);
                lokasi[b.getLantai() - 1][b.getRak() - 1][b.getPosisi() - 1] = b.getKode();
                break;
            case "PINJAM":
                // kebalikan dari pinjam: buku tersedia lagi dan hitungan peminjaman dikurangi
                b.setTersedia(true);
                peminjaman[b.getKategori()][t.getHari()]--;
                break;
            case "KEMBALI":
                // kebalikan dari kembali: buku kembali berstatus dipinjam
                b.setTersedia(false);
                break;
            case "PINDAH":
                // kebalikan dari pindah: kembalikan buku ke lokasi lama
                lokasi[b.getLantai() - 1][b.getRak() - 1][b.getPosisi() - 1] = null;
                b.setLokasi(t.getLantaiLama(), t.getRakLama(), t.getPosisiLama());
                lokasi[b.getLantai() - 1][b.getRak() - 1][b.getPosisi() - 1] = b.getKode();
                break;
            default:
                break;
        }
        System.out.println("Transaksi dibatalkan: " + t);
    }

    // ---------------------------------------------------------------
    // Queue: antrean mahasiswa
    // ---------------------------------------------------------------
    public void tambahAntrean(String nama) {
        antrean.add(nama);
        System.out.println(nama + " masuk antrean nomor " + antrean.size() + ".");
    }

    public void tampilkanAntrean() {
        System.out.println("=== ANTREAN PEMINJAMAN ===");
        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }
        int nomor = 1;
        for (String nama : antrean) {
            System.out.println(nomor + ". " + nama);
            nomor++;
        }
    }

    // Melihat mahasiswa paling depan tanpa mengeluarkannya (null jika kosong)
    public String lihatDepanAntrean() {
        return antrean.peek();
    }

    // Memproses mahasiswa paling depan: dia meminjam buku, lalu keluar dari antrean.
    // Jika peminjaman gagal, mahasiswa tetap di antrean.
    public boolean prosesAntrean(String kode, int hariIdx) {
        String nama = antrean.peek();
        if (nama == null) {
            System.out.println("Antrean kosong.");
            return false;
        }
        boolean berhasil = pinjamBuku(kode, nama, hariIdx);
        if (berhasil) {
            antrean.poll();
        }
        return berhasil;
    }
}
