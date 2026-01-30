package com.dibimbing.api.training;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class CreateTraining {

    public String createTraining(String sessionId, String title, String description) {
        String apiUrl = ConfigReader.getProperty("base_api_url");

        // Mutation
        String query = "mutation createProgram($input: ProgramInput!) { " +
                "  createProgram(input: $input) { " +
                "    id title description __typename " +
                "  } " +
                "}";

        // Variable
        Map<String, Object> input = new HashMap<>();
        input.put("title", title);
        input.put("description", description);
        input.put("type", "training");
        input.put("isSequential", false);

        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", Map.of("input", input));

        // Request
        Response response = RestAssured.given()
                .log().all()
                .contentType("application/json")
                .cookie("sid_b2b", sessionId)
                .body(body)
                .post(apiUrl);

        // 4. ID Training
        return response.jsonPath().getString("data.createProgram.id");
    }
}