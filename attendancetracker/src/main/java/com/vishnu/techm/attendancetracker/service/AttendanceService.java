package com.vishnu.techm.attendancetracker.service;

import com.vishnu.techm.attendancetracker.domain.ATTENDANCE;
import com.vishnu.techm.attendancetracker.model.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    Attendance markAttendance(Long employeeId, ATTENDANCE status);
    List<Attendance> getMyAttendance(Long employeeId);
    List<Attendance> getAttendanceByEmployee(Long employeeId);
    List<Attendance> getAttendanceByDepartment(String department);
    List<Attendance> getAttendanceBetweenDates(LocalDate start, LocalDate end);
}

