package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.spoonaccular.model.ClassifyGroceryProductResponse;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class ClassifyGroceryProductTest extends BaseTest{

    private String requestBody = "{\n" +
            "    \"title\": \"Kroger Vitamin A & D Reduced Fat 2% Milk\",\n" +
            "    \"upc\": \"\",\n" +
            "    \"plu_code\": \"\"\n" +
            "}";


    @Test
    void ClassifyGroceryProductTest() {
        ClassifyGroceryProductResponse actually = given()
                .body(requestBody)
                .when()
                .post("/food/products/classify")
                .body()
                .prettyPeek()
                .as(ClassifyGroceryProductResponse.class);
        Assertions.assertNotNull(actually.getCategory());
        Assertions.assertNotNull(actually.getMatched());
        Assertions.assertNotNull(actually.getImage());
        Assertions.assertNotNull(actually.getBreadcrumbs());
        actually.getMatched().contains("2% milk");
        actually.getCategory().contains("2 percent milk");
        Assertions.assertEquals(1174, actually.getUsdaCode());
        actually.getCleanTitle().toLowerCase(Locale.ROOT).contains("Kroger Vitamin A & D Reduced Fat 2% Milk");


    }
}
