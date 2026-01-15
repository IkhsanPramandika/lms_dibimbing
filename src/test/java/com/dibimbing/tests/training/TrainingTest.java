package com.dibimbing.tests.training;

import com.dibimbing.base.BaseSetup;
import com.dibimbing.pages.login.LoginPage;
import com.dibimbing.pages.training.TrainingPage;
import com.dibimbing.utils.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TrainingTest extends BaseSetup {
    private static final Logger log = LogManager.getLogger(TrainingTest.class);

    @DataProvider(name = "trainingCSVData")
    public Object[][] getTrainingCSVData() {
        // Membaca data dari file CSV eksternal
        return CSVReader.readCSV("src/test/resources/testdata/training_data.csv");
    }

    @Test(dataProvider = "trainingCSVData")
    public void testAddTrainingDataDriven(String name, String desc, String isOrder) {
        // 1. Login & Navigasi
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("admin_email"), properties.getProperty("admin_password"));

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.goToTrainingPage();
        trainingPage.clickAddTraining();

        // 2. Input Data
        trainingPage.fillTrainingData(name, desc, Boolean.parseBoolean(isOrder));
        trainingPage.submitTraining();

        log.info("Skenario selesai untuk Training: " + name);

        // 3. Validasi (Menggunakan 'wait' dari BaseSetup)
        if (name == null || name.isEmpty()) {
            log.info("Skenario Negatif: Verifikasi pesan 'Required'");
            Assert.assertTrue(trainingPage.isErrorMessageDisplayed(), "Pesan error tidak muncul!");
        } else {
            log.info("Skenario Positif: Verifikasi modal tertutup");
            boolean isModalClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("title")));
            Assert.assertTrue(isModalClosed, "Modal tidak tertutup, simpan gagal!");
        }
    }
}