package com.dibimbing.api.login;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class LoginAPI {

    public String getSessionId(String email, String password) {
        // Config
        String apiUrl = ConfigReader.getProperty("base_api_url");
        String companyId = ConfigReader.getProperty("company_id");
         //  Mutation
        String query = "mutation login($usernameOrEmail: String!, $password: String!, $companyId: String!) { " +
                "  login(usernameOrEmail: $usernameOrEmail, password: $password, companyId: $companyId) { " +
                "    user { username id role } " +
                "    errors { field message } " +
                "  } " +
                "}";
        // Variabel
        Map<String, Object> variables = new HashMap<>();
        variables.put("usernameOrEmail", email);
        variables.put("password", password);
        variables.put("companyId", companyId);

        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", variables);

        // Request
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(body)
                .post(apiUrl);

        return response.getCookie("sid_b2b");
    }
}