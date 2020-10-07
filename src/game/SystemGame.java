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
public class SystemGame extends Person implements Abstract {

    public static boolean answer, check, bs = false;
    String quest;
    int score, nilai;

    public SystemGame(boolean answer, String nama) {
        super(nama);
        this.answer = answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }
    int no;

    public String getSoal() {
        try {
            no = (int) (Math.random() * ((111 - 1) + 1)) + 1;
            String sql = "select pernyataan from game where id_pernyataan='" + no + "'";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                quest = res.getString("pernyataan");
            }
        } catch (Exception e) {
        }
        try {
            String sql = "select jawaban from game where id_pernyataan='" + no + "'";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                check = res.getBoolean("jawaban");
            }
        } catch (Exception e) {
        }
        return quest;
    }
    int id;

    public void setScore() {
        boolean test = (check == answer);
        if (test == true) {
            score++;
            bs=true;
            try {
                String sql = "Select * from player ORDER BY id_player ASC";
                java.sql.Connection conn = (Connection) Config.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    id = Integer.parseInt(res.getString("id_player"));
                }
            } catch (Exception e) {
            }
            System.out.println(score);
            try {
                String sql = "Update player set score='" + score + "' where player.id_player='" + id + "'";
                java.sql.Connection conn = (Connection) Config.configDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
            } catch (Exception e) {
            }
        }
    }
    public int getScore() {
        try {
            String sql = "select score from player where id_player='" + id + "'";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                nilai = Integer.parseInt(res.getString("score"));
            }
        } catch (Exception e) {
        }
        return nilai;
    }

    public void info() {
        System.out.println(getNama() + " : " + getScore());
    }

    @Override
    public void jawabanBenar() {
        System.out.println("Jawaban Anda Benar");
    }

    @Override
    public void jawabanSalah() {
        System.out.println("Jawaban anda Salah");
    }

}
