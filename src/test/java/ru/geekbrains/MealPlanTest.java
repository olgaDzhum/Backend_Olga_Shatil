package ru.geekbrains;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class MealPlanTest extends BaseTest {
    String result;
    private int recipeId;
    private String requestBody = "{\n" +
            "    \"date\": 1589500800,\n" +
            "    \"slot\": 1,\n" +
            "    \"position\": 0,\n" +
            "    \"type\": \"PRODUCT\",\n" +
            "    \"value\": {\n" +
            "        \"id\": 183433,\n" +
            "        \"servings\": 1,\n" +
            "        \"title\": \"Ahold Lasagna with Meat Sauce\",\n" +
            "        \"imageType\": \"jpg\"\n" +
            "    }\n" +
            "}";

    @Test
    void AddMealPlanTest() {
        recipeId = given()
                .queryParam("apiKey", apikey)
                .queryParam("hash", hash)
                .body(requestBody)
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("status", Matchers.is("success"))
                .body("id", Matchers.notNullValue())
                .when()
                .post("/mealplanner/{username}/items", username)
                .prettyPeek()
                .jsonPath()
                .get(String.valueOf("id"));
    }


@AfterEach
   void tearDown(){
       given()
               .queryParam("apiKey", apikey)
               .queryParam("hash", hash)
               .expect()
               .statusCode(200)
               .time(lessThan(1000L))
               .body("status", Matchers.is("success"))
               .when()
               .delete("/mealplanner/{username}/items/{id}",username,recipeId)
               .prettyPeek();
   }

}
