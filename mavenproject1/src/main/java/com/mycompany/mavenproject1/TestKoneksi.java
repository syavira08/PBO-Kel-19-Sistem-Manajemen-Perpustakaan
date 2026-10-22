/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.sql.Connection;
/**
 *
 * @author WINDOWS 11 PRO
 */
public class TestKoneksi {

    public static void main(String[] args) {
        System.out.println("Test Koneksi");
        Connection conn = Koneksi.getConnection();
        
        if(conn != null ){
            System.out.println("Koneksi Berhasil");
        }else{
            System.out.println("Koneksi Gagal");
        }
    }
}

