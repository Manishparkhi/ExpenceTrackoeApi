package com.ExpenceTrackerProject.ExpenceTracer.repositories;

import com.ExpenceTrackerProject.ExpenceTracer.model.Authentication;
import com.ExpenceTrackerProject.ExpenceTracer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthentication extends JpaRepository<Authentication,Long> {
    Authentication findByUsers(Users users);

    Authentication findFirstByToken(String token);
}
