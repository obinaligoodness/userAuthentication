package com.goodness.UserAuthenticationApp.dtos.requests;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Recipient {
    private String name;
    @NonNull
    private String email;
}
