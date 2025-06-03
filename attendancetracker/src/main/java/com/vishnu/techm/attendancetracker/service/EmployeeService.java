package com.vishnu.techm.attendancetracker.service;

import com.vishnu.techm.attendancetracker.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
}
