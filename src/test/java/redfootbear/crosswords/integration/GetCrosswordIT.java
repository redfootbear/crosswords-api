package redfootbear.crosswords.integration;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.driver.Driver;
import redfootbear.crosswords.api.crossword.NewCrossword;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver.FakeNewCrossword;
import redfootbear.crosswords.domain.crossword.facade.CrosswordFacade;
import redfootbear.crosswords.domain.crossword.model.Crossword;

@QuarkusTest
@ExtendWith(NewCrosswordResolver.class)
class GetCrosswordIT extends AbstractNeo4jEmbeddedIT {

    private static final String WORDS_URL = "crosswords";

    private final CrosswordFacade crosswordFacade;

    @Inject
    public GetCrosswordIT(Driver driver, CrosswordFacade crosswordFacade) {
        super(driver);
        this.crosswordFacade = crosswordFacade;
    }

    @Test
    void givenValidCrossword_WhenGETFromWordsAPI_Then200(@FakeNewCrossword NewCrossword newCrossword) {
        Crossword crossword = crosswordFacade.createNewCrossword(newCrossword);
        given()
                .contentType(ContentType.JSON)
                .basePath(WORDS_URL)
                .pathParam("word", newCrossword.getWord())
                .when()
                .get("{word}")
                .then()
                .statusCode(200);
    }

}
