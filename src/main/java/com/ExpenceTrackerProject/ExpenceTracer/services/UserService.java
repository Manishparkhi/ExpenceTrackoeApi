package com.ExpenceTrackerProject.ExpenceTracer.services;

import com.ExpenceTrackerProject.ExpenceTracer.dto.SignInInput;
import com.ExpenceTrackerProject.ExpenceTracer.dto.SignInOutput;
import com.ExpenceTrackerProject.ExpenceTracer.dto.SignUpInput;
import com.ExpenceTrackerProject.ExpenceTracer.dto.SignUpOutput;
import com.ExpenceTrackerProject.ExpenceTracer.model.Authentication;
import com.ExpenceTrackerProject.ExpenceTracer.model.Product;
import com.ExpenceTrackerProject.ExpenceTracer.model.Users;
import com.ExpenceTrackerProject.ExpenceTracer.repositories.IAuthentication;
import com.ExpenceTrackerProject.ExpenceTracer.repositories.IProductRepositories;
import com.ExpenceTrackerProject.ExpenceTracer.repositories.IUsersRepositories;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private IUsersRepositories repo;

    @Autowired
    private AuthenticationService authentication;
    @Autowired ProductService productService;

    @Autowired
   private IProductRepositories productRepositories;

    public SignUpOutput  signUp(SignUpInput input) {

        Users user = repo.findFirstByEmail(input.getEmail());

        if(user != null){
            throw new IllegalStateException("User already exists by this details..");
        }

        String  encryptedPassword = null;

        try {
            encryptedPassword =  encryptedPassword(input.getPassword());
        }catch (Exception e){
            e.printStackTrace();

        }

        Users user1 = new Users(input.getUsername(),input.getEmail(),encryptedPassword);

        repo.save(user1);

        Authentication token = new Authentication(user1);
        authentication.save(token);

        return  new  SignUpOutput(HttpStatus.CREATED,"User registered Successfully");

    }

    private String encryptedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }


    public SignInOutput signIn(SignInInput input) {
        Users users = repo.findFirstByEmail(input.getEmail());

        if(users == null){
            throw new IllegalStateException("Email Id Not Found....!!!!");
        }
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptedPassword(input.getPassword());
        }catch (Exception e){
            e.printStackTrace();

        }

       boolean passwordValid = encryptedPassword.equals(users.getPassword());

        if(!passwordValid){
            throw new IllegalStateException("User invalid..!!!!!!!!");
        }
        Authentication authentication1 = authentication.getToken(users);

        return new SignInOutput(HttpStatus.ACCEPTED,authentication1.getToken());

    }

    public void updateUser(SignUpInput signUpInput) {


            Users user1 = repo.findFirstByEmail(signUpInput.getEmail());
            if(user1 == null){
                throw new IllegalStateException("User invalid..!!!");
            }
            String encryptedPassword = null;
            if(signUpInput.getEmail() != null)
            {
                try {
                    encryptedPassword = encryptedPassword(signUpInput.getPassword());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }

            //save the user
            Users user = new Users(user1.getId(),signUpInput.getUsername(),signUpInput.getEmail(),signUpInput.getPassword());

            repo.save(user);

    }

    public List<Users> getUser(Long userId) {

            List<Users> response = new ArrayList<>();
            if(userId == null){
                response = repo.findAll();
            }else{
                if(repo.findById(userId).isPresent()){
                    Users user = repo.findById(userId).get();
                    response.add(user);
                }
            }
            return response;

    }


    public String deleteUser(Long userId) {
        String response = null;
        if(repo.findById(userId).isPresent()){
            List<Product> products = productService.getProducts(userId);
            for (Product product : products)
                productRepositories.delete(product);
            response = "User with username " + repo.findById(userId).get().getUsername();
            repo.deleteById(userId);
        }
        return response;
    }
}
