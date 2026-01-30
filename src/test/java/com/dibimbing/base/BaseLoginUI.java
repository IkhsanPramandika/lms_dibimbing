package com.dibimbing.base;

import com.dibimbing.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;

public class BaseLoginUI extends BaseSetup {

    @BeforeMethod
    public void loginBeforeTest() {
        LoginPage loginPage = new LoginPage(driver);

        String email = properties.getProperty("admin_email");
        String password = properties.getProperty("admin_password");

        loginPage.login(email, password);

        System.out.println("Sesi login otomatis berhasil dimulai untuk: " + email);
    }
}