package com.stas.tests.api.tests;

import com.stas.tests.api.models.UserCredentials;
import com.stas.tests.api.models.LoginResponse;
import com.stas.tests.api.models.AddBookRequest;
import com.stas.tests.api.models.Isbn;
import io.restassured.RestAssured;
import java.util.List;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddBookTest extends BaseApiTest{
    @Test
    public void addBookToUser() {

        UserCredentials credentials = UserCredentials.builder()
                .userName("admin6")
                .password("Password123!")
                .build();

        LoginResponse loginResponse = RestAssured
                .given()
                .contentType("application/json")
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();

        AddBookRequest request = AddBookRequest.builder()
                .userId(userId)
                .collectionOfIsbns(
                        List.of(Isbn.builder()
                                .isbn("9781449325862")
                                .build())
                )
                .build();


        RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);
    }
}
