package ru.geekbrains;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.Matchers.lessThan;

public abstract class BaseTest {
    static Properties properties;
    static String host;
    static String apikey;
    static String username;
    static String hash;
    static ResponseSpecification basePositiveResponseSpecification;
    static RequestSpecification baseRequestSpec;


    @BeforeAll
    static void beforeAll() {

        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        basePositiveResponseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(2000L))
                .expectStatusCode(200)
                .build();


        host = properties.getProperty("host");
        apikey = properties.getProperty("apikey");
        hash = properties.getProperty("hash");
        username = properties.getProperty("username");

        RestAssured.baseURI = host;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); //Логирование в местах, где случился fail

        baseRequestSpec = new RequestSpecBuilder()
                .addQueryParam("apiKey", apikey)
                .log(LogDetail.URI)
                .log(LogDetail.PARAMS)
                .log(LogDetail.METHOD)
                .build();

        RestAssured.responseSpecification = basePositiveResponseSpecification; //  присваивается в конце
        RestAssured.requestSpecification = baseRequestSpec;


    }
}
