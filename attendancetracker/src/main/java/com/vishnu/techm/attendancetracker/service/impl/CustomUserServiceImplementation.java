package com.vishnu.techm.attendancetracker.service.impl;

import com.vishnu.techm.attendancetracker.domain.USER_ROLE;
import com.vishnu.techm.attendancetracker.model.Employee;
import com.vishnu.techm.attendancetracker.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImplementation implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByEmail(username);
        if (user != null) {
            return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
        }
        throw new UsernameNotFoundException("User not found with this email : " + username);
    }

    private UserDetails buildUserDetails(String email, String password, com.vishnu.techm.attendancetracker.domain.USER_ROLE role) {
        if (role == null) role = USER_ROLE.ROLE_EMPLOYEE;

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }
}

