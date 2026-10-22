/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author WINDOWS 11 PRO
 */
public class Motor extends Kendaraan {
    protected int kapasitasCc;
    
    public Motor(String nomorPlat, String merk, int tahun, int kapasitasCc) {
        super(nomorPlat, merk, tahun);
        this.kapasitasCc = kapasitasCc;
    }
    
    public int getkapasitasCc(){
        return kapasitasCc;
    }
    
    public void setjkapasitasCc(int kapasitasCc){
        this.kapasitasCc = kapasitasCc;
    }
    
    @Override
    public void tampilkanInfo(){
        System.out.println("===============");
        System.out.println("Informasi Kendaraan");
        super.tampilkanInfo();
        System.out.println("Kapasitas CC : "+ kapasitasCc);
        System.out.println("===============");
    }
    
}
