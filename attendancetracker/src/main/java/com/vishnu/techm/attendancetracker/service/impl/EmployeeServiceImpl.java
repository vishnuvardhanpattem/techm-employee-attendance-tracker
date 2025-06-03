package com.vishnu.techm.attendancetracker.service.impl;


import com.vishnu.techm.attendancetracker.model.Employee;
import com.vishnu.techm.attendancetracker.repository.EmployeeRepository;
import com.vishnu.techm.attendancetracker.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
