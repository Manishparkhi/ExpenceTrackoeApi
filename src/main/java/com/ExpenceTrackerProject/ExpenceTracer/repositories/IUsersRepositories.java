package com.ExpenceTrackerProject.ExpenceTracer.repositories;

import com.ExpenceTrackerProject.ExpenceTracer.model.Product;
import com.ExpenceTrackerProject.ExpenceTracer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface IUsersRepositories extends JpaRepository<Users,Long> {
    Users findFirstByEmail(String email);


    void deleteByEmail(String email);


}
