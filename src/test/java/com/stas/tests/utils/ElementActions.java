package com.stas.tests.utils;

import org.openqa.selenium.*;

public class ElementActions {
    private WebDriver driver;
    private WaitUtils wait;

    public ElementActions(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, timeout);
    }

    public void click(By locator) {
        wait.waitForClickable(locator);
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        wait.waitForVisibility(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        wait.waitForVisibility(locator);
        return driver.findElement(locator).getText();
    }
}
