/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemparkir;
import model.Mobil;
import model.Motor;
/**
 *
 * @author WINDOWS 11 PRO
 */
public class SistemParkir {

    public static void main(String[] args) {
        Mobil mobil1 = new Mobil(
        "0102 KT", "Toyota", 2020, 4
        );
        
        Motor motor1 = new Motor(
        "0102 KT", "Honda", 2020, 1000
        );
        
        mobil1.tampilkanInfo();
        motor1.tampilkanInfo();   
    }
}
