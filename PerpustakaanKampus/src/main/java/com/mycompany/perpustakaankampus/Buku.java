/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaankampus;

/**
 *
 * @author WINDOWS 11 PRO
 */
// Class Buku: menyimpan data satu buku.
public class Buku {
    private String kode;
    private String judul;
    private int kategori;      // indeks pada array kategori (0 - 3)
    private boolean tersedia;  // true = ada di rak, false = sedang dipinjam
    private int lantai;        // nomor lantai (mulai dari 1)
    private int rak;           // nomor rak (mulai dari 1)
    private int posisi;        // nomor posisi (mulai dari 1)

    public Buku(String kode, String judul, int kategori, int lantai, int rak, int posisi) {
        this.kode = kode;
        this.judul = judul;
        this.kategori = kategori;
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
        this.tersedia = true; // buku baru otomatis tersedia
    }

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public int getKategori() {
        return kategori;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public int getLantai() {
        return lantai;
    }

    public int getRak() {
        return rak;
    }

    public int getPosisi() {
        return posisi;
    }

    public void setLokasi(int lantai, int rak, int posisi) {
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
    }
}
