package ru.geekbrains;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class AutocompleteRecipeSearchTest extends BaseTest {


   @Test
   void complexSearchTest() {
      given()
              .queryParam("apiKey", apikey)
              .queryParam("query", "fish")
              .queryParam("number", "2")
              .log()
              .uri()
              .expect()
              .statusCode(200)
              .time(lessThan(1000L))
              .body("[0].id", Matchers.notNullValue())
              .body(containsStringIgnoringCase("fish"))
              .body("[0].title", containsStringIgnoringCase("fish"))
              .body("[1].title", containsStringIgnoringCase("fish"))
              .body("[0].imageType", Matchers.notNullValue())
              .when()
              .get("/recipes/autocomplete")
              .prettyPeek();


   }

}
