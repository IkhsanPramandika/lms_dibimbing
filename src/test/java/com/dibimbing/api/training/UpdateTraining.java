package com.dibimbing.api.training;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class UpdateTraining {
    public void updateTraining(String sessionId, String trainingId, String newTitle, String newDesc) {
        String apiUrl = ConfigReader.getProperty("base_api_url");

        String mutation = "mutation updateProgram($id: String!, $input: ProgramInput!) { " +
                "  updateProgram(id: $id, input: $input) { id title description __typename } }";

        Map<String, Object> input = new HashMap<>();
        input.put("title", newTitle);
        input.put("description", newDesc);
        input.put("type", "training");
        input.put("isSequential", false);

        Map<String, Object> variables = new HashMap<>();
        variables.put("id", trainingId);
        variables.put("input", input);

        Map<String, Object> body = new HashMap<>();
        body.put("query", mutation);
        body.put("variables", variables);

        RestAssured.given()
                .contentType("application/json")
                .cookie("sid_b2b", sessionId)
                .body(body)
                .post(apiUrl)
                .then().statusCode(200);
    }

}
