package com.stas.tests.api.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserCredentials {
    private String userName;
    private String password;
}
