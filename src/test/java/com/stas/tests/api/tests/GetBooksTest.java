package com.stas.tests.api.tests;

import com.stas.tests.api.models.BooksResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class GetBooksTest extends BaseApiTest {

    @Test
    public void getBooksTest() {

        BooksResponse response = RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract()
                .as(BooksResponse.class);


        Assert.assertNotNull(response.getBooks(), "Books list should not be null");
        Assert.assertTrue(response.getBooks().size() > 0, "Books list should not be empty");


        Assert.assertNotNull(response.getBooks().get(0).getTitle(),
                "First book title should not be null");

        System.out.println("Books count: " + response.getBooks().size());
        System.out.println("First book: " + response.getBooks().get(0).getTitle());
    }
}

