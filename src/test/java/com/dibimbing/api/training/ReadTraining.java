package com.dibimbing.api.training;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ReadTraining {
    public Response getTrainingDetails(String sessionId, String searchTerm) {
        String apiUrl = ConfigReader.getProperty("base_api_url");

        String query = "query programs($search: String, $limit: Float) { " +
                "  programs(search: $search, limit: $limit) { id title description type __typename } }";

        Map<String, Object> variables = new HashMap<>();
        variables.put("search", searchTerm);
        variables.put("limit", 1.0);

        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", variables);

        return RestAssured.given()
                .contentType("application/json")
                .cookie("sid_b2b", sessionId)
                .body(body)
                .post(apiUrl);
    }
}
