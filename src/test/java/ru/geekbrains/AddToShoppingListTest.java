package ru.geekbrains;

import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.spoonaccular.model.AddToShoppingListResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class AddToShoppingListTest extends BaseTest {

    String jsonRequest;
    int shoppingListId;

    @SneakyThrows
    @BeforeEach
    void setUp() throws IOException {
        byte[] bytes = new FileInputStream("src/main/resources/AddToShoppingListPostBody.json").readAllBytes();
        jsonRequest = new String(bytes, StandardCharsets.UTF_8);

    }

    @Test
    void addToShoppingList() {

        AddToShoppingListResponse actually = RestAssured.given()
                .queryParam("hash", hash)
                .body(jsonRequest)
                .when()
                .post("/mealplanner/{username}/shopping-list/items", username)
                .body()
                .prettyPeek()
                .as(AddToShoppingListResponse.class);
        Assertions.assertNotNull(actually.getId());
        Assertions.assertNotNull(actually.getMeasures().getMetric());
        Assertions.assertNotNull(actually.getMeasures().getUs());
        Assertions.assertNotNull(actually.getIngredientId());
        actually.getName().toLowerCase(Locale.ROOT).contains("baking powder");
        Assertions.assertEquals(2.0F, actually.getMeasures().getOriginal().getAmount());
        Assertions.assertEquals("package", actually.getMeasures().getOriginal().getUnit());
        actually.getAisle().toLowerCase().equals("Baking");
        actually.getIngredientId().equals(18369);
        shoppingListId = actually.getId();
    }

    @AfterEach
    void tearDown() {
        given()
                .queryParam("hash", hash)
                .when()
                .delete("/mealplanner/{username}/shopping-list/items/{id}", username, shoppingListId)
                .prettyPeek();
    }


}
