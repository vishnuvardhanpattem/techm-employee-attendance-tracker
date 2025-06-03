package com.vishnu.techm.attendancetracker.controller;

import com.vishnu.techm.attendancetracker.domain.ATTENDANCE;
import com.vishnu.techm.attendancetracker.model.Attendance;
import com.vishnu.techm.attendancetracker.model.Employee;
import com.vishnu.techm.attendancetracker.repository.EmployeeRepository;
import com.vishnu.techm.attendancetracker.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/mark/{employeeId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Attendance> markAttendance(@PathVariable Long employeeId,
                                                     @RequestParam ATTENDANCE status) {
        Attendance attendance = attendanceService.markAttendance(employeeId, status);
        return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
    }


    @GetMapping("/my/{employeeId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<Attendance>> getMyAttendance(@PathVariable Long employeeId) {
        List<Attendance> attendanceList = attendanceService.getMyAttendance(employeeId);
        return ResponseEntity.ok(attendanceList);
    }


    private Employee getEmployeeFromUserDetails(UserDetails userDetails) {
        return employeeRepository.findByEmail(userDetails.getUsername());
    }
}