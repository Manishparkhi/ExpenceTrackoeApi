package com.ExpenceTrackerProject.ExpenceTracer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;
    private String token;
    private LocalDateTime localDateTime;

    @OneToOne(optional = false)
    private Users users;

    public Authentication(Users users) {

        this.localDateTime = LocalDateTime.now();
        this.users = users;
        this.token = UUID.randomUUID().toString();
    }


}
