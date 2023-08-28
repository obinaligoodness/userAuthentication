package com.goodness.UserAuthenticationApp.services;

import com.goodness.UserAuthenticationApp.dtos.requests.EmailNotificationRequest;
import com.goodness.UserAuthenticationApp.dtos.responses.EmailNotificationResponse;

public interface EmailService {
    EmailNotificationResponse sendEmail(EmailNotificationRequest emailNotificationRequest);

}
