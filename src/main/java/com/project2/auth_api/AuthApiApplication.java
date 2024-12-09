package com.project2.auth_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootApplication(scanBasePackages = "com.project2.auth_api")
public class AuthApiApplication {

    public static void main(String[] args) {
        System.out.println("[AuthApiApplication] Starting application...");
        SpringApplication.run(AuthApiApplication.class, args);
        System.out.println("[AuthApiApplication] Server is running...");

        testConn();
        callUsersEndpoint();
    }

    private static void callUsersEndpoint() {
        String url = "http://localhost:8080/users";

            String username = "admin";
            String password = "teste123";

        System.out.println("[AuthApiApplication] Calling users endpoint: " + url);

        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("[AuthApiApplication] Response from server: " + response.getBody());
            } else {
                System.out.println("[AuthApiApplication] Server returned error: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("[AuthApiApplication] Error calling endpoint:");
            e.printStackTrace();
        }
    }

    private static void testConn() {
        String url = "jdbc:sqlserver://srv-sql-001-h.database.windows.net:1433;databaseName=db_aula;encrypt=true;trustServerCertificate=false;";
        String username = "sa_adm";
        String password = "teste@100";

        System.out.println("[AuthApiApplication] Testing database connection...");
        String query = "SELECT id, login, email, pwd FROM tbuser";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("[AuthApiApplication] Database connected successfully!");
            System.out.println("[AuthApiApplication] Data from tbuser:");

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String pwd = resultSet.getString("pwd");

                System.out.printf("[AuthApiApplication] ID: %d, Login: %s, Email: %s, Password: %s%n", id, login, email, pwd);
            }

        } catch (Exception e) {
            System.out.println("[AuthApiApplication] Error connecting to database:");
            e.printStackTrace();
        }
    }
}
