package com.waxes27.School.Controllers.auth.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

//    @PostMapping(path = "/login")
//    public String login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password
//            ){
//        System.out.println(username +" : "+password);
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                    username,password));
//            System.out.println(authentication.isAuthenticated());
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println("Logging in");
//            return "Logging in";
//
//        }catch (Exception e){
//            return "ERRORRR";
//        }
//    }
}