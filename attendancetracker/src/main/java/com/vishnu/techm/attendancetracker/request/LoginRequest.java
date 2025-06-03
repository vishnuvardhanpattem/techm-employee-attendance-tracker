package com.vishnu.techm.attendancetracker.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private String email;
    private String password;

}

