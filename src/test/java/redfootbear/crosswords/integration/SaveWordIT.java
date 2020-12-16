package redfootbear.crosswords.integration;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.driver.Driver;
import redfootbear.crosswords.api.word.NewWord;
import redfootbear.crosswords.api.word.NewWordResolver;
import redfootbear.crosswords.api.word.NewWordResolver.FakeNewWord;
import redfootbear.crosswords.infrastructure.neo4j.AbstractNeo4jRepository;
import redfootbear.neo4j.Neo4jEmbedded;

@Neo4jEmbedded
@ExtendWith(NewWordResolver.class)
public class SaveWordIT extends AbstractNeo4jRepository {

    private static final String WORDS_URL = "words";

    @Inject
    public SaveWordIT(Driver driver) {
        super(driver);
    }

    @Test
    void test(@FakeNewWord NewWord newWord) {
        given()
                .contentType(ContentType.JSON)
                .basePath(WORDS_URL)
                .body(newWord)
                .when()
                .post()
                .then()
                .statusCode(201);
    }

}
