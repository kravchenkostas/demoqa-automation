package com.stas.tests.api.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String userId;
    private String username;
    private String token;
    private String expires;
    private boolean isActive;
}
