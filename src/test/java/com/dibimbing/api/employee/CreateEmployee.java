package com.dibimbing.api.employee;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import java.util.Map;

public class CreateEmployee {
    public String execute(String sessionId, Map<String, Object> inputData) {
        String apiUrl = ConfigReader.getProperty("base_api_url");
        String query = "mutation createEmployee($input: EmployeeInput!) { createEmployee(input: $input) { id __typename } }";

        Map<String, Object> body = Map.of("query", query, "variables", Map.of("input", inputData));

        return RestAssured.given()
                .log().all()
                .contentType("application/json")
                .cookie("sid_b2b", sessionId)
                .body(body)
                .post(apiUrl)
                .jsonPath().getString("data.createEmployee.id");
    }
}