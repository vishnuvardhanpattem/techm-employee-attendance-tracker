package com.vishnu.techm.attendancetracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vishnu.techm.attendancetracker.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private String mobileNumber;

    private String department;

    private String designation;

    private USER_ROLE role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Attendance> attendances = new ArrayList<>();
}


//Employee entity with fields: employeeId, name, department, designation.
//
//Attendance entity with fields: attendanceId, employeeId, date, status (Present/Absent).