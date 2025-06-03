package com.vishnu.techm.attendancetracker.repository;

import com.vishnu.techm.attendancetracker.model.Attendance;
import com.vishnu.techm.attendancetracker.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployeeOrderByDateDesc(Employee employee);

    List<Attendance> findByEmployee_DepartmentOrderByDateDesc(String department);

    List<Attendance> findByDateBetweenOrderByDateAsc(LocalDate start, LocalDate end);

    boolean existsByEmployeeAndAttendanceDate(Employee employee, LocalDate today);
}
