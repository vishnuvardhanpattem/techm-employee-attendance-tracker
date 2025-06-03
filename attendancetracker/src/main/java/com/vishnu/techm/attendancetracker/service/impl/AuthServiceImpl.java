package com.vishnu.techm.attendancetracker.service.impl;


import com.vishnu.techm.attendancetracker.config.JwtProvider;
import com.vishnu.techm.attendancetracker.domain.USER_ROLE;
import com.vishnu.techm.attendancetracker.model.Attendance;
import com.vishnu.techm.attendancetracker.model.Employee;
import com.vishnu.techm.attendancetracker.repository.EmployeeRepository;
import com.vishnu.techm.attendancetracker.request.LoginRequest;
import com.vishnu.techm.attendancetracker.response.AuthResponse;
import com.vishnu.techm.attendancetracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    public String signUp(Employee employeeDetails) throws Exception {
        Employee employee = employeeRepository.findByEmail(employeeDetails.getEmail());
        if (employee != null) {
            throw new Exception("User already existed with this email");
        }
        Employee createdUser = new Employee();
        createdUser.setDesignation(employeeDetails.getDesignation());
        createdUser.setDepartment(employeeDetails.getDepartment());
        createdUser.setEmail(employeeDetails.getEmail());
        createdUser.setPassword(passwordEncoder.encode(employeeDetails.getPassword()));
        createdUser.setFullName(employeeDetails.getFullName());
        createdUser.setRole(USER_ROLE.ROLE_EMPLOYEE);
        createdUser.setMobileNumber(employeeDetails.getMobileNumber());
        employeeRepository.save(createdUser);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        employeeDetails.getEmail(),
                        employeeDetails.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtProvider.generateToken(auth);

    }

    public AuthResponse signin(LoginRequest request) {
        Employee employee = employeeRepository.findByEmail(request.getEmail());
        if (employee == null) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Success");
        authResponse.setRole(employee.getRole());

        return authResponse;
    }
}
