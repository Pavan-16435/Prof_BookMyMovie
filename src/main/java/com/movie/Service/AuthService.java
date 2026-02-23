package com.movie.Service;

import com.movie.Entity.User;

public interface AuthService {
    User register(String email, String password, String role);
    User login(String email, String password);
}