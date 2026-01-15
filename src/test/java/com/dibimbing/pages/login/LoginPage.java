package com.dibimbing.pages.login;

import com.dibimbing.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(id = "input-username-or-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "button-sign-in")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(), 'wrong username or password')]")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        log.info("Input Email: {}", email);
        waitForElementToBeVisible(emailInput);
        emailInput.clear(); // Bersihkan field sebelum input
        emailInput.sendKeys(email);

        log.info("Input Password");
        passwordInput.clear();
        passwordInput.sendKeys(password);

        log.info("Klik Sign In");
        loginButton.click();
    }

    public String getErrorMessageText() {
        waitForElementToBeVisible(errorMessage);
        String text = errorMessage.getText();
        log.warn("Pesan error muncul: {}", text);
        return text;
    }
}