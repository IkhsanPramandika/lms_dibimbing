package com.dibimbing.tests.employee;

import com.dibimbing.base.BaseSetup;
import com.dibimbing.pages.login.LoginPage;
import com.dibimbing.pages.employee.EmployeePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dibimbing.utils.CSVReader;

public class EmployeeTest extends BaseSetup {
    private static final Logger log = LogManager.getLogger(EmployeeTest.class);

    @DataProvider(name = "employeeCSVData")
    public Object[][] getEmployeeCSVData() {
        // Memanggil utility untuk membaca file CSV
        return CSVReader.readCSV("src/test/resources/testdata/employee_data.csv");
    }

    @Test(dataProvider = "employeeCSVData")
    public void testAddEmployeeFromCSV(String name, String id, String email, String phone, String div, String role, String addr) {
        // Logika tes tetap sama, tapi datanya murni dari file luar
        log.info("Menjalankan tes untuk data: " + name);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(properties.getProperty("admin_email"), properties.getProperty("admin_password"));

        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.goToEmployeePage();
        employeePage.clickAddEmployee();

        // Data diambil otomatis dari DataProvider
        employeePage.fillEmployeeData(name, id, email, phone, div, role, addr);
        employeePage.submitForm();

        // 4. Assertion (Validasi hasil - Sesuaikan dengan pesan sukses/URL yang muncul)
        log.info("Validasi penambahan karyawan berhasil");
        Assert.assertTrue(driver.getCurrentUrl().contains("employee"), "Gagal menambahkan karyawan!");
    }
}