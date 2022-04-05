package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.spoonaccular.model.AutocompleteRecipeSearchAccountResponse;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static ru.geekbrains.Endpoints.PECIPES_AUTOCOMPLETE;


public class AutocompleteRecipeSearchTest extends BaseTest {


   @Test
   void complexSearchTest() {

      AutocompleteRecipeSearchAccountResponse[] actually = given()
              .queryParam("query", "fish")
              .queryParam("number", "2")
              .when()
              .get(PECIPES_AUTOCOMPLETE)
              .prettyPeek()
              .body()
              .as(AutocompleteRecipeSearchAccountResponse[].class);
      Assertions.assertNotNull(actually[0].getId());
      actually[0].getTitle().toLowerCase(Locale.ROOT).contains("fish");
   }
   }


