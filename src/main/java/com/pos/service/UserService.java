/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.service;

import com.pos.entity.User;
import com.pos.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User authenticate(String username, String password) {
        // Validasi input dasar: memastikan input tidak kosong sebelum dikirim ke database
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username dan Password tidak boleh kosong!");
        }
   
        return userRepository.login(username, password);
    }
}
