package com.dibimbing.tests.employee;

import com.dibimbing.base.BaseLoginUI;
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
    public void testAddEmployeeFromCSV(String name, String empId, String email, String phone, String division, String role, String address) {
        EmployeePage employeePage = new EmployeePage(driver);

        employeePage.goToEmployeePage();
        employeePage.clickAddEmployee();

        employeePage.addEmployee(name, empId, email, phone, division, role, address);
        employeePage.submitForm();

        Assert.assertTrue(driver.getCurrentUrl().contains("employee"), "Gagal menambahkan karyawan dengan ID: " + empId);
    }
}