package com.stas.tests.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BookStorePage {
    private WebDriver driver;

    private By header = By.xpath("//div[@class='main-header' and contains(text(),'Book Store')]");
    private By searchInput = By.id("searchBox");
    private By bookTitles = By.cssSelector(".rt-tr-group .rt-td:nth-child(2)"); // Заголовки книг
    private By addButton = By.id("addNewRecordButton");

    public BookStorePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check Book Store page is opened")
    public boolean isOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).isDisplayed();
    }

    @Step("Search book by title: {title}")
    public void searchBook(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.clear();
        input.sendKeys(title);

        // ждём появления хотя бы одного элемента с названием книги
        By bookTitles = By.cssSelector("td span[id^='see-book-'] a");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(bookTitles, 0));
    }

    @Step("Get all book titles from search results")
    public List<String> getAllBookTitles() {
        By bookTitles = By.cssSelector("td span[id^='see-book-'] a");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(bookTitles, 0));

        return driver.findElements(bookTitles)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
