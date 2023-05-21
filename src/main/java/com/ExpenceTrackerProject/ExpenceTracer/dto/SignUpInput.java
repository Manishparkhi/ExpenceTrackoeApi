package com.ExpenceTrackerProject.ExpenceTracer.dto;

import com.ExpenceTrackerProject.ExpenceTracer.model.Users;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
 @AllArgsConstructor
public class SignUpInput {



    @NotBlank
    private String username;

    @Email
    private String email;

    private String password;
}
