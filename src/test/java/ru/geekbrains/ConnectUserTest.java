package ru.geekbrains;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConnectUserTest extends BaseTest {


    private String requestBody = "{\n" +
            "    \"username\": \"Olga\",\n" +
            "    \"firstName\": \"Olga\",\n" +
            "    \"lastName\": \"Ivanova\",\n" +
            "    \"email\": \"my@adress.ru\"\n" +
            "}";

    @Test
    void ClassifyGroceryProductTest() {
        given()
                .queryParam("apiKey", apikey)
                .body(requestBody)
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("status", Matchers.is("success"))
                .body("username", Matchers.notNullValue())
                .body("spoonacularPassword", Matchers.notNullValue())
                .body("hash", Matchers.notNullValue())
                .when()
                .post("/users/connect")
                .prettyPeek();


    }
}
