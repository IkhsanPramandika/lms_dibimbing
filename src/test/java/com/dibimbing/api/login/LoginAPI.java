package com.dibimbing.api.login;

import com.dibimbing.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class LoginAPI {
    public String getSessionId(String email, String password) {
        String apiUrl = ConfigReader.getProperty("base_api_url");
        String companyId = ConfigReader.getProperty("company_id");

        String query = "mutation login($usernameOrEmail: String!, $password: String!, $companyId: String!) { " +
                "login(usernameOrEmail: $usernameOrEmail, password: $password, companyId: $companyId) { " +
                "user { id username } errors { message } } }";

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", Map.of("usernameOrEmail", email, "password", password, "companyId", companyId)
        );

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(body)
                .post(apiUrl);

        //  cookie sid_b2b
        String session = response.getCookie("sid_b2b");

        //jika getCookie() null
        if (session == null) {
            String setCookie = response.getHeader("Set-Cookie");
            if (setCookie != null && setCookie.contains("sid_b2b=")) {
                session = setCookie.split("sid_b2b=")[1].split(";")[0];
            }
        }
        return session;
    }
}