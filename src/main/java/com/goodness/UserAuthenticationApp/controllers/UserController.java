package com.goodness.UserAuthenticationApp.controllers;

import com.goodness.UserAuthenticationApp.dtos.requests.UserLoginRequest;
import com.goodness.UserAuthenticationApp.dtos.requests.UserRegistrationRequest;
import com.goodness.UserAuthenticationApp.dtos.responses.ForgotPasswordResponse;
import com.goodness.UserAuthenticationApp.dtos.responses.UserLoginResponse;
import com.goodness.UserAuthenticationApp.dtos.responses.UserRegistrationResponse;
import com.goodness.UserAuthenticationApp.exceptions.AlreadyExistsException;
import com.goodness.UserAuthenticationApp.exceptions.BadCredentialsException;
import com.goodness.UserAuthenticationApp.exceptions.NotFoundException;
import com.goodness.UserAuthenticationApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) throws BadCredentialsException, AlreadyExistsException {
      UserRegistrationResponse registrationResponse = userService.registerUser(userRegistrationRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) throws NotFoundException, BadCredentialsException {
        UserLoginResponse loginResponse = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
    @PostMapping("/forgotPassword/{email}")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@PathVariable String email) throws NotFoundException {
        ForgotPasswordResponse response = userService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
