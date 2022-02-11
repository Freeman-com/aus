package com.aus.ConnectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author A.Kozinov
 * date: Sep 28 2021
 * Test Class
 */

public class SumValueWallets {

    public  boolean xMethod(String q) {

        boolean status1 = false;

        try {
            String url = "jdbc:mysql://localhost:3306/db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
            String username = "root";
            String password = "3873725";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                /**
                 *  Default SQL - ...
                 *  Methods PrepareStatement:
                 *      executeUpdate - ...
                 *      ...
                 */

                String sql = "SELECT * FROM db.erc20 WHERE (ticker) LIKE VALUES (?)";
                PreparedStatement pr = conn.prepareStatement(sql);
                pr.setString(1, q);
                int rs = pr.executeUpdate();
                status1 = rs == 1;
            }

        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

        return status1;
    }
}