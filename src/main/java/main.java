/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import com.pos.database.DatabaseConnection;
import java.sql.Connection;

public class main {
    public static void main(String[] args) {
        System.out.println("Memulai aplikasi POS...");

        Connection conn = DatabaseConnection.getInstance().getConnection();

        if (conn != null) {
            System.out.println("Aplikasi siap digunakan dan terhubung ke database!");
        } else {
            System.out.println("Aplikasi gagal terhubung ke database. Periksa konfigurasi XAMPP/MySQL Anda.");
        }
    }
}
