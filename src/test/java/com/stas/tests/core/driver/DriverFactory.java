package com.stas.tests.core.driver;

import com.stas.tests.core.config.ConfigForTests;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Locale;

public class DriverFactory {

    private static final ConfigForTests config = ConfigFactory.create(ConfigForTests.class);

    public static WebDriver createDriver() {
        WebDriver driver;

        String browser = config.browser();
        if (browser == null || browser.isBlank()) {
            browser = "chrome";
        }

        switch (browser.toLowerCase(Locale.ROOT)) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (config.headless()) {
                    firefoxOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (config.headless()) {
                    chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }
                driver = new ChromeDriver(chromeOptions);
        }

        if (!config.headless()) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.implicitTimeout()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.pageLoadTimeout()));

        return driver;
    }
}
