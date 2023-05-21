package com.ExpenceTrackerProject.ExpenceTracer.repositories;

import com.ExpenceTrackerProject.ExpenceTracer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IProductRepositories extends JpaRepository<Product,Long> {

    @Query(value = "select * from Product  where user_id = :userId", nativeQuery = true)
    List<Product> getAllProduct(Long userId);

    @Query(value = "select * from Product where user_id = :userId" , nativeQuery = true)
    public List<Product> getExpenseByUserId(Long userId);
    @Query(value = "select * from Product where user_id = :userId and date between :startDate and :endDate" , nativeQuery = true)
    public List<Product> findByDateBetween(Long userId , Date startDate, Date endDate);
    @Query(value = "select * from Product where user_id = :userId and date = :date"  , nativeQuery = true)
    public List<Product> findByDate(Long userId, Date date);

    @Query(value = "select * from Product where user_id = :userId and date = :date and time = :time"  , nativeQuery = true)
    public List<Product> findByDateAndTime(Long userId, Date date, Time time);
    @Query(value = "select * from Product where user_id = :userId and MONTH(date) = :month" , nativeQuery = true)
    public List<Product> findByMonth(Long userId, Integer month);
}
