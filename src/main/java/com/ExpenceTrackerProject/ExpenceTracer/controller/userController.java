package com.ExpenceTrackerProject.ExpenceTracer.controller;

import com.ExpenceTrackerProject.ExpenceTracer.dto.SignInInput;
import com.ExpenceTrackerProject.ExpenceTracer.dto.SignInOutput;
import com.ExpenceTrackerProject.ExpenceTracer.dto.SignUpInput;
import com.ExpenceTrackerProject.ExpenceTracer.dto.SignUpOutput;
import com.ExpenceTrackerProject.ExpenceTracer.model.Users;
import com.ExpenceTrackerProject.ExpenceTracer.services.AuthenticationService;
import com.ExpenceTrackerProject.ExpenceTracer.services.UserService;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/api/User/")
public class userController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("SignUp")
    public SignUpOutput signUp(@RequestBody SignUpInput input){
      return  userService.signUp(input);
    }

    @PostMapping("SignIn")
    SignInOutput SignIn(@RequestBody SignInInput input){
         return userService.signIn(input);
    }

    @PutMapping("/update/{email}/{token}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @PathVariable String token, @RequestBody SignUpInput signUpInput){
        HttpStatus status;
        String message = "";
        if(authenticationService.authenticate(email,token)){
            userService.updateUser(signUpInput);
            message = "Updated Successully!!!!....";
            status = HttpStatus.ACCEPTED;
        }else{
            message ="Invalid Details to update";
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<String>(message,status);

    }

    @GetMapping("getUser")
    private List<Users> getUser(@Nullable @RequestParam Long userId){
       return userService.getUser(userId);

    }
    @DeleteMapping("deleteUser") // ✅
    private ResponseEntity<String> deleteUser(@RequestParam Long userId) {
        String response = userService.deleteUser(userId);
        if (response == null)
            return new ResponseEntity<>("User not exist", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
