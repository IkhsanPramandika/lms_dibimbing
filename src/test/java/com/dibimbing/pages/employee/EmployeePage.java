package com.dibimbing.pages.employee;

import com.dibimbing.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class EmployeePage extends BasePage {
    private static final Logger log = LogManager.getLogger(EmployeePage.class);

    // Sidebar & Main Buttons
    @FindBy(id = "layout-desktop-menu-item-box-employee")
    private WebElement employeeMenu;

    @FindBy(id = "button-add-employee")
    private WebElement addEmployeeButton;

    // Modal Fields
    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "employeeId")
    private WebElement idInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phoneNumber")
    private WebElement phoneInput;

    @FindBy(id = "division")
    private WebElement divisionSelect;

    @FindBy(id = "employeeRole")
    private WebElement roleInput;

    @FindBy(id = "add-employee-gender-radio-male-text")
    private WebElement maleRadio;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "button-add-employee-submit")
    private WebElement submitButton;

    public EmployeePage(WebDriver driver) {
        super(driver);
    }

    public void goToEmployeePage() {
        log.info("Klik menu Employee di sidebar");
        waitForElementToBeVisible(employeeMenu);
        employeeMenu.click();
    }

    public void clickAddEmployee() {
        log.info("Klik tombol Add Employee");
        waitForElementToBeVisible(addEmployeeButton);
        addEmployeeButton.click();
    }

    public void fillEmployeeData(String name, String empId, String email, String phone, String division, String role, String address) {
        log.info("Mengisi data karyawan: {}", name);
        nameInput.sendKeys(name);
        idInput.sendKeys(empId);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phone);

        log.info("Memilih divisi: {}", division);
        Select select = new Select(divisionSelect);
        select.selectByVisibleText(division);

        roleInput.sendKeys(role);

        // MENGGUNAKAN JAVASCRIPT CLICK UNTUK GENDER
        log.info("Memilih gender menggunakan JavaScript Click");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", maleRadio);

        addressInput.sendKeys(address);
    }

    public void submitForm() {
        log.info("Klik Submit Add Employee");
        submitButton.click();
    }
}