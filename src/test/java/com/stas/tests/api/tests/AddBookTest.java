package com.stas.tests.api.tests;

import com.stas.tests.core.base.BaseApiTest;
import com.stas.tests.api.models.UserCredentials;
import com.stas.tests.api.models.LoginResponse;
import com.stas.tests.api.models.AddBookRequest;
import com.stas.tests.api.models.Isbn;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Books")
public class AddBookTest extends BaseApiTest{
    @Story("Add book to user profile")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "API should add selected book to authorized user collection")
    public void addBookToUser() {
        Allure.step("Test body", () -> {
            Allure.parameter("login endpoint", "/Account/v1/Login");
            Allure.parameter("add book endpoint", "/BookStore/v1/Books");
            Allure.parameter("username", "admin6");
            Allure.parameter("isbn", "9781449325862");

            UserCredentials credentials = createValidCredentials("admin6", "Password123!");
            LoginResponse loginResponse = loginUser(credentials);
            AddBookRequest request = buildAddBookRequest(loginResponse.getUserId(), "9781449325862");
            sendAddBookRequest(loginResponse.getToken(), request);
        });
    }

    @Step("Prepare valid user credentials for user: {username}")
    private UserCredentials createValidCredentials(String username, String password) {
        return UserCredentials.builder()
                .userName(username)
                .password(password)
                .build();
    }

    @Step("Login user and get token for user: {credentials.userName}")
    private LoginResponse loginUser(UserCredentials credentials) {
        return RestAssured
                .given()
                .contentType("application/json")
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);
    }

    @Step("Build add book request with ISBN: {isbn}")
    private AddBookRequest buildAddBookRequest(String userId, String isbn) {
        return AddBookRequest.builder()
                .userId(userId)
                .collectionOfIsbns(
                        List.of(Isbn.builder()
                                .isbn(isbn)
                                .build())
                )
                .build();
    }

    @Step("Send add book request for userId: {request.userId}")
    private void sendAddBookRequest(String token, AddBookRequest request) {
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .extract()
                .response();
        Allure.step("Check add book response status is 201 (actual: " + response.statusCode() + ")", () ->
                Assert.assertEquals(response.statusCode(), 201, "Book should be added successfully"));
    }
}
