package com.stas.tests.api.tests;

import com.stas.tests.core.base.BaseApiTest;
import com.stas.tests.api.models.BooksResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

@Epic("API Tests")
@Feature("Books")
public class GetBooksTest extends BaseApiTest {

    @Story("Get books list")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "API should return non-empty books list with valid book titles")
    public void getBooksTest() {
        Allure.step("Test body", () -> {
            Allure.parameter("endpoint", "/BookStore/v1/Books");

            BooksResponse response = requestBooks();
            verifyBooksResponse(response);
        });
    }

    @Step("Request books list")
    private BooksResponse requestBooks() {
        return RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract()
                .as(BooksResponse.class);
    }

    @Step("Verify books response contains at least one book")
    private void verifyBooksResponse(BooksResponse response) {
        Allure.step("Check books list is present", () ->
                Assert.assertNotNull(response.getBooks(), "Books list should not be null"));
        int size = response.getBooks() == null ? 0 : response.getBooks().size();
        Allure.step("Check books list is not empty (size: " + size + ")", () ->
                Assert.assertTrue(response.getBooks().size() > 0, "Books list should not be empty"));
        String firstTitle = response.getBooks().isEmpty() ? "<empty>" : response.getBooks().get(0).getTitle();
        Allure.step("Check first book title is present (first title: " + firstTitle + ")", () ->
                Assert.assertNotNull(response.getBooks().get(0).getTitle(),
                        "First book title should not be null"));
    }
}

