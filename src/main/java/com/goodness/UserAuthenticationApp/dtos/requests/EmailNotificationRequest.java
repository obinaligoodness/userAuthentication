package com.goodness.UserAuthenticationApp.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class EmailNotificationRequest {
    private final Sender sender = new Sender("Goodness.com","noreply@promiscuous.africa");
    //to
    @JsonProperty("to")
    private List<Recipient> recipients;
    //cc
    @JsonProperty("cc")
    private List<String> copiedEmails;

    //htmlContent
    @JsonProperty("htmlContent")
    private String mailContent;

    private String textContent;

    private String subject;
}
