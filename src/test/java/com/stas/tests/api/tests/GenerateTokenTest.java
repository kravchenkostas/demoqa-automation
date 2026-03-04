package com.stas.tests.api.tests;

import com.stas.tests.api.models.AuthTokenResponse;
import com.stas.tests.api.models.UserCredentials;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateTokenTest extends BaseApiTest{
    @Test
    public void generateToken(){
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
                .post("Account/v1/GenerateToken")
                .then()
                .statusCode(200)
                .extract()
                .response();
        AuthTokenResponse authTokenResponse = response.as(AuthTokenResponse.class);

        Assert.assertNotNull(authTokenResponse.getToken(), "Token should not be null");
        Assert.assertEquals(authTokenResponse.getStatus(), "Success");
        Assert.assertEquals(authTokenResponse.getResult(), "User authorized successfully.");

        System.out.println("Generated token: " + authTokenResponse.getResult());
    }
}
