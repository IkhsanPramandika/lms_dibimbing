package com.dibimbing.pages.employee;

import com.dibimbing.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EmployeePage extends BasePage {
    private static final Logger log = LogManager.getLogger(EmployeePage.class);

    @FindBy(id = "layout-desktop-menu-item-box-employee")
    private WebElement employeeMenu;

    @FindBy(id = "button-add-employee")
    private WebElement addEmployeeButton;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "employeeId")
    private WebElement idInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phoneNumber")
    private WebElement phoneInput;

    @FindBy(id = "division")
    private WebElement divisionSelect; // Ini adalah elemen <select>

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
        log.info("Navigasi ke menu Employee");
        waitForElementToBeVisible(employeeMenu);
        employeeMenu.click();
    }

    public void clickAddEmployee() {
        log.info("Membuka Modal Add Employee");
        waitForElementToBeVisible(addEmployeeButton);
        addEmployeeButton.click();
    }

    /**
     * Metode utama untuk menambah karyawan yang memanggil pengisian data dan submit
     */
    public void addEmployee(String name, String empId, String email, String phone, String division, String role, String address) {
        fillEmployeeData(name, empId, email, phone, division, role, address);
        submitForm();
    }

    public void fillEmployeeData(String name, String empId, String email, String phone, String division, String role, String address) {
        log.info("Mengisi Field: Name, ID, Email, Phone");
        waitForElementToBeVisible(nameInput);
        nameInput.sendKeys(name);
        idInput.sendKeys(empId);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phone);
        selectDivision(division);

        log.info("Mengisi Role dan Alamat");
        roleInput.sendKeys(role);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", maleRadio);

        addressInput.sendKeys(address);
    }

    /**
     * Fungsi khusus untuk menangani dropdown panjang dengan Scroll & Select
     */
    private void selectDivision(String divisionName) {
        log.info("Mencoba memilih divisi: {}", divisionName);
        waitForElementToBeVisible(divisionSelect);

        try {
            String targetText = divisionName.trim();
            WebElement option = driver.findElement(By.xpath("//option[text()='" + targetText + "']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", option);
            Select select = new Select(divisionSelect);
            select.selectByVisibleText(targetText);

        } catch (NoSuchElementException e) {
            log.error("Divisi '{}' tidak ditemukan di dropdown setelah scroll.", divisionName);
            throw e;
        }
    }

    public void submitForm() {
        log.info("Klik tombol Submit");
        waitForElementToBeClickable(submitButton);
        submitButton.click();
    }
}