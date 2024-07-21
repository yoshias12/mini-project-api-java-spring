package com.enigmacamp.loanApp.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String token;
    @JsonProperty("redirect_url")
    private String redirectUrl;


}
