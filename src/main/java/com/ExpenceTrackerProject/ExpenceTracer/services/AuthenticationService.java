package com.ExpenceTrackerProject.ExpenceTracer.services;

import com.ExpenceTrackerProject.ExpenceTracer.model.Authentication;
import com.ExpenceTrackerProject.ExpenceTracer.model.Users;
import com.ExpenceTrackerProject.ExpenceTracer.repositories.IAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private IAuthentication iAuthentication;
    public void save(Authentication token) {
        iAuthentication.save(token);
    }

    public Authentication getToken(Users users) {
       return iAuthentication.findByUsers(users);
    }

    public boolean authenticate(String email, String token) {

            Authentication authenticationToken = iAuthentication.findFirstByToken(token);
            Optional<String> expectedMail = Optional.ofNullable(authenticationToken.getUsers().getEmail());
            if(expectedMail.isPresent()){
                return true;
            }else
                return false;
        }

}
