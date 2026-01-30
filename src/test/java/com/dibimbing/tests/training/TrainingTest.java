package com.dibimbing.tests.training;

import com.dibimbing.base.BaseLoginUI;
import com.dibimbing.pages.training.TrainingPage;
import com.dibimbing.utils.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TrainingTest extends BaseLoginUI {

    @DataProvider(name = "trainingCSVData")
    public Object[][] getTrainingCSVData() {
        return CSVReader.readCSV("src/test/resources/testdata/training_data.csv");
    }

    @Test(dataProvider = "trainingCSVData")
    public void testAddTrainingDataDriven(String name, String desc, String isOrder) {
        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.goToTrainingPage();
        trainingPage.clickAddTraining();

        trainingPage.fillTrainingData(name, desc, Boolean.parseBoolean(isOrder));
        trainingPage.submitTraining();

        // Validasi
        if (name == null || name.isEmpty()) {
            Assert.assertTrue(trainingPage.isErrorMessageDisplayed(), "Pesan error tidak muncul!");
        } else {
            boolean isModalClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("title")));
            Assert.assertTrue(isModalClosed, "Modal tidak tertutup, simpan gagal!");
        }
    }
}