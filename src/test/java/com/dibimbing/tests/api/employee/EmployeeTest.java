package com.dibimbing.tests.api.employee;

import com.dibimbing.api.employee.*;
import com.dibimbing.base.BaseTest;
import com.dibimbing.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class EmployeeTest extends BaseTest {
    private String employeeId;

    @Test(priority = 1)
    public void testCreateEmployee() {
        Map<String, Object> empData = new HashMap<>();
        empData.put("name", ConfigReader.getProperty("emp_name"));
        empData.put("email", ConfigReader.getProperty("email_prefix") + System.currentTimeMillis() + ConfigReader.getProperty("email_domain"));
        empData.put("phoneNumber", ConfigReader.getProperty("emp_phone"));
        empData.put("divisionId", ConfigReader.getProperty("division_id"));


        employeeId = new CreateEmployee().execute(sessionId, empData);
        Assert.assertNotNull(employeeId, "Gagal mendapatkan Employee ID! Periksa payload dan autentikasi.");
    }

    @Test(priority = 2, dependsOnMethods = "testCreateEmployee")
    public void testUpdateEmployee() {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", ConfigReader.getProperty("update_emp_name"));
        updateData.put("phoneNumber", ConfigReader.getProperty("update_emp_phone"));
        updateData.put("divisionId", ConfigReader.getProperty("division_id"));


        new UpdateEmployee().execute(sessionId, employeeId, updateData);
        System.out.println("Update berhasil untuk ID: " + employeeId);
    }

    @Test(priority = 3, dependsOnMethods = "testUpdateEmployee")
    public void testDeleteEmployee() {
        new DeleteEmployee().execute(sessionId, employeeId);
        System.out.println("Delete berhasil untuk ID: " + employeeId);
    }
}