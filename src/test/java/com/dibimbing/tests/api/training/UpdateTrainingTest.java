package com.dibimbing.tests.api.training;

import com.dibimbing.api.login.LoginAPI;
import com.dibimbing.api.training.CreateTraining;
import com.dibimbing.api.training.UpdateTraining;
import com.dibimbing.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateTrainingTest {

    @Test
    public void testUpdateTraining() {
        // Login
        LoginAPI login = new LoginAPI();
        String sessionId = login.getSessionId(
                ConfigReader.getProperty("admin_email"),
                ConfigReader.getProperty("admin_password")
        );

        // Input
        CreateTraining createApi = new CreateTraining();
        String trainingId = createApi.createTraining(
                sessionId,
                ConfigReader.getProperty("input_title"),
                ConfigReader.getProperty("input_desc")
        );

        // Update
        UpdateTraining updateApi = new UpdateTraining();
        String newTitle = ConfigReader.getProperty("update_training_title");

        updateApi.updateTraining(sessionId, trainingId, newTitle, "Deskripsi hasil update Java");
    }
}