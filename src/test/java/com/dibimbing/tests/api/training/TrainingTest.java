package com.dibimbing.tests.api.training;

import com.dibimbing.api.training.*;
import com.dibimbing.base.BaseTest;
import com.dibimbing.utils.ConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TrainingTest extends BaseTest {
    private String trainingId;

    @Test(priority = 1)
    public void testCreateTraining() {
        CreateTraining createApi = new CreateTraining();
        trainingId = createApi.createTraining(
                sessionId,
                ConfigReader.getProperty("input_title"),
                ConfigReader.getProperty("input_desc")
        );

        Assert.assertNotNull(trainingId, "Gagal mendapatkan ID Training!");
    }
    @Test(priority = 2, dependsOnMethods = "testCreateTraining")
    public void testReadTraining() {
        ReadTraining readApi = new ReadTraining();
        Response response = readApi.getTrainingDetails(sessionId, ConfigReader.getProperty("input_title")); //

        String actualTitle = response.jsonPath().getString("data.programs[0].title");
        Assert.assertEquals(actualTitle, ConfigReader.getProperty("input_title"));
    }

    @Test(priority = 3, dependsOnMethods = "testCreateTraining")
    public void testUpdateTraining() {
        UpdateTraining updateApi = new UpdateTraining();

        updateApi.updateTraining(
                sessionId,
                trainingId,
                ConfigReader.getProperty("update_training_title"),
                ConfigReader.getProperty("update_training_desc")
        );
        System.out.println("Update Training Berhasil.");
    }
}