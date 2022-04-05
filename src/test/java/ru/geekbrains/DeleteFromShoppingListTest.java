package ru.geekbrains;

import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.spoonaccular.model.AddToShoppingListResponse;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static ru.geekbrains.BaseTest.hash;
import static ru.geekbrains.BaseTest.username;

public class DeleteFromShoppingListTest {

    int shoppingListId;
    String jsonRequest;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        byte[] bytes = new FileInputStream("src/main/resources/AddToShoppingListPostBody.json").readAllBytes();
        jsonRequest = new String(bytes, StandardCharsets.UTF_8);
        AddToShoppingListResponse actually = RestAssured.given()
                .queryParam("hash", hash)
                .body(jsonRequest)
                .when()
                .post("/mealplanner/{username}/shopping-list/items", username)
                .body()
                .prettyPeek()
                .as(AddToShoppingListResponse.class);
        shoppingListId = actually.getId();
    }

    @Test
    void DeleteFromShoppingListTest() {
        given()
                .when()
                .delete("/mealplanner/{username}/shopping-list/items/{id}", username, shoppingListId)
                .prettyPeek()
                .then()
                .body("status", Matchers.is("success"));


    }


}
