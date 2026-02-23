package com.movie.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movie.Entity.User;
import com.movie.Model.LoginDto;
import com.movie.Model.RegisterDto;
import com.movie.Model.ResponseMessage;
import com.movie.Service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody RegisterDto dto) {
        User user = authService.register(dto.email, dto.password, dto.role);
        return ResponseEntity.ok(new ResponseMessage(200, "Registration successful", user));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody LoginDto dto) {
        User user = authService.login(dto.email, dto.password);
        return ResponseEntity.ok(new ResponseMessage(200, "Login successful", user));
    }
}