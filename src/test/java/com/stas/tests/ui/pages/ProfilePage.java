package com.stas.tests.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private WebDriver driver;

    private By profileHeader = By.xpath("//div[@class='main-header' and text()='Profile']");
    private By goToBookStoreButton = By.id("gotoStore");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check profile page is opened")
    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("profile"));
        return driver.getCurrentUrl().contains("profile");
    }

    @Step("Go to Book Store from profile page")
    public void goToBookStore() {
        driver.findElement(goToBookStoreButton).click();
    }
}
