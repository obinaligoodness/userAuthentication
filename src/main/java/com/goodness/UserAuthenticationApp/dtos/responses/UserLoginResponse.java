package com.goodness.UserAuthenticationApp.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginResponse {
    private String accessToken;
}
