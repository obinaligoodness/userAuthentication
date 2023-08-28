package com.goodness.UserAuthenticationApp.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetPasswordRequest {
    private String email;
}
