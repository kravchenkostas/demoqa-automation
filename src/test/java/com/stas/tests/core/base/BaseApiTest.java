package com.stas.tests.core.base;

import com.stas.tests.core.config.ConfigForTests;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    private static final ConfigForTests config = ConfigFactory.create(ConfigForTests.class);

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = config.baseUrl();
    }
}
