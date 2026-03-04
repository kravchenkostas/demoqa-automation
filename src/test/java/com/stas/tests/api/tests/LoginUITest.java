package com.stas.tests.api.tests;

import com.stas.tests.ui.pages.LoginPage;
import com.stas.tests.ui.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.aeonbits.owner.ConfigFactory;
import com.stas.tests.config.ConfigForTests;
import com.stas.tests.utils.CryptoUtils;

public class LoginUITest extends BaseUITest {

    private ConfigForTests config = ConfigFactory.create(ConfigForTests.class);

    @Test
    public void successfulLogin() {

        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        String decryptedPassword =
                CryptoUtils.decrypt(config.password());

        loginPage.open();
        loginPage.login(config.username(), decryptedPassword);

        Assert.assertTrue(profilePage.isPageOpened(),
                "Profile page should be opened after successful login");
    }
    @Test
    public void unsuccessfulLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("admin6", "WrongPassword123!");
        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(errorText.contains("Invalid username or password"));
        Assert.assertTrue(loginPage.isLoginPageOpened());
    }
}
