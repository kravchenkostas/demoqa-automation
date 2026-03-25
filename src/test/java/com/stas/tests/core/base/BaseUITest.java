package com.stas.tests.core.base;

import com.stas.tests.core.driver.DriverFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUITest {

    protected WebDriver driver;

    private int width = 1920;
    private int height = 1080;

    Dimension newDimension = new Dimension(width, height);

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver();
        try {
            driver.manage().window().setSize(newDimension);
        } catch (WebDriverException ignored) {
            // Some CI/browser combinations do not support window resizing reliably.
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
