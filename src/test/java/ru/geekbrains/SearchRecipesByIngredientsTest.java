package ru.geekbrains;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.lessThan;

public class SearchRecipesByIngredientsTest extends BaseTest {

    @Test
    void ConvertAmountsCopyTest(){
        given()
                .queryParam("apiKey", apikey)
                .queryParam("ingredients", "cheese, tomato")
                .queryParam("number", 1)
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(containsStringIgnoringCase("tomato"))
                .body(containsStringIgnoringCase("cheese"))
                .body("[0].id",Matchers.notNullValue())
                .body("[0].title",Matchers.notNullValue())
                .body("[0].image",Matchers.notNullValue())
                .body("[0].missedIngredients",Matchers.notNullValue())
                .body("[0].usedIngredients[0].id",Matchers.notNullValue())
                .when()
                .get("/recipes/findByIngredients")
                .prettyPeek();


    }
}
