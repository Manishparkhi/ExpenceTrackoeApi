package com.ExpenceTrackerProject.ExpenceTracer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInOutput {

    private HttpStatus status;
    private String token;

}
