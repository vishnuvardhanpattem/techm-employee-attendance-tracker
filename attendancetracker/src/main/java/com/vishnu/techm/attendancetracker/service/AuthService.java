package com.vishnu.techm.attendancetracker.service;


import com.vishnu.techm.attendancetracker.model.Employee;
import com.vishnu.techm.attendancetracker.request.LoginRequest;
import com.vishnu.techm.attendancetracker.response.AuthResponse;

public interface AuthService {
    public String signUp(Employee employeeDetails) throws Exception;
    public AuthResponse signin(LoginRequest request);
}
