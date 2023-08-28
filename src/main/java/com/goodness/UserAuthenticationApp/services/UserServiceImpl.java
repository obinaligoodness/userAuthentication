package com.goodness.UserAuthenticationApp.services;

import com.goodness.UserAuthenticationApp.config.AppConfig;
import com.goodness.UserAuthenticationApp.dtos.requests.*;
import com.goodness.UserAuthenticationApp.dtos.responses.ForgotPasswordResponse;
import com.goodness.UserAuthenticationApp.dtos.responses.UserLoginResponse;
import com.goodness.UserAuthenticationApp.dtos.responses.UserRegistrationResponse;
import com.goodness.UserAuthenticationApp.exceptions.AlreadyExistsException;
import com.goodness.UserAuthenticationApp.exceptions.BadCredentialsException;
import com.goodness.UserAuthenticationApp.exceptions.NotFoundException;
import com.goodness.UserAuthenticationApp.models.User;
import com.goodness.UserAuthenticationApp.repositories.UserRepository;
import com.goodness.UserAuthenticationApp.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.goodness.UserAuthenticationApp.utils.AppUtils.generateToken;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final EmailService mailService;
    private final AppConfig appConfig;
    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) throws BadCredentialsException, AlreadyExistsException {
        User user = new User();
        String name = userRegistrationRequest.getUserName();
        String email = userRegistrationRequest.getEmail();
        String phoneNumber = userRegistrationRequest.getPhoneNumber();
        String password = userRegistrationRequest.getPassword();

        user.setUserName(name);
        if (!email.matches("\\. +[a-z0-9](?:[a-z0-9-]*[a-z0-9])? ")) throw new BadCredentialsException("invalid email address");
        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser!=null) throw new AlreadyExistsException("user already exists");
        user.setEmail(email);
        if (phoneNumber.length()!=11) throw new BadCredentialsException("Invalid PhoneNumber");
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        User savedUser = userRepository.save(user);


        String generatedToken = AppUtils.generateToken(email);
        EmailNotificationRequest request = new EmailNotificationRequest();
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient(savedUser.getEmail());
        recipients.add(recipient);
        request.setRecipients(recipients);
        request.setSubject("WELCOME TO GOODNESS.COM");
        String activationLink = appConfig.getBaseUrl()+"/user/activate?code="+generatedToken;


        String mailContent = String.format(activationLink);
        request.setMailContent(mailContent);

        mailService.sendEmail(request);

        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setMessage("Registration complete");
        return userRegistrationResponse;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) throws BadCredentialsException, NotFoundException {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        if (!email.matches("\\. +[a-z0-9](?:[a-z0-9-]*[a-z0-9])? ")) throw new BadCredentialsException("wrong email address format");

        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser==null) throw new NotFoundException("User does not exist");

        if (!foundUser.getPassword().equals(password)) throw new BadCredentialsException("invalid email address");

            UserLoginResponse userLoginResponse = new UserLoginResponse();
            String accessToken = AppUtils.generateToken(email);
            userLoginResponse.setAccessToken(accessToken);

        return userLoginResponse;
    }

    @Override
    public ForgotPasswordResponse forgotPassword(String  email) throws NotFoundException {
        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser==null) throw new NotFoundException("User with this email does not exist");
        String accessToken = AppUtils.generateToken(email);
        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
        forgotPasswordResponse.setAccessToken(accessToken);
        return forgotPasswordResponse;
    }
    public void findUser (String email){
        userRepository.findUserByEmail(email);
    }

}
