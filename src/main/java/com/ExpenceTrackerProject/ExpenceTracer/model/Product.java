package com.ExpenceTrackerProject.ExpenceTracer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    @NotNull
    @Column(name = "product_title")
    private String productTitle;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_price")
    private Integer productPrice;
    @Column(name = "local_time")
    private LocalTime localTime = LocalTime.now();
    @NotNull
    @Column(name = "local_date")
    private LocalDate localDate ;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;


}
