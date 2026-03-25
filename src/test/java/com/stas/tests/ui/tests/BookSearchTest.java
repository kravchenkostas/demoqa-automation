package com.stas.tests.ui.tests;

import com.stas.tests.core.base.BaseUITest;
import com.stas.tests.core.config.ConfigForTests;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import com.stas.tests.ui.pages.LoginPage;
import com.stas.tests.ui.pages.ProfilePage;
import com.stas.tests.ui.pages.BookStorePage;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

@Epic("UI Tests")
@Feature("Book Store")
public class BookSearchTest extends BaseUITest{

    private ConfigForTests config = ConfigFactory.create(ConfigForTests.class);

    @Story("Search a book by title")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "User should see a single matching book after searching by exact title")
    public void searchBookTest() {
        Allure.step("Test body", () -> {
            Allure.parameter("username", config.username());
            Allure.parameter("book title", "Programming JavaScript Applications");

            LoginPage loginPage = new LoginPage(driver);
            ProfilePage profilePage = new ProfilePage(driver);
            BookStorePage bookStorePage = new BookStorePage(driver);

            String bookToSearch = "Programming JavaScript Applications";
            loginAndOpenBookStore(loginPage, profilePage, bookStorePage);
            searchBook(bookStorePage, bookToSearch);
            verifySearchResult(bookStorePage, bookToSearch);
        });
    }

    @Step("Login and navigate to Book Store page")
    private void loginAndOpenBookStore(LoginPage loginPage, ProfilePage profilePage, BookStorePage bookStorePage) {
        loginPage.open();
        loginPage.login(config.username(), config.password());
        Assert.assertTrue(profilePage.isPageOpened(), "Profile page is not opened");
        profilePage.goToBookStore();
        Assert.assertTrue(bookStorePage.isOpened(), "Book Store page is not opened");
    }

    @Step("Search for book: {bookToSearch}")
    private void searchBook(BookStorePage bookStorePage, String bookToSearch) {
        bookStorePage.searchBook(bookToSearch);
    }

    @Step("Verify only expected book is displayed in results for: {bookToSearch}")
    private void verifySearchResult(BookStorePage bookStorePage, String bookToSearch) {
        List<String> titles = bookStorePage.getAllBookTitles();
        io.qameta.allure.Allure.step("Check result count equals 1 (actual: " + titles.size() + ")", () ->
                Assert.assertEquals(titles.size(), 1, "Expected exactly one book in search results"));
        io.qameta.allure.Allure.step("Check first result title equals expected (actual: " + titles.get(0) + ")", () ->
                Assert.assertEquals(titles.get(0), bookToSearch, "Book title does not match"));
    }
}
