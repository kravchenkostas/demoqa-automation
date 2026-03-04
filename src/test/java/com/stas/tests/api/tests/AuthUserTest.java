package com.stas.tests.api.tests;

import com.stas.tests.api.models.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthUserTest extends BaseApiTest{
    @Test
    public void authUser(){
        UserCredentials credentials = UserCredentials.builder()
                .userName("admin6")
                .password("Password123!")
                .build();

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .body(credentials)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.asString());
        boolean isAuthorized = response.as(Boolean.class);
        Assert.assertTrue(isAuthorized);
    }
}
