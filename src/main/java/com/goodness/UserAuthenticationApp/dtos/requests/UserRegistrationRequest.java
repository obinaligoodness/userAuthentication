package com.goodness.UserAuthenticationApp.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationRequest {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
}
