package redfootbear.crosswords.integration;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.driver.Driver;
import redfootbear.crosswords.api.word.NewWord;
import redfootbear.crosswords.api.word.NewWordResolver;
import redfootbear.crosswords.api.word.NewWordResolver.FakeNewWord;
import redfootbear.crosswords.domain.word.facade.WordFacade;

@QuarkusTest
@ExtendWith(NewWordResolver.class)
public class GetWordIT extends AbstractNeo4jEmbeddedIT {

    private static final String WORDS_URL = "words";

    private final WordFacade wordFacade;

    @Inject
    public GetWordIT(Driver driver, WordFacade wordFacade) {
        super(driver);
        this.wordFacade = wordFacade;
    }

    @Test
    void givenValidWord_WhenGETFromWordsAPI_Then200(@FakeNewWord NewWord newWord) {
        wordFacade.createNewWord(newWord);
        given()
                .contentType(ContentType.JSON)
                .basePath(WORDS_URL)
                .pathParam("word", newWord.getWord())
                .when()
                .get("{word}")
                .then()
                .statusCode(200);
    }

}
