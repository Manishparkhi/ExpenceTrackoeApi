package com.ExpenceTrackerProject.ExpenceTracer.controller;

import com.ExpenceTrackerProject.ExpenceTracer.model.Product;
import com.ExpenceTrackerProject.ExpenceTracer.repositories.IProductRepositories;
import com.ExpenceTrackerProject.ExpenceTracer.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product/")
public class ProductController {

    @Autowired
    ProductService service;
    @Autowired
    private IProductRepositories iProductRepositories;
    @PostMapping("createProduct")
    private ResponseEntity<String> createProduct(@Valid @RequestBody Product product){
          String respone = service.createProduct(product);

          return new ResponseEntity<String>(respone,HttpStatus.CREATED);
    }
    @GetMapping("getProducts/{userId}")
    private ResponseEntity<List<Product>> getProducts(@PathVariable Long userId){
        List<Product> response = service.getProducts(userId);
        if(response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("deleteProducts/{productid}")
    private ResponseEntity<String> deleteProducts(@PathVariable Long productid){

        return service.deleteProducts(productid);



}

    @PutMapping("updateProducts")
    private ResponseEntity<String> updateProducts(@Valid @RequestBody Product product,
                                                   @RequestParam Long productId , @PathVariable Long userId){
        return service.updateInfo(product,productId);
    }



    @GetMapping(value = "get-expenses-by-user-id")
    public List<Product> getExpensesByUser(@RequestParam Long userId){
        List<Product> expenses = iProductRepositories.getExpenseByUserId(userId);
        return expenses;
    }

    @GetMapping(value = "get-expenses-by-timespan")
    public List<Product> getExpensesByTimeSpan(@RequestParam Long userId , Date startDate , Date endDate){
        List<Product> expenses = iProductRepositories.findByDateBetween(userId,startDate,endDate);
        return expenses;
    }

    @GetMapping(value = "get-expenses-on-particular-date-time")
    public List<Product> getExpenseOnDateTime(@RequestParam Long userId , Date date , @Nullable Time time){
        List<Product> expenses = service.findByDateTime(userId,date,time);
        return expenses;
    }


    @GetMapping(value = "get-total-expense-in-a-month")
    public ResponseEntity<String> getTotalExpenseInAMonth(@RequestParam Long userId , Integer month){
        List<Product> products = iProductRepositories.findByMonth(userId,month);
        int total = 0;
        for(Product product : products){
            total+= product.getProductPrice();
        }
        return new ResponseEntity<String>("The total expense for the timePeriod is : " + total , HttpStatus.OK);
    }



}
