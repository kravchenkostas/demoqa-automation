package com.stas.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.aeonbits.owner.ConfigFactory;
import com.stas.tests.config.ConfigForTests;

import java.time.Duration;

public class DriverFactory {

    private static ConfigForTests config = ConfigFactory.create(ConfigForTests.class);

    public static WebDriver createDriver() {

        WebDriver driver;

        switch (config.browser()) {

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "chrome":
            default:
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(config.implicitTimeout())
        );

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(config.pageLoadTimeout())
        );

        return driver;
    }
}
