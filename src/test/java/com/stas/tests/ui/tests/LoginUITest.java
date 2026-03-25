package com.stas.tests.ui.tests;

import com.stas.tests.core.base.BaseUITest;
import com.stas.tests.ui.pages.LoginPage;
import com.stas.tests.ui.pages.ProfilePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.aeonbits.owner.ConfigFactory;
import com.stas.tests.core.config.ConfigForTests;

@Epic("UI Tests")
@Feature("Login")
public class LoginUITest extends BaseUITest {

    private ConfigForTests config = ConfigFactory.create(ConfigForTests.class);

    @Story("Successful login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "User should be redirected to profile page after valid login")
    public void successfulLogin() {
        Allure.step("Test body", () -> {
            Allure.parameter("username", config.username());
            Allure.parameter("base url", config.baseUrl());

            LoginPage loginPage = new LoginPage(driver);
            ProfilePage profilePage = new ProfilePage(driver);
            openAndLogin(loginPage, config.username(), config.password());
            verifyProfileOpened(profilePage);
        });
    }

    @Story("Unsuccessful login")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "User should see error message with invalid password")
    public void unsuccessfulLogin(){
        Allure.step("Test body", () -> {
            Allure.parameter("username", config.username());
            Allure.parameter("invalid password", "WrongPassword123!");

            LoginPage loginPage = new LoginPage(driver);
            openAndLogin(loginPage, config.username(), "WrongPassword123!");
            String errorText = loginPage.getErrorMessage();
            verifyInvalidLoginResult(loginPage, errorText);
        });
    }

    @Step("Open login page and login as user: {username}")
    private void openAndLogin(LoginPage loginPage, String username, String password) {
        loginPage.open();
        loginPage.login(username, password);
    }

    @Step("Verify profile page is opened")
    private void verifyProfileOpened(ProfilePage profilePage) {
        boolean opened = profilePage.isPageOpened();
        io.qameta.allure.Allure.step("Check profile page opened (actual: " + opened + ")", () ->
                Assert.assertTrue(opened, "Profile page should be opened after successful login"));
    }

    @Step("Verify invalid login error is shown: {errorText}")
    private void verifyInvalidLoginResult(LoginPage loginPage, String errorText) {
        io.qameta.allure.Allure.step("Check error contains expected text", () ->
                Assert.assertTrue(errorText.contains("Invalid username or password"),
                        "Invalid credentials error should be displayed"));
        boolean opened = loginPage.isLoginPageOpened();
        io.qameta.allure.Allure.step("Check login page remains opened (actual: " + opened + ")", () ->
                Assert.assertTrue(opened, "Login page should remain opened after invalid login"));
    }
}
