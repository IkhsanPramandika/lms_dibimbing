package com.dibimbing.tests.employee;

import com.dibimbing.base.BaseLoginUI;
import com.dibimbing.base.BaseSetup;
import com.dibimbing.pages.employee.EmployeePage;
import com.dibimbing.utils.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseLoginUI {

    @DataProvider(name = "employeeCSVData")
    public Object[][] getEmployeeCSVData() {
        return CSVReader.readCSV("src/test/resources/testdata/employee_data.csv");
    }

    @Test(dataProvider = "employeeCSVData")
    public void testAddEmployeeFromCSV(String name, String id, String email, String phone, String div, String role, String addr) {
        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.goToEmployeePage();
        employeePage.clickAddEmployee();

        employeePage.fillEmployeeData(name, id, email, phone, div, role, addr);
        employeePage.submitForm();

        Assert.assertTrue(driver.getCurrentUrl().contains("employee"), "Gagal menambahkan karyawan!");
    }
}