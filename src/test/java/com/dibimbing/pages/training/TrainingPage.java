package com.dibimbing.pages.training;

import com.dibimbing.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrainingPage extends BasePage {
    private static final Logger log = LogManager.getLogger(TrainingPage.class);

    // Sidebar Training - Menggunakan xpath karena ID tidak tersedia
    @FindBy(xpath = "//p[text()='Training']")
    private WebElement trainingMenu;

    @FindBy(id = "add-training-button")
    private WebElement addTrainingBtn;

    @FindBy(id = "title")
    private WebElement trainingNameInput;

    @FindBy(id = "description")
    private WebElement trainingDescInput;

    // Checkbox opsional (Content must be accessed in order)
    @FindBy(className = "chakra-checkbox__control")
    private WebElement contentOrderCheckbox;

    @FindBy(id = "add-training-submit-button")
    private WebElement submitTrainingBtn;

    @FindBy(xpath = "//div[text()='Required']")
    private WebElement errorMessageRequired;

    public boolean isErrorMessageDisplayed() {
        try {
            waitForElementToBeVisible(errorMessageRequired);
            return errorMessageRequired.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public TrainingPage(WebDriver driver) {
        super(driver);
    }

    public void goToTrainingPage() {
        log.info("Navigasi ke halaman Training");
        waitForElementToBeVisible(trainingMenu);
        trainingMenu.click();
    }

    public void clickAddTraining() {
        log.info("Klik tombol Add Training");
        waitForElementToBeVisible(addTrainingBtn);
        addTrainingBtn.click();
    }

    public void fillTrainingData(String name, String desc, boolean checkOrder) {
        log.info("Mengisi data training: {}", name);
        waitForElementToBeVisible(trainingNameInput);
        trainingNameInput.sendKeys(name);
        trainingDescInput.sendKeys(desc);

        if (checkOrder) {
            log.info("Menceklis opsi 'Content must be accessed in order'");
            contentOrderCheckbox.click();
        }
    }

    public void submitTraining() {
        log.info("Submit form Add Training");
        submitTrainingBtn.click();
    }
}