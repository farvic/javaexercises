package com.example.ec.web;

public class LoginClassHelper {

    LoginDto loginDto;
    JwtRequestHelper jwtRequestHelper;

    public LoginClassHelper(LoginDto loginDto, JwtRequestHelper jwtRequestHelper) {
        this.loginDto = loginDto;
        this.jwtRequestHelper = jwtRequestHelper;
    }

}
