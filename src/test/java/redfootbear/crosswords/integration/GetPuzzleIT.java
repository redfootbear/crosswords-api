package redfootbear.crosswords.integration;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;

@QuarkusTest
class GetPuzzleIT extends AbstractNeo4jEmbeddedIT {

    private static final String PUZZLES_URL = "puzzles";

    @Inject
    public GetPuzzleIT(Driver driver) {
        super(driver);
    }

    @Test
    void givenValidCrossword_WhenGETFromPuzzlesAPI_Then200() {
        given()
                .contentType(ContentType.JSON)
                .basePath(PUZZLES_URL)
                .when()
                .get()
                .then()
                .statusCode(200);
    }

}
