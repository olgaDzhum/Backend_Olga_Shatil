package ru.geekbrains;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties;
    static String host;
    static String apikey;
    static String username;
    static String hash;


    @BeforeAll
    static void beforeAll(){
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        host=properties.getProperty("host");
        apikey=properties.getProperty("apikey");
        hash=properties.getProperty("hash");
        username=properties.getProperty("username");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); //Логирование в местах, где случился fail
        RestAssured.baseURI = host;
    }
}
