package com.vishnu.techm.attendancetracker.controller;

import com.vishnu.techm.attendancetracker.model.Attendance;
import com.vishnu.techm.attendancetracker.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final AttendanceService attendanceService;


    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployee(@PathVariable Long employeeId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByEmployee(employeeId);
        return ResponseEntity.ok(attendanceList);
    }


    @GetMapping("/department/{department}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Attendance>> getAttendanceByDepartment(@PathVariable String department) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByDepartment(department);
        return ResponseEntity.ok(attendanceList);
    }

    @GetMapping("/range")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Attendance>> getAttendanceBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<Attendance> attendanceList = attendanceService.getAttendanceBetweenDates(start, end);
        return ResponseEntity.ok(attendanceList);
    }
}
