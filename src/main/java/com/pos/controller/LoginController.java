/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.controller;

import com.pos.entity.User;
import com.pos.service.UserService;
import com.pos.view.LoginForm;
import javax.swing.JOptionPane;
import com.pos.view.DashboardForm;

public class LoginController {
    private final UserService userService;
    private final LoginForm loginForm;

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
        this.userService = new UserService();
    }

    public void prosesLogin() {
        String username = loginForm.getUsernameInput();
        String password = loginForm.getPasswordInput();

        try {
            User userTerautentikasi = userService.authenticate(username, password);

            if (userTerautentikasi != null) {
                JOptionPane.showMessageDialog(loginForm, 
                        "Selamat Datang, " + userTerautentikasi.getNamaLengkap() + " (" + userTerautentikasi.getRole() + ")!", 
                        "Login Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                DashboardForm dashboard = new DashboardForm(userTerautentikasi.getRole(), userTerautentikasi.getIdUser());
                dashboard.setVisible(true);
                loginForm.dispose();
                
            } else {
                JOptionPane.showMessageDialog(loginForm, 
                        "Username atau Password salah!", 
                        "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(loginForm, 
                    e.getMessage(), 
                    "Peringatan Validasi", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(loginForm, 
                    "Terjadi kesalahan sistem: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
