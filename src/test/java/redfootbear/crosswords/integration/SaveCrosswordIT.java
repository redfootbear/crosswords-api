package redfootbear.crosswords.integration;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.driver.Driver;
import redfootbear.crosswords.api.crossword.NewCrossword;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver.FakeNewCrossword;
import redfootbear.crosswords.domain.crossword.model.Crossword;

@QuarkusTest
@ExtendWith(NewCrosswordResolver.class)
class SaveCrosswordIT extends AbstractNeo4jEmbeddedIT {

    private static final String WORDS_URL = "crosswords";

    @Inject
    public SaveCrosswordIT(Driver driver) {
        super(driver);
    }

    @Test
    void givenValidNewCrossword_WhenPOSTToWordsAPI_ThenReceive201(@FakeNewCrossword NewCrossword newCrossword) {
        Crossword crossword = given()
                .contentType(ContentType.JSON)
                .basePath(WORDS_URL)
                .body(newCrossword)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(Crossword.class);

        Assertions.assertNotNull(crossword);
    }

}
