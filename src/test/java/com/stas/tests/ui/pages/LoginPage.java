package com.stas.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.qameta.allure.Step;

public class LoginPage {
    private WebDriver driver;

    private By usernameInput = By.id("userName");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login");
    private By errorMessage = By.id("name");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open login page")
    public void open() {
        driver.get("https://demoqa.com/login");
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Click login button")
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("Login as user: {username}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    @Step("Get error message")
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    @Step("Check login page is opened")
    public boolean isLoginPageOpened() {
        return driver.getCurrentUrl().contains("login");
    }
}
