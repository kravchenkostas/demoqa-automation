package com.stas.tests.api.tests;

import com.stas.tests.config.ConfigForTests;
import com.stas.tests.ui.pages.LoginPage;
import com.stas.tests.ui.pages.ProfilePage;
import com.stas.tests.ui.pages.BookStorePage;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class BookSearchTest extends BaseUITest{

    private ConfigForTests config = ConfigFactory.create(ConfigForTests.class);
    @Test
    public void searchBookTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(config.username(), config.password());

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isPageOpened(), "Profile page is not opened");

        profilePage.goToBookStore();
        BookStorePage bookStorePage = new BookStorePage(driver);
        Assert.assertTrue(bookStorePage.isOpened(), "Book Store page is not opened");

        String bookToSearch = "Programming JavaScript Applications";
        bookStorePage.searchBook(bookToSearch);

        List<String> titles = bookStorePage.getAllBookTitles();
        Assert.assertEquals(titles.size(), 1, "Expected exactly one book in search results");
        Assert.assertEquals(titles.get(0), bookToSearch, "Book title does not match");
    }
}
