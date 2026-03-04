package com.stas.tests.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthTokenResponse {
    private String token;
    private String expires;
    private String status;
    private String result;
}
