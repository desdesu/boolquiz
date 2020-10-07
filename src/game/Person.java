/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.sql.Connection;

/**
 *
 * @author BOOL&QUIZ
 */
public class Person {
    String nama;
    int karakter;
    public Person(String nama){
        this.nama=nama;
    }
    public Person(String nama, int karakter){
        this.nama=nama;
        this.karakter=karakter;
        try {
            String sql = "INSERT INTO player VALUES (NULL,'" + nama + "','" + karakter + "','0')";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
        }
        System.out.println("Username " +nama+ " Karakter " +karakter );
    }
    public String getNama(){
        return nama;
    }
    public int getKarakter(){
        return karakter;
    }
}
