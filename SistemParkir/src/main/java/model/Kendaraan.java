/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author WINDOWS 11 PRO
 */
public class Kendaraan {
    protected String nomorPlat;
    protected String merk;
    protected int tahun;
    
    public Kendaraan (String nomorPlat, String merk, int tahun){
        this.nomorPlat = nomorPlat;
        this.merk = merk;
        this.tahun = tahun;
    }
    
    public String getnomorPlat(){
        return nomorPlat;
    }
    
    public String getmerk(){
        return merk;
    }
    
    public int gettahun(){
        return tahun;
    }
    
    public void setmerk(String judul){
        this.merk = merk;
    }
    
    public void settahun(String penulis){
        this.tahun = tahun;
    }
    
    public void tampilkanInfo(){
        System.out.println("Nomor Plat : " + nomorPlat);
        System.out.println("Merk Kendaraan : " + merk);
        System.out.println("Tahun : " + tahun);
    }
    
}
