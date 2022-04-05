package ru.geekbrains;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.spoonaccular.model.ConnectUserResponse;

import static ru.geekbrains.Endpoints.CONNECT_USER;
import static ru.geekbrains.RequestBody.CONNECT_USER_BODY;

public class ConnectUserTest extends BaseTest {

    @Test
    void connectUserTest() {
        ConnectUserResponse actually = RestAssured.given()
                .body(CONNECT_USER_BODY)
                .when()
                .post(CONNECT_USER)
                .body()
                .prettyPeek()
                .as(ConnectUserResponse.class);
        Assertions.assertNotNull(actually.getUsername());
        Assertions.assertNotNull(actually.getHash());
        Assertions.assertNotNull(actually.getSpoonacularPassword());
        Assertions.assertEquals("success", actually.getStatus());

    }
}
