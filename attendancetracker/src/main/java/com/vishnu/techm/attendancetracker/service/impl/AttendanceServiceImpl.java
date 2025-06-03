package com.vishnu.techm.attendancetracker.service.impl;

import com.vishnu.techm.attendancetracker.domain.ATTENDANCE;
import com.vishnu.techm.attendancetracker.model.Attendance;
import com.vishnu.techm.attendancetracker.model.Employee;
import com.vishnu.techm.attendancetracker.repository.AttendanceRepository;
import com.vishnu.techm.attendancetracker.repository.EmployeeRepository;
import com.vishnu.techm.attendancetracker.service.AttendanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance markAttendance(Long employeeId, ATTENDANCE status) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LocalDate today = LocalDate.now();

        boolean alreadyMarked = attendanceRepository.existsByEmployeeAndAttendanceDate(employee, today);
        if (alreadyMarked) {
            throw new IllegalStateException("Attendance already marked for today");
        }

        Attendance attendance = new Attendance();
        attendance.setAttendanceDate(today);
        attendance.setStatus(status);
        attendance.setEmployee(employee);

        employee.getAttendances().add(attendance);

        employeeRepository.save(employee);

        return attendance;
    }

    @Override
    public List<Attendance> getMyAttendance(Long employeeId) {
        return getAttendanceByEmployee(employeeId);
    }

    @Override
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return attendanceRepository.findByEmployeeOrderByDateDesc(employee);
    }

    @Override
    public List<Attendance> getAttendanceByDepartment(String department) {
        return attendanceRepository.findByEmployee_DepartmentOrderByDateDesc(department);
    }

    @Override
    public List<Attendance> getAttendanceBetweenDates(LocalDate start, LocalDate end) {
        return attendanceRepository.findByDateBetweenOrderByDateAsc(start, end);
    }
}
