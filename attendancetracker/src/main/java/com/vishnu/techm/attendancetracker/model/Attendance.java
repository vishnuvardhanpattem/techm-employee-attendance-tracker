package com.vishnu.techm.attendancetracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vishnu.techm.attendancetracker.domain.ATTENDANCE;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendanceId;

    private LocalDate attendanceDate;

    private ATTENDANCE status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;



}


//Attendance entity with fields: attendanceId, employeeId, date, status (Present/Absent).