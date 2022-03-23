package com.example.appbankomatsystem.controller;

import com.example.appbankomatsystem.models.LoginDto;
import com.example.appbankomatsystem.models.Result;
import com.example.appbankomatsystem.models.UserDto;
import com.example.appbankomatsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> registerToSystem(@RequestBody UserDto userDto) {
        Result result = authService.registerToSystem(userDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
        Result result = authService.loginToSystem(loginDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(result);
    }
}
