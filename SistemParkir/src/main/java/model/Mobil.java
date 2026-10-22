/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author WINDOWS 11 PRO
 */
public class Mobil extends Kendaraan {
    protected int jumlahPintu;
    
    public Mobil(String nomorPlat, String merk, int tahun, int jumlahPintu) {
        super(nomorPlat, merk, tahun);
        this.jumlahPintu = jumlahPintu;
    }
    
    public int getjumlahPintu(){
        return jumlahPintu;
    }
    
    public void setjumlahPintu(int jumlahPintu){
        this.jumlahPintu = jumlahPintu;
    }
    
    @Override
    public void tampilkanInfo(){
        System.out.println("===============");
        System.out.println("Informasi Kendaraan");
        super.tampilkanInfo();
        System.out.println("Jumlah Pintu : "+ jumlahPintu);
        System.out.println("===============");
    }
    
}
