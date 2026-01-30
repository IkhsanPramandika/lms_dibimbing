package com.dibimbing.base;

import com.dibimbing.api.login.LoginAPI;
import com.dibimbing.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected String sessionId;

    @BeforeClass
    public void setup() {
        sessionId = new LoginAPI().getSessionId(
                ConfigReader.getProperty("admin_email"),
                ConfigReader.getProperty("admin_password")
        );
        Assert.assertNotNull(sessionId, "Gagal Login: Cookie sid_b2b tidak ditemukan!");
    }
}