package com.dibimbing.tests.login;

import com.dibimbing.base.BaseSetup;
import com.dibimbing.pages.login.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest extends BaseSetup {

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("admin_email"), properties.getProperty("admin_password"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        Assert.assertEquals(driver.getCurrentUrl(), properties.getProperty("dashboard_url"), "Gagal login!");
    }

    @Test(priority = 2)
    public void testInvalidEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("invalid_email"), properties.getProperty("admin_password"));

        Assert.assertTrue(loginPage.getErrorMessageText().contains(properties.getProperty("error_login_msg")));
    }

    @Test(priority = 3)
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("admin_email"), properties.getProperty("invalid_password"));

        Assert.assertTrue(loginPage.getErrorMessageText().contains(properties.getProperty("error_login_msg")));
    }

    @Test(priority = 4)
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", ""); // Input kosong

        Assert.assertTrue(loginPage.getErrorMessageText().contains(properties.getProperty("error_login_msg")));
    }
}