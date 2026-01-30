package com.dibimbing.tests.api.login;

import com.dibimbing.api.login.LoginAPI;
import com.dibimbing.utils.ConfigReader;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void testLoginAPI() {
        LoginAPI login = new LoginAPI();

        String email = ConfigReader.getProperty("admin_email");
        String password = ConfigReader.getProperty("admin_password");

        String sessionId = login.getSessionId(email, password);

        System.out.println("Login Berhasil! Session ID: " + sessionId);
    }
}