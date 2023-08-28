package com.goodness.UserAuthenticationApp.services;

import com.goodness.UserAuthenticationApp.dtos.requests.ForgetPasswordRequest;
import com.goodness.UserAuthenticationApp.dtos.requests.UserLoginRequest;
import com.goodness.UserAuthenticationApp.dtos.requests.UserRegistrationRequest;
import com.goodness.UserAuthenticationApp.dtos.responses.ForgotPasswordResponse;
import com.goodness.UserAuthenticationApp.dtos.responses.UserLoginResponse;
import com.goodness.UserAuthenticationApp.dtos.responses.UserRegistrationResponse;
import com.goodness.UserAuthenticationApp.exceptions.AlreadyExistsException;
import com.goodness.UserAuthenticationApp.exceptions.BadCredentialsException;
import com.goodness.UserAuthenticationApp.exceptions.NotFoundException;

public interface UserService {

    UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) throws BadCredentialsException, AlreadyExistsException;
    UserLoginResponse login(UserLoginRequest userLoginRequest) throws BadCredentialsException, NotFoundException;

    ForgotPasswordResponse forgotPassword(String email) throws NotFoundException;
}
