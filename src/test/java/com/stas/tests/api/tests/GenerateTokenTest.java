package com.stas.tests.api.tests;

import com.stas.tests.core.base.BaseApiTest;
import com.stas.tests.api.models.AuthTokenResponse;
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
public class GenerateTokenTest extends BaseApiTest{
    @Story("Generate auth token")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "API should generate a token for user with valid credentials")
    public void generateToken(){
        Allure.step("Test body", () -> {
            Allure.parameter("endpoint", "/Account/v1/GenerateToken");
            Allure.parameter("username", "admin6");

            UserCredentials credentials = createValidCredentials("admin6", "Password123!");
            Response response = requestToken(credentials);
            AuthTokenResponse authTokenResponse = mapTokenResponse(response);
            verifyTokenResponse(authTokenResponse);
        });
    }

    @Step("Prepare valid user credentials for user: {username}")
    private UserCredentials createValidCredentials(String username, String password) {
        return UserCredentials.builder()
                .userName(username)
                .password(password)
                .build();
    }

    @Step("Send generate token request for user: {credentials.userName}")
    private Response requestToken(UserCredentials credentials) {
        return RestAssured
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
    }

    @Step("Map token response to model")
    private AuthTokenResponse mapTokenResponse(Response response) {
        return response.as(AuthTokenResponse.class);
    }

    @Step("Verify generated token response fields: status={authTokenResponse.status}, result={authTokenResponse.result}")
    private void verifyTokenResponse(AuthTokenResponse authTokenResponse) {
        Allure.step("Check token is not null (length: " +
                (authTokenResponse.getToken() == null ? 0 : authTokenResponse.getToken().length()) + ")", () ->
                Assert.assertNotNull(authTokenResponse.getToken(), "Token should not be null"));
        Allure.step("Check status equals 'Success' (actual: " + authTokenResponse.getStatus() + ")", () ->
                Assert.assertEquals(authTokenResponse.getStatus(), "Success"));
        Allure.step("Check result message is correct (actual: " + authTokenResponse.getResult() + ")", () ->
                Assert.assertEquals(authTokenResponse.getResult(), "User authorized successfully."));
    }
}
