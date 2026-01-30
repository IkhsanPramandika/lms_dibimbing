package com.dibimbing.api.employee;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import java.util.Map;

public class UpdateEmployee {
    public void execute(String sessionId, String employeeId, Map<String, Object> updateData) {
        String apiUrl = ConfigReader.getProperty("base_api_url");
        String query = "mutation updateEmployee($input: EmployeeInput!, $id: String!) { " +
                "updateEmployee(input: $input, id: $id) { id name email __typename } }";

        Map<String, Object> body = Map.of("query", query, "variables", Map.of("id", employeeId, "input", updateData));

        RestAssured.given()
                .contentType("application/json")
                .cookie("sid_b2b", sessionId)
                .body(body)
                .post(apiUrl)
                .then().statusCode(200);
    }
}