package com.aus;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class TestEmails extends Assert {

    public static List<String> responseMethod(long users_id) {

        try {
            String url = "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
            String username = "root";
            String password = "3873725";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM db.bitmaxaccount  WHERE (users_id) = ?";
                PreparedStatement prepareStatement = conn.prepareStatement(sql);
                prepareStatement.setLong(1, users_id);

                var resultSet = prepareStatement.executeQuery();

                while (resultSet.next()) {
                    String email = resultSet.getString("bitmaxemail");
                    String apiKey = resultSet.getString("apikey");
                    String secret = resultSet.getString("secret");

                    List<Map<String, String>> results = new ArrayList<>();
                    results.add(Map.of(apiKey, secret));

                    Map<String, List<Map<String, String>>> iso = new LinkedHashMap<>();
                    iso.put(email, results);

                    System.out.println(iso);
                }
            }
        } catch (Exception ex) {

            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return null;
    }

    @Test
    public static void main(String[] args) {
        System.out.println(responseMethod(1));

    }
}