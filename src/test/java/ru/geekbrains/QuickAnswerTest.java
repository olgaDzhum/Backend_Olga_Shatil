package ru.geekbrains;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class QuickAnswerTest extends BaseTest {

  //  private String answerVar1 = "There are 16.74 mg of Vitamin C in 2 how much is in apples. This covers about 20% of your daily needs of Vitamin C.";
  //  private String answerVar2 = "In 2 how much is in apples, there are 16.74 mg of Vitamin C. This amount covers 20% of your daily needs of Vitamin C.";


    @Test
    void QuickAnswerTest(){
        given()
                .queryParam("apiKey", apikey)
                .queryParam("q", "How much vitamin c is in 2 apples?")
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("answer", Matchers.notNullValue())
                .body("answer",containsStringIgnoringCase("there are 16.74 mg of Vitamin C"))
                .body("image", Matchers.notNullValue())
                .body("type",Matchers.is("NUTRITION"))
                .when()
                .get("/recipes/quickAnswer")
                .prettyPeek();


    }
}
