package com.dibimbing.api.employee;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import java.util.Map;

public class DeleteEmployee {
    public void execute(String sessionId, String employeeId) {
        String apiUrl = ConfigReader.getProperty("base_api_url");
        String query = "mutation deleteEmployee($id: String!) { deleteEmployee(id: $id) }";

        Map<String, Object> body = Map.of("query", query, "variables", Map.of("id", employeeId));

        RestAssured.given()
                .contentType("application/json")
                .cookie("sid_b2b", sessionId)
                .body(body)
                .post(apiUrl)
                .then().statusCode(200);
    }
}