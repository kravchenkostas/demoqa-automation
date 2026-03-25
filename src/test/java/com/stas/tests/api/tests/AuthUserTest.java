package com.stas.tests.api.tests;

import com.stas.tests.core.base.BaseApiTest;
import com.stas.tests.api.models.UserCredentials;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("API Tests")
@Feature("Authentication")
public class AuthUserTest extends BaseApiTest{
    @Story("Authorize user with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "API should return true for authorized user with valid credentials")
    public void authUser(){
        Allure.step("Test body", () -> {
            Allure.parameter("endpoint", "/Account/v1/Authorized");
            Allure.parameter("username", "admin6");

            UserCredentials credentials = createValidCredentials("admin6", "Password123!");
            Response response = authorizeUser(credentials);
            boolean isAuthorized = extractAuthorizationFlag(response);
            verifyUserIsAuthorized(isAuthorized);
        });
    }

    @Step("Prepare valid user credentials for user: {username}")
    private UserCredentials createValidCredentials(String username, String password) {
        return UserCredentials.builder()
                .userName(username)
                .password(password)
                .build();
    }

    @Step("Send authorize request for user: {credentials.userName}")
    private Response authorizeUser(UserCredentials credentials) {
        return RestAssured
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
    }

    @Step("Extract authorization flag from response body")
    private boolean extractAuthorizationFlag(Response response) {
        return response.as(Boolean.class);
    }

    @Step("Verify user authorization flag is: {isAuthorized}")
    private void verifyUserIsAuthorized(boolean isAuthorized) {
        Allure.step("Check 'authorized' flag equals true (actual: " + isAuthorized + ")", () ->
                Assert.assertTrue(isAuthorized, "Expected authorization flag to be true"));
    }
}
