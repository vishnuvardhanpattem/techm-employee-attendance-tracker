package com.vishnu.techm.attendancetracker.controller;

import com.vishnu.techm.attendancetracker.domain.USER_ROLE;
import com.vishnu.techm.attendancetracker.model.Employee;
import com.vishnu.techm.attendancetracker.request.LoginRequest;
import com.vishnu.techm.attendancetracker.response.AuthResponse;
import com.vishnu.techm.attendancetracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody Employee employee) throws Exception {
        String token = authService.signUp(employee);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setRole(USER_ROLE.ROLE_EMPLOYEE);
        authResponse.setMessage("Sign up successful");
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse res = authService.signin(req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
