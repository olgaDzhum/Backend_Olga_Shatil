package ru.geekbrains;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.lessThan;

public class ConvertAmountsCopyTest extends BaseTest {

    @Test
    void ConvertAmountsCopyTest(){
        given()
                .queryParam("apiKey", apikey)
                .queryParam("ingredientName", "flour")
                .queryParam("sourceAmount", 3)
                .queryParam("sourceUnit", "cups")
                .queryParam("targetUnit", "grams")
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("sourceAmount",Matchers.is(3.0F))
                .body("targetAmount",Matchers.is(375.0F))
                .body("answer",containsStringIgnoringCase("3 cups flour"))
                .body("answer",containsStringIgnoringCase("375 grams"))
                .body("type",Matchers.is("CONVERSION"))
                .when()
                .get("/recipes/convert")
                .prettyPeek();


    }

}
