package com.dibimbing.tests.api.training;

import com.dibimbing.api.login.LoginAPI;
import com.dibimbing.api.training.ReadTraining;
import com.dibimbing.utils.ConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReadTrainingTest {

    @Test
    public void testReadTrainingByTitle() {
        // Login
        LoginAPI login = new LoginAPI();
        String sessionId = login.getSessionId(
                ConfigReader.getProperty("admin_email"),
                ConfigReader.getProperty("admin_password")
        );

        // Search
        String searchTitle = ConfigReader.getProperty("input_title");

        // Read
        ReadTraining training = new ReadTraining();
        Response response = training.getTrainingDetails(sessionId, searchTitle);

        // 4. Assertion
        String actualTitle = response.jsonPath().getString("data.programs[0].title");
        Assert.assertNotNull(actualTitle, "Data training tidak ditemukan!");
        Assert.assertEquals(actualTitle, searchTitle, "Judul yang dicari tidak sesuai!");

        System.out.println("Read Data Berhasil: " + actualTitle);
    }
}