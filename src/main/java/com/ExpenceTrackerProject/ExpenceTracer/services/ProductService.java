package com.ExpenceTrackerProject.ExpenceTracer.services;

import com.ExpenceTrackerProject.ExpenceTracer.model.Product;

import com.ExpenceTrackerProject.ExpenceTracer.repositories.IProductRepositories;
import com.ExpenceTrackerProject.ExpenceTracer.repositories.IUsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepositories repository;
    @Autowired
    private IUsersRepositories usersRepositories;
    @Autowired
    private IProductRepositories iProductRepositories;


    public String createProduct(Product product) {
      repository.save(product);
          return "Succesfully Add Data";
    }

    public List<Product> getProducts(Long userId) {
        List<Product> products = null;
        if(usersRepositories.findById(userId).isPresent())
            products = repository.getAllProduct(userId);
        return products;

    }

    public ResponseEntity<String> deleteProducts(Long productId){
        String respone = null;
        if(repository.findById(productId).isPresent()){
            repository.deleteById(productId);
            respone = "Successfully Deleted By Id ";
            return new ResponseEntity<String>(respone,HttpStatus.OK);
        }else {
            respone = "Id Not Found ...!!!! Please Enter A VAlid Id";
            return new ResponseEntity<String>(respone,HttpStatus.BAD_REQUEST);
        }

    }


    public ResponseEntity<String> updateInfo(Product product, Long productId) {
        if(iProductRepositories.findById(productId).isPresent()){
            iProductRepositories.save(product);
            return new ResponseEntity<>("succesfully update Info...!!!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Id Not Found...!!!", HttpStatus.BAD_REQUEST);
    }



    public List<Product> findByDateTime(Long userId, Date date, Time time) {
        if (time == null){
            return iProductRepositories.findByDate(userId,date);
        }
        return iProductRepositories.findByDateAndTime(userId,date,time);
    }

//    public JSONObject deleteExpense(Integer userId, Integer expId) {
//        List<Expense> expenses = expenseRepository.getExpenseByUserId(userId);
//        JSONObject error = new JSONObject();
//        if(expenses.isEmpty()){
//            error.put("userId" , "No records found!");
//            return error;
//        }
//        expenseRepository.deleteById(expId);
//        return error.put("expense found" ,"Expense Deleted ");
//    }
}
