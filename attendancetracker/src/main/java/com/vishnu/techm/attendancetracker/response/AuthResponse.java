package com.vishnu.techm.attendancetracker.response;


import com.vishnu.techm.attendancetracker.domain.USER_ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String jwt;

    private String message;

    private USER_ROLE role;
}
