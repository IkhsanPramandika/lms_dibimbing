package com.dibimbing.tests.api.training;

import com.dibimbing.api.login.LoginAPI;
import com.dibimbing.api.training.CreateTraining;
import com.dibimbing.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTrainingTest {

    @Test
    public void testCreateTrainingFlow() {
        // Login
        LoginAPI login = new LoginAPI();
        String sessionId = login.getSessionId(
                ConfigReader.getProperty("admin_email"),
                ConfigReader.getProperty("admin_password")
        );

        Assert.assertNotNull(sessionId, "Login Gagal!");

        // Input
        CreateTraining training = new CreateTraining();
        String newId = training.createTraining(
                sessionId,
                ConfigReader.getProperty("input_title"),
                ConfigReader.getProperty("input_desc")
        );

        System.out.println("Berhasil Membuat Training! ID Baru: " + newId);
        Assert.assertNotNull(newId, "Gagal mendapatkan ID Training baru!");
    }
}