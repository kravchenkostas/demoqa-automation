package com.stas.tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import java.time.Duration;

public class WaitUtils {
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
