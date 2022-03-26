package ru.geekbrains;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ClassifyGroceryProductTest extends BaseTest{

    private String requestBody = "{\n" +
            "    \"title\": \"Kroger Vitamin A & D Reduced Fat 2% Milk\",\n" +
            "    \"upc\": \"\",\n" +
            "    \"plu_code\": \"\"\n" +
            "}";


    @Test
    void ClassifyGroceryProductTest(){
        given()
                .queryParam("apiKey", apikey)
                .body(requestBody)
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("matched", Matchers.notNullValue())
                .body("matched",containsStringIgnoringCase("2% milk"))
                .body("breadcrumbs", Matchers.notNullValue())
                .body("category",containsStringIgnoringCase("2 percent milk"))
                .body("usdaCode",equalTo(1174))
                .body("image", Matchers.notNullValue())
                .body("cleanTitle",containsStringIgnoringCase("Kroger Vitamin A & D Reduced Fat 2% Milk"))
                .when()
                .post("/food/products/classify")
                .prettyPeek();


    }
}
