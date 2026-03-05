package com.stas.tests.utils;

import com.stas.tests.config.ConfigForTests;
import org.aeonbits.owner.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.aeonbits.owner.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseUtils {
    private static final ConfigForTests config =
            ConfigFactory.create(ConfigForTests.class);

    public static String getEncryptedPasswordByUsername(String username) {

        String query = "SELECT password FROM data WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(
                config.dbUrl(),
                config.dbUser(),
                config.dbPassword());

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("password");
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: " + e.getMessage(), e);
        }

        throw new RuntimeException("User not found in DB: " + username);
    }
}
